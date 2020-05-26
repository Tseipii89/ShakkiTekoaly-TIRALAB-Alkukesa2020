
package datastructureproject;

import chess.elements.Tile;
import pieces.Piece;

public class BoardValueCalculator {
 
    
    public BoardValueCalculator() {
        
    }
    
    public int boardValue(Tile startTile, Tile finishTile, int promotion) {
        int valueChange = 0;
        
        Piece pieceToMove = startTile.getPiece();
        if(finishTile.getPiece() != null) {
            if(finishTile.getPiece().getSide() != pieceToMove.getSide()) {
                valueChange -= finishTile.getPiece().getValue();
            }
        }
        
        return valueChange + promotion;
    }
    
}
