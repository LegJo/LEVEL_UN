package game.entity.inertEntity.obstacle;

import java.util.ArrayList;
import java.util.Arrays;

import game.GameConstants;
import game.entity.Entity;
import game.entity.inertEntity.item.*;
import game.map.Coord;
import game.texture.Texture;

public class Tree extends Obstacle implements Destroyable{
	private final int type;
	private boolean destroyed;
	private final ArrayList<Class<? extends Item>> destroyToolList;
	
	public Tree(Coord coord, int type) {
		super(
			coord,
			new Texture(GameConstants.TREE1_IMGPATH)
		);
		switch (type) {
			case 1: this.currentTexture = new Texture(GameConstants.TREE1_IMGPATH); break;
			case 2: this.currentTexture = new Texture(GameConstants.TREE2_IMGPATH); break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + type);
		}
		this.type = type;
		this.destroyed = false;
		
		this.destroyToolList = 
		new ArrayList<>(Arrays.asList(Axe.class));
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
		return "TREE " + type ;
	}
	
	@Override
	public Entity copy(Coord coord) {
		return new Tree(coord, this.type);
	}
}
