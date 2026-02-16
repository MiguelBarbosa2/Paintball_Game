/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public class BunkerClass implements Bunker{

	//variaveis de instancia
	private int x;
	private int y;
	private int treasure;
	private int bunkerOrder;
	private String bunkerName;
	private String owner;

	/**
	 * Construtor
	 * @param x - inteiro, posição do bunker no eixo do x
	 * @param y - inteiro, posição do bunker no eixo do y
	 * @param treasure - inteiro, quatidade de treasure presente no bunker
	 * @param bunkerName - nome do bunker 
	 * @pre x != null && y != null && treasure != null && bunkerName != null
	 */
	public BunkerClass(int x, int y, int treasure, String bunkerName) {
		this.x = x;
		this.y = y;
		this.treasure = treasure;
		this.bunkerOrder = 0;
		this.bunkerName = bunkerName;
		this.owner = WITHOUT_OWNER;
	}
	
    @Override
	public String getBunkerName() {
		return bunkerName;
	}
	
    @Override
	public void increaseTreasure() {
		treasure++;
	}
	
    @Override
	public void decreaseTreasure(int amount) {
		treasure = treasure - amount;
	}
	
    @Override
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
    @Override
	public String getOwner() {
		return owner;
	}
	
    @Override
	public void setOrder(int order) {
		bunkerOrder = order;
	}
	
    @Override
	public int getOrder() {
		return bunkerOrder;
	}

    @Override
	public int getTreasure(){
		return treasure;
	}

    @Override
	public int getX(){
		return x;
	}

    @Override
	public int getY(){
		return y;
	}
}
