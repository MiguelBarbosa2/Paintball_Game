/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public interface BunkerCollection {
	
	/**
	 * Determina se bunker dado pretence ao team dado
	 * @param bunkerName - nome do bunker
	 * @param teamName - nome do team
	 * @pre bunkerName != null && teamName != null
	 * @return verdadeiro se o bunker com o nome bunkerName pertencer
	 * ao tem com o nome teamName, falso caso contrario
	 */
	boolean bunkerBelongsToTeam(String bunkerName, String teamName);

	/**
	 * Acresenta um objeto do tipo Bunker ao array bunkers
	 * @param x - inteiro, posição do bunker no eixo do x
	 * @param y - inteiro, posição do bunker no eixo do y
	 * @param treasure - inteiro, quatidade de treasure presente no bunker
	 * @param bunkerName - nome do bunker 
	 * @pre x != null && y != null && treasure != null && bunkerName != null
	 */
	void addBunker(int x, int y, int treasure, String bunkerName);
	
	/**
	 * Verifica se exite um bunker com esse nome no array bunkers
	 * @param bunkerName - nome do bunker
	 * @pre bunkerName != null
	 * @return verdadeiro se existir um bunker com esse nome, falso caso contrario
	 */
	boolean bunkerExists(String bunkerName);
	
	/**
	 * Verifica se ja existe um team assuciado ao bunker com esse nome
	 * @param bunkerName - nome do bunker
	 * @pre bunkerName != null
	 * @return verdadeiro se existe um team assuciado ao bunker com esse nome, falso caso contrario
	 */
	boolean bunkerAlreadyInUse(String bunkerName);
	
	/**
	 * Retorna um iterador com todos os bunker no array bunkers
	 * @return iterador com todos os bunker no array bunkers
	 */
	BunkerIterator bunkerIterator();
	
	/**
	 * Retorna o treasure do bunker com esse nome
	 * @param bunkerName - nome do bunker
	 * @pre bunkerName != null
	 * @return treasure do bunker com esse nome
	 */
	int getTreasure(String bunkerName);
	
	/**
	 * Define o dono (teamName) do bunker com o nome bunkerName e a sua ordem de captura
	 * @param bunkerName - nome do bunker
	 * @param teamName - nome do team
	 * @param bunkerOrder - inteiro, ordem em que o team foi capturado
	 * @pre bunkerName != null && teamName != null && bunkerOrder != null
	 */
	void setOwner(String bunkerName, String teamName, int bunkerOrder);
	
	/**
	 * Redus o tesouro do bunker com esse nome de acordo com o amount
	 * @param bunkerName - nome do bunker
	 * @param amount - quantidade de treasure que sera redusida do treasure do bunker
	 * @pre bunkerName != null && amount != null
	 */
	void decreaseTreasure(String bunkerName, int amount);
	
	/**
	 * Determina se existe um bunker nas cordenadas x e y
	 * @param x - inteiro, posição do bunker no eixo do x
	 * @param y - inteiro, posição do bunker no eixo do y
	 * @pre x != null && y != null
	 * @return verdadeiro se existir um bunker naquelas cordenadas, falso caso contrario
	 */
	boolean bunkerInThatPosition(int x, int y);
	
	/**
	 * @param bunkerName - nome do bunker
	 * @pre bunkerName != null 
	 * @return cordenada x do bunker com aquele nome
	 */
	int getX(String bunkerName);
	
	/**
	 * @param bunkerName - nome do bunker
	 * @pre bunkerName != null 
	 * @return cordenada y do bunker com aquele nome
	 */
	int getY(String bunkerName);
	
	/**
	 * Retorna um iterador de bunkers que pretensem a um team especifico
	 * @param teamName - nome do team a iterar sobre
	 * @pre teamName != null
	 * @return itearador de bunkers que pretensem a um team especifico 
	 */
	BunkerIterator teamBunkerIterator(String teamName);
	
	/**
	 * @return size do array bunkers
	 */
	int getBunkerSize();

	/**
	 * Retorna um objeto do tipo Bunker que tem o index i no array
	 * @param i - index do bunker a returnar
	 * @pre i != null
	 * @return objeto do tipo Bunker que tem o index i no array
	 */
	Bunker getBunker(int i);

	/**
	 * Incrementa o treasure de todos os bunkers no array bunkers
	 */
	void increaseTreasure();
}
