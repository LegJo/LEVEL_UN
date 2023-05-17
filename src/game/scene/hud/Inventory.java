package game.scene.hud;

import game.GameConstants;
import game.entity.inertEntity.item.Item;
import game.entity.livingEntity.*;
import game.map.Coord;
import game.texture.Texture;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
* Represents an inventory associated with a living entity.
* If it's associated to the Player it also manages its display
*/
public class Inventory {
	/*
	 * Owner of the inventory
	 */
	private LivingEntity owner;
	/**
	 * Background texture of the inventory
	 */
	private Texture inventoryTexture;
	/**
	 * StackPane of the inventory. Children : inventoryTexture ImageView & ImagesView of the Items in the itemList 
	 */
	private StackPane inventoryStackPane;
	/**
	 * List of items which are in the inventory
	 */
	private Item[] itemList;
	/*
	 * number of item currently in the inventory
	 */
	private int itemCount;
	/*
	 * index of the item selected in the itemList
	 */
	private int selectedItemIndex;
	/**
	 * The boolean that indicates whether the inventory is displayed/hided on Scene
	 */
	private boolean hide;
	/**
	 * The boolean that indicates whether the inventory is in animation
	 */
	private boolean inAnimation;
	
	
	/**
	 * Constructs an inventory for the specified owner.
	 * @param owner The living entity that owns the inventory.
	 */
	public Inventory(LivingEntity owner) {
		this.itemList = new Item[GameConstants.INVENTORY_SIZE];
		this.itemCount = 0;
		if(owner instanceof Player) {
			this.inventoryTexture = new Texture(GameConstants.INVENTORY_IMGPATH);
			this.inventoryStackPane = new StackPane(this.inventoryTexture.getImgView());
		} else {
			this.inventoryTexture = null;
			this.inventoryStackPane = null;
		}
		this.selectedItemIndex = 0;
		this.owner = owner;
		this.hide = false;
		this.inAnimation = false; 
	}

	/**
	 * Returns the array of items in the inventory.
	 * @return The array of items.
	 */
	public Item[] getItemList() {
		return itemList;
	}
	
	/**
	 * Returns the index of the first item of the specified class in the inventory.
	 * @param cls The class of the item.
	 * @return The index of the item, or -1 if not found.
	 */
	public int getItemIndex(Class<? extends Item> cls) {
		Item[] itemList = this.getItemList();
		for (int i = 0; i < itemList.length; i++) {
			if(cls.isInstance(itemList[i])) {
				return i;
			}
		}
		return -1;	
	}
	
