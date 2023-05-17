package game.level;

import java.util.Arrays;
import java.util.ArrayList;

import game.texture.*;
import game.entity.*;
import game.entity.inertEntity.item.*;
import game.entity.inertEntity.obstacle.*;
import game.entity.livingEntity.*;
import game.entity.livingEntity.enemy.*;
import game.entity.livingEntity.npc.*;
import game.map.Coord;
import game.map.Zone;

public enum Level {
	LEVEL_TEST(
	    new Zone(
	        52, 28, // Largeur et hauteur de la zone
	        new ArrayList<BackgroundGroup>(
	    	Arrays.asList(
	    		new BackgroundGroup(BackgroundType.GRASS, new Coord(0,0), new Coord(51,27)),
	    		//mare
	    		new BackgroundGroup(BackgroundType.WATER1, new Coord(3,20), new Coord(5,22)),
	    		new BackgroundGroup(BackgroundType.WATER1_C_UL,  new Coord(2,19), new Coord(2,19)),
	    		new BackgroundGroup(BackgroundType.WATER1_C_DL,  new Coord(2,23), new Coord(2,23)),
	    		new BackgroundGroup(BackgroundType.WATER1_C_UR,  new Coord(6,19), new Coord(6,19)),
	    		new BackgroundGroup(BackgroundType.WATER1_C_DR,  new Coord(6,23), new Coord(6,23)),
	    		new BackgroundGroup(BackgroundType.WATER1_B_L,  new Coord(2,20), new Coord(2,22)),
	    		new BackgroundGroup(BackgroundType.WATER1_B_R,  new Coord(6,20), new Coord(6,22)),
	    		new BackgroundGroup(BackgroundType.WATER1_B_U,  new Coord(3,19), new Coord(5,19)),
	    		new BackgroundGroup(BackgroundType.WATER1_B_D,  new Coord(3,23), new Coord(5,23))

	    	)),
	        new ArrayList<EntityGroup>(
	        Arrays.asList(
	        	//0,0 -> 6,6 
	        	new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(0,0), new Coord(0,6)),
	        	new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(1,2), new Coord(1,6)),
	        	new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(0,0), new Coord(6,0)),
	        	new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(2,6), new Coord(6,6)),
	        	new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(4,5), new Coord(6,5)),
	        	new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(5,4), new Coord(6,4)),
	        	new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(5,1), new Coord(6,1)),
	        	new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(5,3), new Coord(6,4)),
	        	new EntityGroup(new Rock(new Coord(-1,-1), "gray", 1), new Coord(24,11), new Coord(29,11)),
	        	new EntityGroup(new Rock(new Coord(-1,-1), "gray", 2), new Coord(23,11), new Coord(23,17)),
	        	new EntityGroup(new Rock(new Coord(-1,-1), "gray", 1), new Coord(24,17), new Coord(29,17)),
	        	new EntityGroup(new Rock(new Coord(-1,-1), "gray", 1), new Coord(29,12), new Coord(29,16)),
	        	new EntityGroup(new Tree(new Coord(-1,-1), 2), new Coord(10,5), new Coord(12,6)),
        	    new EntityGroup(new Tree(new Coord(-1,-1), 2), new Coord(10,8), new Coord(12,9)),
        	    new EntityGroup(new Rock(new Coord(-1,-1), "brown", 2), new Coord(15,5), new Coord(17,6)),
        	    new EntityGroup(new Rock(new Coord(-1,-1), "brown", 3), new Coord(15,8), new Coord(17,10)),
        	    new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(3,2), new Coord(3,3)),
        	    new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(7,7), new Coord(7,8)),
        	    new EntityGroup(new Rock(new Coord(-1,-1), "gray", 1), new Coord(20,20), new Coord(20,20)),
        	    new EntityGroup(new Rock(new Coord(-1,-1), "gray", 3), new Coord(22,22), new Coord(24,24)),
        	    new EntityGroup(new Tree(new Coord(-1,-1), 2), new Coord(31,12), new Coord(32,14)),
        	    new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(35,20), new Coord(36,21)),
        	    new EntityGroup(new Rock(new Coord(-1,-1), "brown", 1), new Coord(40,10), new Coord(41,11)),
	        	new EntityGroup(new Rock(new Coord(-1,-1), "brown", 2), new Coord(45,15), new Coord(47,17)),
	        	new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(50,0), new Coord(51,4)),
	        	new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(50,10), new Coord(50,11)),
	        	new EntityGroup(new Rock(new Coord(-1,-1), "gray", 1), new Coord(50,12), new Coord(51,16)),
	        	new EntityGroup(new Rock(new Coord(-1,-1), "gray", 2), new Coord(46,26), new Coord(46,27)),
	        	new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(9, 3), new Coord(9, 3)),
        	    new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(13, 8), new Coord(13, 8)),
        	    new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(17, 13), new Coord(17, 13)),
        	    new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(21, 18), new Coord(21, 18)),
        	    new EntityGroup(new Tree(new Coord(-1,-1), 1), new Coord(25, 23), new Coord(25, 23)),
        	    new EntityGroup(new Rock(new Coord(-1,-1), "gray", 1), new Coord(7, 11), new Coord(7, 11)),
        	    new EntityGroup(new Rock(new Coord(-1,-1), "gray", 1), new Coord(11, 16), new Coord(11, 16)),
        	    new EntityGroup(new Rock(new Coord(-1,-1), "gray", 1), new Coord(15, 21), new Coord(15, 21)),
        	    new EntityGroup(new Rock(new Coord(-1,-1), "gray", 1), new Coord(19, 26), new Coord(19, 26)),
        	    // Labyrinthe
        	    new EntityGroup(new Rock(new Coord(-1,-1), "brown", 1), new Coord(0, 17), new Coord(0, 27)),
        	    new EntityGroup(new Rock(new Coord(-1,-1), "brown", 2), new Coord(1, 27), new Coord(14, 27)),
        	    new EntityGroup(new Rock(new Coord(-1,-1), "brown", 3), new Coord(1, 17), new Coord(14, 17)),
        	    new EntityGroup(new Rock(new Coord(-1,-1), "brown", 1), new Coord(15, 17), new Coord(15, 27))
	        )),
	        new ArrayList<Entity>(
	        Arrays.asList(
                new Player(new Coord(26, 14), 80), 
                //Trees
                //Rock
                new Rock(new Coord(24, 12), "gray", 3),
                new Rock(new Coord(28, 12), "gray", 3),
                new Rock(new Coord(24, 16), "gray", 1),
                new Rock(new Coord(28, 16), "gray", 2),
                //Npc
                new PinkGirl(new Coord(17, 17)),
                
                //Enemy
                new Moblin(new Coord(10, 10), "blue", 75),
                //Item
                new Sword(new Coord(11, 12)),
                new Shield(new Coord(1, 1)), 
                new Apple(new Coord(7,3)), 
                new GoldenApple(new Coord(24,14)),
                new Axe(new Coord(7,4)),  
                new Pickaxe(new Coord(28,14))
			))
		),
		Goal.DESTROY_ALL_DESTROYABLE
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
