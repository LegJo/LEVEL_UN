package game.texture;

public class Background extends Texture {
	private boolean isWalkable;
	public Background(BackgroundType bgType) {
		super(bgType.getImgPath());
		this.setWalkable(bgType.isWalkable());
	}
	public boolean isWalkable() {
		return isWalkable;
	}
	public void setWalkable(boolean isWalkable) {
		this.isWalkable = isWalkable;
	}
}
