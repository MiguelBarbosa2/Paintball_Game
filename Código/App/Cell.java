/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */
package App;

public interface Cell {

    /**
     * @return o Bunker guardado na celula.
     */
    Bunker getBunker();

    /**
     * @return o Player guardado na celula.
     */
    Player getPlayer();

    /**
     * Coloca um novo jogador ou retira nesta celula.
     * @param player - O novo jogador.
     */
    void setPlayer(Player player);

    /**
     * Coloca um Bunker nesta celula.
     * @param bunker - O novo Bunker.
     * @Pre - bunker != null.
     */
    void setBunker(Bunker bunker);

    /**
     * @return true se estiver um Player e um Bunker definido nesta celula, caso contrário, retorna false.
     */
    boolean isOccupied();

    /**
     * @return o nome do dono do Bunker nesta celula.
     */
    String getBunkerOwner();

    /**
     * @return o nome da equipa do Player nesta celula.
     */
    String getPlayerTeam();

    /**
     * @return o tipo de Player nesta celula.
     */
    String getPlayerType();

    /**
     * @return o caracter equivalente ao que está presente nesta celula.
     */
    char getEquivalentChar();
}
