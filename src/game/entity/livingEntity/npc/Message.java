package game.entity.livingEntity.npc;

import game.entity.livingEntity.Player;
import game.Game;
import game.entity.inertEntity.item.Apple;
import game.map.Coord;

public enum Message {
	HELLO("Hello Friend !", "Hello again Friend !"),
	GIVE_APPLE("Takes this gift: an apple", "Destroy trees if you want more apples") {
		@Override
		public void action(Npc npc) {
			super.action(npc);
			Player player = Game.getZone().getPlayer();
			Apple apple = new Apple(new Coord(-1,-1));
			player.getInventory().addItem(apple);
		}
	};
	
	private final String msgQuote;
	private final String actionDoneMsgQuote;
	private boolean actionDone;
	
	private Message(String msgQuote, String actionDoneMsgQuote) {
		this.msgQuote = msgQuote;
		this.actionDoneMsgQuote = actionDoneMsgQuote;
		
	}

	public String getMsgQuote() {
		return msgQuote;
	}
	
	protected void action(Npc npc) {
		setActionDone(true);
		return;
	}

	public boolean isActionDone() {
		return actionDone;
	}

	public void setActionDone(boolean actionDone) {
		this.actionDone = actionDone;
	}

	public String getActionDoneMsgQuote() {
		return actionDoneMsgQuote;
	}
}
