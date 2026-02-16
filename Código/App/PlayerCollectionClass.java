/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public class PlayerCollectionClass implements PlayerCollection{
	
	//variaveis de instancia
	private Player[] players;
	private int playerSize;
	
	/**
	 * Construtor
	 * @param playerNumber - inteiro, numero maximo de players possivel
	 * @pre playerNumber != null
	 */
	public PlayerCollectionClass(int playerNumber) {
		this.players = new Player[playerNumber];
		this.playerSize = 0;
	}
	
	@Override
	public void addPlayer(String type, int x, int y, String team) {
		if(type.equals(Game.BLUE)) {
			players[playerSize++] = new BluePlayerClass(x, y, team);
		}
		else {
			if(type.equals(Game.GREEN)) {
				players[playerSize++] = new GreenPlayerClass(x, y, team);
			}
			else {
				players[playerSize++] = new RedPlayerClass(x, y, team);
			}
		}
	}
	
    @Override
	public PlayerIterator teamPlayerIterator(String teamName){
		return new FilteredPlayerIteratorClass(players, playerSize, teamName);
	}

    @Override
	public Player getLastCreatedPlayer() {
		return players[playerSize - 1];
	}

    @Override
	public Player getPlayer(int x, int y) {
		return players[searchIndexPlayer(x, y)];
	}
	
    /**
     * Determina e retorna o index do player no array playesr de acordo com o x e o y
     * @param x - inteiro, posição do player no eixo do x
     * @param y - inteiro, posição do player no eixo do y
     * @pre x != null && y != null
     * @return index do player com essas cordenadas, caso não exista um player com essas cordenadas retorna -1
     */
	private int searchIndexPlayer(int x, int y) {
		int i = 0;
		int result = -1;
		boolean found = false;
		while (i < playerSize && !found)
			if (players[i].getX() == x && players[i].getY() == y && !players[i].isPlayerDead()) {
				found = true;
			}
			else {
				i++;
			}
		if (found) result = i;
		return result;
	}
}
