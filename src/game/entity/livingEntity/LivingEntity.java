package game.entity.livingEntity;

import javafx.animation.*;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import game.Coord;
import game.Direction;
import game.GameConstants;
import game.entity.Entity;
import game.entity.livingEntity.enemy.Enemy;
import game.level.map.Zone;
import game.texture.Animation;
import game.texture.Texture;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.util.Duration;

public abstract class LivingEntity extends Entity{

	
	
	//Down:0,1,2 | Up: 3,4,5 | Left: 6,7,8 | Right:9,10,11
	protected Texture[] movementTexture;
	protected Coord sceneCoord;
	private boolean inMovement;
	private boolean inAnimation;
	private boolean inAction;
	private boolean invincibility;
	private Direction direction;
	private int health;	
	private int speed;
	
	public LivingEntity(Coord coord, Texture texture, int health, int speed) {
		super(coord, texture);
		this.health = health;
		this.direction = Direction.DOWN;
		this.sceneCoord = new Coord(0,0);
		this.inMovement = false;
		this.inAnimation = false;
		this.setInAction(false);
		this.movementTexture = new Texture[12];
		this.setSpeed(speed);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setCoord(Coord coord) {
		this.coord = coord;
	}	
	
	public void move(Direction direction, Zone zone) {
		if(!direction.equals(this.getDirection()) && this.isInAnimation()) {
			this.setDirection(direction);
			return;
		}
		if(this.willStayInZone(zone, direction)) {
			if(this.willCollide(direction, zone)) {
				zone.getBox(direction, this.getCoord()).getEntity().resolvCollision(this, direction);
			} else {
				this.setInMovement(true);
				this.getCoord().addDirection(direction);
			}
		}
		System.out.println(this + ": " + this.getCoord());
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
	
	 public void moveTo(Direction direction) {
		int startIndex;
		int textureSize = GameConstants.TEXTURE_SIZE;
		int animDuration = GameConstants.MOVEMENT_DURATION;
		Coord sceneCoord = this.getSceneCoord();
		
		switch(this.getDirection().name()) {
			case "DOWN": 
				startIndex = 0;
				if(this.isInMovement())
					sceneCoord.addY(textureSize);
			break;
			case "UP": 
				startIndex = 3;
				if(this.isInMovement())
					sceneCoord.addY(-textureSize);
				break;
			case "LEFT":
				startIndex = 6;
				if(this.isInMovement())
					sceneCoord.addX(-textureSize);
				break;
			case "RIGHT": 
				startIndex = 9;
				if(this.isInMovement())
					sceneCoord.addX(textureSize);
			break;
			default: throw new IllegalArgumentException("Unexpected value: " + direction.name());
		}
		
		ImageView imageView = this.getTexture().getImgView();
	    Image[] images = {
    		this.getMovementTexture(startIndex + 1).getImage(),
    		this.getMovementTexture(startIndex + 2).getImage(),
	    };
	    
	    TranslateTransition translateTransition = Animation.movementTranslation(imageView, sceneCoord, animDuration);
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
	   
	    System.out.println(this + ": " + sceneCoord + " (scene)");
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
	
	public void takeDamage(int damage) {
		if(this.isInvincible()) {
			return;
		}
		int invincibilityDuration = (this instanceof Enemy)?(int)(GameConstants.ONHIT_INVINCIBILITY_DURATION/2):GameConstants.ONHIT_INVINCIBILITY_DURATION;
		this.setHealth(this.getHealth() - damage);
		System.out.println(this + " health: " + this.getHealth() + " (-" + damage + ")");
		//ajouter de vérification de MORT
		ImageView imageView = this.getTexture().getImgView();
		Animation.damageTakenAnim(imageView).play();
		Animation.invincibilityAnimation(imageView, invincibilityDuration);
		this.setInvincibility(true);
		Timer invincibilityTimer = new Timer();
		invincibilityTimer.schedule(new TimerTask() {
		    public void run() {
		        setInvincibility(false);
		    }
		}, invincibilityDuration);

	}
	
	public boolean willCollide(Direction direction, Zone zone) {
		return (!zone.getBox(direction, this.getCoord()).isEmpty());
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
	
	
}
