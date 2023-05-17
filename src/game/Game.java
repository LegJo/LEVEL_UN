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
import game.entity.livingEntity.enemy.Enemy;
import game.controls.Keyboard;
import game.entity.inertEntity.obstacle.Destroyable;
import game.entity.livingEntity.AutoMoveEntity;
import game.level.Level;
import game.map.Direction;
import game.map.Zone;
import game.scene.SceneUtils;


/**
 * The main class representing the game running.
 */
public class Game extends Application {
	/**
	 * the reference to the current game (unique)
	 */
	private static Game uniqueGame;
	/**
	 * The principal stage of the game.
	 */
	private static Stage primaryStage;
	/**
	 * The current level of the game.
	 */
	private static Level level;
	/**
	 * The scene of the game.
	 */
	private static Scene scene;
	/**
	 * Retrieves the current level.
	 * @return The current level.
	 */
	public static Level getLevel() {
		return level;
	}
	
	/**
	 * Retrieves the zone.
	 * @return The zone.
	 */
	public static Zone getZone() {
		return level.getZone();
	}
	
	/**
	 * Retrieves the primary stage.
	 * @return The primary stage.
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Retrieves the game scene.
	 * @return The game scene.
	 */
	public static Scene getScene() {
		return scene;
	}

	/**
	 * The entry point of the game. Only launch start method
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) { 
		launch(args);
	}
	
	/**
	 * Initializes and starts the game.
	 * @param primaryStage The primary stage of the game.
	 * @throws Exception If an exception occurs during startup.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception { 
		System.out.println("call: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		Game.primaryStage = primaryStage;
		Game.level = Level.LEVEL_TEST;
		Game.scene = SceneUtils.getSceneFromLevel(level);
		Game.uniqueGame = this;
		initWindow();
		showWindow();
		initKeyboard();
		gameLoop();
	}
	
	/**
	 * The main game loop.
	 * Call update method each frame
	 */
	private void gameLoop() {
		System.out.println("call: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();		
	    executor.scheduleAtFixedRate(() -> {
	        Platform.runLater(() -> {
	        	if(!this.update()) {
	        		executor.shutdown();
	        		//ici ptetre menu pour restart / quittez
	        		System.exit(0);
	        	}
	        });
	    }, 0, 1000/GameConstants.FPS, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * Updates the game state.
	 * @return True if the game should continue, false otherwise.
	 */
	private boolean update() {
		Zone zone = Game.getZone();
		//Scene Update according to entity modified list
		zone.getEntityModified().forEach(entityMod -> {
    		if(entityMod instanceof LivingEntity) {
    			LivingEntity livEntityMod = (LivingEntity) entityMod;
    			if(livEntityMod.isInRecoil()) {
    				livEntityMod.recoilOnScene();
    				if(livEntityMod.isDead()) {
    					livEntityMod.deadOnScene();
    				}
    			} else if(livEntityMod.isDead()) {
					livEntityMod.deadOnScene();
				} else {
    				livEntityMod.moveOnScene();
    			}
    		} else if(entityMod instanceof Destroyable) {
    			Destroyable destroyable = (Destroyable) entityMod;
    			if(destroyable.isDestroyed()) {
    				destroyable.destroyOnScene();
    			}
    		}
    	});
		//victory & defeat Check
    	if(level.isCompleted()) {
    		System.out.println("VICTOIRE !");
    		return false;
    	} else if(zone.getPlayer().isDead()) {
    		System.out.println("DEFAITE !");
    		return false;
    	}
    	//Zone update : update layout & clear entity modified list
    	zone.updateLayout();
    	zone.clearEntityModified();  
    	//"AI" gestion
    	zone.getEntityList().forEach(entity -> {
    		if(entity instanceof AutoMoveEntity) {
    			AutoMoveEntity movEntity = (AutoMoveEntity) entity;
    			if(entity instanceof Enemy) {
    				Enemy enemy = (Enemy) movEntity;
    				if(enemy.wantToAttack() != null)
            			enemy.attackBehavior();
    				else if(!enemy.isInAttack() && enemy.canMove())
    					enemy.destinationMovement(zone.getPlayer());
    			} else if(movEntity.canMove()) {
        			movEntity.randomMovement(Direction.getRandomDirection());
    			}
    		}
    	});
    	return true;
	}
	
	/**
	 * Initializes the game window created by start and set the scene on it.
	 */
	private void initWindow() {
		System.out.println("call: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		primaryStage.setTitle("LEVEL ONE <Joan Legrand>");
		primaryStage.getIcons().add(new Texture( (GameConstants.PLAYER_MOV_IMGPATH)[0]).getImage());
		primaryStage.setWidth(GameConstants.SCREEN_WIDTH); 
		primaryStage.setHeight(GameConstants.SCREEN_HEIGHT);
//		primaryStage.setFullScreen(true);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(e -> {
			quit();
		});
		primaryStage.setScene(scene);
	}
	
	/**
	 * Initializes the Keyboard and start the keys handlers for the game.
	 */
	private void initKeyboard() {
		Keyboard keyboard = new Keyboard(Game.scene);
		keyboard.initGameHandlers();
	}
	
	/**
	 * Shows the game window.
	 */
	private void showWindow() {
		System.out.println("call: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		primaryStage.sizeToScene();
		primaryStage.show();
		primaryStage.centerOnScreen();
	}
	
	/**
	 * Quit the application by calling System.exit(0)
	 */
	public static void quit() {
		System.exit(0);
	}
	
	public static void restart() {
		try {
			Game.uniqueGame.start(Game.primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
			quit();
		}
	}
	
	@Override
	public void init() {
		System.out.println("call: " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
}
