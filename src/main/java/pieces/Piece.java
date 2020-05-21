package pieces;

import chess.model.Side;
import chess.elements.Board;
import chess.elements.Tile;


public interface Piece {
    
    public Side getSide();
    
    public PieceType getPieceType();

    public String[] getMoves(Board gameBoard, Tile tile, int sideMultiplier);
  
}
