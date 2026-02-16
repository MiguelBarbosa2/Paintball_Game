/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public interface Team {

	/**
	 * @return nome do team
	 */
	String getTeamName();
	
	/**
	 * Incrementa a quantidede de bunkers que o team possui
	 */
	void incrementTeamBunkerSize();
	
	/**
	 * Redus a quantidede de bunkers que o team possui
	 */
	void decreaceTeamBunkerSize();
	
	/**
	 * @return numero de bunkers que o team possui
	 */
	int getTeamBunkerSize();
	
	/**
	 * Incrementa a quantidede de players que o team possui
	 */
	void incrementTeamPlayerSize();
	
	/**
	 * Redus a quantidede de players que o team possui
	 */
	void decreaceTeamPlayerSize();
	
	/**
	 * @return numero de players que o team possui
	 */
	int getTeamPlayerSize();
	
	/**
	 * Verifica se o team ainda está em jogo
	 * @return verdadeiro se o team estiver fora de jogo, falço caso contrario
	 */
	boolean teamIsDead();

}
