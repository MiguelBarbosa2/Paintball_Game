/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public interface Bunker {
	
	//constantes
	public final String WITHOUT_OWNER = "without owner";

	/**
	 * @return o nome do bunker
	 */
	String getBunkerName(); 
	
	/**
	 * Incrementa a quantidede de treasure presente no bunker
	 */
	void increaseTreasure();
	
	/**
	 * Redus a quantidede de treasure presente no bunker
	 * @param amount - quantidade de treasure que sera redusida do treasure total
	 * @pre amount != null
	 */
	void decreaseTreasure(int amount);
	
	/**
	 * Define o dono do bunker
	 * @param owner - dono do bunker
	 * @pre owner != null
	 */
	void setOwner(String owner);
	
	/**
	 * @return o dono do bunker
	 */
	String getOwner();

	/**
	 * @return o treasure presente no bunker
	 */
	int getTreasure();
	
	/**
	 * Define a ordem pela qual o bunker foi capturado
	 * @param order - inteiro, ordem pela qual o bunker foi capturado pelo team
	 * @pre order != null
	 */
	public void setOrder(int order);
	
	/**
	 * @return order do team
	 */
	int getOrder();

	/**
	 * @return a posição do bunker no eixo do x
	 */
	int getX();

	/**
	 * @return a posição do bunker no eixo do y
	 */
	int getY();
}
