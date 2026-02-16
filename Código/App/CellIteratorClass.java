/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public class CellIteratorClass implements CellIterator{

	//variaveis de instancia
    private Cell[] rowOfMap;
    private int size;
    private int currentIdx;

	/**
	 * Construtor
	 * @param rowOfMap - array de objetos do tipo Cell
	 * @param size - inteiro, numero de objetos Cell
	 * @pre rowOfMap != null && size != null
	 */
    public CellIteratorClass(Cell[] rowOfMap, int size) {
        this.rowOfMap = rowOfMap;
        this.size = size;
        currentIdx = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIdx < size;
    }

    @Override
    public Cell next() {
        return rowOfMap[currentIdx++];
    }
}
