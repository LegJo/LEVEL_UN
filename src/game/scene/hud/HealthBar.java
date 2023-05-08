package game.scene.hud;

import javafx.scene.layout.HBox;

public class HealthBar {
	HBox heartContainer;
	Heart[] heartTab;
	
	public HealthBar(int playerHealth) {
		int fullHeartNo = (int) Math.floor(playerHealth / 10);
		int lastHeartHealth = playerHealth - (fullHeartNo*10);
		this.heartContainer = new HBox();

		if(lastHeartHealth > 0)
			this.heartTab = new Heart[fullHeartNo+1];
		else
			this.heartTab = new Heart[fullHeartNo];
		for (int i = 0; i < fullHeartNo; i++) {
			heartTab[i] = new Heart(10);
			heartContainer.getChildren().add(heartTab[i].getHeartTexture().getImgView());
		}
		if(lastHeartHealth > 0) {
			heartTab[fullHeartNo] = new Heart(lastHeartHealth);
			heartContainer.getChildren().add(heartTab[fullHeartNo].getHeartTexture().getImgView());
		}		
	}

	public Heart getHeart(int index) {
		return heartTab[index];
	}
	
	public int getAllHealthIn() {
		int allHealthIn = 0;
		for (int i = 0; i < heartTab.length; i++) {
			allHealthIn += getHeart(i).getHealthIn();
		}
		return allHealthIn;
	}
	
	public int getFirstNonEmptyHeartIndex() {
		for (int i = heartTab.length-1; i >= 0 ; i--) {
			if(getHeart(i).getHealthIn() > 0) {
				return i;
			}
		}
		return 0; // return -1 ? theoriquement si ici c'est que player est mort
	}
	
	public Heart getFirstNonEmptyHeart() {
		return getHeart(getFirstNonEmptyHeartIndex());
	}
	
	public void updateHealthIn(int playerHealth) {
		int diffHealth = playerHealth - this.getAllHealthIn();
		if(diffHealth == 0) 
			return;
		
		int heartIndex = getFirstNonEmptyHeartIndex();
		if (diffHealth > 0) { //Add health to HealthBar
			int healthToAdd = diffHealth;
			do {
				healthToAdd = getHeart(heartIndex).addHealthIn(healthToAdd);
				heartIndex += 1;
			} while (healthToAdd !=  0 && heartIndex  < heartTab.length - 1);
		} else { //remove health from healthBar
			int healthToRemove = -diffHealth;
			do {
				healthToRemove = getHeart(heartIndex).removeHealthIn(healthToRemove);
				heartIndex -= 1;
			} while(healthToRemove != 0 && heartIndex  > 0);
		}
		
		
	}

	public HBox getHeartBarHBox() {
		return heartContainer;
	}

}
