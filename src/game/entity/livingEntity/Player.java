package game.entity.livingEntity;

import game.Coord;
import game.Direction;
import game.GameConstants;
import game.entity.Entity;
import game.entity.inertEntity.item.Inventory;
import game.entity.livingEntity.enemy.Enemy;
import game.level.map.Zone;
import game.texture.Texture;

public class Player extends LivingEntity {
	private Inventory inventory;
	
	public Player(Coord coord, int speed) {
		super(
			coord,
			new Texture(GameConstants.PLAYER_MOV_IMGPATH[0]),
			GameConstants.PLAYER_HP,
			speed
		);
		String[] movImgPath = GameConstants.PLAYER_MOV_IMGPATH;
		for (int i = 0; i < movImgPath.length; i++) {
			this.movementTexture[i] = new Texture(movImgPath[i]);
		}
		this.inventory = new Inventory();
	}
	
	@Override
	public String toString() {
		return "PLAYER";
	}
	
	@Override
	public void resolvCollision(Entity entity, Direction direction, Zone zone) {
		if(entity instanceof Enemy) {
			Enemy enemy = (Enemy) entity;
//			if(Math.random()*10 < enemy.getHustle()) {
//				enemy.counterAttack(this, direction, zone);
//			}
		}
	}

	public Inventory getInventory() {
		return inventory;
	}

}
