package game.level.map;

import game.entity.Entity;
import game.texture.BackgroundTexture;
import game.texture.BackgroundType;

public class Box {
    private Entity entity;
    private BackgroundTexture backgroundTexture;

    public Box(Entity entity, BackgroundType bgType) {
        this.entity = entity;
        this.backgroundTexture = new BackgroundTexture(bgType);
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

    public BackgroundTexture getBackgroundTexture() {
        return backgroundTexture;
    }

    public void setBackgroundTexture(BackgroundType bgType) {
		backgroundTexture.setImagePath(bgType.getImgPath());
    }
    
    public boolean isEmpty() {
    	return(entity == null);
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
