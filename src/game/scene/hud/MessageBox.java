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

    private final Texture messageBoxTexture;
    private final Text message;
    private final StackPane messageBoxStackPane;

    public MessageBox(String goalQuote) {
        this.messageBoxTexture = new Texture(GameConstants.MESSAGEBOX_IMGPATH);
        message = new Text(goalQuote);
        message.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 24));
        message.setStyle("-fx-font-family: 'Old English Text MT';");
        messageBoxStackPane = new StackPane(messageBoxTexture.getImgView(), message);  
        
        new java.util.Timer().schedule(
	        new java.util.TimerTask() {
	            @Override
	            public void run() {
	                hide();
	            }},3000);
    }

    public void setMessage(String newMessage) {
        message.setText(newMessage);
    }

    public void hide() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), messageBoxStackPane);
        transition.setToY(messageBoxStackPane.getHeight());
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.play();
    }

    public void show() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), messageBoxStackPane);
        transition.setToY(0);
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.play();
    }


    public StackPane getStackPane() {
        return messageBoxStackPane;
    }
}
