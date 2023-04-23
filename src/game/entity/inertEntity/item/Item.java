package game.entity.inertEntity.item;

import game.Coord;
import game.Direction;
import game.GameConstants;
import game.entity.Entity;
import game.entity.inertEntity.InertEntity;
import game.entity.livingEntity.LivingEntity;
import game.entity.livingEntity.Player;
import game.level.map.Zone;
import game.texture.Animation;
import game.texture.Texture;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Item extends InertEntity {
	private boolean inInventory;
	protected Texture[] actionTexture;
	
	public Item(Coord coord, Texture texture, int actionImgNo) {
		super(coord, texture);
		this.inInventory = false;
		this.actionTexture = new Texture[actionImgNo];
	}
	
	public Texture[] getAllActionTexture() {
		return actionTexture;
	}
	
	public Texture getActionTexture(int i) {
		return actionTexture[i];
	}

	public void action(LivingEntity livEntity, Direction direction, Zone zone) {
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
	
	public void resolvCollision(Entity entity, Direction direction, Zone zone) {
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
}
