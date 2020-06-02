
package chess.rules;

import chess.elements.Board;
import chess.elements.Tile;
import chess.model.Side;
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
    
    /**
     * Class checks if given move will give opponent an opportunity to capture player's king.
     */
    public KingCheckedCounter() {
        this.random = new Random();
        newTileCounter = new NewTileCounter();
        movementGenerator = new MovementGenerator();
        moveValueCounter = new MoveValueCounter();
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
        Tile startTile = newTileCounter.moveToTile(move, 0, 2, checkBoard);
        Tile finishTile = newTileCounter.moveToTile(move, 2, 4, checkBoard);
        Piece startTilePiece = checkBoard.getTile(startTile.getFile(), startTile.getRank()).getPiece();
        Piece finishTilePiece = checkBoard.getTile(finishTile.getFile(), finishTile.getRank()).getPiece();

        // update the movement on Board to see what happens next
        movementGenerator.updateMovementOnBoard(move, checkBoard); 

        // Check all moves for opponent player and check if they will capture the king
        String[] movesToCheck = new String[0];
        movesToCheck = movementGenerator.countAllMoves(opponent, checkBoard, movesToCheck);
        boolean kingCaptured = false;
        // Let's see what is the best move for the opponent, if he/she doesn't do KingCheck
        if (movesToCheck.length > 0) {
            String moveToDo = this.moveToDoWithoutKingCheck(movesToCheck, opponent, checkBoard); 
            kingCaptured = this.isKingCaptured(opponent, moveToDo, checkBoard);
        }
        
        

        // set pieces back to situation before check
        startTile.setPiece(startTilePiece);
        finishTile.setPiece(finishTilePiece);

        // is our king free or captured
        return kingCaptured;
    }
    
     /**
     *
     * We want to check a move without the possibility of our king being in danger. 
     * 
     * @param moves all the moves possible for given Side
     * @param sideToCheck the Side who's moves we want to check
     * @param checkBoard the baord from which all the moves are to be checked
     * @return the best for given player, given all the moves and the baord's situation
     */
    private String moveToDoWithoutKingCheck(String[] moves, Side sideToCheck, Board checkBoard) {
        // current value of the checkBoard is set to 0. This counts the change in value, not the actual value of Board.
        int changeNow = 0; 
        // If all moves are equal in value, we want to return random move, and not for example the first move.
        String moveToReturn = moves[(random.nextInt(moves.length))]; 
        if (sideToCheck == Side.BLACK) {
            for (String move : moves) {
                // Black player wants to minimize the Board value. 
                // This means that there is a move that has better value for black than previous best (or initial 0)
                if (moveValueCounter.moveValueCount(move, -1, checkBoard) < changeNow) { 
                    // set the changeNow value to the new best value for Black (hence the -1 multiplier)
                    changeNow = moveValueCounter.moveValueCount(move, -1, checkBoard); 
                    moveToReturn = move; // set the returnable move to new best
                }
            }
        } else { // the side is White
            for (String move : moves) {
                // White player wants to maximize the Board value. 
                // This means that there is a move that has better value for white than previous best (or initial 0)
                if (moveValueCounter.moveValueCount(move, 1, checkBoard) > changeNow) { 
                    // set the changeNow value to the new best for White
                    changeNow = moveValueCounter.moveValueCount(move, 1, checkBoard); 
                    moveToReturn = move; // set the returnable move to new best
                }
            }
        }
        return moveToReturn;
    }
    
      /**
     *
     * Checks that will given opponent be able to capture the player's king.
     * 
     * @param opponent the opponent's Side who's possible king's capture we want to avoid
     * @param move the move that we want to check isn't capturing our king
     * @param board the Board from which we want to check that our king is not captured
     * @return true if opponent will capture our king, false otherwise
     */
    public boolean isKingCaptured(Side opponent, String move, Board board) {
        if (opponent == Side.WHITE) {
            if (moveValueCounter.moveValueCount(move, 1, board) >= 900) { // opponent will capture our King
                return true;
            }
        } else {
            if (moveValueCounter.moveValueCount(move, -1, board) <= -900) { // opponent will capture our king
                return true;
            }
        }
        return false;
    }
    
   
}
