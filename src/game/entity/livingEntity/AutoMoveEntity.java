package game.entity.livingEntity;

import java.util.ArrayList;
import java.util.Random;

import game.Game;
import game.GameConstants;
import game.entity.Entity;
import game.map.Direction;
import game.map.Zone;
import game.map.Coord;

public interface AutoMoveEntity {
	Coord getCoord();
	boolean isInMovement();
	boolean isInAnimation();
	boolean canMove();
	void move(Direction direction);
	int getHustle();
	
  	default void randomMovement(Direction direction) { 
  		if(!this.canMove() || this.isInAnimation()) {
			return;
		}
  		Zone zone = Game.getZone();
  		Random random = new Random();
        if (random.nextInt(200)*GameConstants.FPS < this.getHustle()) {    
        	if(this.canWalkIn(direction)) { 
        		if(!(zone.getBox(direction, getCoord()).getEntity() instanceof LivingEntity)) //this line resolve some collisions bugs : but probably removable later
        			this.move(direction);
        	}
        }
  	};
  	
  	boolean canWalkIn(Direction direction);
	default void destinationMovement(Entity target) {
  		ArrayList<Entity> entityList = Game.getZone().getEntityList();
  		Coord targetCoord = entityList.get(entityList.indexOf(target)).getCoord();
  		int diffX = this.getCoord().getX() - targetCoord.getX();
  		int diffY = this.getCoord().getY() - targetCoord.getY();
  		if(diffX < 0) { //target is on the RIGHT of this
  			if(diffY < 0) { //target is under of this
  				this.randomMovement(Direction.DOWN);
  	  		} else if(diffY > 0) { //target is on the above of this
  	  			this.randomMovement(Direction.UP);
  	  		}
  			this.randomMovement(Direction.RIGHT);
  		} else if(diffX > 0){ //target is on the LEFT of this
  			if(diffY < 0) { //target is under of this
  				this.randomMovement(Direction.DOWN);
  	  		} else if(diffY > 0) { //target is on the above of this
  	  			this.randomMovement(Direction.UP);
  	  		}
			this.randomMovement(Direction.LEFT);
  		} else {
  			if(diffY < 0) { //target is under of this
  				this.randomMovement(Direction.DOWN);
  	  		} else if(diffY > 0) { //target is on the above of this
  	  			this.randomMovement(Direction.UP);
  	  		}
  		}
  	}
}
