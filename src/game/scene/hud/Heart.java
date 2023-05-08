package game.scene.hud;

import game.GameConstants;
import game.texture.Texture;

public class Heart {
	Texture heartTexture;
	int healthIn;	

	public Heart(int healthIn) {
		this.heartTexture = new Texture(GameConstants.HEART_IMGPATH[healthIn]);
		this.healthIn = healthIn;
	}

	public int getHealthIn() {
		return healthIn;
	}

	public void setHealthIn(int healthIn) {
		this.healthIn = healthIn;
		this.getHeartTexture().setImagePath(GameConstants.HEART_IMGPATH[this.getHealthIn()]);
	}

	public Texture getHeartTexture() {
		return heartTexture;
	}
	
	public int addHealthIn(int healthToAdd) {
		int healthAddable = 10 - this.getHealthIn();
		if(healthToAdd > this.getHealthIn()) {
			this.setHealthIn(10);
			return (healthToAdd - healthAddable);
		} else {
			this.setHealthIn(this.getHealthIn() + healthToAdd);
			return 0;
		}
	}
	
	public int removeHealthIn(int healthToRemove) {
		int healthRemovable = this.getHealthIn();
		if(healthToRemove > healthRemovable) {
			this.setHealthIn(0);
			return (healthToRemove - healthRemovable);
		} else {
			this.setHealthIn(this.getHealthIn() - healthToRemove);
			return 0;
		}
	}
}
