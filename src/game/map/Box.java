package game.map;

import game.entity.Entity;
import game.texture.Background;
import game.texture.BackgroundType;

public class Box {
    private Entity entity;
    private Background background;

    
    public Box(Entity entity, Background bg) {
    	this.entity = entity;
        this.background = bg;
    }
    
    public Box(Entity entity, BackgroundType bgType) {
        this(entity, new Background(bgType));
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    
    public void remEntity() {
    	this.setEntity(null);
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background bg) {
		background = bg;
    }
    
    public void setBackground(BackgroundType bgType) {
		background.setImagePath(bgType.getImgPath());
    }
    
    public boolean isEmpty() {
    	return(entity == null);
    }
    
	public static Box emptyBox() {
		return new Box(null, BackgroundType.DEFAULT);
	}
    
    @Override
    public String toString() {
    	if(!this.isEmpty()) {
	        return "[" + entity + "]";
    	} else {
    		return "[]";
    	}
    }
}
