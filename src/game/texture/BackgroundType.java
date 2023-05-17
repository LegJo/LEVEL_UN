package game.texture;

import game.GameConstants;

public enum BackgroundType {
	DEFAULT(GameConstants.BG_GRASS_IMGPATH, true),
	GRASS(GameConstants.BG_GRASS_IMGPATH, true),
	GRASS_DARKER(GameConstants.BG_GRASS_DARKER_IMGPATH, true),
	WATER1(GameConstants.BG_WATER_IMGPATHS[0], false),
	WATER1_B_D(GameConstants.BG_WATER_IMGPATHS[1], false),
	WATER1_B_U(GameConstants.BG_WATER_IMGPATHS[2], false),
	WATER1_B_L(GameConstants.BG_WATER_IMGPATHS[3], false),
	WATER1_B_R(GameConstants.BG_WATER_IMGPATHS[4], false),
	WATER1_C_DL(GameConstants.BG_WATER_IMGPATHS[5], false),
	WATER1_C_DR(GameConstants.BG_WATER_IMGPATHS[6], false),
	WATER1_C_UL(GameConstants.BG_WATER_IMGPATHS[7], false),
	WATER1_C_UR(GameConstants.BG_WATER_IMGPATHS[8], false);

	
	private final boolean walkable;
	private final String imgPath;
	
	private BackgroundType(String imgPath, boolean isWalkable) {
        this.imgPath = imgPath;
        this.walkable = isWalkable;
    }
	
    public String getImgPath() {
        return imgPath;
    }

	public boolean isWalkable() {
		return walkable;
	}
}