	/**
	 * Returns the index of the specified item in the inventory.
	 * @param item The item to find.
	 * @return The index of the item, or -1 if not found.
	 */
	public int getItemIndex(Item item) {
		Item[] itemList = this.getItemList();
		for (int i = 0; i < itemList.length; i++) {
			if(itemList[i] == item) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Returns the item at the specified index in the inventory.
	 * @param index The index of the item.
	 * @return The item at the specified index.
	 */
	public Item getItem(int index) {
		return this.getItemList()[index];
	}
	
	/**
	 * Sets the item at the specified index in the inventory.
	 * @param item The item to set.
	 * @param index The index at which to set the item.
	 */
	public void setItem(Item item, int index) {
		this.getItemList()[index] = item;
	}

	/**
	 * Returns the owner of the inventory.
	 * @return The owner of the inventory.
	 */
	public LivingEntity getOwner() {
		return owner;
	}

	/**
	 * Sets the owner of the inventory.
	 * @param owner The owner of the inventory.
	 */
	public void setOwner(LivingEntity owner) {
		this.owner = owner;
	}
	
	/**
	 * Returns the inventory texture.
	 * @return The inventory texture.
	 */
	public Texture getInventoryTexture() {
		return inventoryTexture;
	}
	
	/**
	 * Returns the inventory stack pane.
	 * @return The inventory stack pane.
	 */
	public StackPane getInventoryStackPane() {
		return inventoryStackPane;
	}
	
	/**
	 * Returns the index of the currently selected item.
	 * @return The index of the selected item.
	 */
	public int getSelectedItemIndex() {
		return selectedItemIndex;
	}

	/**
	 * Sets the index of the currently selected item.
	 * @param selectedItemIndex The index of the selected item.
	 */
	public void setSelectedItemIndex(int selectedItemIndex) {
		if(selectedItemIndex+1 > this.getItemCount() || selectedItemIndex == -1)
			return;
		if(this.getItem(this.getSelectedItemIndex()) != null)
			this.undisplayItemSelection(this.getSelectedItemIndex());
		if(this.getItem(selectedItemIndex) != null) {
			this.selectedItemIndex = selectedItemIndex;
			this.displayItemSelection(selectedItemIndex);	
		}
	}
	
	/**
	 * Returns the number of items in the inventory.
	 * @return The item count.
	 */
	public int getItemCount() {
		return itemCount;
	}
	
	/**
	 * Sets the number of items in the inventory.
	 * @param itemCount The item count to set.
	 */
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	
	/**
	 * Increases the item count by one if the inventory is not full.
	 */
	public void incItemCount() {
		if(this.getItemCount() < GameConstants.INVENTORY_SIZE)
			this.setItemCount(this.getItemCount() + 1);
	}
	
	/**
	 * Decreases the item count by one if the inventory is not empty.
	 */
	public void decItemCount() {
		if(this.getItemCount() >= 0)
			this.setItemCount(this.getItemCount() - 1);
	}
	
	/**
	 * Returns the boolean corresponding to hide status of inventory stackpane on Scene
	 * @return the boolean hide.
	 */
	public boolean isHide() {
		return hide;
	}
	
	/**
	 * Sets the boolean hide
	 * @param hide The boolean to set to hide.
	 */
	public void setHide(boolean hide) {
		this.hide = hide;
	}
	
	/**
	 * Returns the boolean indicating if the StackPane is in animation 
	 * @return the boolean inAnimation.
	 */
	public boolean isInAnimation() {
		return inAnimation;
	}
	
	/**
	 * Sets the boolean inAnimation
	 * @param inAnimation The boolean to set.
	 */
	public void setInAnimation(boolean inAnimation) {
		this.inAnimation = inAnimation;
	}
	
	/**
	 * Adds an item to the inventory.
	 * @param item The item to add.
	 */
	public void addItem(Item item) {
		if(this.getItemCount() < GameConstants.INVENTORY_SIZE) {
			this.getItemList()[this.getItemCount()] = item;
			this.incItemCount();
			item.setInInventory(true);
			if(owner instanceof Player)
				this.addChildToStackPane(item.getTexture().getImgView());
			else {
				item.getTexture().hide();
			}
		} else {
			this.removeAndDropItem(this.getSelectedItemIndex());
			this.addItem(item);
		}
		
	}
	
	/**
	 * Removes the item at the specified index from the inventory.
	 * @param itemIndex The index of the item to remove.
	 */
	public void removeItem(int itemIndex) {
		if(itemIndex != -1) {
			this.decItemCount();
			if(owner instanceof Player) {
				this.removeChildFromStackPane(itemIndex);
			}
			if(this.getItemCount() != 0) {
				for (int i = itemIndex; i <= ((this.getItemCount() == this.itemList.length-1) ? this.getItemCount()-1 : this.getItemCount()); i++) {
					itemList[i] = itemList[i+1];
				}
				if(owner instanceof Player) {
					int currSelect = this.getSelectedItemIndex();
					this.setSelectedItemIndex((currSelect == 0)?currSelect:currSelect-1);
				}
			}
			if(owner instanceof Player) {
				this.refreshItemPosStackPane();
			}
		}
	}
	
	/**
	 * Removes the item of the specified class from the inventory.
	 * @param cls The class of the item to remove.
	 */
	public void removeItem(Class<? extends Item> cls) {
		removeItem(this.getItemIndex(cls));
	}
	
	/**
	 * Removes and drops on the zone the item at the specified index from the inventory.
	 * @param itemIndex The index of the item to remove and drop.
	 */
	public void removeAndDropItem(int itemIndex) {
		if(itemIndex != -1) {
			Item item = this.getItem(itemIndex);
			removeItem(itemIndex);
			Coord dropCoord = new Coord(owner.getCoord());
			item.dropTo(dropCoord);
			item.setInInventory(false);
		}
	}
	
	/**
	 * Adds an ImageView of an item to the inventory stack pane.
	 * @param itemImgView The ImageView of the item to add.
	 */
	public void addChildToStackPane(ImageView itemImgView) {
		int itemCount = this.getItemCount();
		itemImgView.setFitWidth(GameConstants.TEXTURE_SIZE);
      	itemImgView.setFitHeight(GameConstants.TEXTURE_SIZE);
		StackPane inventoryPane = this.getInventoryStackPane();
		inventoryPane.getChildren().add(1, itemImgView);
		itemImgView.setTranslateX(1);
		itemImgView.setTranslateY((GameConstants.TEXTURE_SIZE + 8) * (itemCount - 5) - 1);
		itemImgView.toFront();
		this.setSelectedItemIndex(itemCount-1);
	}
	
	/**
	 * Refreshes the positions of items in the inventory stack pane.
	 */
	public void refreshItemPosStackPane() {
		for (int i = 0; i < this.getItemCount(); i++) {
			ImageView itemImgView = itemList[i].getTexture().getImgView();
			itemImgView.setTranslateX(1);
			itemImgView.setTranslateY((GameConstants.TEXTURE_SIZE + 8) * ((i+1) - 5) - 1);
			itemImgView.toFront();
		}
	}
	
	/**
	 * Removes an item's ImageView from the inventory stack pane.
	 * @param itemIndex The index of the item to remove.
	 */
	public void removeChildFromStackPane(int itemIndex) {
		Item item = this.getItem(itemIndex);
		ImageView itemImgView = item.getTexture().getImgView();
		StackPane inventoryPane = this.getInventoryStackPane();
		inventoryPane.getChildren().removeAll(itemImgView);
		this.undisplayItemSelection(itemIndex);
	}	

	/**
	 * Displays a visual selection effect on the item at the specified index.
	 * @param index The index of the item to select.
	 */
	public void displayItemSelection(int index) {
		this.getItem(index).getTexture().getImgView().setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
	}
	
	/**
	 * Removes the visual selection effect from the item at the specified index.
	 * @param index The index of the item to deselect.
	 */
	public void undisplayItemSelection(int index) {
		this.getItem(index).getTexture().getImgView().setStyle("-fx-effect: none;");
	}
	
	/**
	 * Launch animation translation to hide the inventory stackPane
	 * @param seconds duration
	 */
	public void hide(double seconds) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(seconds), this.getInventoryStackPane());
        transition.setToX(this.getInventoryTexture().getImage().getWidth());
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.setOnFinished(e -> {
        	this.setHide(true);
        	this.setInAnimation(false);
        });
        transition.play();
        this.setInAnimation(true);
    }
    
