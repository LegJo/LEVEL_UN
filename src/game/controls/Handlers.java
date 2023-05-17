package game.controls;

import game.Game;
import game.entity.Entity;
import game.entity.inertEntity.item.Item;
import game.entity.livingEntity.Player;
import game.entity.livingEntity.npc.Npc;
import game.map.Direction;
import game.map.Zone;
import game.scene.SceneUtils;
import game.scene.hud.Inventory;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Utility class that provides various event handlers for game interactions.
 */
public class Handlers {
	/**
     * Returns an event handler for arrow key inputs to handle player movement.
     * @return The arrow key event handler.
     */
	public static EventHandler<KeyEvent> arrowHandler() {
    	EventHandler<KeyEvent> arrowHandler = new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        KeyCode code = event.getCode();
		        Player player = Game.getZone().getPlayer();
		        if (code == KeyCode.UP || code == KeyCode.Z) {
		            if(player.canMove()) {
		            	player.move(Direction.UP);		
		            }    
		        } else if (code == KeyCode.LEFT || code == KeyCode.Q) {
		        	if(player.canMove()) {
		            	player.move(Direction.LEFT);		
		            }  
		        } else if (code == KeyCode.DOWN || code == KeyCode.S) {
		        	if(player.canMove()) {
		            	player.move(Direction.DOWN);		
		            } 
		        } else if (code == KeyCode.RIGHT || code == KeyCode.D) {
		        	if(player.canMove()) {
		            	player.move(Direction.RIGHT);		
		            } 
		        }
		    }
		};
		return arrowHandler;
    }
    
	/**
     * Returns an event handler for action key inputs to handle player actions.
     * @return The action key event handler.
     */
    public static EventHandler<KeyEvent> actionHandler() {
    	EventHandler<KeyEvent> actionHandler = new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        KeyCode code = event.getCode();
		        Player player = Game.getZone().getPlayer();
		        Inventory inventory = player.getInventory();
		        if(inventory.getItemCount() != 0) {
			        if (code == KeyCode.SPACE) {
			            if(player.canMove()) {
			            	Item selectedItem = inventory.getItem(inventory.getSelectedItemIndex());
			            	selectedItem.action(player, player.getDirection());	
			            }    
			        } else if(code == KeyCode.NUMPAD1 || code == KeyCode.DIGIT1) {
			        	inventory.setSelectedItemIndex(0);
			        }  else if(code == KeyCode.NUMPAD2 || code == KeyCode.DIGIT2) {
			        	inventory.setSelectedItemIndex(1);
			        }  else if(code == KeyCode.NUMPAD3 || code == KeyCode.DIGIT3) {
			        	inventory.setSelectedItemIndex(2);
			        }  else if(code == KeyCode.NUMPAD4 || code == KeyCode.DIGIT4) {
			        	inventory.setSelectedItemIndex(3);
			        }  else if(code == KeyCode.NUMPAD5 || code == KeyCode.DIGIT5) {
			        	inventory.setSelectedItemIndex(4);
			        }  else if(code == KeyCode.NUMPAD6 || code == KeyCode.DIGIT6) {
			        	inventory.setSelectedItemIndex(5);
			        }  else if(code == KeyCode.NUMPAD7 || code == KeyCode.DIGIT7) {
			        	inventory.setSelectedItemIndex(6);
			        }  else if(code == KeyCode.NUMPAD8 || code == KeyCode.DIGIT8) {
			        	inventory.setSelectedItemIndex(7);
			        }  else if(code == KeyCode.NUMPAD9 || code == KeyCode.DIGIT9) {
			        	inventory.setSelectedItemIndex(8);
			        }
		        }
		    }
		};
		return actionHandler;
    }
    
    /**
     * Returns an event handler for the run key input to toggle player running.
     * @return The run key event handler.
     */
    public static EventHandler<KeyEvent> toggleRunHandler() {
    	EventHandler<KeyEvent> runHandler = new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        KeyCode code = event.getCode();
		        Player player = Game.getZone().getPlayer();
		        if (code == KeyCode.SHIFT) {
		            if(!player.isInRun()) {
		            	player.setInRun(true);
		            	System.out.println(player + " is Running");
		            } else {
		            	player.setInRun(false);
		            	System.out.println(player + " is Walking");
		            }
		        }
		    }   
		};
		return runHandler;
    }
    
    /**
     * Returns an event handler for the goal key input to toggle the goal box display.
     * @return The goal key event handler.
     */
    public static EventHandler<KeyEvent> toggleHudHandler() {
    	EventHandler<KeyEvent> goalHandler = new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        KeyCode code = event.getCode();
		        if (code == KeyCode.G) {
		        	if(!SceneUtils.goalBox.isInAnimation())
		        	{
			            SceneUtils.goalBox.toggle();
		        	}
		        } else if (code == KeyCode.I) {
		        	if(!Game.getZone().getPlayer().getInventory().isInAnimation())
		        	{
		        		Game.getZone().getPlayer().getInventory().toggle();
		        	}		        
			    } else if (code == KeyCode.M || code == KeyCode.ESCAPE) {
		        	SceneUtils.echapMenu.toggle();
		        }
		    }   
		};
		return goalHandler;
    }
    
    public static EventHandler<KeyEvent> dropItemHandler() {
    	EventHandler<KeyEvent> goalHandler = new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        KeyCode code = event.getCode();
		        if (code == KeyCode.Y) {
		        	Player player = Game.getZone().getPlayer();
		        	if(player.getInventory().getItemCount() > 0) {
			           Inventory inv = player.getInventory();
			           inv.removeAndDropItem(inv.getSelectedItemIndex());
		        	}
		        }
		    }   
		};
		return goalHandler;
    }
    
    public static EventHandler<KeyEvent> interactHandler() {
    	EventHandler<KeyEvent> interactHandler = new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        KeyCode code = event.getCode();
		        if (code == KeyCode.E) {
			        Zone zone = Game.getZone();
		        	Player player = zone.getPlayer();
		        	Entity inFrontOfPlayer = zone.getBox(player.getDirection(), player.getCoord()).getEntity();
		        	if(inFrontOfPlayer instanceof Npc) {
		        		Npc npc = (Npc) inFrontOfPlayer;
		        		if(npc.hasMessages()) {
		        			npc.dialogue();
		        		}
		        	} else if(inFrontOfPlayer instanceof Item) {
		    			player.getInventory().addItem((Item) inFrontOfPlayer);

			        }
		        }
		    }   
		};
		return interactHandler;
    }
}
