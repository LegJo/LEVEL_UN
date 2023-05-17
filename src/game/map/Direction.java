package game.map;

import java.util.Random;

public enum Direction {
    LEFT,
    RIGHT,
    DOWN,
    UP;
	
	public Direction getOpposite() {
		switch(this.name()) {
			case "LEFT": return Direction.RIGHT;
			case "RIGHT": return Direction.LEFT;
			case "UP": return Direction.DOWN;
			case "DOWN": return Direction.UP;
		}
		return null;
	}

	public static Direction getRandomDirection() {
		Random random = new Random();
		return Direction.values()[random.nextInt(Direction.values().length)];
	}
}