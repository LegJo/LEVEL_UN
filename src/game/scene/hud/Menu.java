package game.scene.hud;
import game.Game;
import game.GameConstants;
import game.scene.SceneUtils;
import game.texture.Animation;
import game.texture.Texture;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Menu {
	private VBox menuVBox;
	private boolean hide;
	
	public Menu() {
		this.menuVBox = new VBox(Menu.newRestartButton(), Menu.newQuitButton(), Menu.newResumeButton());
		this.menuVBox.setVisible(false);
		this.hide = false;
	}
	
	public VBox getMenuVBox() {
		return menuVBox;
	}
	
	/**
	 * Returns the boolean corresponding to hide status of inventory stackpane on Scene
	 * @return the boolean hide.
	 */
	public boolean isHide() {
		return hide;
	}
	
	/**
	 * Sets the boolean hide
	 * @param hide The boolean to set to hide.
	 */
	public void setHide(boolean hide) {
		this.hide = hide;
	}
	
	/*
	 * Returns a button to restart the game
	 */
	public static Button newRestartButton() {
		Button buttonRestart = new Button();
		buttonRestart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Game.restart();
            }
        });
		buttonRestart.setGraphic(new MessageBox("Restart", false).getStackPane());
        buttonRestart.setStyle("-fx-background-color: transparent;");
		return buttonRestart;
	}
	
	/*
	 * Returns a button to quit the game
	 */
	public static Button newQuitButton() {
		Button buttonQuit = new Button();
		buttonQuit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Game.quit();
            }
        });
		buttonQuit.setGraphic(new MessageBox("Exit", false).getStackPane());
        buttonQuit.setStyle("-fx-background-color: transparent;");
		return buttonQuit;
	}
	
	/*
	 * Returns a button to resume the game
	 */
	public static Button newResumeButton() {
		Button buttonResume = new Button();
		buttonResume.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	SceneUtils.echapMenu.toggle();
            }
        });
		buttonResume.setGraphic(new MessageBox("Resume", false).getStackPane());
        buttonResume.setStyle("-fx-background-color: transparent;");
		return buttonResume;
	}
	
	/**
	 * hide the menuVBox on Scene
	 */
	public void hide() {
		this.getMenuVBox().setVisible(false);
		this.setHide(true);
	}
	
	/**
	 * show the menuVBox on Scene
	 */
	public void show() {
		this.getMenuVBox().setVisible(true);
		this.setHide(false);
	}
	
	/**
	 * Regarding Whether than the menuVBox is hide or not, hide or show the menuVBox
	 */
	public void toggle() {
		if(this.isHide()) {
        	this.show();
        } else {
        	this.hide();
        }
	}
}
