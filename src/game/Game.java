package game;

import javafx.application.Application;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import game.texture.Texture;
import game.entity.livingEntity.LivingEntity;
import game.entity.livingEntity.AutoMoveEntity;
import game.level.Level;
import game.map.Zone;
import game.scene.SceneUtils;
import game.scene.hud.MessageBox;


public class Game extends Application{
	private Stage primaryStage;
	private Level level;
	private Scene scene;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception { 
		System.out.println("call: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		this.primaryStage = primaryStage;
		this.level = Level.LEVEL_TEST;
		this.scene = SceneUtils.getSceneFromLevel(level);
		initWindow();
		showWindow();
		gameLoop();
	}
	
	public void gameLoop() {
		System.out.println("call: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		Zone zone = level.getZone();	
		Keyboard keyboard = new Keyboard(scene);
		keyboard.initGameHandlers(zone);
		
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();		
	    executor.scheduleAtFixedRate(() -> {
	        Platform.runLater(() -> {
	        	if(!this.update()) {
	        		executor.shutdown();
	        		//ici ptetre menu pour restart / quittez
	        		closeWindow(500);
	        	}
	        });
	    }, 0, 1000/GameConstants.FPS, TimeUnit.MILLISECONDS);
	}
	
	public boolean update() {
		Zone zone = level.getZone();
		zone.getEntityModified().forEach(entityMod -> {
    		if(entityMod instanceof LivingEntity) {
    			LivingEntity livEntityMod = (LivingEntity) entityMod;
    			if(livEntityMod.isInRecoil()) {
    				livEntityMod.recoilOnScene();
    				if(livEntityMod.isDead()) {
    					livEntityMod.DeadOnScene();
    				}
    			} else if(livEntityMod.isDead()) {
					livEntityMod.DeadOnScene();
				} else {
    				livEntityMod.moveOnScene();
    			}
    		}
    		
    	});
    	if(level.isCompleted()) {
    		System.out.println("VICTOIRE !");
    		return false;
    	} else if(zone.getPlayer().isDead()) {
    		System.out.println("DEFAITE !");
    		return false;
    	}
    	zone.updateLayout();
    	zone.clearEntityModified();  
    	zone.getEntityList().forEach(entity -> {
    		if(entity instanceof AutoMoveEntity) {
    			AutoMoveEntity movEntity = (AutoMoveEntity) entity;
    			movEntity.randomMovement(zone);
    		}
    	});
    	return true;
	}
	
	public void initWindow() {
		System.out.println("call: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		primaryStage.setTitle("LEVEL ONE <Joan Legrand>");
		primaryStage.getIcons().add(new Texture( (GameConstants.PLAYER_MOV_IMGPATH)[0]).getImage());
		primaryStage.setWidth(GameConstants.SCREEN_WIDTH); 
		primaryStage.setHeight(GameConstants.SCREEN_HEIGHT);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
	}
	
	public void showWindow() {
		System.out.println("call: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		primaryStage.sizeToScene();
		primaryStage.show();
		primaryStage.centerOnScreen();
	}
		
	public void closeWindow(int delay) {
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

	
}
