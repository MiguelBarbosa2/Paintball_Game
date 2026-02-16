/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public class BunkerCollectionClass implements BunkerCollection{

	//variaveis de instancia
	private Bunker[] bunkers;
	private int bunkerSize;
	
	/**
	 * Construtor
	 * @param bunkerNumber - inteiro, numero maximo de bunkers possivel
	 * @pre bunkerNumber != null
	 */
	public BunkerCollectionClass(int bunkerNumber) {
		this.bunkers = new Bunker[bunkerNumber];
		this.bunkerSize = 0;
	}
	
    @Override
	public boolean bunkerBelongsToTeam(String bunkerName, String teamName) {
		return bunkers[searchIndexBunker(bunkerName)].getOwner().equals(teamName);
	}
	
    @Override
	public void addBunker(int x, int y, int treasure, String bunkerName) {
		bunkers[bunkerSize++] = new BunkerClass(x, y, treasure, bunkerName);
	}
	
    @Override
	public boolean bunkerExists(String bunkerName) {
		if(bunkerSize == 0) {
			return false;
		}
		else {
			return searchIndexBunker(bunkerName) >= 0;
		}
	}
	
    @Override
	public boolean bunkerAlreadyInUse(String bunkerName) {
		return !bunkers[searchIndexBunker(bunkerName)].getOwner().equals(Bunker.WITHOUT_OWNER);
	}
	
    @Override
	public BunkerIterator bunkerIterator() {
		return new BunkerIteratorClass(bunkers, bunkerSize);
	}
	
    @Override
	public void setOwner(String bunkerName, String teamName, int bunkerOrder) {
		bunkers[searchIndexBunker(bunkerName)].setOrder(bunkerOrder);
		bunkers[searchIndexBunker(bunkerName)].setOwner(teamName);
	}
	
    @Override
	public void decreaseTreasure(String bunkerName, int amount) {
		bunkers[searchIndexBunker(bunkerName)].decreaseTreasure(amount);
	}
	
    @Override
	public int getTreasure(String bunkerName) {
		return bunkers[searchIndexBunker(bunkerName)].getTreasure();
	}
	
    @Override
	public int getX(String bunkerName) {
		return bunkers[searchIndexBunker(bunkerName)].getX();
	}
	
    @Override
	public int getY(String bunkerName) {
		return bunkers[searchIndexBunker(bunkerName)].getY();
	}
	
    @Override
	public int getBunkerSize() {
		return bunkerSize;
	}
    
    @Override
	public Bunker getBunker(int i) {
		return bunkers[i];
	}
	
    @Override
	public void increaseTreasure() {
		for(int i = 0; i < bunkerSize; i++) {
			bunkers[i].increaseTreasure();
		}
	}
	
    @Override
	public boolean bunkerInThatPosition(int x, int y) {
		if(bunkerSize == 0) {
			return false;
		}
		else {
			return searchIndexBunkerXY(x, y) >= 0;
		}
	}
	
    @Override
	public BunkerIterator teamBunkerIterator(String teamName) {
		Bunker[] aux = new Bunker[bunkerSize];
		for(int i = 0; i < bunkerSize; i++) {
			aux[i] = bunkers[i];
		}	
		selectionSort(aux);
		return new FilteredBunkerIteratorClass(aux, bunkerSize, teamName);
	}
	
    /**
     * Este metodo organiza os bunkers em ordem cresente de acordo com a sua ordem de captura 
     * @param bunkers - array de objetos do tipo Bunker
     * @pre bunkers != null
     */
	private void selectionSort(Bunker[] bunkers) {
		for(int i = 0; i < bunkers.length - 1; i++) {
			int idxOfMin = i;
			for(int d = i + 1; d < bunkers.length; d++) {
				if(bunkers[d].getOrder() <= bunkers[idxOfMin].getOrder()) {
					idxOfMin = d;
				}
			}
			Bunker tmp = bunkers[i];
			bunkers[i] = bunkers[idxOfMin];
			bunkers[idxOfMin] = tmp;
		}
	}
	
	/**
     * Determina e retorna o index do bunker no array bunkers de acordo com o bunkerName
     * @param bunkerName - nome do bunker a incontrar o index
     * @pre bunkerName != null
     * @return index do bunker com esse nome, caso não exista um bunker com esse noma retorna -1
     */
    private int searchIndexBunker(String bunkerName) {
        int i = 0;
        int result = -1;
        boolean found = false;
        while (i < bunkerSize && !found)
            if (bunkers[i].getBunkerName().equals(bunkerName))
                found = true;
            else
                i++;
        if (found) result = i;
        return result;
    }
    
    /**
     * Determina e retorna o index do bunker no array bunkers de acordo com o x e o y
     * @param x - inteiro, posição do bunker no eixo do x
     * @param y - inteiro, posição do bunker no eixo do y
     * @pre x != null && y != null
     * @return index do bunker com essas cordenadas, caso não exista um bunker com essas cordenadas retorna -1
     */
	private int searchIndexBunkerXY(int x, int y) {
		int i = 0;
		int result = -1;
		boolean found = false;
		while (i < bunkerSize && !found)
			if (bunkers[i].getX() == x && bunkers[i].getY() == y) {
				found = true;
			}
			else {
				i++;
			}
		if (found) result = i;
		return result;
	}
}
