package game.level;

import java.util.ArrayList;
import game.entity.Entity;
import game.entity.inertEntity.item.Item;
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
        	for (Entity entity : entityList) {
                if (entity instanceof Item) {
                	Item item = (Item) entity;
                	if(!item.isInInventory())
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
