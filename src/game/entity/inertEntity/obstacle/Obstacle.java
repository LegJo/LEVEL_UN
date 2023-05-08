package game.entity.inertEntity.obstacle;

import game.entity.Entity;
import game.entity.inertEntity.InertEntity;
import game.map.Coord;
import game.map.Direction;
import game.map.Zone;
import game.texture.Texture;

public abstract class Obstacle extends InertEntity {
	
	public Obstacle(Coord coord, Texture texture) {
		super(coord, texture);
	}
	
	@Override
	public void resolvCollision(Entity entity, Direction direction, Zone zone) {
		return;  
	}

}
