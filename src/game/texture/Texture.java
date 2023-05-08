package game.texture;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Texture {
    private String imagePath;
    private Image image;
    private ImageView imageView;

    public Texture(String relativImagePath) {
        this.imagePath = relativImagePath;
        Image image = new Image(imagePath);
        this.image = image;
        ImageView imageView = new ImageView(image);
        imageView.setCache(false);
        imageView.setStyle("-fx-background-color: transparent;");
        imageView.setPreserveRatio(true);
        this.imageView = imageView;
    }

    public ImageView getImgView() {
        return this.imageView;
    }
    
    public Image getImage() {
    	return this.image;
    }
    
    private void setImage(Image image) {
    	this.image = image;
    }
    
    
    private String getImagePath() {
    	return this.imagePath;
    }

    public void setImagePath(String relativImagePath) {
        this.imagePath = relativImagePath;
        this.refreshImageView();
    }
    
    private void refreshImage() {
   	 	this.setImage(new Image(this.getImagePath()));
   }
    
    private void refreshImageView() {
    	 this.refreshImage();
         this.getImgView().setImage(this.getImage());;
    }
}
