package game.entity.livingEntity.enemy;


import game.Coord;
import game.GameConstants;
import game.texture.Texture;

public class Moblin extends Enemy {
	
	
	public Moblin(Coord coord, String color, int hustle, int power, int speed) {
		super(
			coord,
			new Texture(GameConstants.DEFAULT_IMGPATH),
			GameConstants.MOBLIN_HP,
			hustle,
			power,
			speed
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
	}
	
	@Override
	public String toString() {
		return "MOBLIN" ;
	}
}
