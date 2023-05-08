package game.level;

import java.util.Arrays;
import java.util.ArrayList;

import game.texture.BackgroundType;
import game.entity.inertEntity.item.Sword;
import game.entity.inertEntity.obstacle.Rock;
import game.entity.inertEntity.obstacle.Tree;
import game.entity.livingEntity.*;
import game.entity.livingEntity.enemy.Moblin;
import game.entity.livingEntity.npc.PinkGirl;
import game.map.Coord;
import game.map.Zone;

public enum Level {
	LEVEL_TEST(
	    new Zone(
	        40, 25, // Largeur et hauteur de la zone
	        BackgroundType.GRASS,
	        new ArrayList<>(
	            Arrays.asList(
	                new Player(new Coord(12, 12), 50), 
	                
	                new Tree(new Coord(5, 8), 1), new Tree(new Coord(6, 8), 1), new Tree(new Coord(5, 9), 1), new Tree(new Coord(6, 9), 1), new Tree(new Coord(5, 10), 1), new Tree(new Coord(6, 10), 1),
	                new Tree(new Coord(18, 21), 2), new Tree(new Coord(19, 21), 2), new Tree(new Coord(18, 22), 2), new Tree(new Coord(19, 22), 2), new Tree(new Coord(18, 23), 2), new Tree(new Coord(19, 23), 2),
	                new Tree(new Coord(24, 5), 1), new Tree(new Coord(24, 6), 1), new Tree(new Coord(24, 7), 1), new Tree(new Coord(24, 8), 1), new Tree(new Coord(25, 5), 1), new Tree(new Coord(25, 6), 1), new Tree(new Coord(25, 7), 1), new Tree(new Coord(25, 8), 1),
	                
	                new Rock(new Coord(2, 15), "gray", 2), new Rock(new Coord(3, 15), "gray", 2), new Rock(new Coord(2, 16), "gray", 2), new Rock(new Coord(3, 16), "gray", 2), new Rock(new Coord(2, 17), "gray", 2), new Rock(new Coord(3, 17), "gray", 2), new Rock(new Coord(2, 18), "gray", 2), new Rock(new Coord(3, 18), "gray", 2), new Rock(new Coord(2, 19), "gray", 2), new Rock(new Coord(3, 19), "gray", 2), new Rock(new Coord(2, 20), "gray", 2), new Rock(new Coord(3, 20), "gray", 2),
	                new Rock(new Coord(8, 11), "brown", 3), new Rock(new Coord(8, 12), "brown", 3), new Rock(new Coord(8, 13), "brown", 3), new Rock(new Coord(8, 14), "brown", 3), new Rock(new Coord(8, 15), "brown", 3), new Rock(new Coord(8, 16), "brown", 3), new Rock(new Coord(8, 17), "brown", 3), new Rock(new Coord(8, 18), "brown", 3), new Rock(new Coord(8, 19), "brown", 3), new Rock(new Coord(8, 20), "brown", 3), new Rock(new Coord(8, 21), "brown", 3),
	                new Rock(new Coord(18, 7), "gray", 2), new Rock(new Coord(0, 0), "gray", 2),
	                
	                new Moblin(new Coord(10, 10), "blue"), new PinkGirl(new Coord(17, 17)),
	                new Sword(new Coord(11, 12), 8), new Sword(new Coord(10, 12), 8), new Sword(new Coord(8, 12), 8),
	                new Sword(new Coord(11, 10), 8), new Sword(new Coord(10, 13), 8), new Sword(new Coord(8, 10), 8),
	                new Sword(new Coord(6, 10), 8), new Sword(new Coord(7, 13), 8), new Sword(new Coord(7, 10), 8), new Sword(new Coord(1, 10), 8)
				)
			)
		),
		Goal.KILL_ALL_ENEMIES
	);
			
	private final Zone zone;
    private final Goal goal;

    private Level(Zone zone, Goal goal) {
        this.zone = zone;
        this.goal = goal;
    }
    
    public Zone getZone() {
  		return zone;
  	}
    
    public boolean isCompleted() {
       return this.getGoal().isAchieved(getZone().getEntityList());
    }

	public Goal getGoal() {
		return goal;
	}
}
