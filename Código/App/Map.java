/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public interface Map {

    //Constantes
    public final int NOTHING_THERE = 0;
    public final int ENEMY_BUNKER_AND_PLAYER = 1;
    public final int BUNKER_THERE = 2;
    public final int ENEMY_PLAYER_THERE = 3;
    public final String PLAYER_LOSE = "Player lose fight";

    /**
     * @param x - Coordenada x que queremos verificar.
     * @param y - Coordenada y que queremos verificar.
     * @return o Player nas coordenadas dadas.
     * @Pre - x != null && x >= 0 && x < width && y != null && y >= 0 && y < height.
     */
    Player getPlayer(int x, int y);

    /**
     * Coloca um Player na coordenada (x, y) dada.
     * @param x - Coordenada x que queremos colocar o Player.
     * @param y - Coordenada y que queremos colocar o Player.
     * @param player - O Player que queremos adicionar.
     * @Pre - x != null && x >= 0 && x < width && y != null && y >= 0 && y < height.
     */
    void setPlayer(int x, int y, Player player);

    /**
     * Verifica se o Bunker na coordenada (x, y) dada têm um Player.
     * @param x - Coordenada x que queremos verificar.
     * @param y - Coordenada y que queremos verificar.
     * @return true se o Bunker têm um Player, caso contrário, retorna false.
     * @Pre - @Pre - x != null && x >= 0 && x < width && y != null && y >= 0 && y < height.
     */
    boolean isOccupied(int x, int y);

    /**
     * Verifica a equipa do Player na coordenada (x, y) dada.
     * @param x - A coordenada x do Player.
     * @param y - A coordenada y do Player.
     * @return o nome da equipa do Player naquela coordenada.
     * @Pre - x != null && x >= 0 && x < width && y != null && y >= 0 && y < height.
     */
    String getPlayerTeam(int x, int y);

    /**
     * Verifica o tipo de Player na coordenada (x, y) dada.
     * @param x - A coordenada x do Player.
     * @param y - A coordenada y do Player.
     * @return o tipo de Player naquela coordenada.
     * @Pre - x != null && x >= 0 && x < width && y != null && y >= 0 && y < height.
     */
    String getPlayerType(int x, int y);

    /**
     * O iterador da linha de células y do mapa de uma equipa.
     * @param y - A linha que queremos iterar.
     * @param team - Nome da equipa que queremos a linha do mapa.
     * @return o iterador que contém a linha de células dada de uma equipa.
     * @Pre - y != null && y >= 0 && y < height && team != null.
     */
    CellIterator rowMapIterator(int y, String team);

    /**
     * Cria o mapa do jogo já com todos os Bunkers do jogo.
     * @param bc - Array dos Bunker do jogo que vamos adicionar ao mapa.
     */
    void defaultMap(BunkerCollection bc);

    /**
     * Verifica se o Player na coordenada (x,y) dada ao se mover na direção indicada vai sair para fora do mapa.
     * @param x - A coordenada x do Player.
     * @param y - A coordenada y do Player.
     * @param where - Direção que queremos verificar.
     * @return true se o Player na coordenada dada vai sair para fora do mapa ao se mover na direção
     * indicada, caso contrário, retorna false.
     * @Pre - x != null && x >= 0 && x < width && y != null && y >= 0 && y < height && where != null.
     */
    boolean isGoingOutOfBounds(int x, int y, String where);

    /**
     * Verifica se o Player na coordenada (x,y) dada ao se mover na direção indicada vai para o mesmo espaço
     * que um Player da mesma equipa.
     * @param x - A coordenada x do Player.
     * @param y - A coordenada y do Player.
     * @param where - Direção que queremos verificar.
     * @return true se o Player na coordenada dada vai para o mesmo espaço que um Player da mesma equipa,
     * caso contrário, retorna false.
     * @Pre - x != null && x >= 0 && x < width && y != null && y >= 0 && y < height && where != null.
     */
    boolean isTeamPlayerThere(int x, int y, String where);

    /**
     * Verifica para que posição o Player na coordenada (x,y) dada quer-se mover.
     * @param x - A coordenada x do Player.
     * @param y - A coordenada y do Player.
     * @param team - O nome da equipa a jogar de momento.
     * @param where - Direção que queremos verificar.
     * @return 0 se não têm nada, 1 se têm um Bunker e Player inimigo, 2 se estiver um Bunker que não pertence
     * à equipa do Player, 3 se estiver um Player inimigo.
     * @Pre - x != null && x >= 0 && x < width && y != null && y >= 0 && y < height && team != null && where != null.
     */
    int whatIsThere(int x, int y, String team, String where);

    /**
     * Verifica o que está na coordenada (x,y) dada.
     * @param x - A coordenada x que queremos verificar.
     * @param y - A coordenada y que queremos verificar.
     * @param team - O nome da equipa a jogar de momento.
     * @return 0 se não têm nada, 1 se têm um Bunker e Player inimigo, 2 se estiver um Bunker que não pertence
     * à equipa do Player, 3 se estiver um Player inimigo.
     * @Pre - x != null && x >= 0 && x < width && y != null && y >= 0 && y < height && team != null.
     */
    int foundWhatIsThere(int x, int y, String team);

    /**
     * Utilizado para mover o Player na coordenada (x, y) dada.
     * @param x - A coordenada x do Player.
     * @param y - A coordenada y do Player.
     * @param where - Direção que queremos mover.
     * @param player - O Player que queremos mover.
     * @Pre - x != null && x >= 0 && x < width && y != null && y >= 0 && y < height && where != null && player != null.
     */
    void movePlayer(int x, int y, String where, Player player);

    /**
     * Captura o Bunker na direção indicada a partir da coordenada (x, y) dada de um Player.
     * @param x - A coordenada x do Player.
     * @param y - A coordenada y do Player.
     * @param team - A equipa a jogar de momento.
     * @param order - Ordem pela qual o bunker foi capturado pelo team.
     * @param where - Direção que queremos capturar.
     * @return o nome do antigo dono do Bunker, ou sem dono caso não o tivesse.
     * @Pre - x != null && x >= 0 && x < width && y != null && y >= 0 && y < height &&
     * team != null && order != null && where != null.
     */
    String seizeBunker(int x, int y, String team, int order, String where);

    /**
     * Executa a luta entre um Player na coordenada (x, y) dada com um inimigo na direção indicada.
     * @param x - A coordenada x do Player.
     * @param y - A coordenada y do Player.
     * @param where - Direção que queremos verificar.
     * @return nome do Player inimigo caso o Player na coordenada
     * dada ganhe ou uma constante que indica que o Player nas coordenadas dadas perdeu.
     * @Pre - x != null && x >= 0 && x < width && y != null && y >= 0 && y < height && where != null.
     */
    String winFightByMove(int x, int y, String where);

    /**
     * Executa a luta entre um Player na coordenada (xPlayer, yPlayer)
     * dada com um inimigo na coordenada (xEnemy, yEnemy) dada.
     * @param xPlayer - A coordenada x do Player.
     * @param yPlayer - A coordenada y do Player.
     * @param xEnemy - A coordenada x do inimigo.
     * @param yEnemy - A coordenada y do inimigo.
     * @return true se o Player ganhou, caso contrário, retorna false.
     * @Pre - xPlayer != null && xPlayer >= 0 && xPlayer < width &&
     * yPlayer != null && yPlayer >= 0 && yPlayer < height &&
     * xEnemy != null && xEnemy >= 0 && xEnemy < width &&
     * yEnemy != null && yEnemy >= 0 && yEnemy < height && (xPlayer, yPlayer) != (xEnemy, yEnemy).
     */
    boolean winFightPlayer(int xPlayer, int yPlayer, int xEnemy, int yEnemy);

    /**
     * Captura o Bunker na direção indicada a partir das coordenadas (x, y) dadas de um Player.
     * @param x - A coordenada x do Player.
     * @param y - A coordenada y do Player.
     * @param team - A equipa a jogar de momento.
     * @param order - Ordem pela qual o bunker foi capturado pelo team.
     * @param where - Direção que queremos capturar.
     * @return o nome do antigo dono do Bunker, ou sem dono caso não o tivesse.
     * @Pre - x != null && x >= 0 && x < width && y != null && y >= 0 && y < height &&
     * team != null && order != null && where != null.
     */
    /**
     * Captura o Bunker nas coordenada (x, y) dada.
     * @param x - A coordenada x do Bunker.
     * @param y - A coordenada y do Bunker.
     * @param team - A equipa a jogar de momento.
     * @param order - Ordem pela qual o bunker foi capturado pelo team.
     * @return o nome do antigo dono do Bunker, ou sem dono caso não o tivesse.
     * @Pre - x != null && x >= 0 && x < width && y != null && y >= 0 && y < height &&
     * team != null && order != null.
     */
    String seizeBunkerWithCord(int x, int y, String team, int order);
}
