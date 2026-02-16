/**
 * @author André Marques Tavares - nº67161
 * @author Miguel Castanho Barbosa - nº68206
 */

package App;

public class MapClass implements Map{

    //Variáveis de instância
    private int width;
    private int heigth;
    private Cell map[][];

    /**
     * Construtor
     * @param width - o tamanho da dimensão horizontal do mapa.
     * @param height - o tamanho da dimensão vertical do mapa.
     * @Pre width != null && height !null.
     */
    public MapClass(int width, int height) {
        this.width = width;
        this.heigth = height;
        map = new Cell[width][height];
    }

    @Override
    public Player getPlayer(int x, int y) {
        return map[x][y].getPlayer();
    }

    @Override
    public void setPlayer(int x, int y, Player player) {
        map[x][y].setPlayer(player);
    }

    @Override
    public boolean isOccupied(int x, int y) {
        return map[x][y].isOccupied();
    }

    @Override
    public String getPlayerTeam(int x, int y) {
        return map[x][y].getPlayerTeam();
    }

    @Override
    public String getPlayerType(int x, int y) {
        return map[x][y].getPlayerType();
    }

    @Override
    public CellIterator rowMapIterator(int y, String team) {
        Cell[] rowOfMap = new Cell[width];
        for (int x = 0; x < width; x++) {
            if (isPartOfTheTeam(x, y, team)) {
                rowOfMap[x] = map[x][y];
            }
            else {
                rowOfMap[x] = new CellClass();
            }
        }
        return new CellIteratorClass(rowOfMap, width);
    }

    /**
     * Verifica se naquela coordenada o Player ou o Bunker presente pertence à equipa.
     * @param x - Coordenada x que queremos verificar.
     * @param y - Coordenada y que queremos verificar.
     * @param team - Nome da equipa que queremos verificar igualdade.
     * @return true se o Player ou o Bunker pertence(em) à equipa, caso contrário, retorna false.
     * @Pre - x != null && y != null && team != null.
     */
    private boolean isPartOfTheTeam(int x, int y, String team) {
        return map[x][y].getPlayerTeam().equals(team) || map[x][y].getBunkerOwner().equals(team);
    }

