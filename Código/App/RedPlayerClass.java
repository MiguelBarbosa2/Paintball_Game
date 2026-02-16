/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public class RedPlayerClass extends AbstractPlayerClass{

	/**
     * Construtor da subClass abstrata
     * @param x - inteiro, posição do player no eixo do x
     * @param y - inteiro, posição do player no eixo do y
     * @param team - nome do team do qual o player faz parte
     * @pre x != null && y != null && team != null
     */
    public RedPlayerClass(int x, int y, String team){
        super(x,y,team);
    }

    @Override
    public String getType() {
        return Game.RED;
    }

    @Override
    public boolean winFight(String type) {
        return type.equals(Game.BLUE) || type.equals(Game.RED);
    }
}
