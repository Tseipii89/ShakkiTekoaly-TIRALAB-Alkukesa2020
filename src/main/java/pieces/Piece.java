package pieces;

import chess.model.Side;
import chess.elements.Board;
import chess.elements.Tile;


public interface Piece {
    
    Side getSide();
    
    PieceType getPieceType();

    String[] getMoves(Board gameBoard, Tile tile, int sideMultiplier);
  
}
