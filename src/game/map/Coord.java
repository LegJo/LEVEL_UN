package game.map;

import game.GameConstants;

public class Coord {
    private int x;
    private int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Coord(Coord coord) {
        this(coord.getX(), coord.getY());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public void addX(int add) {
    	this.setX(this.getX() + add);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void addY(int add) {
    	this.setY(this.getY() + add);
    }
    
    public void addDirection(Direction direction) {
    	switch (direction.name()) {
			case "DOWN": this.addY(1); break;
			case "UP": this.addY(-1); break;
			case "LEFT": this.addX(-1); break;
			case "RIGHT":this.addX(1); break;
    	}
    }
    
    public void addDirectionScene(Direction direction) {
    	int textureSize = GameConstants.TEXTURE_SIZE;
    	switch (direction.name()) {
			case "DOWN": this.addY(textureSize); break;
			case "UP": this.addY(-textureSize); break;
			case "LEFT": this.addX(-textureSize); break;
			case "RIGHT":this.addX(textureSize); break;
		}
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        
        if (!(obj instanceof Coord)) {
            return false;
        }
        
        Coord other = (Coord) obj;
        return this.x == other.x && this.y == other.y;
    }
    
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
    
    
}