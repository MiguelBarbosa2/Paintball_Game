/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public class FilteredBunkerIteratorClass implements BunkerIterator{

	//variaveis de instancia
    private Bunker[] bunkers;
    private int size;
    private int currentIdx;
    private String owner;

	/**
	 * Construtor
	 * @param bunkers - array de objetos do tipo Bunker
	 * @param size - inteiro, numero de objetos Bunker
	 * @pre bunkers != null && size != null
	 */
    public FilteredBunkerIteratorClass(Bunker[] bunkers, int size, String owner) {
        this.bunkers = bunkers;
        this.size = size;
        this.owner = owner;
        currentIdx = -1;
        advance();
    }

    @Override
    public boolean hasNext() {
        return currentIdx < size;
    }

    @Override
    public Bunker next() {
        Bunker tmp = bunkers[currentIdx];
        advance();
        return tmp;
    }

    /*
     * Filtra o array bunkers de modo a iterar apenas aqueles que fazem parte do team a jogar
     */
    private void advance() {
        int i = currentIdx + 1;
        while(i < size && !bunkers[i].getOwner().equals(owner)) {
            i++;
        }
        currentIdx = i;
    }
}
