/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

/**
 * Um iterador de uma coleção de cells
 */
public interface CellIterator {
	/**
	 * Retorna verdade se a iteração tever mais elementos
	 * @return verdade se a iteração tem mais elementos
	 */
    public boolean hasNext();

	/**
	 * Retorna o elemento a seguir na iteração
	 * @return o proximo elemento na iteração
	 * @pre this.hasNext()
	 */
    public Cell next();
}
