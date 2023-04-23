package game;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import game.texture.Texture;
import game.entity.inertEntity.item.Item;
import game.entity.livingEntity.LivingEntity;
import game.entity.livingEntity.AutoMoveEntity;
import game.entity.livingEntity.Player;
import game.level.Level;
import game.level.map.Zone;


public class Game extends Application{
	
	public void playGame(Stage primaryStage, Scene scene, Level level, int fps) {
		Zone zone = level.getZone();	
		Keyboard keyboard = new Keyboard(scene);
		keyboard.startArrowHandler(zone);
		keyboard.startActionHandler(zone);
		
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.schedule(() -> {
		    executor.shutdown();
		}, 100, TimeUnit.SECONDS);
		
		
	    executor.scheduleAtFixedRate(() -> {
	        Platform.runLater(() -> {
	        	zone.getEntityModified().forEach(entityMod -> {
	        		if(entityMod instanceof LivingEntity) {
	        			LivingEntity livEntityMod = (LivingEntity) entityMod;
	        			livEntityMod.moveTo(livEntityMod.getDirection());
	        		}
	        		
	        	});
	        	zone.updateLayout();
	        	zone.clearEntityModified();  
	        	zone.getEntityList().forEach(entity -> {
	        		if(entity instanceof AutoMoveEntity) {
	        			AutoMoveEntity movEntity = (AutoMoveEntity) entity;
	        			movEntity.randomMovement(zone);
	        		}
	        	});
	        });
	    }, 0, 1000/fps, TimeUnit.MILLISECONDS);
	}

	public void initWindow(Stage primaryStage , int width, int height) {
		System.out.println("call: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		primaryStage.setTitle("LEVEL ONE <Joan Legrand>");
		primaryStage.getIcons().add(new Texture( (GameConstants.PLAYER_MOV_IMGPATH)[0]).getImage());
		primaryStage.setWidth(width); 
		primaryStage.setHeight(height);
		primaryStage.setResizable(false);
	}
	
	public void showWindow(Stage primaryStage) {
		System.out.println("call: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		primaryStage.show();
		primaryStage.centerOnScreen();
	}
	
	public void closeWindow(Stage primaryStage, int delay) {
		System.out.println("call: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		new Thread(() -> {
			try {
				Thread.sleep(delay);
				Platform.runLater(() ->	primaryStage.close());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	@Override
	public void init() {
		System.out.println("call: " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception { 
		System.out.println("call: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		initWindow(primaryStage, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
		Level level = Level.LEVEL_TEST;
		Scene scene = level.getZoneScene();
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		showWindow(primaryStage);
		playGame(primaryStage, scene, level, GameConstants.FPS);
		closeWindow(primaryStage, 100000);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
}
