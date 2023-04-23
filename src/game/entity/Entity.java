package game.entity;
import game.Coord;
import game.Direction;
import game.entity.livingEntity.LivingEntity;
import game.level.map.Zone;
import game.texture.Texture;

public abstract class Entity {
    protected Coord coord; 
    protected Texture currentTexture;
    
    
	public Entity(Coord coord, Texture texture) {
        this.coord = coord;
        this.currentTexture = texture;
    }

    public Coord getCoord() {
        return coord;
    }
    
    public Texture getTexture() {
		return currentTexture;
	}
    
    /**
	 * {@summary} function to resolve the collision when an Entity collide with this
	 * @param entity that is colliding with this
	 * @return return a boolean that indicate if the entity can move on the entity he collide or not
	 */
    abstract public void resolvCollision(Entity entity, Direction direction);
    
    public boolean willStayInZone(Zone zone, Direction direction) {
		switch(direction.name()) {
		case "DOWN": 
			return(this.getCoord().getY() < zone.getHeight()-1);
		case "UP": 
			return(this.getCoord().getY() > 0);
		case "LEFT": 
			return(this.getCoord().getX() > 0);
		case "RIGHT": 
			return (this.getCoord().getX() < zone.getWidth()-1);
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction.name());
		}
	}
}
