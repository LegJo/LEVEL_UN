package game.map;

import java.util.ArrayList;

import game.entity.*;
import game.entity.inertEntity.item.Item;
import game.entity.livingEntity.Player;
import game.entity.livingEntity.enemy.Enemy;
import game.texture.BackgroundType;

public class Zone {
	private Player player;
	private ArrayList<Entity> entityList;
	private ArrayList<Entity> entityModified;
	private int height;
	private int width;
	private Box[][] layout;

	public Zone(int width, int height, BackgroundType bgType, ArrayList<Entity> entityList) {
		this.height = height;
		this.width = width;
		this.layout = new Box[width][height];
		this.entityList = entityList;
		this.entityModified = new ArrayList<>();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				this.layout[x][y] = new Box(null, bgType);
			}
		}

		entityList.forEach(entity -> {
			this.layout[entity.getCoord().getX()][entity.getCoord().getY()] = new Box(entity, bgType);
			if (entity instanceof Player)
				this.player = (Player) entity;
		});
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Box[][] getLayout() {
		return layout;
	}

	public Box getBox(int x, int y) {
		return this.getLayout()[x][y];
	}
	
	public Box getBox(Coord coord) {
		return getBox(coord.getX(), coord.getY());
	}
	
	public Box getBox(Direction direction, Coord fromCoord) {
		Coord boxCoord = new Coord(fromCoord);
		switch (direction.name()) {
			case "LEFT": boxCoord.addX(-1); break;
			case "RIGHT": boxCoord.addX(1); break;
			case "UP": boxCoord.addY(-1); break;
			case "DOWN": boxCoord.addY(1); break;
			default: throw new IllegalArgumentException("Unexpected value: " + direction.name());
		}
		return this.getBox(boxCoord);
	}

	public void setBox(int x, int y, BackgroundType bgType, Entity entity) {
		this.getBox(x, y).setBackgroundTexture(bgType);
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Entity> getEntityList() {
		return entityList;
	}
	
	public void addEntityModified(Entity entity) {
		entityModified.add(entity);
	}
	 
	public void clearEntityModified() {
		entityModified.clear();
	}

	public void updateLayout() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (!this.getLayout()[x][y].isEmpty()) {
					this.getLayout()[x][y].remEntity();;
				}
			}
		}

		entityList.forEach(entity -> {
			if(entity instanceof Item) {
				Item item = (Item) entity;
				if(!item.isInInventory())
					this.getBox(item.getCoord()).setEntity(item);
			} else if(entity instanceof Enemy) {
				Enemy enemy = (Enemy) entity;
				if(enemy.isDead()) {
					this.getBox(enemy.getCoord()).remEntity();
				} else {
					this.getBox(enemy.getCoord()).setEntity(enemy);
				}
			} else {
				this.getBox(entity.getCoord()).setEntity(entity);
			}
		});
	}

	public ArrayList<Entity> getEntityModified() {
		return entityModified;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    for (int y = 0; y < height; y++) {
	        for (int x = 0; x < width; x++) {
	            sb.append(getBox(x, y).toString());
	        }
	        sb.append("\n");
	    }
	    return sb.toString();
	}
}
