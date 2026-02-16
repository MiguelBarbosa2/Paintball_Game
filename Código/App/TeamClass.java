/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public class TeamClass implements Team{
	
	//variaveis de instancia
	private String teamName;
	private boolean teamIsDead;
	private int teamBunkerSize;
	private int teamPlayerSize;

	/**
	 * Construtor
	 * @param teamName - nome do team
	 * @pre teamName != null
	 */
	public TeamClass(String teamName) {
		this.teamIsDead = false;
		this.teamName = teamName;
		this.teamBunkerSize = 1;
		this.teamPlayerSize = 0;
	}
	
    @Override
	public void incrementTeamBunkerSize() {
		teamBunkerSize++;
	}
	
    @Override
	public void decreaceTeamBunkerSize() {
		teamBunkerSize--;
	}
	
    @Override
	public void incrementTeamPlayerSize() {
		teamPlayerSize++;
	}
	
    @Override
	public void decreaceTeamPlayerSize() {
		teamPlayerSize--;
	}
	
    @Override
	public String getTeamName() {
		return teamName;
	}
	
    @Override
	public int getTeamBunkerSize() {
		return teamBunkerSize;
	}
	
    @Override
	public int getTeamPlayerSize() {
		return teamPlayerSize;
	}
	
    @Override
	public boolean teamIsDead() {
		if(!teamIsDead) {
			if(teamBunkerSize == 0 && teamPlayerSize == 0) {
				teamIsDead = true;
			}
		}
		return teamIsDead;
	}
}
