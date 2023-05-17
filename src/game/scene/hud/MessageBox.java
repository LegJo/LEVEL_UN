package game.scene.hud;

import game.GameConstants;
import game.texture.Texture;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MessageBox {

    private Texture messageBoxTexture;
    private Text message;
    private StackPane messageBoxStackPane;
    private boolean isHide;
    private boolean inAnimation;

    public MessageBox(String goalQuote, boolean hide) {
        this.messageBoxTexture = new Texture(GameConstants.MESSAGEBOX_IMGPATH);
        message = new Text(goalQuote);
        message.setFont(Font.font("Trebushet MS", FontWeight.BOLD, 24));
        message.setStyle("-fx-font-family: 'Trebushet MS';");
        messageBoxStackPane = new StackPane(messageBoxTexture.getImgView(), message);  
        inAnimation = false;
        isHide = false;
        if(hide) {
        	this.hide();
        }
    }

    public void setMessage(String newMessage) {
        message.setText(newMessage);
    }
    
    public boolean isInAnimation() {
		return inAnimation;
	}

	public void setInAnimation(boolean inAnimation) {
		this.inAnimation = inAnimation;
	}

    public void hide(double seconds) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(seconds), messageBoxStackPane);
        transition.setToY(messageBoxTexture.getImage().getHeight());
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.setOnFinished(e -> {
        	this.setHide(true);
        	this.setInAnimation(false);
        });
        transition.play();
        this.setInAnimation(true);
    }
    
    public void hide() {
    	hide(0.5);
    }
    
	public void show(double seconds) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(seconds), messageBoxStackPane);
        transition.setToY(0);
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.setOnFinished(e -> {
        	this.setHide(false);
        	this.setInAnimation(false);
        });
        transition.play();
        this.setInAnimation(true);
    }
	
	public void show() {
		show(0.5);
	}
	
	public void toggle() {
		if(this.isHide()) {
        	this.show();
        } else {
        	this.hide();
        }
	}
    
    public void hideDelayed(int millis) {
    	new java.util.Timer().schedule(
    	        new java.util.TimerTask() {
    	            @Override
    	            public void run() {
    	                hide();
    	            }},millis);
    }
    
    public void showDelayed(int millis) {
    	new java.util.Timer().schedule(
    	        new java.util.TimerTask() {
    	            @Override
    	            public void run() {
    	                show();
    	            }},millis);
    }

    public StackPane getStackPane() {
        return messageBoxStackPane;
    }

	public boolean isHide() {
		return isHide;
	}

	public void setHide(boolean isHide) {
		this.isHide = isHide;
	}
}
