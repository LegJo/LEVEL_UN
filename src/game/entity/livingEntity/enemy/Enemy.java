package game.entity.livingEntity.enemy;

import game.Coord;
import game.Direction;
import game.entity.Entity;
import game.entity.livingEntity.LivingEntity;
import game.entity.livingEntity.AutoMoveEntity;
import game.level.map.Zone;
import game.texture.Texture;
public class Enemy extends LivingEntity implements AutoMoveEntity{
	private final int hustle;
	private final int power;
	
	public Enemy(Coord coord, Texture texture, int health, int hustle, int power, int speed) {
		super(coord, texture, health, speed);
		this.hustle = hustle;
		this.power = power;
	}
	
	boolean attack(LivingEntity target) {
		
		return false;
	}

	public int getHustle() {
		return this.hustle;
	}
	
	public int getPower() {
		return this.power;
	}
	
	@Override
	public void resolvCollision(Entity entity, Direction direction) {
		if( (entity instanceof LivingEntity) && !(entity instanceof Enemy) ) {
			LivingEntity livEntity = (LivingEntity) entity;
			livEntity.takeDamage(this.getPower());
		}
		return;
	}

	

	
}
