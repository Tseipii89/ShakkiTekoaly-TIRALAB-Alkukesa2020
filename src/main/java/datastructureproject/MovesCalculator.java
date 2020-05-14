package datastructureproject;

import gameElements.Board;
import gameElements.Tile;
import gameElements.File;
import gameElements.Rank;


public class MovesCalculator {
    
    private Board gameBoard;
    
    public MovesCalculator(Board board) {
        this.gameBoard = board;
    }
    
    public Tile calculateNewTile(Tile startTile, int fileMove, int rankMove) {
        int newFileInteger = startTile.getFile().getIntegerFile() + fileMove;
        int newRankInteger = startTile.getRank().getIntegerRank() + rankMove;
        
        try {
            File newFile = File.valueOfLabel(newFileInteger);
            Rank newRank = Rank.valueOfLabel(newRankInteger);
            return gameBoard.getTile(newFile, newRank);
        } catch (Exception e) {
            System.out.println("The move is outside the board possibly...");
        }
        
        return null;
        
    }
}
