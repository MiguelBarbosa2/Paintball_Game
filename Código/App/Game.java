/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public interface Game {

	//Constantes
	public final String BLUE = "BLUE";
	public final String RED = "RED";
	public final String GREEN = "GREEN";

	/**
	 * Verifica se o tipo de Player inserido é valido.
	 * @param type - A String que queremos verificar se corresponde a algum tipo de Player.
	 * @return true se type corresponder a um dos tipos de Player, caso contrário, retorna false;
	 * @Pre - type != null.
	 */
	static boolean isValidType(String type) {
		return type.equals(BLUE) || type.equals(RED) || type.equals(GREEN);
	}

	/**
	 * @return o tamanho da dimensão horizontal (width) do mapa.
	 */
	int getWidth();

	/**
	 * @return o tamanho da dimensão vertical (height) do mapa.
	 */
	int getHeight();

	/**
	 * Adiciona um Bunker novo ao jogo.
	 * @param x - A coordenada x do Bunker.
	 * @param y - A coordenada y do Bunker.
	 * @param treasure - O tesouro inicial do Bunker.
	 * @param bunkerName - O nome do Bunker.
	 * @Pre - x != null && x > 0 && x <= width && y != null && y > 0 && y <= height &&
	 * treasure != null && treasure >= 0 && bunkerName != null.
	 */
	void addBunker(int x, int y, int treasure, String bunkerName);

	/**
	 * Adiciona uma equipa nova ao jogo.
	 * @param teamName - O nome da equipa.
	 * @param bunkerName - O nome do Bunker que vai pertencer à equipa.
	 * @Pre - teamName != null && bunkerName != null.
	 */
	void addTeam(String teamName, String bunkerName);

	/**
	 * Adiciona um Player novo ao jogo.
	 * @param type - O tipo de Player que é.
	 * @param bunkerName - O nome do Bunker que o Player vai ser criado.
	 * @Pre - type != null && bunkerName != null.
	 */
	void addPlayer(String type, String bunkerName);

	/**
	 * Verifica se o Bunker com o nome dado existe.
	 * @param bunkerName - O nome do Bunker.
	 * @return true se existe um Bunker com esse nome, caso contrário, retorna falso.
	 * @Pre - bunkerName != null.
	 */
	boolean bunkerExists(String bunkerName);

	/**
	 * Verifica se a equipa com o nome dado existe.
	 * @param teamName - O nome da equipa.
	 * @return true se existe uma equipa com esse nome, caso contrário, retorna falso.
	 * @Pre - teamName != null.
	 */
	boolean teamExists(String teamName);

	/**
	 * Verifica se o Bunker com o nome dado pertence a alguma equipa.
	 * @param bunkerName - O nome do Bunker.
	 * @return true se o Bunker com esse nome pertence a uma equipa, caso contrário, retorna falso.
	 * @Pre - bunkerName != null.
	 */
	boolean bunkerAlreadyInUse(String bunkerName);

	/**
	 * Verifica se o Bunker com o nome dado pertence à equipa do turno atual.
	 * @param bunkerName - O nome do Bunker.
	 * @return true se o Bunker com esse nome pertence à equipa do turno atual, caso contrário, retorna falso.
	 * @Pre - bunkerName != null.
	 */
	boolean bunkerBelongsToTeam(String bunkerName);

	/**
	 * Verifica se o Bunker com o nome dado tem fundos suficientes para criar um Player do tipo dado.
	 * @param bunkerName - O nome do Bunker.
	 * @param type - O tipo de Player.
	 * @return true se o Bunker com o nome dado tem fundos suficientes para criar um Player do tipo dado,
	 * caso contrário, retorna falso.
	 * @Pre - bunkerName != null && type != null.
	 */
	boolean enoughFunds(String bunkerName, String type);

	/**
	 * @return o número total de equipas criadas neste jogo.
	 */
	int getTeamSize();

	/**
	 * @return o número de equipas ainda em jogo.
	 */
	int getCurrentTeamSize();

	/**
	 * @return o número total de Bunkers no jogo.
	 */
	int getBunkerSize();

	/**
	 * @return o iterador que contém todos os Bunkers do jogo.
	 */
	BunkerIterator bunkerIterator();

	/**
	 * @return o iterador que contém todas as equipas ainda em jogo.
	 */
	TeamIterator teamIterator();

	/**
	 * @return o número de Bunkers que pertencem à equipa do turno atual.
	 */
	int getTeamBunkerSize();

	/**
	 * @return o iterador que contém todos os Bunkers que pertencem à equipa do turno atual.
	 */
	BunkerIterator teamBunkerIterator();

	/**
	 * @return o número de Players que pertecem à equipa do turno atual.
	 */
	int getTeamPlayerSize();

	/**
	 * @return o iterador que contém todos os Players que pertencem à equipa do turno atual.
	 */
	PlayerIterator teamPlayerIterator();

	/**
	 * Verifica se há um Bunker na coordenada (x, y) dada.
	 * @param x - A coordenada x do Bunker.
	 * @param y - A coordenada y do Bunker.
	 * @return true se houver um Bunker naquelas coordenadas, caso contrário, retorna falso.
	 * @Pre - x != null && y != null.
	 */
	boolean bunkerInThatPosition(int x, int y);

	/**
	 * @return o nome da equipa do turno atual.
	 */
	String getTeamNameTurn();

	/**
	 * Passa o turno para a próxima equipa e verifica que equipas foram eliminadas.
	 */
	void endTurn();

	/**
	 * Cria o mapa do jogo já com todos os Bunkers do jogo.
	 */
	void defaultMap();

	/**
	 * O iterador da linha de células y do mapa.
	 * @param y - A linha que queremos iterar.
	 * @return o iterador que contém a linha de células dada.
	 * @Pre - y != null && y > 0 && y <= height.
	 */
	CellIterator teamCellIterator(int y);

	/**
	 * Verifica se o Bunker com o nome dado têm um Player.
	 * @param bunkerName - O nome do Bunker.
	 * @return true se o Bunker têm um Player, caso contrário, retorna false.
	 * @Pre - bunkerName != null.
	 */
	boolean isOccupied(String bunkerName);

	/**
	 * Verifica o tipo de Player na coordenada (x, y) dada.
	 * @param x - A coordenada x do Player.
	 * @param y - A coordenada y do Player.
	 * @return o tipo de Player naquela coordenada.
	 * @Pre - x != null && x > 0 && x <= width && y != null && y > 0 && y <= height.
	 */
	String getPlayerType(int x, int y);

	/**
	 * Verifica se existe um Player na coordenada (x, y) dada.
	 * @param x - A coordenada x do que queremos ver.
	 * @param y - A coordenada y do que queremos ver.
	 * @return true se existir um Player naquelas coordenadas, caso contrário, retorna falso.
	 * @Pre - x != null && x > 0 && x <= width && y != null && y > 0 && y <= height.
	 */
	boolean isPlayerThere(int x, int y);

	/**
	 * Verifica se o Player nas coordenadas (x,y) dadas ao se mover na direção indicada vai sair para fora do mapa.
	 * @param x - A coordenada x do Player.
	 * @param y - A coordenada y do Player.
	 * @param where - Direção que queremos verificar.
	 * @return true se o Player nas coordenadas dadas vai sair para fora do mapa ao se mover na direção
	 * indicada, caso contrário, retorna false.
	 * @Pre - x != null && x > 0 && x <= width && y != null && y > 0 && y <= height && where != null.
	 */
	boolean isGoingOutOfBounds(int x, int y, String where);

	/**
	 * Verifica se o Player nas coordenadas (x,y) dadas ao se mover na direção indicada vai para o mesmo espaço
	 * que um Player da mesma equipa.
	 * @param x - A coordenada x do Player.
	 * @param y - A coordenada y do Player.
	 * @param where - Direção que queremos verificar.
	 * @return true se o Player nas coordenadas dadas vai para o mesmo espaço que um Player da mesma equipa,
	 * caso contrário, retorna false.
	 * @Pre - x != null && x > 0 && x <= width && y != null && y > 0 && y <= height && where != null.
	 */
	boolean isTeamPlayerThere(int x, int y, String where);

	/**
	 * Verifica o que está na posição para onde queremos mover o Player nas coordenadas (x,y) dadas.
	 * @param x - A coordenada x do Player.
	 * @param y - A coordenada y do Player.
	 * @param where - Direção que queremos verificar.
	 * @return 0 se não têm nada, 1 se têm um Bunker e Player inimigo, 2 se estiver um Bunker que não pertence
	 * à equipa do Player, 3 se estiver um Player inimigo.
	 * @Pre - x != null && x > 0 && x <= width && y != null && y > 0 && y <= height && where != null.
	 */
	int whatIsThere(int x, int y, String where);

	/**
	 * @param x - A coordenada x do Player.
	 * @param y - A coordenada y do Player.
	 * @return o Player na coordenada (x, y) dada.
	 * @Pre - x != null && x > 0 && x <= width && y != null && y > 0 && y <= height.
	 */
	Player getPlayer(int x, int y);

	/**
	 * Utilizado para mover o Player nas coordenadas (x, y) dadas.
	 * @param x - A coordenada x do Player.
	 * @param y - A coordenada y do Player.
	 * @param where - Direção que queremos mover.
	 * @Pre - x != null && x > 0 && x <= width && y != null && y > 0 && y <= height && where != null.
	 */
	void movePlayer(int x, int y, String where);

	/**
	 * Captura o Bunker na direção indicada a partir das coordenadas (x, y) dadas de um Player.
	 * @param x - A coordenada x do Player.
	 * @param y - A coordenada y do Player.
	 * @param where - Direção que queremos capturar.
	 * @Pre - x != null && x > 0 && x <= width && y != null && y > 0 && y <= height && where != null.
	 */
	void seizeBunker(int x, int y, String where);

	/**
	 * Executa a luta entre o Player nas coordenadas (x, y) dadas do turno atual e
	 * o Player na posição indicada e verifica se o Player da equipa do turno atual ganhou e moveu-se.
	 * @param x - A coordenada x do Player do turno atual.
	 * @param y - A coordenada y do Player do turno atual.
	 * @param where - Direção do Player inimigo.
	 * @return true se o Player do turno atual ganhou, caso contrário, retorna false.
	 * @Pre - x != null && x > 0 && x <= width && y != null && y > 0 && y <= height && where != null.
	 */
	boolean didPlayerWinAndMove(int x, int y, String where);

	/**
	 * Dá update à coordenada x de um Player vermelho à medida que se vai movendo.
	 * @param x - A coordenada x do Player.
	 * @param where - Direção que se moveu.
	 * @return nova coordenada x do Player vermelho.
	 * @Pre - x != null && where != null.
	 */
	int updateRedPlayerX(int x, String where);

	/**
	 * Dá update à coordenada y de um Player vermelho à medida que se vai movendo.
	 * @param y - A coordenada y do Player.
	 * @param where - Direção que se moveu.
	 * @return nova coordenada y do Player vermelho.
	 * @Pre y != null && where != null.
	 */
	int updateRedPlayerY(int y, String where);

	/**
	 * @return true se a equipa do turno atual está morta/eliminada, caso contrário, retorna false.
	 */
	boolean isTurnTeamDead();

	/**
	 * @return true se o jogo acabou, caso contrário, retorna false.
	 */
	boolean isGameOver();

	/**
	 * @return o nome da equipa vencedora.
	 */
	String getWinner();

	/**
	 * Vai executar o comando para todos os Players do turno atual atacarem.
	 */
	void allPlayerAttack();

}
