package game.entity.livingEntity;

import java.util.Random;

import game.Direction;
import game.GameConstants;
import game.level.map.Zone;

public interface AutoMoveEntity {
	boolean isInMovement();
	boolean isInAnimation();
	void move(Direction direction, Zone zone);
	int getHustle();
	
  	default void randomMovement(Zone zone) {
  		if(this.isInMovement() || this.isInAnimation()) {
			return;
		}
        Random random = new Random();
        if (random.nextInt(200)*GameConstants.FPS < this.getHustle()) {       
	        Direction[] directions = Direction.values();
	        Direction randomDirection = directions[random.nextInt(directions.length)];
	        this.move(randomDirection, zone);
        }
  	};
}
