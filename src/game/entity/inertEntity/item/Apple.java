package game.entity.inertEntity.item;

import game.GameConstants;
import game.entity.Entity;
import game.entity.livingEntity.LivingEntity;
import game.map.Coord;
import game.map.Direction;
import game.scene.hud.Inventory;
import game.texture.Texture;
import game.entity.livingEntity.Player;

public class Apple extends Item implements Usable { 
	private int healthGain;
	private boolean used;

	public Apple(Coord coord) {
		super(coord, new Texture(GameConstants.APPLE_IMGPATH), 0);
		this.healthGain = 10;
	}

	@Override
	public void action(LivingEntity livEntity, Direction direction) {
		System.out.println(this + " used by " + livEntity);
		if(livEntity instanceof Player) {
			Player player = (Player) livEntity;
			player.setHealth(player.getHealth() + this.getHealthGain());
			Inventory inv = player.getInventory();
			inv.removeItem(inv.getItemIndex(this));
			this.setUsed(true);
		}
	}

	public int getHealthGain() {
		return healthGain;
	}
	

	public void setHealthGain(int healthGain) {
		this.healthGain = healthGain;
	}
	
	@Override
	public String toString() {
		return "APPLE";
	}

	@Override
	public Entity copy(Coord coord) {
		return new Apple(coord);
	}

	public boolean hasBeenUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
}
