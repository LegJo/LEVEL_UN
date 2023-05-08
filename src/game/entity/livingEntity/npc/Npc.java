package game.entity.livingEntity.npc;

import game.entity.Entity;
import game.entity.livingEntity.AutoMoveEntity;
import game.entity.livingEntity.LivingEntity;
import game.entity.livingEntity.enemy.Enemy;
import game.map.Coord;
import game.map.Direction;
import game.map.Zone;
import game.texture.Texture;

public abstract class Npc extends LivingEntity implements AutoMoveEntity {
	private int hustle;
	
	public Npc(Coord coord, Texture texture, int health, int hustle, int speed) {
		super(coord, texture, health, speed);
		this.hustle = hustle;
	}

	@Override
	public int getHustle() {
		return hustle;
	}

	@Override
	public void resolvCollision(Entity entity, Direction direction, Zone zone) {
		if(entity instanceof Enemy) {
			Enemy enemy = (Enemy) entity;
			this.takeDamage(enemy.getPower(), direction, zone);
		}
	}
	
	@Override
	public String toString() {
		return "NPC" ;
	}

}
