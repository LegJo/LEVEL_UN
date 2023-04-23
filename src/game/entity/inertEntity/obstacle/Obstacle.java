package game.entity.inertEntity.obstacle;

import game.Coord;
import game.Direction;
import game.entity.Entity;
import game.entity.inertEntity.InertEntity;
import game.level.map.Zone;
import game.texture.Texture;

public abstract class Obstacle extends InertEntity {
	
	public Obstacle(Coord coord, Texture texture) {
		super(coord, texture);
	}
	
	@Override
	public void resolvCollision(Entity entity, Direction direction) {
		return;  
	}

}
