/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public class FilteredPlayerIteratorClass implements PlayerIterator{

	//variaveis de instancia
    private Player[] players;
    private int size;
    private int currentIdx;
    private String team;

	/**
	 * Construtor
	 * @param players - array de objetos do tipo Player
	 * @param size - inteiro, numero de objetos Player
	 * @pre players != null && size != null
	 */
    public FilteredPlayerIteratorClass(Player[] players, int size, String team) {
        this.players = players;
        this.size = size;
        this.team = team;
        currentIdx = -1;
        advance();
    }

    @Override
    public boolean hasNext() {
        return currentIdx < size;
    }

    @Override
    public Player next() {
        Player tmp = players[currentIdx];
        advance();
        return tmp;
    }

    /*
     * Filtra o array players de modo a iterar apenas aqueles que fazem parte do team a jogar e que estejam em jogo
     */
    private void advance() {
        int i = currentIdx + 1;
        while(i < size && (!players[i].getTeam().equals(team) || players[i].isPlayerDead())) {
            i++;
        }
        currentIdx = i;
    }
}

