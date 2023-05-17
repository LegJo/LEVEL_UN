package game.entity.inertEntity.item;

import game.GameConstants;
import game.entity.Entity;
import game.entity.livingEntity.LivingEntity;
import game.map.Coord;
import game.map.Direction;
import game.scene.SceneUtils;
import game.scene.hud.Inventory;
import game.texture.Texture;
import game.entity.livingEntity.Player;
import javafx.scene.image.ImageView;

public class Shield extends Item {
	
	public Shield(Coord coord) {
		super(coord, new Texture(GameConstants.SHIELD_IMGPATH), 0);
	}

	@Override
	public void action(LivingEntity livEntity, Direction direction) {
		System.out.println(this + " used by " + livEntity);
		if(livEntity instanceof Player) {
			Player player = (Player) livEntity;
			Inventory inv = player.getInventory();
			inv.removeAndDropItem(inv.getItemIndex(this));
		}
	}
	
	@Override
	public void dropTo(Coord dropCoord) {
		ImageView imgView = this.getTexture().getImgView();
		SceneUtils.gridPane_Entity.add(imgView, 0, 0);
		imgView.setTranslateX(dropCoord.getX()*GameConstants.TEXTURE_SIZE);
		imgView.setTranslateY(dropCoord.getY()*GameConstants.TEXTURE_SIZE);
		this.setCoord(dropCoord);
		imgView.toFront();
	}
	
	@Override
	public String toString() {
		return "SHIELD";
	}
	
	@Override
	public Entity copy(Coord coord) {
		return new Shield(coord);
	}
}
