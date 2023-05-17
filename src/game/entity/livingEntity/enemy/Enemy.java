package game.entity.livingEntity.enemy;

import game.Game;
import game.GameConstants;
import game.entity.Entity;
import game.entity.inertEntity.item.Shield;
import game.entity.livingEntity.LivingEntity;
import game.map.Box;
import game.map.Coord;
import game.map.Direction;
import game.map.Zone;
import game.entity.livingEntity.AutoMoveEntity;
import game.texture.Animation;
import game.texture.Texture;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public abstract class Enemy extends LivingEntity implements AutoMoveEntity{
	private final int hustle;
	private final int power;
	private boolean inAttack;
	protected Texture[] attackTexture;

	
	public Enemy(Coord coord, Texture texture, int health, int hustle, int power, int speed, int attackImgNo) {
		super(coord, texture, health, speed);
		this.hustle = hustle;
		this.power = power;
		this.attackTexture = new Texture[attackImgNo];
	}
	
	public void attack(Direction direction) {
		this.attackAnim(direction);
  		Zone zone = Game.getZone();
		Entity entity = zone.getBox(direction, this.getCoord()).getEntity();
		if(entity instanceof LivingEntity) {
			LivingEntity livEntity = (LivingEntity) entity;
			livEntity.takeDamage(getPower(), direction);
		}
	}
	
	public Texture[] getAllAttackTexture() {
		return attackTexture;
	}
	
	public Texture getAttackTexture(int i) {
		return getAllAttackTexture()[i];
	}
	
	public Direction wantToAttack() {
		if(!this.canMove() || this.isInAttack()) {
			return null;
		}
		for (int i = 0; i < Direction.values().length; i++) {
			if(this.willStayInZone(Direction.values()[i])) {
				Box targetBox = Game.getZone().getBox(Direction.values()[i], this.getCoord());
				if(!targetBox.isEmpty()) {
					Entity entity = targetBox.getEntity();
					if((entity instanceof LivingEntity || entity instanceof Shield) && !(entity instanceof Enemy)) {
						return Direction.values()[i];
					}
				}
			}
		}
		return null;
	}

	public void attackBehavior() {
		Direction attackDirection = wantToAttack();
		if(attackDirection != null) {
			attack(attackDirection);
		}
	}
	
	public int getHustle() {
		return this.hustle;
	}
	
	public int getPower() {
		return this.power;
	}
	
	@Override
	public void resolvCollision(Entity entity, Direction direction) {
		if( (entity instanceof LivingEntity) && !(entity instanceof Enemy) ) {
			LivingEntity livEntity = (LivingEntity) entity;
			livEntity.takeDamage(getPower(), direction.getOpposite());
		}
		return;
	}

	public boolean isInAttack() {
		return inAttack;
	}

	public void setInAttack(boolean inAttack) {
		this.inAttack = inAttack;
	}	
	
	public void attackAnim(Direction direction) {
		int movTextureInd;
		int noImgByAttack = this.getAllAttackTexture().length/Direction.values().length;
		int startActionInd = 0;
		switch(direction.name()) {
			case "DOWN": 
				movTextureInd = 0;
			break;
			case "UP": 
				movTextureInd = 3;
				startActionInd = noImgByAttack;
			break;
			case "LEFT":
				movTextureInd = 6;
				startActionInd = 2*noImgByAttack;
			break;
			case "RIGHT": 
				movTextureInd = 9;
				startActionInd = 3*noImgByAttack;
			break;
			default: throw new IllegalArgumentException("Unexpected value: " + direction.name());
		}
		ImageView imageView = this.getTexture().getImgView();
		imageView.setScaleX(GameConstants.TEXTURE_ACTION_ANIM_SCALE );
		imageView.setScaleY(GameConstants.TEXTURE_ACTION_ANIM_SCALE );
		Image[] images = {
    		this.getAttackTexture(startActionInd).getImage(),
    		this.getAttackTexture(startActionInd + 1).getImage(),
	    };
		
		 Transition attackAnimation = Animation.ImgAnimation(imageView, images, GameConstants.ACTION_DURATION);
		 	attackAnimation.setOnFinished(e -> {
		    	this.setInAttack(false);
		    	imageView.setImage(this.getMovementTexture(movTextureInd).getImage());
		    	imageView.setScaleX(1);
				imageView.setScaleY(1);
		    });
		    if(!this.isInAttack()) {
		    	attackAnimation.play();
		    	this.setInAttack(true);
		    }
	}
}
