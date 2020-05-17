package pieces;

import chess.model.Side;
import gameElements.Board;
import gameElements.Tile;


public interface Piece {
    
    public Side getSide();
    
    public void setSide(Side side);
    
    public void setPieceType(PieceType type);
    
    public PieceType getPieceType();

    public String[] getMoves(Board gameBoard, Tile tile, int sideMultiplier);
  
}
