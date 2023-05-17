package game.texture;

import java.util.ArrayList;
import game.map.Coord;

public class BackgroundGroup {
		private ArrayList<Background> backgroundList;
		private ArrayList<Coord> backgroundCoord;

		public BackgroundGroup(BackgroundType backgroundType, Coord startCoord, Coord endCoord) {
			this.backgroundList = new ArrayList<Background>();
			this.backgroundCoord = new ArrayList<Coord>();
			for (int i = startCoord.getX(); i <= endCoord.getX(); i++) {
				for (int j = startCoord.getY(); j <= endCoord.getY(); j++) {
					backgroundList.add(new Background(backgroundType));
					backgroundCoord.add(new Coord(i, j));
				}
			}
		}
		
		public ArrayList<Background> getBackgroundList() {
			return backgroundList;
		}

		public ArrayList<Coord> getBackgroundCoord() {
			return backgroundCoord;
		}
	}