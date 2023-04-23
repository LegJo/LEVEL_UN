package game.level;

public enum GoalType {
	//ajouter ici définition d'un type d'objectif et la quote :
	//ajouter peut etre un moyen de définir plus précisement le goal
	KILL("Kill all Enemies");
	
	private final String goalQuote;
	
	private GoalType(String goalQuote) {
		this.goalQuote = goalQuote;
	}
	
	public String getGoalQuote() {
		return this.goalQuote;
	}
}
