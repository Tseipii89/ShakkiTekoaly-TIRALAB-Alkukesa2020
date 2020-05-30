/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.rules;

import chess.elements.Board;
import chess.elements.File;
import chess.elements.Rank;
import chess.elements.Tile;

/**
 *
 * @author juhop
 */
public class NewTileCounter {
    
    public NewTileCounter() {
        
    }
    
    
    public Tile countNewTile(Tile currentTile, int sideMultiplier, int fileMovement, int rankMovement) {
        
        int newFileInt = currentTile.getFile().getIntegerFile() + fileMovement * sideMultiplier;
        int newRankInt = currentTile.getRank().getIntegerRank() + rankMovement * sideMultiplier;
        
        if (newFileInt < 1 || newFileInt > 8) {
            return null;
        }
        if (newRankInt < 1 || newRankInt > 8) {
            return null;
        }
        return new Tile(File.valueOfInteger(newFileInt), Rank.valueOfInteger(newRankInt));
    }
    
     /**
     *
     * Transforms the move String's tile to a actual Tile on Board.
     * 
     * @param move the move that holds the information about the Tile
     * @param start index of the starting point of the Tile info
     * @param end index of the end point of Tile info
     * @param board The Board on which the move should be updated 
     * @return The Tile referenced on the move. The Tile is associated with the game Board.
     */
    public Tile moveToTile(String move, int start, int end, Board board) {
        
        String currentFile = move.substring(start, start + 1);
        currentFile = currentFile.toLowerCase();
        int currentRank = Integer.parseInt(move.substring(end - 1, end));
        
        return board.getTile(File.valueOfLabel(currentFile), Rank.valueOfInteger(currentRank));
    }
    
}
