/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public interface Player {

	//constantes
	public final String NORTH = "north";
	public final String SOUTH = "south";
	public final String WEST = "west";
	public final String EAST = "east";

	/**
	 * Incrementa ou redus as cordenadas do player (x e y) de acordo com a String where
	 * @param where - direção para a qual o player se movimenta
	 * @pre where == NORTH || where == SOUTH || where == WEST || where == EAST 
	 */
    void move(String where);

	/**
	 * @return a posição do player no eixo do x
	 */
    int getX();

	/**
	 * @return a posição do player no eixo do y
	 */
    int getY();

    /**
     * @return type do player
     */
    String getType();
    
    /**
     * @return falso se o player estiver ainda em jogo, verdadeiro caso contrario
     */
    boolean isPlayerDead();
    	
    /**
     * @return team do qual o player faz parte
     */
    String getTeam();

    /**
     * Define o player como fora de jogo
     */
    void eliminatePlayer();

    /**
     * Determina se o nosso player ganhou a batanha contra o outro players
     * @param type - tipo do player com o qual o nosso player vai batanhar
     * @pre type == RED || type == GREEN || type == BLUE
     * @return verdadeiro se o nosso player for do tipo vantajoso em relação
     * ao tipo do outro player, falço caso contrario
     */
    boolean winFight(String type);
}
