/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public class TeamCollectionClass implements TeamCollection{
	
	//variaveis de instancia
	private Team[] teams;
	private int teamSize;
	private int currentTeamSize;
	
	/**
	 * Construtor
	 * @param teamNumber - inteiro, numero maximo de teams possivel
	 * @pre teamNumber != null
	 */
	public TeamCollectionClass(int teamNumber) {
		this.teams = new Team[teamNumber];
		this.teamSize = 0;
		this.currentTeamSize = 0;
	}
	
    @Override
	public void addTeam(String teamName) {
		teams[teamSize++] = new TeamClass(teamName);
		currentTeamSize++;
	}
	
    @Override
	public boolean teamExists(String teamName) {
		if(teamSize == 0) {
			return false;
		}
		else {
			return searchIndexTeam(teamName) >= 0;
		}
	}
	
    @Override
	public TeamIterator teamIterator() {
		return new FilteredTeamIteratorClass(teams, teamSize);
	}
	
    @Override
	public int getTeamBunkerSize(int turn){
		return teams[turn].getTeamBunkerSize();
	}

    @Override
	public int getTeamPlayerSize(int turn){
		return teams[turn].getTeamPlayerSize();
	}

    @Override
	public int getTeamSize() {
		return teamSize;
	}
	
    @Override
	public int getCurrentTeamSize() {
		return currentTeamSize;
	}
	
    @Override
	public String getTeamName(int turn) {
		return teams[turn].getTeamName();
	}
	
    @Override
	public void incrementTeamPlayerSize(int turn) {
		teams[turn].incrementTeamPlayerSize();
	}

    @Override
	public void decreaceTeamPlayerSize(String teamName) {
		teams[searchIndexTeam(teamName)].decreaceTeamPlayerSize();
	}

    @Override
	public void incrementTeamBunkerSize(int turn) {
		teams[turn].incrementTeamBunkerSize();
	}

    @Override
	public void decreaceTeamBunkerSize(String teamName) {
		teams[searchIndexTeam(teamName)].decreaceTeamBunkerSize();
	}

    @Override
	public int teamTurn(String teamName) {
		return searchIndexTeam(teamName);
	}

    @Override
	public boolean isTeamDead(int idx) {
		return teams[idx].teamIsDead();
	}

    @Override
	public void updateDeadTeams() {
		int teamsDead = 0;
		for (int i = 0; i < teamSize; i++) {
			boolean isDead = teams[i].teamIsDead();
			if (isDead) {
				teamsDead++;
			}
		}
		currentTeamSize = teamSize - teamsDead;
	}

    @Override
	public boolean isGameOver() {
		return currentTeamSize == 1;
	}

    @Override
	public String getWinner() {
		int i = 0;
		while (teams[i].teamIsDead() && i < teamSize) {
			i++;
		}
		return teams[i].getTeamName();
	}
	
    /**
     * Determina e retorna o index do team no array teams de acordo com o teamName
     * @param teamName - nome do team a incontrar o index
     * @pre teamName != null
     * @return index do team com esse nome, caso não exista um team com esse noma retorna -1
     */
    private int searchIndexTeam(String teamName) {
        int i = 0;
        int result = -1;
        boolean found = false;
        while (i < teamSize && !found)
            if (teams[i].getTeamName().equals(teamName))
                found = true;
            else
                i++;
        if (found) result = i;
        return result;
    }
}
