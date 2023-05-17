package game.entity.livingEntity.enemy;


import game.GameConstants;
import game.entity.Entity;
import game.map.Coord;
import game.texture.Texture;

public class Moblin extends Enemy {
	private final String color;
	
	public Moblin(Coord coord, String color, int health) {
		super(
			coord,
			new Texture(GameConstants.DEFAULT_IMGPATH),
			health,
			70,
			13,
			0,
			8
		);
		String[] movImgPath;
		String[] attackImgPath;
		switch (color.toLowerCase()) {
			case "blue": 
				movImgPath = GameConstants.BLUE_MOBLIN_MOV_IMGPATH;
				attackImgPath = GameConstants.BLUE_MOBLIN_ATTACK_IMGPATH;
			break;
			case "orange": 
				movImgPath = GameConstants.ORANGE_MOBLIN_MOV_IMGPATH;
				attackImgPath = GameConstants.ORANGE_MOBLIN_ATTACK_IMGPATH;
			break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + color);
		}
		
		for (int i = 0; i < movImgPath.length; i++) {
			this.movementTexture[i] = new Texture(movImgPath[i]);
		}
		for (int i = 0; i < attackImgPath.length; i++) {
			this.attackTexture[i] = new Texture(attackImgPath[i]);
		}
		
		this.currentTexture = new Texture(movImgPath[0]);
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "MOBLIN " + color.toUpperCase() ;
	}
	
	@Override
	public Entity copy(Coord coord) {
		return new Moblin(coord, this.color, this.getHealth());
	}
}
