/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */
package App;

public class CellClass implements Cell{

	//Constantes
	private static final char BUNKER_OCCUPIED = 'O';
	private static final char PLAYER_HERE = 'P';
	private static final char BUNKER_HERE = 'B';
	private static final char NOTHING_HERE = '.';

	//Variáveis de instância
	private Player player;
	private Bunker bunker;

	//Construtor
	public CellClass() {
		this.player = null;
		this.bunker = null;
	}

	@Override
	public Bunker getBunker() {
		return bunker;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public void setBunker(Bunker bunker) {
		this.bunker = bunker;
	}

	@Override
	public boolean isOccupied() {
		return (player != null && bunker != null);
	}

	@Override
	public String getBunkerOwner() {
		if (bunker == null) {
			return "";
		}
		else {
			return bunker.getOwner();
		}
	}

	@Override
	public String getPlayerTeam() {
		if (player == null) {
			return "";
		}
		else {
			return player.getTeam();
		}
	}

	@Override
	public String getPlayerType() {
		return player.getType();
	}

	@Override
	public char getEquivalentChar() {
		if (isOccupied()) {
			return BUNKER_OCCUPIED;
		}
		else if (player != null) {
			return PLAYER_HERE;
		}
		else if (bunker != null) {
			return BUNKER_HERE;
		}
		else {
			return NOTHING_HERE;
		}
	}
}