	/**
	 * Call this.hide with 0.5 seconds param
	 */
    public void hide() {
    	hide(0.5);
    }
    
    /**
	 * Launch animation translation to show the inventory stackPane
	 * @param seconds duration
	 */
	public void show(double seconds) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(seconds), this.getInventoryStackPane());
        transition.setToX(0);
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.setOnFinished(e -> {
        	this.setHide(false);
        	this.setInAnimation(false);
        });
        transition.play();
        this.setInAnimation(true);
    }
	
	/**
	 * Call this.show with 0.5 seconds param
	 */
	public void show() {
		show(0.5);
	}
	
	/**
	 * Regarding Whether than the inventoryStackPane is hide or not, hide or show the inventoryStackPane
	 */
	public void toggle() {
		if(this.isHide()) {
        	this.show();
        } else {
        	this.hide();
        }
	}
    

	@Override
	/**
	 * @implNote Items beeween [] & selected item beetween [()]
	 */
	public String toString() {
		String invStr = "";
		for(int i = 0; i<this.getItemCount() ; i++) {
			if(i == this.getSelectedItemIndex()) {
				invStr += "[(" + this.getItem(i) + ")]";
			} else {
				invStr += "[" + this.getItem(i) + "]";
			}			
		}
		return invStr;
	}
}
