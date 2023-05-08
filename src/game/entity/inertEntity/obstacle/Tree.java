package game.entity.inertEntity.obstacle;

import game.GameConstants;
import game.map.Coord;
import game.texture.Texture;

public class Tree extends Obstacle {
	private final int type;
	
	public Tree(Coord coord, int type) {
		super(
			coord,
			new Texture(GameConstants.TREE1_IMGPATH)
		);
		this.type = type;
		switch (type) {
			case 1: this.currentTexture = new Texture(GameConstants.TREE1_IMGPATH); break;
			case 2: this.currentTexture = new Texture(GameConstants.TREE2_IMGPATH); break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}
	
	@Override
	public String toString() {
		return "TREE " + ((Integer) type).toString().toUpperCase()  ;
	}
}
