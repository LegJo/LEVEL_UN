package game.entity.inertEntity.item;

import game.Coord;
import game.Direction;
import game.GameConstants;
import game.entity.Entity;
import game.entity.livingEntity.LivingEntity;
import game.level.map.Box;
import game.level.map.Zone;
import game.texture.Texture;

public class Sword extends Item {
	private int power;
	
	public Sword(Coord coord, int power) {
		super(coord, new Texture(GameConstants.SWORD_IMGPATH), 8);
		this.power = power;
		String[] actionImgPath = GameConstants.SWORD_ACTION_IMGPATH;
		for (int i = 0; i < actionImgPath.length; i++) {
			this.actionTexture[i] = new Texture(actionImgPath[i]);
		}
	}

	@Override
	public void action(LivingEntity livEntity, Direction direction, Zone zone) {
		super.action(livEntity, direction, zone);
		System.out.println(this + " used by " + livEntity + " in direction " + direction);
		Box targetBox = zone.getBox(direction, livEntity.getCoord());
		if(!targetBox.isEmpty()) {
			Entity entity = targetBox.getEntity();
			if(entity instanceof LivingEntity) {
				LivingEntity target = (LivingEntity) entity;
				target.takeDamage(this.getPower(), direction, zone);
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
	
}
