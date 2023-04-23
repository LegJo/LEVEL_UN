package game.entity.livingEntity;

import javafx.animation.*;

import java.util.Timer;
import java.util.TimerTask;
import game.Coord;
import game.Direction;
import game.GameConstants;
import game.entity.Entity;
import game.entity.livingEntity.enemy.Enemy;
import game.level.map.Zone;
import game.texture.Animation;
import game.texture.Texture;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public abstract class LivingEntity extends Entity{
	//Down:0,1,2 | Up: 3,4,5 | Left: 6,7,8 | Right:9,10,11
	protected Texture[] movementTexture;
	protected Coord sceneCoord;
	private boolean inMovement;
	private boolean inAnimation;
	private boolean inAction;
	private boolean inRun;
	private boolean inRecoil;
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
		this.inRun = false;
		this.inAction = false;
		this.inRecoil = false;
		this.movementTexture = new Texture[12];
		this.speed = speed;
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
				zone.getBox(direction, this.getCoord()).getEntity().resolvCollision(this, direction, zone);
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
		int animDuration = (this.isInRun())? GameConstants.MOVEMENT_DURATION - this.getSpeed() : GameConstants.MOVEMENT_DURATION;
		Coord sceneCoord = this.getSceneCoord();
		if(this.isInMovement()) {
			sceneCoord.addDirectionToScene(direction);
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
	
	public void takeDamage(int damage, Direction direction, Zone zone) {
		this.setHealth(this.getHealth() - damage);
		System.out.println(this + " health: " + this.getHealth() + " (-" + damage + ")");
		
		//ajouter de vérification de MORT
		if(this.willStayInZone(zone, direction))
			this.recoilMovement(direction, zone);
		this.enableInvincibility();
	}
	
	public void recoilMovement(Direction direction, Zone zone) {
		ImageView imageView = this.getTexture().getImgView();
		Coord recoilCoord = new Coord(this.getSceneCoord());
		recoilCoord.addDirectionToScene(direction);
		TranslateTransition recoilTranslation = Animation.translateTranslation(imageView, recoilCoord, GameConstants.MOVEMENT_DURATION);
		recoilTranslation.setOnFinished(e -> {
			this.setInRecoil(false);
			this.getSceneCoord().addDirectionToScene(direction);
		});
		if(!this.isInRecoil() && zone.getBox(direction, this.getCoord()).isEmpty()) { //voir si il ne faut pas remplacer cette  empechage quand la Box target n'est pas empty avec un resolvCollision si c'est le cas z
			this.setInRecoil(true);
			recoilTranslation.play();
			this.getCoord().addDirection(direction);
		}
		zone.addEntityModified(this);
	}
	
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

	public boolean isInRun() {
		return inRun;
	}

	public void setInRun(boolean inRun) {
		this.inRun = inRun;
	}
	
	public boolean canMove() {
		return (!this.isInMovement() && !this.isInRecoil() && !this.isInAction());
	}

	public boolean isInRecoil() {
		return inRecoil;
	}

	public void setInRecoil(boolean inRecoil) {
		this.inRecoil = inRecoil;
	}

	
}
