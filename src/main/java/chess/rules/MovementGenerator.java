/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.rules;

import chess.model.Side;
import gameElements.Board;
import gameElements.Tile;
import pieces.Piece;
import pieces.PieceType;

/**
 *
 * @author juhop
 */
public class MovementGenerator {
    
    public MovementGenerator() {
        
    }
    
    public String[] pieceMovement(Board gameBoard, Tile tile) {
        
        Piece piece = gameBoard.getTile(tile.getFile(), tile.getRank()).getPiece();
        if(piece.getPieceType() != PieceType.Pawn) {
            return null;
        }
        
        int sideMultiplier = 1;
        if (piece.getSide() == Side.BLACK) sideMultiplier = -1;
        
        return piece.getMoves(gameBoard, tile, sideMultiplier);
        
    }
}
