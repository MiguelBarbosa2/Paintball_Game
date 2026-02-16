/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public interface PlayerCollection {

	/**
	 * Acresenta um objeto do tipo Player ao array players
	 * @param type - tipo de player
	 * @param x - inteiro, posição do player no eixo do x
	 * @param y - inteiro, posição do player no eixo do y
	 * @param team - nome do team do qual o player faz parte
	 * @pre (type == RED || type == GREEN || type == BLUE) && x != null && y != null && team != null
	 */
	void addPlayer(String type, int x, int y, String team);
	
	/**
	 * Retorna um iterador de players que pretensem a um team especifico
	 * @param teamName - nome do team a iterar sobre
	 * @pre teamName != null
	 * @return itearador de players que pretensem a um team especifico 
	 */
	PlayerIterator teamPlayerIterator(String teamName);

	/**
	 * Retorna o ultimo objeto do tipo Player a ser criado e adicionado ao array players
	 * @return ultimo objeto do tipo Player a ser criado
	 */
	Player getLastCreatedPlayer();

	/**
	 * Retorna um objeto do tipo Player com as cordenadas x e y no array players
	 * @param x - inteiro, posição do player no eixo do x
	 * @param y - inteiro, posição do player no eixo do y
	 * @pre x != null && y != null
	 * @return objeto do tipo Player com as cordenadas x e y
	 */
	Player getPlayer(int x, int y);

}
