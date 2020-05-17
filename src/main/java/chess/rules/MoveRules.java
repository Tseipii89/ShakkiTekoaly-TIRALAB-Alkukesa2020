
package chess.rules;

import gameElements.File;
import gameElements.Rank;
import gameElements.Tile;

public class MoveRules {
    
    public MoveRules() {
        
    }
    
    public Tile countNewTile(Tile currentTile, int sideMultiplier, int fileMovement, int rankMovement) {
        
        int newFileInt = currentTile.getFile().getIntegerFile() + fileMovement*sideMultiplier;
        int newRankInt = currentTile.getRank().getIntegerRank() + rankMovement*sideMultiplier;
        
        return new Tile(File.valueOfLabel(newFileInt), Rank.valueOfLabel(newRankInt));
    }

    public String[] addNewMoveToArray(String[] moves, String move) {
       int n = moves.length;
       String[] newMoves = new String[n+1];
       
        for (int j = 0; j < n; j++) {
            newMoves[j] = moves[j];
            
        }
        
        newMoves[n] = move;
        
        return newMoves;
    }
}
