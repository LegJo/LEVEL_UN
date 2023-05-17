package game.entity.inertEntity.obstacle;

import java.util.ArrayList;

import game.Game;
import game.entity.Entity;
import game.entity.inertEntity.item.Item;
import game.map.Zone;

/**
 * 
 *
 */
public interface Destroyable {
	ArrayList<Class<? extends Item>> getDestroyToolList();
	boolean isDestroyed();
	void setDestroyed(boolean destroyed);
	void destroyOnScene();
	default boolean canBeDestroyedBy(Item item) {
	    for (Class<? extends Item> itemClass : getDestroyToolList()) {
	        if (itemClass.isAssignableFrom(item.getClass())) {
	            return true;
	        }
	    }
	    return false;
	}  
	
	default void tryDestruction(Item tool) {
  		Zone zone = Game.getZone();
		if(this.canBeDestroyedBy(tool)) {
			this.setDestroyed(true);
			if(this instanceof Entity) //class which implements Destroyable must/should be Entities a least
				zone.addEntityModified((Entity) this);
		}
	}
}
