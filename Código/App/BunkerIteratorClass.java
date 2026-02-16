/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public class BunkerIteratorClass implements BunkerIterator{
	
	//variaveis de instancia
	private Bunker[] bunkers;
	private int size;
	private int currentIdx;
	
	/**
	 * Construtor
	 * @param bunkers - array de objetos do tipo Bunker
	 * @param size - inteiro, numero de objetos Bunker
	 * @pre bunkers != null && size != null
	 */
	public BunkerIteratorClass(Bunker[] bunkers, int size) {
		this.bunkers = bunkers;
		this.size = size;
		currentIdx = 0;
	}

    @Override
	public boolean hasNext() {
		return currentIdx < size;
	}

    @Override
	public Bunker next() {
		return bunkers[currentIdx++];
	}
}
