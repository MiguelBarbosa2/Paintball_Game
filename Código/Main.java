/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

import java.util.Scanner;

import App.*;

public class Main {
	
	/*Constantes*/
	private static final String GAME = "game";
	private static final String HELP  = "help";
	private static final String MOVE  = "move";
	private static final String CREATE  = "create";
	private static final String ATTACK = "attack";
	private static final String STATUS = "status";
	private static final String MAP = "map";
	private static final String BUNKERS = "bunkers";
	private static final String PLAYERS = "players";
	private static final String QUIT = "quit";
	
	private static final String POINTER = "> ";
	private static final String POINTER_WITH_TEAM = "%s> ";
	private static final String BYE = "Bye.\n";
	private static final String HELP_COMMANDS1 = "game - Create a new game\nhelp - Show available commands\nquit - End program execution\n";
	private static final String HELP_COMMANDS2 = "game - Create a new game\nmove - Move a player\ncreate - Create a player in a bunker\nattack - Attack with all players of the current team\nstatus - Show the current state of the game\nmap - Show the map of the current team\nbunkers - List the bunkers of the current team, by the order they were seized\nplayers - List the active players of the current team, by the order they were created\nhelp - Show available commands\nquit - End program execution\n";
	private static final String NUMBER_BUNKERS = "%d bunkers:\n";
	private static final String NUMBER_PLAYERS = "%d players:\n";
	private static final String NO_PLAYERS = "Without players.\n";
	private static final String NO_BUNKERS = "Without bunkers.\n";
	private static final String NUMBER_TEAMS = "%d teams:\n";
	private static final String NOT_VALID_BUNKER = "Bunker not created.\n";
	private static final String NOT_VALID_TEAM = "Team not created.\n";
	private static final String FATAL_ERROR = "FATAL ERROR: Insufficient number of teams.\n";
	private static final String LIST_PLAYERS = "%s player in position (%d, %d)\n";
	private static final String LIST_BUNKERS_1 = "%s (%s)\n";
	private static final String LIST_BUNKERS_2 = "%s with %d coins in position (%d, %d)\n";
	private static final String GET_DIMENSIONS = "%d %d\n";
	private static final String GET_TEAM_NAME_1 = "%s; ";
	private static final String GET_TEAM_NAME_2 = "%s\n";
	private static final String WRONG_TYPE = "Non-existent player type.\n";
	private static final String BUNKER_DONT_EXIST = "Non-existent bunker.\n";
	private static final String BUNKER_INVADED = "Bunker illegally invaded.\n";
	private static final String BUNKER_OCCUPIED = "Bunker not free.\n";
	private static final String INSUFFICIENT_COINS = "Insufficient coins for recruitment.\n";
	private static final String PLAYER_CREATED = "%s player created in %s\n";
	private static final String MIN_WIDTH = "**1 2 3 4 5 6 7 8 9 ";
	private static final String MAX_WIDTH = "%d\n";
	private static final String GRID_NUMBER = "%d ";
	private static final String CELL = "%c ";
	private static final String LAST_ROW_CELL = "%c\n";
	private static final String INVALID_POSITION = "Invalid position.\n";
	private static final String INVALID_DIR = "Invalid direction.\n";
	private static final String NO_PLAYER_THERE = "No player in that position.\n";
	private static final String INVALID_MOVE = "Invalid move.\n";
	private static final String TRY_OFF_MAP = "Trying to move off the map.\n";
	private static final String POSITION_OCCUPIED = "Position occupied.\n";
	private static final String PLAYER_MOVE = "%s player in position (%d, %d)\n";
	private static final String SEIZE_BUNKER = "Bunker seized.\n";
	private static final String FIGHT_WON = "Won the fight.\n";
	private static final String FIGHT_LOSE = "Player eliminated.\n";
	private static final String FIGHT_AND_BUNKER_WON = "Won the fight and bunker seized.\n";
	private static final String TEAM_DEAD = "All players eliminated.\n";
	private static final String WINNER = "Winner is %s.\n";
	private static final String INVALID_COMM = "Invalid command.\n";
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String option;
		boolean gameOver = false;
		Game game;
		do {
			do {
				System.out.printf(POINTER);
				option = in.next().toLowerCase();
				executeOp1(option, in);
			}while(!option.equals(QUIT) && !option.equals(GAME));
			if(!option.equals(QUIT)) {
				game = game(in);
				if(game != null) {
					do {
						System.out.printf(POINTER_WITH_TEAM, game.getTeamNameTurn());
						option = in.next().toLowerCase();
						if(option.equals(GAME)) {
							game = game(in);
						}
						else {
							executeOp2(option, in, game);
							gameOver = isGameOver(game);
						}
					}while(!option.equals(QUIT) && game != null && !gameOver);
				}
			}	
		}while(!option.equals(QUIT));
		in.close();
	}

	/**
	 * Vai executar a opção escolhida antes de um jogo começar.
	 * @param opt - A opção escolhida.
	 * @param in - Scanner
	 * @Pre - opt != null.
	 */
	private static void executeOp1(String opt, Scanner in){
		switch(opt){
		case HELP -> help1();
		case QUIT -> quit();
		default -> 	{		
			if(!opt.equals(GAME)) {
			in.nextLine();
			System.out.printf(INVALID_COMM);
			}
		}
		}
	}

	/**
	 * Vai executar a opção escolhida durante um jogo.
	 * @param opt - Opcção escolhida.
	 * @param in - Scanner.
	 * @param game - Jogo corrente.
	 * @Pre - opt != null && game != null.
	 */
	private static void executeOp2(String opt, Scanner in, Game game){
		switch(opt){
		case HELP -> help2();
		case MOVE -> move(in, game);
		case CREATE -> create(in, game);
		case ATTACK -> attack(game);
		case STATUS -> status(game);
		case MAP -> map(game);
		case BUNKERS -> bunkers(game);
		case PLAYERS -> players(game);
		case QUIT -> quit();
		default -> {
			if(!opt.equals(GAME)) {
				in.nextLine();
				System.out.printf(INVALID_COMM);
			}
		}
		}
	}

	/**
	 * Verifica se o jogo acabou.
	 * @param game - Jogo corrente.
	 * @return true se o jogo acabou, caso contrário, retorna falso.
	 * @Pre - game != null.
	 */
	private static boolean isGameOver(Game game) {
		boolean gameOver = false;
		if (game.isGameOver()) {
			System.out.printf(WINNER, game.getWinner());
			gameOver = true;
		}
		return gameOver;
	}

	/**
	 * Cria/começa um jogo novo.
	 * @param in - Scanner.
	 * @return o jogo novo criado.
	 * @Pre - width >= 10 && height >= 10 && teamNumber >= 2 && teamNumber <= 8 &&
	 * bunkerNumber >= teamNumber && bunkerNumber <= width * height.
	 */
	private static Game game(Scanner in) {
		Game game;
		int width = in.nextInt();
		int height = in.nextInt();
		int teamNumber = in.nextInt();
		int bunkerNumber = in.nextInt();
		game = new GameClass(width, height, teamNumber, bunkerNumber);
		System.out.printf(NUMBER_BUNKERS, bunkerNumber);
		createBunker(in, game, width, height, bunkerNumber);
		System.out.printf(NUMBER_TEAMS, teamNumber);
		createTeam(in, game, teamNumber);
		if(game.getTeamSize() >= 2) {
			game.defaultMap();
			return game;
		}
		else {
			System.out.printf(FATAL_ERROR);
			return null;
		}
	}

	/**
	 * Cria as equipas do jogo.
	 * @param in - Scanner.
	 * @param game - Jogo corrente.
	 * @param teamNumber - Número de equipas que queremos criar.
	 * @Pre game != null && teamNumber != null.
	 */
	private static void createTeam(Scanner in, Game game, int teamNumber) {
		for(int i = 0; i < teamNumber; i++) {
			String teamName = in.next();
			String bunkerName = in.nextLine().trim();
			if(isValidTeam(game, teamName, bunkerName)) {
				game.addTeam(teamName, bunkerName);
			}
			else {
				System.out.printf(NOT_VALID_TEAM);
			}
		}
	}

	/**
	 * Verifica se é valido a criação da equipa.
	 * @param game - Jogo corrente.
	 * @param teamName - Nome da equipa.
	 * @param bunkerName - Nome do Bunker.
	 * @return true se se verificar as condições necessárias para a criação da equipa, caso contrário, retorna false.
	 * @Pre game != null && teamName != null && bunkerName != null.
	 */
	private static boolean isValidTeam(Game game, String teamName, String bunkerName) {
		return game.bunkerExists(bunkerName) && !game.teamExists(teamName) && !game.bunkerAlreadyInUse(bunkerName);
	}

	/**
	 * Cria os Bunkers do jogo.
	 * @param in - Scanner.
	 * @param game - Jogo corrente.
	 * @param width - o tamanho da dimensão horizontal do mapa.
	 * @param height - o tamanho da dimensão vertical do mapa.
	 * @param bunkerNumber - Número de Bunkers que queremos criar.
	 * @Pre - game != null && width != null && width != null && bunkerNumber != null.
	 */
	private static void createBunker(Scanner in, Game game, int width, int height, int bunkerNumber) {
		for(int i = 0; i < bunkerNumber; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			int treasure = in.nextInt();
			String bunkerName = in.nextLine().trim();
			if(isValidBunker(game, width, height, x, y, treasure, bunkerName)) {
				game.addBunker(x, y, treasure, bunkerName);
			}
			else {
				System.out.printf(NOT_VALID_BUNKER);
			}
		}
	}

	/**
	 * Verifica se é valido a criação do Bunker.
	 * @param game - Jogo corrente.
	 * @param width - o tamanho da dimensão horizontal do mapa.
	 * @param height - o tamanho da dimensão vertical do mapa.
	 * @param x - a coordenada x do Bunker.
	 * @param y - a coordenada y do Bunker.
	 * @param treasure - o tesouro presente no Bunker.
	 * @param bunkerName - Nome do Bunker.
	 * @return true se se verificar as condições necessárias para a criação do Bunker, caso contrário, retorna false.
	 * @Pre - game != null && width != null && height != null && x != null && y != null && treasure != null && bunkerName != null.
	 */
	private static boolean isValidBunker(Game game, int width, int height, int x, int y, int treasure,
			String bunkerName) {
		return width >= x && x > 0 && height >= y && y > 0 && treasure > 0 && !game.bunkerExists(bunkerName) && !game.bunkerInThatPosition(x, y);
	}

	/**
	 * Comando para mover um Player.
	 * @param in - Scanner.
	 * @param game - Jogo corrente.
	 * @Pre game != null.
	 */
	private static void move(Scanner in, Game game) {
		String trash = null;
		boolean noPlayer = false;
        int x = in.nextInt();
        int y = in.nextInt();
		if (x > game.getWidth() || x <= 0 || y > game.getHeight() || y <= 0) {
			trash = in.nextLine();
			System.out.printf(INVALID_POSITION);
		}
		else if (!game.isPlayerThere(x, y)){
			noPlayer = true;
		}
		if (trash == null) {
			if (!noPlayer && game.getPlayerType(x, y).equals(Game.RED)) {
				redPlayerMove(in, game, x, y);
			}
			else {
				blueGreenPlayerMove(in, game, x, y, noPlayer);
			}
		}
		game.endTurn();
	}

	/**
	 * Movimento de um jogador vermelho
	 * @param in - Scanner.
	 * @param game - Jogo corrente.
	 * @param x - Coordenada x do Player.
	 * @param y - Coordenada y do Player.
	 * @Pre - game != null && x != null && y != null.
	 */
	private static void redPlayerMove(Scanner in, Game game, int x, int y) {
		String[] dir = in.nextLine().trim().toLowerCase().split(" ");
		for (int i = 0; i < dir.length && i < 3; i++) {
			boolean isValid = !(dir[i].equals(Player.NORTH) || dir[i].equals(Player.SOUTH) ||
					dir[i].equals(Player.EAST) || dir[i].equals(Player.WEST));
			if (isValid) {
				System.out.printf(INVALID_DIR);
			}
			else if (game.isGoingOutOfBounds(x, y, dir[i])) {
				System.out.printf(TRY_OFF_MAP);
			}
			else if (game.isTeamPlayerThere(x, y, dir[i])) {
				System.out.printf(POSITION_OCCUPIED);
			}
			else {
				boolean playerElim = successMove(game, x, y, dir[i]);
				if (playerElim) {
					i = 4;
				}
				else {
					x = game.updateRedPlayerX(x, dir[i]);
					y = game.updateRedPlayerY(y, dir[i]);
				}
			}
		}
	}

	/**
	 * Movimento de um jogador azul ou verde ou caso não foi detetado um jogador em (x, y).
	 * @param in - Scanner.
	 * @param game - Jogo corrente.
	 * @param x - Coordenada x do Player.
	 * @param y - Coordenada y do Player.
	 * @param noPlayer - Se não existe um Player nas coordenadas dadas.
	 * @Pre - game != null && x != null && y != null && noPlayer != null.
	 */
	private static void blueGreenPlayerMove(Scanner in, Game game, int x, int y, boolean noPlayer) {
		String[] dir = in.nextLine().trim().toLowerCase().split(" ");
		boolean isValid = !(dir[0].equals(Player.NORTH) || dir[0].equals(Player.SOUTH) ||
				dir[0].equals(Player.EAST) || dir[0].equals(Player.WEST));
		if (isValid) {
			System.out.printf(INVALID_DIR);
		} else if (noPlayer) {
			System.out.printf(NO_PLAYER_THERE);
		} else if (dir.length > 1) {
			System.out.printf(INVALID_MOVE);
		}
		else if (game.isGoingOutOfBounds(x, y, dir[0])) {
			System.out.printf(TRY_OFF_MAP);
		}
		else if (game.isTeamPlayerThere(x, y, dir[0])) {
			System.out.printf(POSITION_OCCUPIED);
		}
		else {
			successMove(game, x, y, dir[0]);
		}
	}

	/**
	 * Caso o movimento de um Player seja valido é executado.
	 * @param game - Jogo corrente.
	 * @param x - Coordenada x do Player.
	 * @param y - Coordenada y do Player.
	 * @param dir - Direção que quer mover.
	 * @return true se o Player foi eliminado, caso contrário, retorna false.
	 * @Pre game != null && x != null && y != null && dir != null.
	 */
	private static boolean successMove(Game game, int x, int y, String dir) {
		Player player = game.getPlayer(x, y);
		boolean playerElim = false;
		switch (game.whatIsThere(x, y, dir)) {
			case Map.NOTHING_THERE -> {
				game.movePlayer(x, y, dir);
				System.out.printf(PLAYER_MOVE, player.getType().toLowerCase(), player.getX(), player.getY());
			}
			case Map.ENEMY_BUNKER_AND_PLAYER -> {
				boolean winFight = game.didPlayerWinAndMove(x, y, dir);
				if (winFight) {
					game.seizeBunker(x, y, dir);
					System.out.printf(FIGHT_AND_BUNKER_WON);
					game.movePlayer(x, y, dir);
					System.out.printf(PLAYER_MOVE, player.getType().toLowerCase(), player.getX(), player.getY());
				}
				else {
					System.out.printf(FIGHT_LOSE);
					playerElim = true;
				}
			}
			case Map.BUNKER_THERE -> {
				game.seizeBunker(x, y, dir);
				System.out.printf(SEIZE_BUNKER);
				game.movePlayer(x, y, dir);
				System.out.printf(PLAYER_MOVE, player.getType().toLowerCase(), player.getX(), player.getY());
			}
			case Map.ENEMY_PLAYER_THERE -> {
				boolean winFight = game.didPlayerWinAndMove(x, y, dir);
				if (winFight) {
					System.out.printf(FIGHT_WON);
					game.movePlayer(x, y, dir);
					System.out.printf(PLAYER_MOVE, player.getType().toLowerCase(), player.getX(), player.getY());
				}
				else {
					System.out.printf(FIGHT_LOSE);
					playerElim = true;
				}
			}
		}
		return playerElim;
	}

	/**
	 * Comando para cria um Player.
	 * @param in - Scanner.
	 * @param game - Jogo corrente.
	 * @Pre game != null.
	 */
	private static void create(Scanner in, Game game) {
		String type = in.next().toUpperCase();
		String bunkerName = in.nextLine().trim();
		if (!Game.isValidType(type)) {
			System.out.printf(WRONG_TYPE);
		}
		else if (!game.bunkerExists(bunkerName)) {
			System.out.printf(BUNKER_DONT_EXIST);
		}
		else if (!game.bunkerBelongsToTeam(bunkerName)) {
			System.out.printf(BUNKER_INVADED);
		}
		else if (game.isOccupied(bunkerName)) {
			System.out.printf(BUNKER_OCCUPIED);
		}
		else if (!game.enoughFunds(bunkerName, type)){
			System.out.printf(INSUFFICIENT_COINS);
		}
		else {
			game.addPlayer(type, bunkerName);
			System.out.printf(PLAYER_CREATED, type.toLowerCase(), bunkerName);
		}
		game.endTurn();
	}

	/**
	 * Comando para todos os Player de uma equipa atacarem.
	 * @param game - Jogo corrente.
	 * @Pre game != null.
	 */
	private static void attack(Game game) {
		game.allPlayerAttack();
		if (game.isTurnTeamDead()) {
			System.out.printf(TEAM_DEAD);
		}
		else {
			map(game);
		}
		game.endTurn();
	}

	/**
	 * Dá informação sobre o jogo no momento.
	 * @param game - Jogo corrente.
	 * @Pre game != null.
	 */
	private static void status(Game game) {
		System.out.printf(GET_DIMENSIONS, game.getWidth(), game.getHeight());
		System.out.printf(NUMBER_BUNKERS, game.getBunkerSize());
		BunkerIterator it1 = game.bunkerIterator();
		while(it1.hasNext()) {
			Bunker b = it1.next();
			System.out.printf(LIST_BUNKERS_1, b.getBunkerName(), b.getOwner());
		}
		System.out.printf(NUMBER_TEAMS, game.getCurrentTeamSize());
		TeamIterator it2 = game.teamIterator();
		int i = 0;
		while(it2.hasNext()) {
			i++;
			Team t = it2.next();
			if(i != game.getCurrentTeamSize()) {
				System.out.printf(GET_TEAM_NAME_1, t.getTeamName());
			}
			else {
				System.out.printf(GET_TEAM_NAME_2, t.getTeamName());
			}
		}
		
	}

	/**
	 * Mostra o mapa de uma equipa.
	 * @param game - Jogo corrente.
	 * @Pre game != null.
	 */
	private static void map(Game game) {
		System.out.printf(GET_DIMENSIONS, game.getWidth(), game.getHeight());
		System.out.printf(MIN_WIDTH);
		for (int i = 10; i <= game.getWidth(); i++) {
			if (i == game.getWidth()) {
				System.out.printf(MAX_WIDTH, i);
			}
			else {
				System.out.printf(GRID_NUMBER, i);
			}
		}
		for (int i = 1; i <= game.getHeight(); i++) {
			System.out.printf(GRID_NUMBER, i);
			CellIterator it = game.teamCellIterator(i);
			int j = 1;
			while(it.hasNext()) {
				Cell c = it.next();
				if (j == game.getWidth()) {
					System.out.printf(LAST_ROW_CELL, c.getEquivalentChar());
				}
				else {
					System.out.printf(CELL, c.getEquivalentChar());
				}
				j++;
			}
		}
	}

	/**
	 * Mostra os Bunkers de uma equipa.
	 * @param game - Jogo corrente.
	 * @Pre game != null.
	 */
	private static void bunkers(Game game) {
		if (game.getTeamBunkerSize() == 0) {
			System.out.printf(NO_BUNKERS);
		}
		else {
			System.out.printf(NUMBER_BUNKERS, game.getTeamBunkerSize());
			BunkerIterator it = game.teamBunkerIterator();
			while(it.hasNext()) {
				Bunker b = it.next();
				System.out.printf(LIST_BUNKERS_2, b.getBunkerName(), b.getTreasure(), b.getX(), b.getY());
			}
		}
	}

	/**
	 * Mostra os Players de uma equipa.
	 * @param game - Jogo corrente.
	 * @Pre game != null.
	 */
	private static void players(Game game) {
		if (game.getTeamPlayerSize() == 0){
			System.out.printf(NO_PLAYERS);
		}else{
			System.out.printf(NUMBER_PLAYERS, game.getTeamPlayerSize());
			PlayerIterator it = game.teamPlayerIterator();
			while(it.hasNext()) {
				Player p = it.next();
				System.out.printf(LIST_PLAYERS, p.getType().toLowerCase(), p.getX(), p.getY());
			}
		}
	}

	/**
	 * Comando que dá informações sobre todos os comandos disponiveis durante um jogo.
	 */
	private static void help2() {
		System.out.printf(HELP_COMMANDS2);
	}

	/**
	 * Comando que dá informações sobre todos os comandos disponiveis antes de um jogo.
	 */
	private static void help1() {
		System.out.printf(HELP_COMMANDS1);
	}

	/**
	 * Acaba o jogo.
	 */
	private static void quit() {
		System.out.printf(BYE);
	}
}