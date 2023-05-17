package game;
import java.awt.Toolkit;

/**
 * This class contains constants used in the game.
 */
public class GameConstants {
	// Constants for game settings
	public final static int ONHIT_INVINCIBILITY_DURATION = 1200;
	public final static int FPS = 180;
	public final static int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public final static int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	// Constants for entity animations and textures
	public final static int MOVEMENT_DURATION = 400;
	public final static int ACTION_DURATION = 400;
	
	public final static int TEXTURE_SIZE = 28;
	public final static double TEXTURE_SCALE = 1.75; // TEXTURE_SIZE/16
	public final static double TEXTURE_ACTION_ANIM_SCALE = 1.5; //ACTION_TEXTURE_SIZE/TEXTURE_SIZE
	
	// Constants for inventory and HUD
	public final static int INVENTORY_SIZE = 9;
	
	// Constants for images and textures used in the game
	public final static String INVENTORY_IMGPATH = "file:images/hud/inventory.png";
	
	public final static String MESSAGEBOX_IMGPATH = "file:images/hud/messageBox.png";
	
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
	
	public final static String[] BLUE_MOBLIN_ATTACK_IMGPATH = new String[] {
			"file:images/entity/moblin/moblinB_Down_Attack1.png",
			"file:images/entity/moblin/moblinB_Down_Attack2.png",
			"file:images/entity/moblin/moblinB_Up_Attack1.png",
			"file:images/entity/moblin/moblinB_Up_Attack2.png",
			"file:images/entity/moblin/moblinB_Left_Attack1.png",
			"file:images/entity/moblin/moblinB_Left_Attack2.png",
			"file:images/entity/moblin/moblinB_Right_Attack1.png",
			"file:images/entity/moblin/moblinB_Right_Attack1.png",
		};
	
	public final static String[] ORANGE_MOBLIN_MOV_IMGPATH = new String[] {
			"file:images/entity/moblin/moblinO_Down0.png",
			"file:images/entity/moblin/moblinO_Down1.png",
			"file:images/entity/moblin/moblinO_Down2.png",
			"file:images/entity/moblin/moblinO_Up0.png",
			"file:images/entity/moblin/moblinO_Up1.png",
			"file:images/entity/moblin/moblinO_Up2.png",
			"file:images/entity/moblin/moblinO_Left0.png",
			"file:images/entity/moblin/moblinO_Left1.png",
			"file:images/entity/moblin/moblinO_Left2.png",
			"file:images/entity/moblin/moblinO_Right0.png",
			"file:images/entity/moblin/moblinO_Right1.png",
			"file:images/entity/moblin/moblinO_Right2.png",
		};
	
	public final static String[] ORANGE_MOBLIN_ATTACK_IMGPATH = new String[] {
			"file:images/entity/moblin/moblinO_Down_Attack1.png",
			"file:images/entity/moblin/moblinO_Down_Attack2.png",
			"file:images/entity/moblin/moblinO_Up_Attack1.png",
			"file:images/entity/moblin/moblinO_Up_Attack2.png",
			"file:images/entity/moblin/moblinO_Left_Attack1.png",
			"file:images/entity/moblin/moblinO_Left_Attack2.png",
			"file:images/entity/moblin/moblinO_Right_Attack1.png",
			"file:images/entity/moblin/moblinO_Right_Attack1.png",
		};
	
	public final static String[] PINKGRIL_MOV_IMGPATH = new String[] {
			"file:images/entity/npc1/npc1_Down0.png",
			"file:images/entity/npc1/npc1_Down1.png",
			"file:images/entity/npc1/npc1_Down2.png",
			"file:images/entity/npc1/npc1_Up0.png",
			"file:images/entity/npc1/npc1_Up1.png",
			"file:images/entity/npc1/npc1_Up2.png",
			"file:images/entity/npc1/npc1_Left0.png",
			"file:images/entity/npc1/npc1_Left1.png",
			"file:images/entity/npc1/npc1_Left2.png",
			"file:images/entity/npc1/npc1_Right0.png",
			"file:images/entity/npc1/npc1_Right1.png",
			"file:images/entity/npc1/npc1_Right2.png",
		};
	
	public final static String TREE1_IMGPATH = "file:images/entity/tree/tree1.png";
	public final static String TREE2_IMGPATH = "file:images/entity/tree/tree2.png";
	
	public final static String GRAYROCK1_IMGPATH = "file:images/entity/rock/grayRock1.png";
	public final static String GRAYROCK2_IMGPATH = "file:images/entity/rock/grayRock2.png";
	public final static String GRAYROCK3_IMGPATH = "file:images/entity/rock/grayRock3.png";
	public final static String BROWNROCK1_IMGPATH = "file:images/entity/rock/brownRock1.png";
	public final static String BROWNROCK2_IMGPATH = "file:images/entity/rock/brownRock2.png";
	public final static String BROWNROCK3_IMGPATH = "file:images/entity/rock/brownRock3.png";
	
	public final static String SWORD_IMGPATH = "file:images/entity/item/sword.png";
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
	
	public final static String SHIELD_IMGPATH = "file:images/entity/item/shield.png";
	public final static String AXE_IMGPATH = "file:images/entity/item/axe.png";
	public final static String PICKAXE_IMGPATH = "file:images/entity/item/pickaxe.png";
	public final static String APPLE_IMGPATH = "file:images/entity/item/apple.png";
	public final static String GOLDEN_APPLE_IMGPATH = "file:images/entity/item/golden_apple.png";

	
	public final static String BG_GRASS_IMGPATH = "file:images/background/grass1.png";
	public final static String BG_GRASS_DARKER_IMGPATH = "file:images/background/grass2.png";
	public final static String[] BG_WATER_IMGPATHS = new String[] {
			"file:images/background/Water1.png",
			"file:images/background/Water1_Border_Down.png",
			"file:images/background/Water1_Border_Up.png",
			"file:images/background/Water1_Border_Left.png",
			"file:images/background/Water1_Border_Right.png",
			"file:images/background/Water1_Corner_Down_Left.png",
			"file:images/background/Water1_Corner_Down_Right.png",
			"file:images/background/Water1_Corner_Up_Left.png",
			"file:images/background/Water1_Corner_Up_Right.png"
	};

	
	public final static String[] HEART_IMGPATH = new String[] {
			"file:images/hud/heart/heart_empty.png",
			"file:images/hud/heart/heart_1.png",
			"file:images/hud/heart/heart_2.png",
			"file:images/hud/heart/heart_3.png",
			"file:images/hud/heart/heart_4.png",
			"file:images/hud/heart/heart_5.png",
			"file:images/hud/heart/heart_6.png",
			"file:images/hud/heart/heart_7.png",
			"file:images/hud/heart/heart_8.png",
			"file:images/hud/heart/heart_9.png",
			"file:images/hud/heart/heart_full.png",			
		};
	
	public final static String DEFAULT_IMGPATH = "file:images/default.png";
	
	
	
	
	
}
