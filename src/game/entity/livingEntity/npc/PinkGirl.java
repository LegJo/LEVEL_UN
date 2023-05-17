package game.entity.livingEntity.npc;

import game.GameConstants;
import game.entity.Entity;
import game.map.Coord;
import game.texture.Texture;

public class PinkGirl extends Npc {

	public PinkGirl(Coord coord) {
		super(
			coord,
			new Texture(GameConstants.PINKGRIL_MOV_IMGPATH[0]),
			2,
			20,
			2,
			0
		);
		String[] movImgPath = GameConstants.PINKGRIL_MOV_IMGPATH;
		for (int i = 0; i < movImgPath.length; i++) {
			this.movementTexture[i] = new Texture(movImgPath[i]);
		}
		this.messages[0]=Message.HELLO;
		this.messages[1]=Message.GIVE_APPLE;
		
	}
	
	@Override
	public String toString() {
		return "PINK GIRL" ;
	}
	
	@Override
	public Entity copy(Coord coord) {
		return new PinkGirl(coord);
	}
}
