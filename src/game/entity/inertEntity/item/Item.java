package game.entity.inertEntity.item;

import game.Game;
import game.GameConstants;
import game.entity.Entity;
import game.entity.inertEntity.InertEntity;
import game.entity.inertEntity.obstacle.Destroyable;
import game.entity.livingEntity.LivingEntity;
import game.entity.livingEntity.Player;
import game.map.Coord;
import game.map.Direction;
import game.map.Zone;
import game.scene.SceneUtils;
import game.texture.Animation;
import game.texture.Texture;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Item extends InertEntity {
	protected boolean isTool;
	private boolean inInventory;
	protected Texture[] actionTexture;
	
	public Item(Coord coord, Texture texture, int actionImgNo) {
		super(coord, texture);
		this.inInventory = false;
		this.actionTexture = new Texture[actionImgNo];
		this.isTool = false;
	}
	
	public Texture[] getAllActionTexture() {
		return actionTexture;
	}
	
	public Texture getActionTexture(int i) {
		return actionTexture[i];
	}
	
	/**
	 * {@summary} In superclass Item : launch the action animation and check Destruction interaction
	 * 			  In subclasses : specify the action make by the item 
	 * 			  (effect on zone &| on the livEntity)
	 * @param livEntity : Living Entity whose using the item
	 * @param direction of the action
	 */
	public void action(LivingEntity livEntity, Direction direction) {
    	Zone zone = Game.getZone();
		//launch action animation
		this.actionAnimation(livEntity, direction, zone);
	    //check Destruction interaction
	    if(this.isTool()) {
	    	Entity usedOn = zone.getBox(direction, livEntity.getCoord()).getEntity();
	    	if(usedOn instanceof Destroyable) {
	    		Destroyable destroyable = (Destroyable) usedOn;
	    		destroyable.tryDestruction(this);
	    	}
	    }
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		if(obj instanceof Item) {
			Item comparedItem = (Item) obj;
			return comparedItem.toString().equals(this.toString());
		}
		return false;
	}
	
	@Override
	public void resolvCollision(Entity entity, Direction direction) {
		if(entity instanceof Player) {
			Player player = (Player) entity;
			player.getInventory().addItem(this);
			if(this.isInInventory()) {
				player.setInMovement(true);
				player.getCoord().addDirection(direction);
			}
		}
	}

	public boolean isInInventory() {
		return inInventory;
	}

	public void setInInventory(boolean inInventory) {
		this.inInventory = inInventory;
	}
	
	public void dropTo(Coord dropCoord) {
		dropCoord = Game.getZone().getNearestEmptyBox(dropCoord);
		ImageView imgView = this.getTexture().getImgView();
		SceneUtils.gridPane_Entity.add(imgView, 0, 0);
		imgView.setTranslateX(dropCoord.getX()*GameConstants.TEXTURE_SIZE);
		imgView.setTranslateY(dropCoord.getY()*GameConstants.TEXTURE_SIZE);
		this.setCoord(dropCoord);
		imgView.toBack();
	}

	public boolean isTool() {
		return isTool;
	}
	
	public void actionAnimation(LivingEntity livEntity, Direction direction, Zone zone) {
		int movTextureInd;
		int startActionInd = 0;
		int noImgByAction = this.getAllActionTexture().length/Direction.values().length;
				switch(direction.name()) {
			case "DOWN": 
				movTextureInd = 0;
			break;
			case "UP": 
				movTextureInd = 3;
				startActionInd = noImgByAction;
			break;
			case "LEFT":
				movTextureInd = 6;
				startActionInd = 2*noImgByAction;
			break;
			case "RIGHT": 
				movTextureInd = 9;
				startActionInd = 3*noImgByAction;
			break;
			default: throw new IllegalArgumentException("Unexpected value: " + direction.name());
		}
		
		//action animation
		ImageView imageView = livEntity.getTexture().getImgView();
		imageView.setScaleX(GameConstants.TEXTURE_ACTION_ANIM_SCALE );
		imageView.setScaleY(GameConstants.TEXTURE_ACTION_ANIM_SCALE );
	    Image[] images = {
    		this.getActionTexture(startActionInd).getImage(),
    		this.getActionTexture(startActionInd + 1).getImage(),
	    };
	    
	    Transition actionAnimation = Animation.ImgAnimation(imageView, images, GameConstants.ACTION_DURATION);
	    actionAnimation.setOnFinished(e -> {
	    	livEntity.setInAction(false);
	    	imageView.setImage(livEntity.getMovementTexture(movTextureInd).getImage());
	    	imageView.setScaleX(1);
			imageView.setScaleY(1);
	    });
	    if(!livEntity.isInAction()) {
	    	actionAnimation.play();
	    	livEntity.setInAction(true);
	    }
	}
}
