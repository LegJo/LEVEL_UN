package game.texture;

import game.GameConstants;

public enum BackgroundType {
	GRASS(GameConstants.BG_GRASS_IMGPATH);
	
	private final String imgPath;
	
	private BackgroundType(String imgPath) {
        this.imgPath = imgPath;
    }
	
    public String getImgPath() {
        return imgPath;
    }
}