    @Override
    public void defaultMap(BunkerCollection bc) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                map[i][j] = new CellClass();
            }
        }
        for (int i = 0; i < bc.getBunkerSize(); i++) {
            Bunker bunker = bc.getBunker(i);
            map[bunker.getX() - 1][bunker.getY() - 1].setBunker(bunker);
        }
    }

    @Override
    public boolean isGoingOutOfBounds(int x, int y, String where) {
        boolean isGoingOut = false;
        switch (where){
            case Player.NORTH -> isGoingOut = (y == 0);
            case Player.SOUTH -> isGoingOut = (y == heigth - 1);
            case Player.WEST -> isGoingOut = (x == 0);
            case Player.EAST -> isGoingOut = (x == width - 1);
        }
        return isGoingOut;
    }

    @Override
    public boolean isTeamPlayerThere(int x, int y, String where) {
        boolean isPlayerThere = false;
        switch (where){
            case Player.NORTH -> isPlayerThere = map[x][y - 1].getPlayer() != null &&
                    map[x][y].getPlayerTeam().equals(map[x][y - 1].getPlayerTeam());
            case Player.SOUTH -> isPlayerThere = map[x][y + 1].getPlayer() != null &&
                    map[x][y].getPlayerTeam().equals(map[x][y + 1].getPlayerTeam());
            case Player.WEST -> isPlayerThere = map[x - 1][y].getPlayer() != null &&
                    map[x][y].getPlayerTeam().equals(map[x - 1][y].getPlayerTeam());
            case Player.EAST -> isPlayerThere = map[x + 1][y].getPlayer() != null &&
                    map[x][y].getPlayerTeam().equals(map[x + 1][y].getPlayerTeam());
        }
        return isPlayerThere;
    }

    @Override
    public int whatIsThere(int x, int y, String team, String where) {
        int inThere = 0;
        switch (where){
            case Player.NORTH -> inThere = foundWhatIsThere(x, y - 1, team);
            case Player.SOUTH -> inThere = foundWhatIsThere(x, y + 1, team);
            case Player.WEST -> inThere = foundWhatIsThere(x - 1, y, team);
            case Player.EAST -> inThere = foundWhatIsThere(x + 1, y, team);
        }
        return inThere;
    }
    @Override
    public int foundWhatIsThere(int x, int y, String team) {
        int inThere = NOTHING_THERE;
        boolean bunkerThere = map[x][y].getBunker() != null;
        boolean playerThere = map[x][y].getPlayer() != null;
        boolean notTeamBunkerThere = false;
        boolean notTeamPlayerThere = false;
        if (bunkerThere) {
            notTeamBunkerThere = !map[x][y].getBunkerOwner().equals(team);
        }
        if (playerThere) {
            notTeamPlayerThere = !map[x][y].getPlayerTeam().equals(team);
        }
        if (bunkerThere && playerThere && notTeamBunkerThere && notTeamPlayerThere) {
            inThere = ENEMY_BUNKER_AND_PLAYER;
        }
        else if (bunkerThere && notTeamBunkerThere) {
            inThere = BUNKER_THERE;
        }
        else if (playerThere && notTeamPlayerThere) {
            inThere = ENEMY_PLAYER_THERE;
        }
        return inThere;
    }

    @Override
    public void movePlayer(int x, int y, String where, Player player) {
        switch (where){
            case Player.NORTH -> {
                map[x][y].setPlayer(null);
                map[x][y - 1].setPlayer(player);
                map[x][y - 1].getPlayer().move(where);
            }
            case Player.SOUTH -> {
                map[x][y].setPlayer(null);
                map[x][y + 1].setPlayer(player);
                map[x][y + 1].getPlayer().move(where);
            }
            case Player.WEST -> {
                map[x][y].setPlayer(null);
                map[x - 1][y].setPlayer(player);
                map[x - 1][y].getPlayer().move(where);
            }
            case Player.EAST -> {
                map[x][y].setPlayer(null);
                map[x + 1][y].setPlayer(player);
                map[x + 1][y].getPlayer().move(where);
            }
        }
    }

    @Override
    public String seizeBunker(int x, int y, String team, int order, String where) {
        String teamName = Bunker.WITHOUT_OWNER;
        switch (where){
            case Player.NORTH -> {
                teamName = map[x][y - 1].getBunkerOwner();
                map[x][y - 1].getBunker().setOwner(team);
                map[x][y - 1].getBunker().setOrder(order);
            }
            case Player.SOUTH -> {
                teamName = map[x][y + 1].getBunkerOwner();
                map[x][y + 1].getBunker().setOwner(team);
                map[x][y + 1].getBunker().setOrder(order);
            }
            case Player.WEST -> {
                teamName = map[x - 1][y].getBunkerOwner();
                map[x - 1][y].getBunker().setOwner(team);
                map[x - 1][y].getBunker().setOrder(order);
            }
            case Player.EAST -> {
                teamName = map[x + 1][y].getBunkerOwner();
                map[x + 1][y].getBunker().setOwner(team);
                map[x + 1][y].getBunker().setOrder(order);
            }
        }
        return teamName;
    }

    @Override
    public String winFightByMove(int x, int y, String where) {
        String playerTeamName = PLAYER_LOSE;
        boolean playerTeamWin = false;
        switch (where){
            case Player.NORTH -> {
                playerTeamName = map[x][y - 1].getPlayerTeam();
                playerTeamWin = winFightPlayer(x, y, x, y - 1);
            }
            case Player.SOUTH -> {
                playerTeamName = map[x][y + 1].getPlayerTeam();
                playerTeamWin = winFightPlayer(x, y, x, y + 1);
            }
            case Player.WEST -> {
                playerTeamName = map[x - 1][y].getPlayerTeam();
                playerTeamWin = winFightPlayer(x, y, x - 1, y);
            }
            case Player.EAST -> {
                playerTeamName = map[x + 1][y].getPlayerTeam();
                playerTeamWin = winFightPlayer(x, y, x + 1, y);
            }
        }
        if(!playerTeamWin) {
            playerTeamName = PLAYER_LOSE;
        }
        return playerTeamName;
    }

    @Override
    public boolean winFightPlayer(int xPlayer, int yPlayer, int xEnemy, int yEnemy) {
        boolean playerTeamWin;
        String enemyType = map[xEnemy][yEnemy].getPlayerType();
        if (map[xPlayer][yPlayer].getPlayer().winFight(enemyType)) {
            playerTeamWin = true;
            map[xEnemy][yEnemy].getPlayer().eliminatePlayer();
            map[xEnemy][yEnemy].setPlayer(null);
        }
        else {
            playerTeamWin = false;
            map[xPlayer][yPlayer].getPlayer().eliminatePlayer();
            map[xPlayer][yPlayer].setPlayer(null);
        }
        return playerTeamWin;
    }

    @Override
    public String seizeBunkerWithCord(int x, int y, String team, int order) {
        String teamName = Bunker.WITHOUT_OWNER;
        teamName = map[x][y].getBunkerOwner();
        map[x][y].getBunker().setOwner(team);
        map[x][y].getBunker().setOrder(order);
        return teamName;
    }
}
