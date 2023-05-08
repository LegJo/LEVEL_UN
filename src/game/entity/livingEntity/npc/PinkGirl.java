package game.entity.livingEntity.npc;

import game.GameConstants;
import game.map.Coord;
import game.texture.Texture;

public class PinkGirl extends Npc {

	public PinkGirl(Coord coord) {
		super(
			coord,
			new Texture(GameConstants.PINKGRIL_MOV_IMGPATH[0]),
			20,
			2,
			0
		);
		String[] movImgPath = GameConstants.PINKGRIL_MOV_IMGPATH;
		for (int i = 0; i < movImgPath.length; i++) {
			this.movementTexture[i] = new Texture(movImgPath[i]);
		}
		
	}
	
	@Override
	public String toString() {
		return "PINK GIRL" ;
	}
}
