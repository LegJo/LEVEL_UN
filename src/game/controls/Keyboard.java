package game.controls;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;


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
    
    public void initGameHandlers() {
    	addHandler(Handlers.arrowHandler());
    	addHandler(Handlers.actionHandler());
    	addHandler(Handlers.toggleRunHandler());
    	addHandler(Handlers.toggleHudHandler());
    	addHandler(Handlers.dropItemHandler());
    	addHandler(Handlers.interactHandler());
    }
}

