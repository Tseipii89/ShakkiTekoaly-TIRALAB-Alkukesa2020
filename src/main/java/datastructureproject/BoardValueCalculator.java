
package datastructureproject;

import chess.elements.Board;
import chess.elements.Tile;
import chess.model.Side;
import pieces.Piece;

/**
 *
 * Board Value Calculator calculates the whole board's value and
 * value of independent single move that player might do.
 * 
 * @author juhop
 */
public class BoardValueCalculator {
 
    /**
     * Creates new Board Value Calculator ready to be used.
     */
    public BoardValueCalculator() {
        
    }
    
    /**
     *
     * Calculates the value change that can be achieved by doing the intended move.
     * 
     * @param startTile where the move starts.
     * @param finishTile where the move ends.
     * @param promotion if the piece is being promoted.
     * @return value change after the move.
     */
    public int boardValue(Tile startTile, Tile finishTile, int promotion) {
        int valueChange = 0;
        int startIndex = 8 * (startTile.getRank().getIntegerRank() - 1) + startTile.getFile().getIntegerFile() - 1;
        int finishIndex = 8 * (finishTile.getRank().getIntegerRank() - 1) + finishTile.getFile().getIntegerFile() - 1;
        Piece pieceToMove = startTile.getPiece();
        if (finishTile.getPiece() != null) {
            if (finishTile.getPiece().getSide() != pieceToMove.getSide()) {
                Piece checkPiece = finishTile.getPiece();
                valueChange -= checkPiece.getValue() * checkPiece.getSide().getMultiplier();
                if(checkPiece.getSide() == Side.BLACK) {
                    valueChange -= checkPiece.getPositionValue(63 - finishIndex) * checkPiece.getSide().getMultiplier();
                } else {
                    valueChange -= checkPiece.getPositionValue(finishIndex) * checkPiece.getSide().getMultiplier();
                }
            }
        }
        if(pieceToMove.getSide() == Side.BLACK) {
            valueChange += pieceToMove.getPositionValue(63 - startIndex);
            valueChange -= pieceToMove.getPositionValue(63 - finishIndex);
        } else {
            valueChange -= pieceToMove.getPositionValue(startIndex);
            valueChange += pieceToMove.getPositionValue(finishIndex);
        }
        
        return valueChange + promotion * pieceToMove.getSide().getMultiplier();
    }
    
    /**
     *
     * Calculates the whole game Board's value.
     * Whites are + and Blacks are -.
     * 
     * @param board to be calculated.
     * @return the value of board.
     */
    public int allTilesBoardValue(Board board) {
        int value = 0;
        Tile[] tiles = board.getTilesList();
        for (int i = 0; i < 64; i++) {
            if (tiles[i].getPiece() != null) {
                Piece checkPiece = tiles[i].getPiece();
                value += checkPiece.getValue() * checkPiece.getSide().getMultiplier();
                if(checkPiece.getSide() == Side.BLACK) {
                    value -= checkPiece.getPositionValue(63 - i);
                } else {
                    value += checkPiece.getPositionValue(i);
                }
            }
        }
        return value;
    }
    
}
