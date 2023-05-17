package game.entity.inertEntity.obstacle;

import java.util.ArrayList;
import java.util.Arrays;

import game.GameConstants;
import game.entity.Entity;
import game.entity.inertEntity.item.*;
import game.map.Coord;
import game.texture.Texture;

public class Rock extends Obstacle implements Destroyable {
	private final int type;
	private final String color;
	private boolean destroyed;
	private final ArrayList<Class<? extends Item>> destroyToolList;
	
	public Rock(Coord coord, String color, int type) {
		super(
			coord,
			new Texture(GameConstants.DEFAULT_IMGPATH)
		);
		switch (color.toLowerCase()) {
			case "gray": 
				switch (type) {
					case 1: this.currentTexture = new Texture(GameConstants.GRAYROCK1_IMGPATH); break;
					case 2: this.currentTexture = new Texture(GameConstants.GRAYROCK2_IMGPATH); break;
					case 3: this.currentTexture = new Texture(GameConstants.GRAYROCK3_IMGPATH); break;
					default:
						throw new IllegalArgumentException("Unexpected value: " + type);
				}
			break;
			case "brown": 
				switch (type) {
					case 1: this.currentTexture = new Texture(GameConstants.BROWNROCK1_IMGPATH); break;
					case 2: this.currentTexture = new Texture(GameConstants.BROWNROCK2_IMGPATH); break;
					case 3: this.currentTexture = new Texture(GameConstants.BROWNROCK3_IMGPATH); break;
					default:
						throw new IllegalArgumentException("Unexpected value: " + type);
				}
			break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + color);
		}
		this.type = type;
		this.color = color.toUpperCase();
		this.destroyed = false;
		
		this.destroyToolList = 
		new ArrayList<>(Arrays.asList(Pickaxe.class));
	}

	@Override
	public boolean isDestroyed() {
		return destroyed;
	}
	
	@Override
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	@Override
	public void destroyOnScene() {
		disapearOnScene();
	}
	
	@Override
	public ArrayList<Class<? extends Item>> getDestroyToolList() {
		return destroyToolList;
	}

	@Override
	public String toString() {
		return color + " ROCK " + type;
	}
	
	@Override
	public Entity copy(Coord coord) {
		return new Rock(coord, this.color, this.type);
	}
}
