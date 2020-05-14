package pieces;

import chess.model.Side;


public interface Piece {
    
    public Side getSide();
    
    public void setSide(Side side);
    
    public void setPieceType(PieceType type);
    
    public PieceType getPieceType();
  
}
