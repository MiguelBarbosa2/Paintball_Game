/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public class FilteredTeamIteratorClass implements TeamIterator{

	//variaveis de instancia
    private Team[] teams;
    private int size;
    private int currentIdx;

	/**
	 * Construtor
	 * @param teams - array de objetos do tipo Team
	 * @param size - inteiro, numero de objetos Team
	 * @pre teams != null && size != null
	 */
    public FilteredTeamIteratorClass(Team[] teams, int size) {
        this.teams = teams;
        this.size = size;
        currentIdx = -1;
        advance();
    }

    @Override
    public boolean hasNext() {
        return currentIdx < size;
    }

    @Override
    public Team next() {
        Team tmp = teams[currentIdx];
        advance();
        return tmp;
    }

    /*
     * Filtra o array teams de modo a iterar apenas aqueles que ainda estão no jogo
     */
    private void advance() {
        int i = currentIdx + 1;
        while (i < size && teams[i].teamIsDead()) {
            i++;
        }
        currentIdx = i;
    }
}
