package game.entity.inertEntity.item;

import game.GameConstants;
import game.map.Coord;

public class GoldenApple extends Apple {

	public GoldenApple(Coord coord) {
		super(coord);
		this.setHealthGain(50);
		this.getTexture().setImagePath(GameConstants.GOLDEN_APPLE_IMGPATH);
	}

}
