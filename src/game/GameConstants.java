package game;

public class GameConstants {
	
	public final static int MOVEMENT_DURATION = 400;
	public final static int ACTION_DURATION = 400;
	public final static int ONHIT_INVINCIBILITY_DURATION = 1200;
	public final static int FPS = 90;
	public final static int TEXTURE_SIZE = 28;
	public final static double TEXTURE_SCALE = 1.75; // TEXTURE_SIZE/16
	public final static double TEXTURE_ACTION_ANIM_SCALE = 1.5; //ACTION_TEXTURE_SIZE/TEXTURE_SIZE
	public final static int SCREEN_WIDTH = 1280;
	public final static int SCREEN_HEIGHT = 720;
	
	public final static int INVENTORY_SIZE = 9;
	public final static String INVENTORY_IMGPATH = "file:images/hud/inventory.png";
	
	public final static int PLAYER_HP = 50;
	public final static String[] PLAYER_MOV_IMGPATH = new String[] {
		"file:images/entity/player/player_Down0.png",
		"file:images/entity/player/player_Down1.png",
		"file:images/entity/player/player_Down2.png",
		"file:images/entity/player/player_Up0.png",
		"file:images/entity/player/player_Up1.png",
		"file:images/entity/player/player_Up2.png",
		"file:images/entity/player/player_Left0.png",
		"file:images/entity/player/player_Left1.png",
		"file:images/entity/player/player_Left2.png",
		"file:images/entity/player/player_Right0.png",
		"file:images/entity/player/player_Right1.png",
		"file:images/entity/player/player_Right2.png",
	};
	
	public final static int MOBLIN_HP = 100;
	public final static String[] BLUE_MOBLIN_MOV_IMGPATH = new String[] {
		"file:images/entity/moblin/moblinB_Down0.png",
		"file:images/entity/moblin/moblinB_Down1.png",
		"file:images/entity/moblin/moblinB_Down2.png",
		"file:images/entity/moblin/moblinB_Up0.png",
		"file:images/entity/moblin/moblinB_Up1.png",
		"file:images/entity/moblin/moblinB_Up2.png",
		"file:images/entity/moblin/moblinB_Left0.png",
		"file:images/entity/moblin/moblinB_Left1.png",
		"file:images/entity/moblin/moblinB_Left2.png",
		"file:images/entity/moblin/moblinB_Right0.png",
		"file:images/entity/moblin/moblinB_Right1.png",
		"file:images/entity/moblin/moblinB_Right2.png",
	};
	
	public final static String TREE1_IMGPATH = "file:images/entity/tree/tree1.png";
	public final static String TREE2_IMGPATH = "file:images/entity/tree/tree2.png";
	
	public final static String GRAYROCK1_IMGPATH = "file:images/entity/rock/grayRock1.png";
	public final static String GRAYROCK2_IMGPATH = "file:images/entity/rock/grayRock2.png";
	public final static String GRAYROCK3_IMGPATH = "file:images/entity/rock/grayRock3.png";
	public final static String BROWNROCK1_IMGPATH = "file:images/entity/rock/brownRock1.png";
	public final static String BROWNROCK2_IMGPATH = "file:images/entity/rock/brownRock2.png";
	public final static String BROWNROCK3_IMGPATH = "file:images/entity/rock/brownRock3.png";
	
	public final static String SWORD_IMGPATH = "file:images/entity/sword/sword.png";
	public final static String[] SWORD_ACTION_IMGPATH = new String[] {
			"file:images/entity/player/player_Down_Sword1.png",
			"file:images/entity/player/player_Down_Sword2.png",
			"file:images/entity/player/player_Up_Sword1.png",
			"file:images/entity/player/player_Up_Sword2.png",
			"file:images/entity/player/player_Left_Sword1.png",
			"file:images/entity/player/player_Left_Sword2.png",
			"file:images/entity/player/player_Right_Sword1.png",
			"file:images/entity/player/player_Right_Sword2.png",
		};
	
	public final static String BG_GRASS_IMGPATH = "file:images/background/grass1.png";
	
	public final static String DEFAULT_IMGPATH = "file:images/default.png";
	
	
	
	
	
}
