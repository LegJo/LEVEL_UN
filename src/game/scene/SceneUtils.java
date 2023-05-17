package game.scene;

import game.Game;
import game.GameConstants;
import game.entity.livingEntity.Player;
import game.level.Level;
import game.map.Box;
import game.map.Zone;
import game.scene.hud.Menu;
import game.scene.hud.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class SceneUtils {
	public static BorderPane borderPane_Hud;
	public static GridPane gridPane_Bg;
	public static GridPane gridPane_Entity;
	
	public static MessageBox goalBox;
	public static MessageBox npcBox;
	public static Menu echapMenu;
	
	 public static GridPane getEntityPane() {
	  		Zone zone = Game.getZone();
	    	SceneUtils.gridPane_Entity = new GridPane();
			int width = zone.getWidth();
			int height = zone.getHeight();
			int textureSize = GameConstants.TEXTURE_SIZE;
			for (int i = 0; i < width; i++) {
			    for (int j = 0; j < height; j++) {
		    	    Region emptyBox = new Region();
		    	    emptyBox.setPrefSize(textureSize, textureSize);
		    	    gridPane_Entity.add(emptyBox, i, j);
			    }
			}
			
			zone.getEntityList().forEach(entity -> {
				ImageView entityImgView = entity.getTexture().getImgView();
				entityImgView.setFitWidth(textureSize);
		       	 entityImgView.setFitHeight(textureSize);
		       	 gridPane_Entity.add(entityImgView, entity.getCoord().getX(), entity.getCoord().getY());
			});
			return gridPane_Entity;
	    }

	    public static GridPane getBackgroundPane() {
	  		Zone zone = Game.getZone();
	    	SceneUtils.gridPane_Bg = new GridPane();
	    	int width = zone.getWidth();
			int height = zone.getHeight();
			int textureSize = GameConstants.TEXTURE_SIZE;
			for (int i = 0; i < width; i++) {
			    for (int j = 0; j < height; j++) {
			        Box box = zone.getBox(i, j);
			        
			        ImageView backgroundImageView = box.getBackground().getImgView();
			        backgroundImageView.setFitWidth(textureSize);
			        backgroundImageView.setFitHeight(textureSize);
			        backgroundImageView.toBack();
			        gridPane_Bg.add(backgroundImageView, i, j);
			    }
			}
			return gridPane_Bg;
	    }
	    
	    public static BorderPane getBorderPane_Hud(String goalQuote) {
	    	SceneUtils.borderPane_Hud = new BorderPane();
	    	Player player = Game.getZone().getPlayer();
	    	SceneUtils.goalBox = new MessageBox(goalQuote, false);
	    	SceneUtils.npcBox = new MessageBox("", true);
	    	SceneUtils.goalBox.hideDelayed(4000);
	    	SceneUtils.echapMenu = new Menu();
	    	SceneUtils.echapMenu.getMenuVBox().setAlignment(Pos.CENTER);
	    	
	    	borderPane_Hud.setRight(player.getInventory().getInventoryStackPane());
	    	borderPane_Hud.setTop(player.getHealthBar().getHeartBarHBox());
	    	borderPane_Hud.setBottom(new StackPane(SceneUtils.npcBox.getStackPane(), SceneUtils.goalBox.getStackPane()));
	    	borderPane_Hud.setCenter(SceneUtils.echapMenu.getMenuVBox());
	    	
	    	
	    	return borderPane_Hud;
	    }
	    
	    public static Scene getSceneFromLevel(Level level) {
	    	Zone zone = level.getZone();
	    	int textureSize = GameConstants.TEXTURE_SIZE;
			GridPane gridPane_Bg = SceneUtils.getBackgroundPane();
			GridPane gridPane_Entity = SceneUtils.getEntityPane();
			BorderPane borderPane_Hud = SceneUtils.getBorderPane_Hud(level.getGoal().getGoalQuote());
			StackPane root = new StackPane(gridPane_Bg, gridPane_Entity, borderPane_Hud);
			StackPane.setAlignment(borderPane_Hud, Pos.TOP_RIGHT);
	        Scene scene = new Scene(root, textureSize*zone.getWidth(), textureSize*zone.getHeight());
		    return scene;
	    }
	

}
