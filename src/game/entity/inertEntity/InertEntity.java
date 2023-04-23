package game.entity.inertEntity;

import game.Coord;
import game.entity.Entity;
import game.texture.Texture;

public abstract class InertEntity extends Entity{
	public InertEntity(Coord coord, Texture texture) {
		super(coord, texture);
	}
}
