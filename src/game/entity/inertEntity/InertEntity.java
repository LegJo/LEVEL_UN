package game.entity.inertEntity;

import game.entity.Entity;
import game.map.Coord;
import game.texture.Texture;

public abstract class InertEntity extends Entity{
	public InertEntity(Coord coord, Texture texture) {
		super(coord, texture);
	}
	
	@Override
	public String toString() {
		return "INERT ENTITY" ;
	}
}
