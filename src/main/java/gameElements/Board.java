
package gameElements;


public class Board {
    
    private Tile[] tiles;
    
    public Board() {
        tiles = new Tile[64];
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                File fileToAdd = File.valueOfLabel(j);
                Rank rankToAdd = Rank.valueOfLabel(i);
                Tile tileToAdd = new Tile(fileToAdd, rankToAdd);
                int index = 8*(i - 1) + j -1;
                tiles[index] = tileToAdd;
            } 
        }
    }
    
    public Tile[] getTilesList() {
        return tiles;
    }

    public Tile getTile(File newFile, Rank newRank) {
        int index = 8*(newRank.getIntegerRank() - 1) + newFile.getIntegerFile()-1;
        return tiles[index];
    }
    
}
