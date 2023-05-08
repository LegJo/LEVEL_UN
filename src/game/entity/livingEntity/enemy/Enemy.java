package game.entity.livingEntity.enemy;

import game.entity.Entity;
import game.entity.livingEntity.LivingEntity;
import game.map.Coord;
import game.map.Direction;
import game.map.Zone;
import game.entity.livingEntity.AutoMoveEntity;
import game.texture.Texture;
public class Enemy extends LivingEntity implements AutoMoveEntity{
	private final int hustle;
	private final int power;
	
	public Enemy(Coord coord, Texture texture, int health, int hustle, int power, int speed) {
		super(coord, texture, health, speed);
		this.hustle = hustle;
		this.power = power;
	}
	
	public void attack(LivingEntity target, Direction direction, Zone zone) {
		this.setDirection(target.getDirection().getOpposite());
		//ajouter animation d'attack 
		if(!target.isInvincible())
			target.takeDamage(this.getPower(), direction, zone);
	}

	public int getHustle() {
		return this.hustle;
	}
	
	public int getPower() {
		return this.power;
	}
	
	@Override
	public void resolvCollision(Entity entity, Direction direction, Zone zone) {
		if( (entity instanceof LivingEntity) && !(entity instanceof Enemy) ) {
			LivingEntity livEntity = (LivingEntity) entity;
			livEntity.takeDamage(getPower(), direction.getOpposite(), zone);
		}
		return;
	}

	

	
}
