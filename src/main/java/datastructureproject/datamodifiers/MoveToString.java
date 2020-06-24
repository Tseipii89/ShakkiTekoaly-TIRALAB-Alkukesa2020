/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructureproject.datamodifiers;

import chess.elements.Tile;

/**
 * 
 * Creates String movement from given start and finish tile.
 * 
 * @author juhop
 */
public class MoveToString {
    
    public MoveToString() {
        
    }
    
     /**
     *
     * Transforms the given movement tiles to a String presentation of the move.
     * 
     * @param start The tile where the piece moves from.
     * @param finish The tile where the piece moves to.
     * @param canPromotion If piece is pawn and can promote. 
     * @return the String presentation of the movement.
     */
    public String createMovementString(Tile start, Tile finish, double canPromotion) {
        String basic;
        if (start.getRank().getIntegerRank() == canPromotion) {
            basic = start.getFile().toString() // start file as a String
                    + start.getRank().toString() // start rank as a String 
                    + finish.getFile().toString() // finish file as a String
                    + finish.getRank().toString() // finish rank as a String
                    + "q"; // all pawns are promoted to Queens
        } else {
            basic = start.getFile().toString() // start file as a String
                    + start.getRank().toString() // start rank as a String 
                    + finish.getFile().toString() // finish file as a String
                    + finish.getRank().toString(); // finish rank as a String
        } 
        return basic;
    }
    
}
