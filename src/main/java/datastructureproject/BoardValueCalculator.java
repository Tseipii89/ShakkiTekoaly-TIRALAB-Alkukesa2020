
package datastructureproject;

import chess.elements.Board;
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
    
    public int allTilesBoardValue(Board board){
        int value = 0;
        Tile[] tiles = board.getTilesList();
        for (int i = 0; i < 64; i++) {
            if(tiles[i].getPiece() != null) {
                value += tiles[i].getPiece().getValue();
            }
        }
        return value;
    }
    
}
