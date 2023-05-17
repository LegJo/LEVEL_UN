package game.entity.livingEntity;

import game.GameConstants;
import game.entity.Entity;
import game.entity.inertEntity.item.Apple;
import game.entity.livingEntity.enemy.Enemy;
import game.map.Coord;
import game.map.Direction;
import game.scene.hud.HealthBar;
import game.scene.hud.Inventory;
import game.texture.Texture;

public class Player extends LivingEntity {
	private Inventory inventory;
	private HealthBar healthBar;
	
	public Player(Coord coord, int health) {
		super(
			coord,
			new Texture(GameConstants.PLAYER_MOV_IMGPATH[0]),
			health,
			100 
		);
		String[] movImgPath = GameConstants.PLAYER_MOV_IMGPATH;
		for (int i = 0; i < movImgPath.length; i++) {
			this.movementTexture[i] = new Texture(movImgPath[i]);
		}
		this.inventory = new Inventory(this);
		this.healthBar= new HealthBar(health);
	}
	
	@Override
	public String toString() {
		return "PLAYER";
	}	
	
	@Override
	public void resolvCollision(Entity entity, Direction direction) {
		if(entity instanceof Enemy) {
			Enemy enemy = (Enemy) entity;
			this.takeDamage(enemy.getPower(), direction);
		}
	}

	public Inventory getInventory() {
		return inventory;
	}
	
	public HealthBar getHealthBar() {
		return healthBar;
	}

	@Override
	public void setHealth(int health) {
		super.setHealth(health);
		this.getHealthBar().updateHealthIn(getHealth());
	}

	@Override
	public Entity copy(Coord coord) {
		return this;
	}
}
