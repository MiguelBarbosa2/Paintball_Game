/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;
public abstract class AbstractPlayerClass implements Player{

	//variaveis de instancia
    private boolean isDead;
    private String team;
    private int x;
    private int y;

    /**
     * Construtor da Class abstrata
     * @param x - inteiro, posição do player no eixo do x
     * @param y - inteiro, posição do player no eixo do y
     * @param team - nome do team do qual o player faz parte
     * @pre x != null && y != null && team != null
     */
    public AbstractPlayerClass(int x, int y , String team){
        this.x = x;
        this.y = y;
        this.isDead = false;
        this.team = team;
    }
    
    @Override
    public void move(String where){
        switch (where){
            case NORTH -> y--;
            case SOUTH -> y++;
            case WEST -> x--;
            case EAST -> x++;
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    
    @Override
    public boolean isPlayerDead() {
    	return isDead;
    }
    
    @Override
    public String getTeam() {
    	return team;
    }

    @Override
    public void eliminatePlayer() {
        isDead = true;
    }

    @Override
    public abstract String getType();
    
    @Override
    public abstract boolean winFight(String type);
}
