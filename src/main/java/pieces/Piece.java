package pieces;

import chess.model.Side;
import chess.elements.Board;
import chess.elements.Tile;

/**
 * Piece interface that every piece has to implement. 
 * Every class and method depends on Piece interface, not on particular piece.
 * @author juhop
 */
public interface Piece {
    
    /**
     *
     * @return side of this piece.
     */
    Side getSide();
    
    /**
     *
     * @return type of this piece.
     */
    PieceType getPieceType();
    
    /**
     *
     * @return the value of this piece.
     */
    int getValue();

    /**
     * Returns all the moves this piece can do.
     * 
     * @param gameBoard the gameBoard to check all the moves.
     * @param tile the tiles where this piece is (the piece itself doesn't know where it is)
     * @param sideMultiplier -1 for black and 1 for white.
     * @return All the possible moves in String array.
     */
    String[] getMoves(Board gameBoard, Tile tile, int sideMultiplier);
  
}
