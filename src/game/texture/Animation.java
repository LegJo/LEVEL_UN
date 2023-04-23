package game.texture;

import game.Coord;
import game.GameConstants;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Animation {
	public static TranslateTransition movementTranslation(ImageView imageView, Coord translateCoord, int animDuration) {
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(animDuration-150), imageView);
	    translateTransition.setToX(translateCoord.getX());
	    translateTransition.setToY(translateCoord.getY());
	    return translateTransition;
	}
	
	public static Transition ImgAnimation(ImageView imageView, Image[] images, int animDuration) {
		
		Transition imageTransition = new Transition() {
	        { 
	            setCycleDuration(Duration.millis(animDuration));
	        }

	        @Override
	        protected void interpolate(double frac) {
	        	for (int i = 0; i < images.length; i++) {
	        		float startFrac = (float) i/images.length;
	        		float endFrac =(float) (i+1)/images.length;
	        		if (frac >= startFrac && frac < endFrac) {
	        			imageView.setFitWidth(images[i].getWidth()*GameConstants.TEXTURE_SCALE);
	        			imageView.setFitHeight(images[i].getHeight()*GameConstants.TEXTURE_SCALE);
		            	imageView.setImage(images[i]);
	        		}
	        	}
	        }
	    };
		return imageTransition;
	}
	
	public static Transition damageTakenAnim(ImageView imageView) {
		Transition transition = new Transition() {
	        { 
	            setCycleDuration(Duration.seconds(0.5));
	        }
	        @Override
	        protected void interpolate(double frac) {
	            if (frac > 0 && frac < 0.5) {
	            	imageView.setStyle("-fx-effect: dropshadow(three-pass-box, red," + (int) (10*frac) +", 0, 0, 0);");
	            } else {
	            	imageView.setStyle("-fx-effect: dropshadow(three-pass-box, red," + (int) (10/(1-frac)) +", 0, 0, 0);");
	            }
	        }
		};
		
		transition.setOnFinished(event -> {
		    imageView.setStyle("-fx-effect: none;");
		});
		
		return transition;
	}
	
	public static void invincibilityAnimation(ImageView imageView, int animDuration) {
		FadeTransition ft = new FadeTransition(Duration.millis((int) animDuration/12), imageView);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(javafx.animation.Animation.INDEFINITE); 
        ft.setAutoReverse(true);
        ft.setOnFinished(e -> {
        	imageView.setOpacity(1);
        });
        ft.play();
        
	        
		 new java.util.Timer().schedule(
	        new java.util.TimerTask() {
	            @Override
	            public void run() {
	                ft.stop();
	            }
	        },
	        animDuration
	    );
	}
}
