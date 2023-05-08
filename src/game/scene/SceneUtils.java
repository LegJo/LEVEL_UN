package game.scene;

import game.GameConstants;
import game.entity.livingEntity.Player;
import game.level.Level;
import game.map.Box;
import game.map.Zone;
import game.scene.hud.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class SceneUtils {
	public static MessageBox messageBox;
	
	 public static GridPane getEntityPane(Zone zone) {
	    	GridPane gridPane_Entity = new GridPane();
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

	    public static GridPane getBackgroundPane(Zone zone) {
	    	GridPane gridPane_Bg = new GridPane();
	    	int width = zone.getWidth();
			int height = zone.getHeight();
			int textureSize = GameConstants.TEXTURE_SIZE;
			for (int i = 0; i < width; i++) {
			    for (int j = 0; j < height; j++) {
			        Box box = zone.getBox(i, j);
			        
			        ImageView backgroundImageView = box.getBackgroundTexture().getImgView();
			        backgroundImageView.setFitWidth(textureSize);
			        backgroundImageView.setFitHeight(textureSize);
			        backgroundImageView.toBack();
			        gridPane_Bg.add(backgroundImageView, i, j);
			    }
			}
			return gridPane_Bg;
	    }
	    
	    public static BorderPane getBorderPane_Hud(Zone zone, String goalQuote) {
	    	BorderPane borderPane_Hud = new BorderPane();
	    	Player player = zone.getPlayer();
	    	SceneUtils.messageBox = new MessageBox(goalQuote);
	    	borderPane_Hud.setRight(player.getInventory().getInventoryStackPane());
	    	borderPane_Hud.setTop(player.getHealthBar().getHeartBarHBox());
	    	borderPane_Hud.setBottom(SceneUtils.messageBox.getStackPane());
	    	return borderPane_Hud;
	    }
	    
	    public static Scene getSceneFromLevel(Level level) {
	    	Zone zone = level.getZone();
	    	int textureSize = GameConstants.TEXTURE_SIZE;
			GridPane gridPane_Bg = SceneUtils.getBackgroundPane(zone);
			GridPane gridPane_Entity = SceneUtils.getEntityPane(zone);
			BorderPane borderPane_Hud = SceneUtils.getBorderPane_Hud(zone, level.getGoal().getGoalQuote());
			borderPane_Hud.setId("borderPane_Hud");
			gridPane_Entity.setId("gridPane_Entity");
			gridPane_Bg.setId("gridPane_Bg");
			StackPane root = new StackPane(gridPane_Bg, gridPane_Entity, borderPane_Hud);
			StackPane.setAlignment(borderPane_Hud, Pos.TOP_RIGHT);
	        Scene scene = new Scene(root, textureSize*zone.getWidth(), textureSize*zone.getHeight());
		    return scene;
	    }
	

}
