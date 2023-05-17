package game.level;

import java.util.ArrayList;
import game.entity.Entity;
import game.entity.inertEntity.item.Item;
import game.entity.inertEntity.obstacle.Destroyable;
import game.entity.livingEntity.Player;
import game.entity.livingEntity.enemy.Enemy;
public enum Goal {
    KILL_ALL_ENEMIES("Kill all Enemies to Win") {
        @Override
        public boolean isAchieved(ArrayList<Entity> entityList) {
            for (Entity entity : entityList) {
                if (entity instanceof Enemy) {
                	Enemy enemy = (Enemy) entity;
                	if(!enemy.isDead())
                		return false; 
                }
            }
            return true; 
        }
    },
    COLLECT_ALL_ITEMS("Collect every Items to Win") {
        @Override
        public boolean isAchieved(ArrayList<Entity> entityList) {
        	Player player = null;
        	for (Entity entity : entityList) {
        		if(entity instanceof Player)
        			player = (Player) entity;
        	}
        	for (Entity entity : entityList) {
        		
                if (entity instanceof Item) {
                	Item item = (Item) entity;
                	if(!item.isInInventory() || player.getInventory().getItemIndex(item) == -1) //note les item remove de l'inventaire mais non drop (utilisation unique) garde le status in inventory
                		return false; 
                }
            }
            return true; 
        }
    },
    DESTROY_ALL_DESTROYABLE("Destroy everything to Win") {
    	@Override
        public boolean isAchieved(ArrayList<Entity> entityList) {
            for (Entity entity : entityList) {
                if (entity instanceof Destroyable) {
                	Destroyable destroyable = (Destroyable) entity;
                	if(!destroyable.isDestroyed())
                		return false; 
                }
            }
            return true; 
        }
    };

    private final String goalQuote;

    private Goal(String goalQuote) {
        this.goalQuote = goalQuote;
    }

    public String getGoalQuote() {
        return this.goalQuote;
    }
    
    public abstract boolean isAchieved(ArrayList<Entity> entityList);
}
