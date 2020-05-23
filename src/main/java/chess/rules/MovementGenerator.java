/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.rules;

import chess.model.Side;
import chess.elements.Board;
import chess.elements.Tile;
import pieces.Piece;

/**
 * Checks the piece in the tile and returns all the legal moves for that piece.
 * @author juhop
 */
public class MovementGenerator {
    
    /**
     * Creates MovementGenerator to be ready to be used.
     */
    public MovementGenerator() {
        
    }
    
    /**
     *
     * @param gameBoard the board used in the game. All references should be done to this board during the game.
     * @param tile the tile for which we want to check all possible moves during this turn.
     * @return array of all possible moves in Algebraic notation
     */
    public String[] pieceMovement(Board gameBoard, Tile tile) {
        
        Piece piece = gameBoard.getTile(tile.getFile(), tile.getRank()).getPiece();
        if (piece.getPieceType() == null) {
            return null;
        }
        
        int sideMultiplier = 1;
        if (piece.getSide() == Side.BLACK) {
            sideMultiplier = -1;
        }
        
        return piece.getMoves(gameBoard, tile, sideMultiplier);
        
    }
}
