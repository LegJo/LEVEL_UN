package game.level;

import java.util.Arrays;
import java.util.ArrayList;

import game.Coord;
import game.GameConstants;
import game.texture.BackgroundType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Region;
import game.entity.inertEntity.item.Sword;
import game.entity.inertEntity.obstacle.Rock;
import game.entity.inertEntity.obstacle.Tree;
import game.entity.livingEntity.*;
import game.entity.livingEntity.enemy.Moblin;
import game.level.map.Box;
import game.level.map.Zone;

public enum Level {
	LEVEL_TEST(
	    new Zone(
	        40, 25, // Largeur et hauteur de la zone
	        BackgroundType.GRASS,
	        new ArrayList<>(
	            Arrays.asList(
	                new Player(new Coord(12, 12), 100), 
	                
	                new Tree(new Coord(5, 8), 1), new Tree(new Coord(6, 8), 1), new Tree(new Coord(5, 9), 1), new Tree(new Coord(6, 9), 1), new Tree(new Coord(5, 10), 1), new Tree(new Coord(6, 10), 1),
	                new Tree(new Coord(18, 21), 2), new Tree(new Coord(19, 21), 2), new Tree(new Coord(18, 22), 2), new Tree(new Coord(19, 22), 2), new Tree(new Coord(18, 23), 2), new Tree(new Coord(19, 23), 2),
	                new Tree(new Coord(24, 5), 1), new Tree(new Coord(24, 6), 1), new Tree(new Coord(24, 7), 1), new Tree(new Coord(24, 8), 1), new Tree(new Coord(25, 5), 1), new Tree(new Coord(25, 6), 1), new Tree(new Coord(25, 7), 1), new Tree(new Coord(25, 8), 1),
	                
	                new Rock(new Coord(2, 15), "gray", 2), new Rock(new Coord(3, 15), "gray", 2), new Rock(new Coord(2, 16), "gray", 2), new Rock(new Coord(3, 16), "gray", 2), new Rock(new Coord(2, 17), "gray", 2), new Rock(new Coord(3, 17), "gray", 2), new Rock(new Coord(2, 18), "gray", 2), new Rock(new Coord(3, 18), "gray", 2), new Rock(new Coord(2, 19), "gray", 2), new Rock(new Coord(3, 19), "gray", 2), new Rock(new Coord(2, 20), "gray", 2), new Rock(new Coord(3, 20), "gray", 2),
	                new Rock(new Coord(8, 11), "brown", 3), new Rock(new Coord(8, 12), "brown", 3), new Rock(new Coord(8, 13), "brown", 3), new Rock(new Coord(8, 14), "brown", 3), new Rock(new Coord(8, 15), "brown", 3), new Rock(new Coord(8, 16), "brown", 3), new Rock(new Coord(8, 17), "brown", 3), new Rock(new Coord(8, 18), "brown", 3), new Rock(new Coord(8, 19), "brown", 3), new Rock(new Coord(8, 20), "brown", 3), new Rock(new Coord(8, 21), "brown", 3),
	                new Rock(new Coord(18, 7), "gray", 2), new Rock(new Coord(0, 0), "gray", 2),
	                
	                new Moblin(new Coord(10, 10), "blue", 4, 10, 0),
	                new Sword(new Coord(11, 12), 8), new Sword(new Coord(10, 12), 8), new Sword(new Coord(8, 12), 8),
	                new Sword(new Coord(11, 10), 8), new Sword(new Coord(10, 13), 8), new Sword(new Coord(8, 10), 8),
	                new Sword(new Coord(6, 10), 8), new Sword(new Coord(7, 13), 8), new Sword(new Coord(7, 10), 8), new Sword(new Coord(1, 10), 8)
				)
			)
		),
		new Goal(GoalType.KILL)
	);
			

	private final Zone zone;
    private final Goal goal;

    private Level(Zone zone, Goal goal) {
        this.zone = zone;
        this.goal = goal;
    }
   
    public GridPane getEntityPane() {
    	GridPane gridPane_Entity = new GridPane();
		int width = this.zone.getWidth();
		int height = this.zone.getHeight();
		int textureSize = GameConstants.TEXTURE_SIZE;
		for (int i = 0; i < width; i++) {
		    for (int j = 0; j < height; j++) {
	    	    Region emptyBox = new Region();
	    	    emptyBox.setPrefSize(textureSize, textureSize);
	    	    gridPane_Entity.add(emptyBox, i, j);
		    }
		}
		
		this.zone.getEntityList().forEach(entity -> {
			ImageView entityImgView = entity.getTexture().getImgView();
	       	 entityImgView.setFitWidth(textureSize);
	       	 entityImgView.setFitHeight(textureSize);
	       	 gridPane_Entity.add(entityImgView, entity.getCoord().getX(), entity.getCoord().getY());
		});
		return gridPane_Entity;
    }

    public GridPane getBackgroundPane() {
    	GridPane gridPane_Bg = new GridPane();
    	int width = this.zone.getWidth();
		int height = this.zone.getHeight();
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
    
    public BorderPane getBorderPane_Hud() {
    	BorderPane borderPane_Hud = new BorderPane();
    	borderPane_Hud.setRight(this.getZone().getPlayer().getInventory().getInventoryStackPane());
    	return borderPane_Hud;
    }
    
    public Scene getZoneScene() {
    	int textureSize = GameConstants.TEXTURE_SIZE;
		GridPane gridPane_Bg = this.getBackgroundPane();
		GridPane gridPane_Entity = this.getEntityPane();
		BorderPane borderPane_Hud = this.getBorderPane_Hud();
		borderPane_Hud.setId("borderPane_Hud");
		gridPane_Entity.setId("gridPane_Entity");
		gridPane_Bg.setId("gridPane_Bg");
		StackPane root = new StackPane(gridPane_Bg, gridPane_Entity, borderPane_Hud);
		StackPane.setAlignment(borderPane_Hud, Pos.TOP_RIGHT); 
		//StackPane.setMargin(borderPane_Hud, new Insets(10));
        Scene scene = new Scene(root, textureSize*this.zone.getWidth(), textureSize*this.zone.getHeight());
	    return scene;
    }
    
    public Zone getZone() {
  		return zone;
  	}
    
    public boolean isFinished() {
       return false;
    }
}
