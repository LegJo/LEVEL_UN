package game.entity;

import java.util.ArrayList;
import game.map.Coord;

public class EntityGroup {
	private ArrayList<Entity> entityList;
	public EntityGroup(Entity entityTemplate, Coord startCoord, Coord endCoord) {
		this.entityList = new ArrayList<Entity>();
		for (int i = startCoord.getX(); i <= endCoord.getX(); i++) {
			for (int j = startCoord.getY(); j <= endCoord.getY(); j++) {
				Entity entity = entityTemplate.copy(new Coord(i,j));
				entityList.add(entity);
			}
		}
	}
	public ArrayList<Entity> getEntityList() {
		return entityList;
	}

}
