package game.level;

public enum GoalType {
	//ajouter ici d�finition d'un type d'objectif et la quote :
	//ajouter peut etre un moyen de d�finir plus pr�cisement le goal
	KILL("Kill all Enemies");
	
	private final String goalQuote;
	
	private GoalType(String goalQuote) {
		this.goalQuote = goalQuote;
	}
	
	public String getGoalQuote() {
		return this.goalQuote;
	}
}
