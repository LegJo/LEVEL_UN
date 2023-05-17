package game.entity.livingEntity.npc;

import game.entity.Entity;
import game.entity.livingEntity.AutoMoveEntity;
import game.entity.livingEntity.LivingEntity;
import game.entity.livingEntity.enemy.Enemy;
import game.map.Coord;
import game.map.Direction;
import game.scene.SceneUtils;
import game.texture.Texture;

public abstract class Npc extends LivingEntity implements AutoMoveEntity {
	private int hustle;
	protected Message[] messages;
	private int currentMessage;
	
	public Npc(Coord coord, Texture texture, int msgCount ,int health, int hustle, int speed) {
		super(coord, texture, health, speed);
		this.hustle = hustle;
		this.messages = new Message[msgCount];
		this.currentMessage = 0;
	}

	@Override
	public int getHustle() {
		return hustle;
	}

	@Override
	public void resolvCollision(Entity entity, Direction direction) {
		if(entity instanceof Enemy) {
			Enemy enemy = (Enemy) entity;
			this.takeDamage(enemy.getPower(), direction);
		}
	}
	
	public void dialogue() {
		if(this.getCurrentMessage() == this.getMessages().length && !SceneUtils.npcBox.isHide()) {
			this.setCurrentMessage(0);
			SceneUtils.npcBox.hide();
		} else {
			if(this.getCurrentMessage() == this.getMessages().length) {
				this.setCurrentMessage(0);
			}
			//hide goal msg box if not hide & unhide npc msg box if hide
			if(!SceneUtils.goalBox.isHide()) {
				SceneUtils.goalBox.hide(0.3);
				if(SceneUtils.npcBox.isHide())
					SceneUtils.npcBox.showDelayed(300);
			} else if(SceneUtils.npcBox.isHide()) {
				SceneUtils.npcBox.show();
			}
			//replace msg in msgBox & play action 
			Message msg = this.getMessages()[getCurrentMessage()];
			if(!msg.isActionDone()) {
				SceneUtils.npcBox.setMessage(msg.getMsgQuote());
				msg.action(this);
			} else {
				SceneUtils.npcBox.setMessage(msg.getActionDoneMsgQuote());
			}
			this.incCurrentMessage();
			//hide if player stop talking
			int curr = this.getCurrentMessage();
			new java.util.Timer().schedule(
	    	        new java.util.TimerTask() {
	    	            @Override
	    	            public void run() {
	    	                if(curr == Npc.this.getCurrentMessage() && !SceneUtils.npcBox.isHide()) {
	    	                	SceneUtils.npcBox.hide();
	    	                }
	    	            }},4000);
		}
	}
		
	
	public Message[] getMessages() {
		return messages;
	}

	@Override
	public String toString() {
		return "NPC" ;
	}

	public int getCurrentMessage() {
		return currentMessage;
	}

	public void setCurrentMessage(int currentMessage) {
		this.currentMessage = currentMessage;
	}
	
	public void incCurrentMessage() {
		this.setCurrentMessage(this.getCurrentMessage()+1);
	}
	
	public boolean hasMessages() {
		return (this.getMessages().length != 0);
	}

}
