package game.entity.livingEntity;

import javafx.animation.*;


import java.util.Timer;
import java.util.TimerTask;

import game.Game;
import game.GameConstants;
import game.entity.Entity;
import game.entity.livingEntity.enemy.Enemy;
import game.map.Coord;
import game.map.Direction;
import game.map.Zone;
import game.texture.Animation;
import game.texture.Texture;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public abstract class LivingEntity extends Entity{
	//Down:0,1,2 | Up: 3,4,5 | Left: 6,7,8 | Right:9,10,11 see GameConstants.java
	protected Texture[] movementTexture;
	protected Coord sceneCoord;
	private boolean inMovement;
	private boolean inAnimation;
	private boolean inAction;
	private boolean inRun;
	private boolean inRecoil;
	private boolean invincibility;
	private boolean isDead;
	private Direction direction;
	private int health;	
	private final int maxHealth;
	private int speed;
	
	public LivingEntity(Coord coord, Texture texture, int health, int speed) {
		super(coord, texture);
		this.health = health;
		this.maxHealth = (health % 10 == 0) ? health : (((int) Math.floor(health/10)) + 1)*10; 
		this.direction = Direction.DOWN;
		this.sceneCoord = new Coord(0,0);
		this.inMovement = false;
		this.inAnimation = false;
		this.inRun = false;
		this.inAction = false;
		this.inRecoil = false;
		this.isDead = false;
		this.movementTexture = new Texture[12];
		this.speed = speed;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if(health > maxHealth) {
			this.health = maxHealth;
		} else {
			this.health = health;
		}
	}
	
	public void setCoord(Coord coord) {
		this.coord = coord;
	}	
	
	/**
	 * {@summary} prepare the L.Entity to move on the Scene & update the L.Entity accordingly
	 * @param direction of the movement
	 */
	public void move(Direction direction) {
		if(!direction.equals(this.getDirection()) && this.isInAnimation() && !this.isInRecoil()) {
			this.setDirection(direction);
			return;
		}
    	Zone zone = Game.getZone();
		if(this.canWalkIn(direction)) {
			if(this.willCollide(direction)) {
				zone.getBox(direction, this.getCoord()).getEntity().resolvCollision(this, direction);
			} else {
				this.setInMovement(true);
				this.getCoord().addDirection(direction);
			}
		}
		//System.out.println(this + ": " + this.getCoord());
		this.setDirection(direction);
		zone.addEntityModified(this);
	}

	public boolean isInMovement() {
		return inMovement;
	}

	public void setInMovement(boolean inMovement) {
		this.inMovement = inMovement;
	}

	public Texture getMovementTexture(int i) {
		return movementTexture[i];
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * {@summary} launch animation to move the ImageView on the scene after a movement (called in gameloop)
	 */
	 public void moveOnScene() {
		Direction direction = this.getDirection();
		int startIndex;
		int animDuration = (this.isInRun())? GameConstants.MOVEMENT_DURATION - this.getSpeed() : GameConstants.MOVEMENT_DURATION;
		Coord sceneCoord = this.getSceneCoord();
		if(this.isInMovement()) {
			sceneCoord.addDirectionScene(direction);
		}
		switch(this.getDirection().name()) {
			case "DOWN": startIndex = 0; break;
			case "UP": startIndex = 3; break;
			case "LEFT": startIndex = 6; break;
			case "RIGHT": startIndex = 9; break;
			default: throw new IllegalArgumentException("Unexpected value: " + direction.name());
		}
		
		ImageView imageView = this.getTexture().getImgView();
	    Image[] images = {
    		this.getMovementTexture(startIndex + 1).getImage(),
    		this.getMovementTexture(startIndex + 2).getImage(),
	    };
	    
	    TranslateTransition translateTransition = Animation.translateTranslation(imageView, sceneCoord, animDuration);
	    translateTransition.setOnFinished(event -> {
	        this.setInMovement(false);	        
	    });
	    
	    Transition imageTransition = Animation.ImgAnimation(imageView, images, animDuration);
	    imageTransition.setOnFinished(event -> {
	    	imageView.setImage(this.getMovementTexture(startIndex).getImage());
	    	this.setInAnimation(false);
	    });
	    
	    if(this.isInMovement())
	    	translateTransition.play();
	    if(!this.isInAnimation()) {
	    	imageTransition.play();
	    	this.setInAnimation(true);
	    }
	   
	    //System.out.println(this + ": " + sceneCoord + " (scene)");
	}

	private Coord getSceneCoord() {
		return this.sceneCoord;
	}

	public boolean isInAnimation() {
		return inAnimation;
	}

	public void setInAnimation(boolean inAnimation) {
		this.inAnimation = inAnimation;
	}
	
	/**
	 * {@summary} update the health & call the recoil after damage has been taken
	 * @param damage dealed to the living entity
	 * @param direction of the recoil
	 */
	public void takeDamage(int damage, Direction direction) {
		if(this.isInvincible()) {
			return;
		}
    	Zone zone = Game.getZone();
		this.setHealth(this.getHealth() - damage);
		if(this.getHealth() <= 0) {
			this.setDead(true);
			zone.addEntityModified(this);
		}
		this.recoil(direction);
		this.enableInvincibility();
	}
	
	/**
	 * {@summary} prepare the L.Entity to be in recoil on the Scene & update the L.Entity accordingly (launch by takeDamage)
	 * @param direction of the recoil
	 */
	public void recoil(Direction direction) {
		if(!this.canWalkIn(direction) || !this.canMove() || (this.isInMovement() || this.isInAnimation()))
			return;
    	Zone zone = Game.getZone();
		if(zone.getBox(direction, this.getCoord()).isEmpty()) { 
			this.getCoord().addDirection(direction);
			this.setInRecoil(true);
			this.setDirection(direction.getOpposite());
			zone.addEntityModified(this);

		}
	}
	
	/**
	 * {@summary} launch recoil animation on the ImageView on the scene after a movement (called in gameloop)
	 */
	public void recoilOnScene() {
		Direction recoilDirection = this.getDirection().getOpposite();
		ImageView imageView = this.getTexture().getImgView();
		Coord recoilCoord = new Coord(this.getSceneCoord());
		recoilCoord.addDirectionScene(recoilDirection);
		TranslateTransition recoilTranslation = Animation.translateTranslation(imageView, recoilCoord, GameConstants.MOVEMENT_DURATION);
		recoilTranslation.setOnFinished(e -> {
			this.getSceneCoord().addDirectionScene(recoilDirection);
			this.setInAnimation(false);
		});
		recoilTranslation.play();
		this.setInAnimation(true);
		this.setInRecoil(false);
	}
	
	/**
	 * {@summary} enable Invincibility and launch invincibility animation
	 */
	public void enableInvincibility() {
		int invincibilityDuration = (this instanceof Enemy)?(int)(GameConstants.ONHIT_INVINCIBILITY_DURATION/2):GameConstants.ONHIT_INVINCIBILITY_DURATION;
		ImageView imageView = this.getTexture().getImgView();
		Animation.invincibilityAnimation(imageView, invincibilityDuration);
		this.setInvincibility(true);
		Timer invincibilityTimer = new Timer();
		invincibilityTimer.schedule(new TimerTask() {
		    public void run() {
		        setInvincibility(false);
		        imageView.setOpacity(1);
		    }
		}, invincibilityDuration);
	}
	
	/**
	 * {@summary} check if the L.Entity will collide with another Entity in if ove in direction
	 */
	public boolean willCollide(Direction direction) {
		return (!Game.getZone().getBox(direction, this.getCoord()).isEmpty());
	}

	public boolean isInvincible() {
		return invincibility;
	}

	public void setInvincibility(boolean invincibility) {
		this.invincibility = invincibility;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isInAction() {
		return inAction;
	}

	public void setInAction(boolean inAction) {
		this.inAction = inAction;
	}

	public boolean isInRun() {
		return inRun;
	}

	public void setInRun(boolean inRun) {
		this.inRun = inRun;
	}
	
	/**
	 * {@summary} check if the L.Entity is enable to move 
	 */
	public boolean canMove() {
		return 
		(
			(!this.isInMovement() && !this.isInRecoil() && !this.isInAction()) && 
			!(this.isInAnimation() && this.isInMovement())
		);
	}

	public boolean isInRecoil() {
		return inRecoil;
	}

	public void setInRecoil(boolean inRecoil) {
		this.inRecoil = inRecoil;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	/**
	 * {@summary} launch animation & update on Scene when an entity is dead (called by gameloop)
	 */
	public void deadOnScene() {
       disapearOnScene();;
    }
	
	@Override
	public String toString() {
		return "LIVING ENTITY" ;
	}

	public int getMaxHealth() {
		return maxHealth;
	}
	
}
