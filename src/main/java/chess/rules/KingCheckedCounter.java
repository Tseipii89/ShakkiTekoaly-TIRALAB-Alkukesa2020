
package chess.rules;

import chess.elements.Board;
import chess.elements.Tile;
import chess.model.Side;
import datastructureproject.BoardStatusSaver;
import datastructureproject.MoveValueCounter;
import java.util.Random;
import pieces.Piece;

/**
 * Checks if opponent have an opportunity to capture player's king.
 * @author juhop
 */
public class KingCheckedCounter {
     /**
     * The Random is used to return random move.
     */
    private final Random random; 
     /**
     * Counts the value of the Board, given the positions of all pieces.
     */
    private final NewTileCounter newTileCounter;

    /**
     * Generates all moves for given side on given board.
     */
    private final MovementGenerator movementGenerator;

    /**
     * Returns the value of the Board.
     */
    private final MoveValueCounter moveValueCounter;

    private BoardStatusSaver boardStatusSaver; //public for testing purposes

    
    /**
     * Class checks if given move will give opponent an opportunity to capture player's king.
     */
    public KingCheckedCounter() {
        this.random = new Random();
        newTileCounter = new NewTileCounter();
        movementGenerator = new MovementGenerator();
        moveValueCounter = new MoveValueCounter();
        this.boardStatusSaver = new BoardStatusSaver();
    }
    
     
    /**
     *
     * Check's that is current player's king in danger if the player does the move given in method parameter.
     * Made public for testing purposes.
     * 
     * @param move the move that we want to check won't sacrifice our king
     * @param opponent the opponent's Side
     * @param checkBoard the board where all the checking is done
     * @return true if king will be captured because of this move, false if our king will be safe
     */
    public boolean kingInCheck(String move, Side opponent, Board checkBoard) {
        // we have to take the pieces into memory so, they can be added back
        this.boardStatusSaver.savePieces(move, checkBoard);
        // update the movement on Board to see what happens next
        movementGenerator.updateMovementOnBoard(move, checkBoard); 

        // Check all moves for opponent player and check if they will capture the king
        String[] movesToCheck = new String[0];
        movesToCheck = movementGenerator.countAllMoves(opponent, checkBoard, movesToCheck);
        boolean kingCaptured = false;
        // Let's see what is the best move for the opponent, if he/she doesn't do KingCheck
        if (movesToCheck.length > 0) {
            for (String moveToCheck : movesToCheck) {
                if (Math.abs(moveValueCounter.moveValueCount(moveToCheck, opponent.getMultiplier(), checkBoard)) >= 900) {
                    kingCaptured = true;
                }
            }
        }
        // set pieces back to situation before check
        this.boardStatusSaver.putSavedPiecesBack();

        // is our king free or captured
        return kingCaptured;
    } 
}
