package game.entity.inertEntity.item;

import game.Coord;
import game.GameConstants;
import game.texture.Texture;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Inventory {
	private StackPane inventoryStackPane;
	private Texture inventoryTexture;
	private Item[] itemList;
	private int itemCount;
	private int selectedItemIndex;
	
	public Inventory() {
		this.itemList = new Item[GameConstants.INVENTORY_SIZE];
		this.itemCount = 0;
		this.inventoryTexture = new Texture(GameConstants.INVENTORY_IMGPATH);
		this.inventoryStackPane = new StackPane(this.inventoryTexture.getImgView());
		this.selectedItemIndex = 0;
	}
	
	public Item[] getItemList() {
		return itemList;
	}

	public Item getItem(int index) {
		return this.getItemList()[index];
	}
	
	public void setItem(Item item, int index) {
		this.getItemList()[index] = item;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	
	public void incItemCount() {
		if(this.getItemCount() < GameConstants.INVENTORY_SIZE)
			this.setItemCount(this.getItemCount() + 1);
	}
	
	public void decItemCount() {
		if(this.getItemCount() >= 0)
			this.setItemCount(this.getItemCount() - 1);
	}
	
	public void addItem(Item item) {
		if(this.getItemCount() < GameConstants.INVENTORY_SIZE) {
			this.getItemList()[this.getItemCount()] = item;
			this.incItemCount();
			item.setInInventory(true);
			this.addChildToStackPane(item.getTexture().getImgView());
		}
	}
	
	public int getItemIndex(Class<? extends Item> cls) {
		Item[] itemList = this.getItemList();
		for (int i = 0; i < itemList.length; i++) {
			if(cls.isInstance(itemList[i])) {
				return i;
			}
		}
		return -1;	
	}
	
	public void removeItem(Class<? extends Item> cls) {
		this.setItem(null, this.getItemIndex(cls));
		this.decItemCount();
	}

	public Texture getInventoryTexture() {
		return inventoryTexture;
	}

	public StackPane getInventoryStackPane() {
		return inventoryStackPane;
	}
	
	public void addChildToStackPane(ImageView itemImgView) {
		int itemIndex = this.getItemCount();
		StackPane inventoryPane = this.getInventoryStackPane();
		//int index = Math.min(1, inventoryPane.getChildren().size()); // pour s'assurer que l'index est >= 1
		inventoryPane.getChildren().add(1, itemImgView);
		itemImgView.setTranslateX(1);
		itemImgView.setTranslateY((GameConstants.TEXTURE_SIZE + 8) * (itemIndex - 5) - 1);
		itemImgView.toFront();
		this.setSelectedItemIndex(itemIndex-1);
	}

	public int getSelectedItemIndex() {
		return selectedItemIndex;
	}

	public void setSelectedItemIndex(int selectedItemIndex) {
		if(selectedItemIndex >= this.getItemCount())
			return;
		this.undisplayItemSelection(this.getSelectedItemIndex());
		this.selectedItemIndex = selectedItemIndex;
		//System.out.println("item selected:" + selectedItemIndex);
		this.displayItemSelection(selectedItemIndex);
	}
	
	public void displayItemSelection(int index) {
		this.getItem(index).getTexture().getImgView().setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);");
	}
	
	public void undisplayItemSelection(int index) {
		this.getItem(index).getTexture().getImgView().setStyle("-fx-effect: none;");
	}
}
