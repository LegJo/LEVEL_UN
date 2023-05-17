package game.map;

import java.util.ArrayList;
import game.entity.*;
import game.entity.inertEntity.item.Item;
import game.entity.inertEntity.item.Usable;
import game.entity.inertEntity.obstacle.Destroyable;
import game.entity.livingEntity.LivingEntity;
import game.entity.livingEntity.Player;
import game.texture.BackgroundGroup;
import game.texture.BackgroundType;

public class Zone {
	private Player player;
	private ArrayList<Entity> entityList;
	private ArrayList<Entity> entityModified;
	private int height;
	private int width;
	private Box[][] layout;

	public Zone(int width, int height, ArrayList<BackgroundGroup> groupBgList, ArrayList<EntityGroup> groupEntityList , ArrayList<Entity> singleEntityList) {
		this.height = height;
		this.width = width;
		this.layout = new Box[width][height];
		this.entityList = new ArrayList<>();
		this.entityModified = new ArrayList<>();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				this.layout[x][y] = new Box(null, BackgroundType.DEFAULT);
			}
		}
		
		groupBgList.forEach(backgroundGroup -> {
			backgroundGroup.getBackgroundList().forEach(bg -> {
				Coord bgCoord = backgroundGroup.getBackgroundCoord().get(backgroundGroup.getBackgroundList().indexOf(bg));
				this.layout[bgCoord.getX()][bgCoord.getY()].setBackground(bg);
			});
		});
		
		groupEntityList.forEach(entityGroup -> {
			entityGroup.getEntityList().forEach(entity -> {
				this.layout[entity.getCoord().getX()][entity.getCoord().getY()].setEntity(entity);
				entityList.add(entity);
			});
		});
		
		singleEntityList.forEach(entity -> {
			this.layout[entity.getCoord().getX()][entity.getCoord().getY()].setEntity(entity);
			entityList.add(entity);
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
		if(x >= 0 && y >= 0 && x < getWidth() && y < getHeight())
			return this.getLayout()[x][y];
		else
			return Box.emptyBox();
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
		this.getBox(x, y).setBackground(bgType);
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Entity> getEntityList() {
		return entityList;
	}
	
	public Coord getNearestEmptyBox(Coord coord) {
	    int row = coord.getX();
	    int col = coord.getY();
	    // Recherche des boîtes vides dans l'ordre de proximité
	    for (int i = -1; i <= 1; i++) {
	        for (int j = -1; j <= 1; j++) {
	            int r = row + i;
	            int c = col + j;
	            if (r >= 0 && r < getHeight() && c >= 0 && c < getWidth() && getBox(r,c).isEmpty()) {
	                return new Coord(r, c);
	            }
	        }
	    }
	    for (int d = 2; d >= 1; d--) {
	        for (int i = -d; i <= d; i++) {
	            for (int j = -d; j <= d; j++) {
	                int r = row + i;
	                int c = col + j;
	                if (r >= 0 && r < getHeight() && c >= 0 && c < getWidth() && getBox(r,c).isEmpty()) {
	                    return new Coord(r, c);
	                }
	            }
	        }
	    }
	    return coord;
	}

	
	public void addEntityModified(Entity entity) {
		entityModified.add(entity);
	}
	 
	public void clearEntityModified() {
		entityModified.clear();
	}
	
	/**
	 * {@summary} update entities on the layout of the zone each frame 
	 */
	public void updateLayout() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (!this.getLayout()[x][y].isEmpty()) {
					this.getLayout()[x][y].remEntity();;
				}
			}
		}
		
		entityList.forEach(
		entity -> {
			boolean toPutInLayout = true;
			
			if(entity instanceof Item) {
				Item item = (Item) entity;
				if(item.isInInventory())
					toPutInLayout = false;
			} 
			
			if(entity instanceof Usable) {
				Usable usItem = (Usable) entity;
				if(usItem.hasBeenUsed())
					toPutInLayout = false;
			} 			
			
			if(entity instanceof LivingEntity) {
				LivingEntity livEntity = (LivingEntity) entity;
				if(livEntity.isDead()) {
					toPutInLayout = false;
				}
			} 
			
			if(entity instanceof Destroyable) {
				Destroyable destroyable = (Destroyable) entity;
				if(destroyable.isDestroyed()) {
					toPutInLayout = false;
				}
			} 
			
			if(toPutInLayout) {
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
