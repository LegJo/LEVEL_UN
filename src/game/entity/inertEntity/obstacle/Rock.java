package game.entity.inertEntity.obstacle;

import game.Coord;
import game.GameConstants;
import game.entity.inertEntity.InertEntity;
import game.texture.Texture;

public class Rock extends Obstacle {
	public Rock(Coord coord,String color, int type) {
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
	}
	
	@Override
	public String toString() {
		return "ROCK";
	}

}
