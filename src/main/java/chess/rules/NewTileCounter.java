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
 * Counts the new tile given the movements information.
 * @author juhop
 */
public class NewTileCounter {
    
    /**
     * NewTileCounter ready to be used.
     */
    public NewTileCounter() {
        
    }
    
    /**
     *
     * Counts new tile on the board, given startTile, side, changes in rank and file, and gameBoard. 
     * 
     * @param currentTile the startTile.
     * @param sideMultiplier which side to count the new tile. -1 for black and 1 for white.
     * @param fileMovement how many files to move.
     * @param rankMovement how many ranks to move.
     * @param board the board on which to count the new move.
     * @return the new tile on given board computed from info above. 
     */
    public Tile countNewTile(Tile currentTile, int sideMultiplier, int fileMovement, int rankMovement, Board board) {
        
        int newFileInt = currentTile.getFile().getIntegerFile() + fileMovement * sideMultiplier;
        int newRankInt = currentTile.getRank().getIntegerRank() + rankMovement * sideMultiplier;
        
        if (newFileInt < 1 || newFileInt > 8) {
            return null;
        }
        if (newRankInt < 1 || newRankInt > 8) {
            return null;
        }
        
        return board.getTile(File.valueOfInteger(newFileInt), Rank.valueOfInteger(newRankInt));
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
