/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public class GameClass implements Game{

	//Variáveis de instância
	private TeamCollection tc;
	private BunkerCollection bc;
	private PlayerCollection pc;
	private Map map;
	private int width;
	private int height;
	private int turn;

	/**
	 * Construtor
	 * @param width - o tamanho da dimensão horizontal do mapa.
	 * @param height - o tamanho da dimensão vertical do mapa.
	 * @param teamNumber - número total de equipas.
	 * @param bunkerNumber - número total de Bunkers.
	 * @Pre - width != null && width >= 10 && height != null && height >= 10 &&
	 * teamNumber != null && teamNumber >= 2 && bunkerNumber != null && bunkerNumber >= 2.
	 */
	public GameClass(int width, int height, int teamNumber, int bunkerNumber) {
		this.tc = new TeamCollectionClass(teamNumber);
		this.bc = new BunkerCollectionClass(bunkerNumber);
		this.pc = new PlayerCollectionClass(width*height);
		this.width = width;
		this.height = height;
		this.map = new MapClass(width, height);
		this.turn = 0;
	}

	@Override
	public int getWidth(){
		return width;
	}

	@Override
	public int getHeight(){
		return height;
	}

	@Override
	public void addBunker(int x, int y, int treasure, String bunkerName) {
		bc.addBunker(x, y, treasure, bunkerName);
	}

	@Override
	public void addTeam(String teamName, String bunkerName) {
		bc.setOwner(bunkerName, teamName, 1);
		tc.addTeam(teamName);
	}

	@Override
	public void addPlayer(String type, String bunkerName) {
		switch(type){
		case BLUE -> bc.decreaseTreasure(bunkerName, 2);
		case RED -> bc.decreaseTreasure(bunkerName, 4);
		case GREEN -> bc.decreaseTreasure(bunkerName, 2);
		}
		tc.incrementTeamPlayerSize(turn);
		pc.addPlayer(type, bc.getX(bunkerName), bc.getY(bunkerName), tc.getTeamName(turn));
		map.setPlayer(bc.getX(bunkerName) - 1, bc.getY(bunkerName) - 1, pc.getLastCreatedPlayer());
	}

	@Override
	public boolean bunkerExists(String bunkerName) {
		return bc.bunkerExists(bunkerName);
	}

	@Override
	public boolean teamExists(String teamName) {
		return tc.teamExists(teamName);
	}

	@Override
	public boolean bunkerAlreadyInUse(String bunkerName) {
		return bc.bunkerAlreadyInUse(bunkerName);
	}

	@Override
	public boolean bunkerBelongsToTeam(String bunkerName) {
		return bc.bunkerBelongsToTeam(bunkerName, tc.getTeamName(turn));
	}

	@Override
	public boolean enoughFunds(String bunkerName, String type) {
		boolean enoughFunds = false;
		switch(type){
		case BLUE -> enoughFunds = bc.getTreasure(bunkerName) >= 2;
		case RED -> enoughFunds = bc.getTreasure(bunkerName) >= 4;
		case GREEN -> enoughFunds = bc.getTreasure(bunkerName) >= 2;
		}
		return enoughFunds;
	}

	@Override
	public int getTeamSize() {
		return tc.getTeamSize();
	}

	@Override
	public int getCurrentTeamSize() {
		return tc.getCurrentTeamSize();
	}

	@Override
	public int getBunkerSize() {
		return bc.getBunkerSize();
	}

	@Override
	public BunkerIterator bunkerIterator() {
		return bc.bunkerIterator();
	}

	@Override
	public TeamIterator teamIterator() {
		return tc.teamIterator();
	}

	@Override
	public int getTeamBunkerSize(){
		return tc.getTeamBunkerSize(turn);
	}

	@Override
	public BunkerIterator teamBunkerIterator(){
		return bc.teamBunkerIterator(tc.getTeamName(turn));
	}

	@Override
	public int getTeamPlayerSize(){
		return tc.getTeamPlayerSize(turn);
	}

	@Override
	public PlayerIterator teamPlayerIterator(){
		return pc.teamPlayerIterator(tc.getTeamName(turn));
	}

	@Override
	public boolean bunkerInThatPosition(int x, int y) {
		return bc.bunkerInThatPosition(x, y);
	}

	@Override
	public String getTeamNameTurn() {
		return tc.getTeamName(turn);
	}

	@Override
	public void endTurn() {
		boolean isAlive = false;
		bc.increaseTreasure();
		tc.updateDeadTeams();
		turn++;
		while(!isAlive) {
			if (turn >= tc.getTeamSize()) {
				turn = 0;
			}
			if (tc.isTeamDead(turn)) {
				turn++;
			}
			else {
				isAlive = true;
			}
		}
	}

	@Override
	public void defaultMap() {
		map.defaultMap(bc);
	}

	@Override
	public CellIterator teamCellIterator(int y) {
		return map.rowMapIterator(y - 1, tc.getTeamName(turn));
	}

	@Override
	public boolean isOccupied(String bunkerName) {
		return map.isOccupied(bc.getX(bunkerName) - 1, bc.getY(bunkerName) - 1);
	}

	@Override
	public String getPlayerType(int x, int y) {
		return map.getPlayerType(x - 1, y - 1);
	}

	@Override
	public boolean isPlayerThere(int x, int y) {
		return (map.getPlayer(x - 1, y - 1) != null) &&
				(map.getPlayerTeam(x - 1, y - 1).equals(tc.getTeamName(turn)));
	}

	@Override
	public boolean isGoingOutOfBounds(int x, int y, String where) {
		return map.isGoingOutOfBounds(x - 1, y - 1, where);
	}

	@Override
	public boolean isTeamPlayerThere(int x, int y, String where) {
		return map.isTeamPlayerThere(x - 1, y - 1, where);
	}

	@Override
	public int whatIsThere(int x, int y, String where) {
		return map.whatIsThere(x - 1, y - 1, tc.getTeamName(turn), where);
	}

	@Override
	public Player getPlayer(int x, int y) {
		return map.getPlayer(x - 1, y - 1);
	}

	@Override
	public void movePlayer(int x, int y, String where) {
		map.movePlayer(x - 1, y - 1, where, pc.getPlayer(x, y));
	}

	@Override
	public void seizeBunker(int x, int y, String where) {
		tc.incrementTeamBunkerSize(turn);
		String teamName = map.seizeBunker(x - 1, y - 1, tc.getTeamName(turn), tc.getTeamBunkerSize(turn), where);
		if (!teamName.equals(Bunker.WITHOUT_OWNER)) {
			tc.decreaceTeamBunkerSize(teamName);
		}
	}

	@Override
	public boolean didPlayerWinAndMove(int x, int y, String where) {
		boolean didPlayerWin = false;
		String playerTeamName = map.winFightByMove(x - 1, y - 1, where);
		if (playerTeamName.equals(Map.PLAYER_LOSE)) {
			tc.decreaceTeamPlayerSize(tc.getTeamName(turn));
		}
		else {
			tc.decreaceTeamPlayerSize(playerTeamName);
			didPlayerWin = true;
		}
		return didPlayerWin;
	}

	@Override
	public int updateRedPlayerX(int x, String where) {
		int newX = x;
		switch (where){
			case Player.WEST -> newX--;
			case Player.EAST -> newX++;
		}
		return newX;
	}

	@Override
	public int updateRedPlayerY(int y, String where) {
		int newY = y;
		switch (where){
			case Player.NORTH -> newY--;
			case Player.SOUTH -> newY++;
		}
		return newY;
	}

	@Override
	public boolean isTurnTeamDead() {
		return tc.isTeamDead(turn);
	}

	@Override
	public boolean isGameOver() {
		return tc.isGameOver();
	}

	@Override
	public String getWinner() {
		return tc.getWinner();
	}

	@Override
	public void allPlayerAttack() {
		PlayerIterator it = pc.teamPlayerIterator(tc.getTeamName(turn));
		while (it.hasNext()) {
			Player player = it.next();
			int x = player.getX() - 1;
			int y = player.getY() - 1;
			switch (player.getType()) {
				case RED -> attackRed(x, y);
				case BLUE -> attackBlue(x, y);
				case GREEN -> attackGreen(x, y);
			}
		}
	}

	/**
	 * Executa o ataque caso o tipo de Player seja vermelho.
	 * @param xPlayer - A coordenada x do Player.
	 * @param yPlayer - A coordenada y do Player.
	 * @Pre - xPlayer != null && xPlayer > 0 && xPlayer <= width && yPlayer != null && yPlayer > 0 && yPlayer <= height.
	 */
	private void attackRed(int xPlayer, int yPlayer) {
		int xCheck = xPlayer;
		int yCheck = yPlayer;
		boolean playerAlive = true;
		while (yCheck < height && playerAlive) {
			while (xCheck < width && playerAlive) {
				playerAlive = executeAttack(xPlayer, yPlayer, xCheck, yCheck, playerAlive);
				xCheck++;
			}
			xCheck = xPlayer;
			yCheck++;
		}
	}

	/**
	 * Executa o ataque caso o tipo de Player seja azul.
	 * @param xPlayer - A coordenada x do Player.
	 * @param yPlayer - A coordenada y do Player.
	 * @Pre - xPlayer != null && xPlayer > 0 && xPlayer <= width && yPlayer != null && yPlayer > 0 && yPlayer <= height.
	 */
	private void attackBlue(int xPlayer, int yPlayer) {
		int xCheckWest = xPlayer;
		int xCheckEast = xPlayer;
		boolean maxWest = false;
		boolean maxEast = false;
		boolean turnCheck = true;
		boolean playerAlive = true;
		while (playerAlive && (!maxEast || !maxWest)) {
			if (turnCheck) {
				if (xCheckWest - 1 < 0) {
					maxWest = true;
				}
				else {
					xCheckWest--;
					playerAlive = executeAttack(xPlayer, yPlayer, xCheckWest, yPlayer, playerAlive);
				}
				turnCheck = false;
			}
			else {
				if (xCheckEast + 1 >= width) {
					maxEast = true;
				}
				else {
					xCheckEast++;
					playerAlive = executeAttack(xPlayer, yPlayer, xCheckEast, yPlayer, playerAlive);
				}
				turnCheck = true;
			}
		}
	}

	/**
	 * Executa o ataque caso o tipo de Player seja verde.
	 * @param xPlayer - A coordenada x do Player.
	 * @param yPlayer - A coordenada y do Player.
	 * @Pre - xPlayer != null && xPlayer > 0 && xPlayer <= width && yPlayer != null && yPlayer > 0 && yPlayer <= height.
	 */
	private void attackGreen(int xPlayer, int yPlayer) {
		int xCheckWest = xPlayer;
		int xCheckEast = xPlayer;
		int yCheckNorth = yPlayer;
		int yCheckSouth = yPlayer;
		boolean maxNorth = false;
		boolean maxSouth = false;
		boolean maxWest = false;
		boolean maxEast = false;
		boolean maxArea = false;
		int turnCounter = 4;
		boolean playerAlive = true;
		while (playerAlive && !maxArea) {
			if (turnCounter != 4) {
				turnCounter++;
			}
			else {
				if (yCheckNorth - 1 < 0) {
					maxNorth = true;
				}
				else {
					yCheckNorth--;
				}
				if (yCheckSouth + 1 >= height) {
					maxSouth = true;
				}
				else {
					yCheckSouth++;
				}
				if (xCheckWest - 1 < 0) {
					maxWest = true;
				}
				else {
					xCheckWest--;
				}
				if (xCheckEast + 1 >= width) {
					maxEast = true;
				}
				else {
					xCheckEast++;
				}
				maxArea = ((maxNorth || maxWest) && (maxNorth || maxEast) &&
						(maxSouth || maxWest) && (maxSouth || maxEast));
				turnCounter = 1;
			}
			playerAlive = attackGreenHelp(xPlayer, yPlayer, turnCounter, maxNorth, maxWest, playerAlive, xCheckWest, yCheckNorth, maxEast, xCheckEast, maxSouth, yCheckSouth);
		}
	}

	/**
	 * Ajuda o metodo attackGreen a fazer o ataque.
	 * @param xPlayer - A coordenada x do Player.
	 * @param yPlayer - A coordenada y do Player.
	 * @param turnCounter - Indicador para saber que posição atacar.
	 * @param maxNorth - Máximo que pode atingir na coordenada norte.
	 * @param maxWest - Máximo que pode atingir na coordenada oeste.
	 * @param playerAlive - Se o Player a atacar continua vivo.
	 * @param xCheckWest - A coordenada x que queremos verificar de momento depois de movida para oeste.
	 * @param yCheckNorth - A coordenada y que queremos verificar de momento depois de movida para norte.
	 * @param maxEast - Máximo que pode atingir na coordenada este.
	 * @param xCheckEast - A coordenada x que queremos verificar de momento depois de movida para este.
	 * @param maxSouth - Máximo que pode atingir na coordenada sul.
	 * @param yCheckSouth - A coordenada y que queremos verificar de momento depois de movida para sul.
	 * @return true se o Player estiver vivo, caso contrário, retorna false.
	 * @Pre - xPlayer != null && xPlayer > 0 && xPlayer <= width &&
	 * yPlayer != null && yPlayer > 0 && yPlayer <= height &&
	 * yCheckNorth != null && yCheckNorth > 0 && yCheckNorth <= height &&
	 * yCheckSouth != null && yCheckSouth > 0 && yCheckSouth <= height &&
	 * xCheckWest != null && xCheckWest > 0 && xCheckWest <= width &&
	 * xCheckEast != null && xCheckEast > 0 && xCheckEast <= width &&
	 * turnCounter != null. && maxNorth != null && maxWest != null &&
	 * maxEast != null && maxSouth != null && playerAlive != null
	 */
	private boolean attackGreenHelp(int xPlayer, int yPlayer, int turnCounter, boolean maxNorth, boolean maxWest, boolean playerAlive, int xCheckWest, int yCheckNorth, boolean maxEast, int xCheckEast, boolean maxSouth, int yCheckSouth) {
		switch (turnCounter) {
			case 1 -> {
				if (!maxNorth && !maxWest) {
					playerAlive = executeAttack(xPlayer, yPlayer, xCheckWest, yCheckNorth, playerAlive);
				}
			}
			case 2 -> {
				if (!maxNorth && !maxEast) {
					playerAlive = executeAttack(xPlayer, yPlayer, xCheckEast, yCheckNorth, playerAlive);
				}
			}
			case 3 -> {
				if (!maxSouth && !maxWest) {
					playerAlive = executeAttack(xPlayer, yPlayer, xCheckWest, yCheckSouth, playerAlive);
				}
			}
			case 4 -> {
				if (!maxSouth && !maxEast) {
					playerAlive = executeAttack(xPlayer, yPlayer, xCheckEast, yCheckSouth, playerAlive);
				}
			}
		}
		return playerAlive;
	}

	/**
	 * Comando auxiliar que executa o ataque dependendo do que está na posição dada em Check.
	 * @param xPlayer - A coordenada x do Player.
	 * @param yPlayer - A coordenada y do Player.
	 * @param xCheck - A coordenada x que queremos ver.
	 * @param yCheck - A coordenada y que queremos ver.
	 * @param playerAlive - Verifica se o Player está vivo.
	 * @return true se o Plaver ficou vivo depois do ataque, caso contrário, retorna false.
	 * @Pre - xPlayer != null && xPlayer > 0 && xPlayer <= width &&
	 * yPlayer != null && yPlayer > 0 && yPlayer <= height &&
	 * xCheck != null && xCheck > 0 && xCheck <= width &&
	 * yCheck != null && yCheck > 0 && yCheck <= height &&
	 * playerAlive != null.
	 */
	private boolean executeAttack(int xPlayer, int yPlayer, int xCheck, int yCheck, boolean playerAlive) {
		switch (map.foundWhatIsThere(xCheck, yCheck, tc.getTeamName(turn))) {
			case Map.ENEMY_BUNKER_AND_PLAYER -> {
				String enemyTeamName = map.getPlayerTeam(xCheck, yCheck);
				playerAlive = map.winFightPlayer(xPlayer, yPlayer, xCheck, yCheck);
				if (playerAlive) {
					tc.incrementTeamBunkerSize(turn);
					map.seizeBunkerWithCord(xCheck, yCheck, tc.getTeamName(turn), tc.getTeamBunkerSize(turn));
					tc.decreaceTeamPlayerSize(enemyTeamName);
					tc.decreaceTeamBunkerSize(enemyTeamName);
				} else {
					tc.decreaceTeamPlayerSize(tc.getTeamName(turn));
				}
			}
			case Map.BUNKER_THERE -> {
				tc.incrementTeamBunkerSize(turn);
				String enemyTeamName = map.seizeBunkerWithCord(xCheck, yCheck, tc.getTeamName(turn),  tc.getTeamBunkerSize(turn));
				if (!enemyTeamName.equals(Bunker.WITHOUT_OWNER)) {
					tc.decreaceTeamBunkerSize(enemyTeamName);
				}
			}
			case Map.ENEMY_PLAYER_THERE -> {
				String enemyTeamName = map.getPlayerTeam(xCheck, yCheck);
				playerAlive = map.winFightPlayer(xPlayer, yPlayer, xCheck, yCheck);
				if (playerAlive) {
					tc.decreaceTeamPlayerSize(enemyTeamName);
				} else {
					tc.decreaceTeamPlayerSize(tc.getTeamName(turn));
				}
			}
		}
		return playerAlive;
	}
}
