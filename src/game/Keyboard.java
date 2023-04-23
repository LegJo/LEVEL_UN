package game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

import game.entity.inertEntity.item.Inventory;
import game.entity.inertEntity.item.Item;
import game.entity.livingEntity.Player;
import game.level.map.Zone;

public class Keyboard {
    private Scene scene;
    private List<EventHandler<KeyEvent>> handlers;

    public Keyboard(Scene scene) {
        this.scene = scene;
        this.handlers = new ArrayList<>();
        addListeners();
    }

    public void addHandler(EventHandler<KeyEvent> handler) {
        handlers.add(handler);
    }

    public void removeHandler(EventHandler<KeyEvent> handler) {
        handlers.remove(handler);
    }

    private void addListeners() {
        scene.setOnKeyPressed(event -> {
            for (EventHandler<KeyEvent> handler:handlers) {
                handler.handle(event);
            }
        });
    }
    
    public void clearHandlers() {
        this.handlers.clear();
    }
    
    public void startArrowHandler(Zone zone) {
    	EventHandler<KeyEvent> arrowHandler = new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        KeyCode code = event.getCode();
		        Player player = zone.getPlayer();
		        if (code == KeyCode.UP || code == KeyCode.Z) {
		            if(!(player.isInMovement() || player.isInAction())) {
		            	player.move(Direction.UP, zone);		
		            }    
		        } else if (code == KeyCode.LEFT || code == KeyCode.Q) {
		        	if(!(player.isInMovement() || player.isInAction())) {
		            	player.move(Direction.LEFT, zone);		
		            }  
		        } else if (code == KeyCode.DOWN || code == KeyCode.S) {
		        	if(!(player.isInMovement() || player.isInAction())) {
		            	player.move(Direction.DOWN, zone);		
		            } 
		        } else if (code == KeyCode.RIGHT || code == KeyCode.D) {
		        	if(!(player.isInMovement() || player.isInAction())) {
		            	player.move(Direction.RIGHT, zone);		
		            } 
		        }
		    }
		};
		this.addHandler(arrowHandler);
    }
    
    public void startActionHandler(Zone zone) {
    	EventHandler<KeyEvent> actionHandler = new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        KeyCode code = event.getCode();
		        Player player = zone.getPlayer();
		        Inventory inventory = player.getInventory();
		        if(inventory.getItemCount() != 0) {
			        if (code == KeyCode.SPACE) {
			            if(!(player.isInMovement() || player.isInAction())) {
			            	Item selectedItem = inventory.getItem(inventory.getSelectedItemIndex());
			            	selectedItem.action(player, player.getDirection(), zone);	
			            }    
			        } else if(code == KeyCode.NUMPAD1) {
			        	inventory.setSelectedItemIndex(0);
			        }  else if(code == KeyCode.NUMPAD2) {
			        	inventory.setSelectedItemIndex(1);
			        }  else if(code == KeyCode.NUMPAD3) {
			        	inventory.setSelectedItemIndex(2);
			        }  else if(code == KeyCode.NUMPAD4) {
			        	inventory.setSelectedItemIndex(3);
			        }  else if(code == KeyCode.NUMPAD5) {
			        	inventory.setSelectedItemIndex(4);
			        }  else if(code == KeyCode.NUMPAD6) {
			        	inventory.setSelectedItemIndex(5);
			        }  else if(code == KeyCode.NUMPAD7) {
			        	inventory.setSelectedItemIndex(6);
			        }  else if(code == KeyCode.NUMPAD8) {
			        	inventory.setSelectedItemIndex(7);
			        }  else if(code == KeyCode.NUMPAD9) {
			        	inventory.setSelectedItemIndex(8);
			        }
		        }
		    }
		};
		this.addHandler(actionHandler);
    }
}

