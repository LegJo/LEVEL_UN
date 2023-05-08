package game.entity.livingEntity;

import game.GameConstants;
import game.entity.Entity;
import game.entity.livingEntity.enemy.Enemy;
import game.map.Coord;
import game.map.Direction;
import game.map.Zone;
import game.scene.hud.HealthBar;
import game.scene.hud.Inventory;
import game.texture.Texture;

public class Player extends LivingEntity {
	private Inventory inventory;
	private HealthBar healthBar;
	
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
		this.healthBar= new HealthBar(this.getHealth());
	}
	
	@Override
	public String toString() {
		return "PLAYER";
	}
	
	
	
	@Override
	public void resolvCollision(Entity entity, Direction direction, Zone zone) {
		if(entity instanceof Enemy) {
			Enemy enemy = (Enemy) entity;
			this.takeDamage(enemy.getPower(), direction, zone);
		}
	}

	public Inventory getInventory() {
		return inventory;
	}
	
	public HealthBar getHealthBar() {
		return healthBar;
	}

	@Override
	public void takeDamage(int damage, Direction direction, Zone zone) {
		super.takeDamage(damage, direction, zone);
		this.getHealthBar().updateHealthIn(getHealth());
	}

}
