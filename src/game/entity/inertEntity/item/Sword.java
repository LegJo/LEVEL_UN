package game.entity.inertEntity.item;

import game.Game;
import game.GameConstants;
import game.entity.Entity;
import game.entity.livingEntity.LivingEntity;
import game.map.Box;
import game.map.Coord;
import game.map.Direction;
import game.map.Zone;
import game.texture.Texture;

public class Sword extends Item {
	private int power;
	
	public Sword(Coord coord) {
		super(coord, new Texture(GameConstants.SWORD_IMGPATH), 8);
		this.isTool = true;
		this.power = 15;
		String[] actionImgPath = GameConstants.SWORD_ACTION_IMGPATH;
		for (int i = 0; i < actionImgPath.length; i++) {
			this.actionTexture[i] = new Texture(actionImgPath[i]);
		}
	}

	@Override
	public void action(LivingEntity livEntity, Direction direction) {
		super.action(livEntity, direction);
    	Zone zone = Game.getZone();
		System.out.println(this + " used by " + livEntity + " in direction " + direction);
		Box targetBox = zone.getBox(direction, livEntity.getCoord());
		if(!targetBox.isEmpty()) {
			Entity entity = targetBox.getEntity();
			if(entity instanceof LivingEntity) {
				LivingEntity target = (LivingEntity) entity;
				target.takeDamage(this.getPower(), direction);
			}
		}
	}

	public int getPower() {
		return power;
	}
	
	@Override
	public String toString() {
		return "SWORD";
	}
	
	@Override
	public Entity copy(Coord coord) {
		return new Sword(coord);
	}
}
