/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public interface TeamCollection {

	/**
	 * Acresenta um objeto do tipo Team ao array bunkers
	 * @param teamName - nome do team a adicionar ao array teams
	 * @pre teamname != null
	 */
	void addTeam(String teamName);
	
	/**
	 * Determina se existe um team com esse nome no array teams
	 * @param teamName - nome do team
	 * @pre teamname != null
	 * @return verdadeiro se existe um team com esse nome, falso caso contrario
	 */
	boolean teamExists(String teamName);
	
	/**
	 * Retorna um iterador com todos os teams no array teams
	 * @return iterador com todos os teams no array teams
	 */
	TeamIterator teamIterator();
	
	/**
	 * Retorna o numero de bunkers que o team possui
	 * @param turn - inteiro, indica o index do team a jogar no momento atual do jogo
	 * @pre turn != null
	 * @return numero de bunkers que o team possui
	 */
	int getTeamBunkerSize(int turn);
	
	/**
	 * Retorna o numero de players que o team possui
	 * @param turn - inteiro, indica o index do team a jogar no momento atual do jogo
	 * @pre turn != null
	 * @return numero de players que o team possui
	 */
	int getTeamPlayerSize(int turn);

	/**
	 * Retorna o numero de teams criados no inicio do jogo
	 * @return numero de teams criados no inicio do jogo
	 */
	int getTeamSize();
	
	/**
	 * Retorna o numero de teams ainda em jogo no momento
	 * @return umero de teams ainda em jogo no momento
	 */
	int getCurrentTeamSize();
	
	/**
	 * Retorna o nome do team no index (turn) no array teams
	 * @param turn - inteiro, indica o index do team a jogar no momento atual do jogo
	 * @pre turn != null
	 * @return nome do team no index (turn) no array teams
	 */
	String getTeamName(int turn);
	
	/**
	 * Incrementa a quantidede de players que o team com aquele index (turn) possui
	 * @param turn - inteiro, indica o index do team a jogar no momento atual do jogo
	 * @pre turn != null
	 */
	void incrementTeamPlayerSize(int turn);

	/**
	 * Redos a quantidede de players do team com aquele nome
	 * @param teamName - nome do player
	 * @pre teamNamw != null
	 */
	void decreaceTeamPlayerSize(String teamName);

	/**
	 * Incrementa a quantidede de bunkers que o team com aquele index (turn) possui
	 * @param turn - inteiro, indica o index do team a jogar no momento atual do jogo
	 * @pre turn != null
	 */
	void incrementTeamBunkerSize(int turn);

	/**
	 * Redos a quantidede de bunkers do team com aquele nome
	 * @param teamName - nome do player
	 * @pre teamNamw != null
	 */
	void decreaceTeamBunkerSize(String teamName);

	/**
	 * Retorna o index (turn) no array teams do team com aquele nome (teamName)
	 * @param teamName - nome do player
	 * @pre teamNamw != null
	 * @return index (turn) no array teams do team com aquele nome (teamName)
	 */
	int teamTurn(String teamName);

	/**
	 * Determina se o team com aquele index está fora de jogo
	 * @param idx - index do team no array teams a verificar 
	 * @pre idx != null
	 * @return verdadeiro se o team com aquele index está fora de jogo, falso caso contrario
	 */
	boolean isTeamDead(int idx);

	/**
	 * Atualisa a variavel de intancia currentTeamSize que indica o numero de teams ainda em jogo
	 */
	void updateDeadTeams();

	/**
	 * Determina se o jogo acabou ao verificar o valor da variavel de intancia currentTeamSize
	 * @return verdadeiro caso o jogo tenha acabado, falso caso contrario
	 */
	boolean isGameOver();

	/**
	 * Retorna o nome do vercedor do jogo ao verificar o ultimo team ainda em jogo
	 * @return nome do vercedor do jogo ao verificar o ultimo team ainda em jogo
	 */
	String getWinner();
}
