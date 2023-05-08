package game.entity.livingEntity.enemy;


import game.GameConstants;
import game.map.Coord;
import game.texture.Texture;

public class Moblin extends Enemy {
	private final String color;
	
	public Moblin(Coord coord, String color) {
		super(
			coord,
			new Texture(GameConstants.DEFAULT_IMGPATH),
			GameConstants.MOBLIN_HP,
			4,
			13,
			0
		);
		String[] movImgPath;
		switch (color.toLowerCase()) {
			case "blue": 
				movImgPath = GameConstants.BLUE_MOBLIN_MOV_IMGPATH;
			break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + color);
		}
		for (int i = 0; i < movImgPath.length; i++) {
			this.movementTexture[i] = new Texture(movImgPath[i]);
		}
		this.currentTexture = new Texture(movImgPath[0]);
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "MOBLIN " + color.toUpperCase() ;
	}
}
