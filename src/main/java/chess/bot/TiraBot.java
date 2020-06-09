package chess.bot;

import chess.elements.Board;
import chess.engine.GameState;
import chess.model.Side;
import chess.rules.KingCheckedCounter;
import chess.rules.MovementGenerator;
import datastructureproject.MoveValueCounter;
import datastructureproject.datamodifiers.ArrayModifier;
import java.util.Random;


/**
 *
 * @author juhop
 */
public class TiraBot implements ChessBot {
    
    /**
     * The Random is used to return random move.
     */
    private final Random random; 

    /**
     * Game board to hold the current game piece positions.
     */
    private final Board currentGameBoard;   
    
    /**
     * Counts all moves for all Pieces.
     * @see chess.rules.MovementGenerator
     */
    private final MovementGenerator movementgenerator;
    
    /**
     * Counts the value of the Board, given the positions of all pieces.
     */

    private final ArrayModifier arrayModifier;

    /**
     * Counts the value of the Board after given movement.
     */
    private final MoveValueCounter moveValueCounter;
    
    /**
     * Checks if opponent can check our king if we do this move.
     */
    private final KingCheckedCounter kingChecked;
    
    /**
     * The depth how many steps to check down MIN-MAX tree.
     */
    private int depth;

    /**
     * Tirabot is the implementation for the algorithms and data structures course.
     * It counts the best next move for the given the limitations. 
     */
    public TiraBot() {
        this.random = new Random();
        this.currentGameBoard = new Board();
        currentGameBoard.initBoard();
        movementgenerator = new MovementGenerator();
        arrayModifier = new ArrayModifier();
        moveValueCounter = new MoveValueCounter();
        kingChecked = new KingCheckedCounter();
        this.depth = 2;
    }
    
    /**
     *
     * Tirabot is the implementation for the algorithms and data structures course.
     * It counts the best next move for the given the limitations. 
     * 
     * @param depth counts the moves down this many steps.
     */
    public TiraBot(int depth) {
        this.random = new Random();
        this.currentGameBoard = new Board();
        currentGameBoard.initBoard();
        movementgenerator = new MovementGenerator();
        arrayModifier = new ArrayModifier();
        moveValueCounter = new MoveValueCounter();
        kingChecked = new KingCheckedCounter();
        this.depth = depth;
    }

    /**
     *
     * @return game board.
     */
    public Board getBoard() {
        return this.currentGameBoard;
    }
    
    /**
     *
     * Takes the gameState and returns the next move for the bot.
     * 
     * @param gameState gamestate holds all the logs of the game, and has the latest move that we want to check.
     * @return the bots next move in universal chess interface format.
     */
    @Override
    public String nextMove(GameState gameState) {
        // First the Next Move updates the current Board with last move the opponent made
        this.updateGameStateMove(gameState, this.currentGameBoard);
        
        // Second method calculates the next best move or returns null, if player has lost
        return this.calculateBestMoveForGivenSide(gameState.playing, this.currentGameBoard);
    }
    
    /**
     *
     * Updates the Board with the latest move saved in GameState.
     * Public for testing purposes.
     * 
     * @param gameState the GameState that holds the game log data, and from which the latest move will be parsed
     * @param boardToUpdate The Board that should be updated with given move from GameState
     */
    public void updateGameStateMove(GameState gameState, Board boardToUpdate) {
        // if given GameState is not empty we update the Board given in the method with latest move
        if (gameState.myTurn() && !gameState.moves.isEmpty()) {
            String latestMove = gameState.getLatestMove();
            movementgenerator.updateMovementOnBoard(latestMove, boardToUpdate);
        }
    }
    
    /**
     *
     * Calculates and returns the best move for given Side with given Board situation.
     * Public for testing.
     * 
     * @param side for which to check to which to check the best move.
     * @param boardToUpdate The Board from which to check the best move.
     * @return the best move as a String 
     */
    public String calculateBestMoveForGivenSide(Side side, Board boardToUpdate) {
        // The moves always start from beginning, so all moves should be calculated with new array
        String[] moves = new String[0]; 
        // countAllMoves method calculates all legal moves for given side and given board
        moves = movementgenerator.countAllMoves(side, boardToUpdate, moves); 
         
        if (moves.length > 0) { // if moves has even 1 legal move, we can do something.
            // We calculate the best move from given "all-moves" -list
            String moveToReturn = this.moveToDo(moves, side, boardToUpdate); 
            // the move needs to be updated on the Board
            movementgenerator.updateMovementOnBoard(moveToReturn, boardToUpdate);          
            return moveToReturn; // we also want the API used to gaming to know our move
        } else { // game is lost, and we can't move any piece. GameOver.
            return null; 
        } 
    }
            
    /**
     *
     * Checks all moves from given array and returns the move with best value.
     * Public for testing.
     * 
     * @param moves all the moves for player's side.
     * @param sideToCheck The side who's moves we want to check.
     * @param checkBoard The Board from which the moves should be checked from.
     * @return the move that holds the best value within the game. Or if all moves are equal, a random move.
     */
    public String moveToDo(String[] moves, Side sideToCheck, Board checkBoard) {
        
        if (sideToCheck == Side.BLACK) {
            return this.countBestMoveForBlack(moves, checkBoard);
        } else { 
            return this.countBestMoveForWhite(moves, checkBoard);
        }

    }
    
    /**
     *
     * Counts the best move for black player.
     * Made public for testing purposes.
     * 
     * @param moves all possible moves given as an array
     * @param checkBoard the board that we want to check all the moves from
     * @return the best move for black from the array of moves given the Board situation
     */
    public String countBestMoveForBlack(String[] moves, Board checkBoard) {
        // current value of the checkBoard is set to 0. This counts the change in value, not the actual value of Board.
        int changeNow = 0; 
        String[] movesWithoutChecks = new String[0];
        
        // remove all moves where Black king is left unchecked
        for (String move : moves) {
            if (!kingChecked.kingInCheck(move, Side.WHITE, checkBoard)) {
                movesWithoutChecks = arrayModifier.addNewMoveToArray(movesWithoutChecks, move);
            }
        }
        // If all moves are equal in value, we want to return random move, and not for example the first move.
        String moveToReturn = movesWithoutChecks[(random.nextInt(movesWithoutChecks.length))]; 

        for (String move : movesWithoutChecks) {
            // Black player wants to minimize the Board value. 
            // This means that there is a move that has better value for black than previous best (or initial 0)
            // if (moveValueCounter.moveValueCount(move, -1, checkBoard) < changeNow) { LET'S TEST MINMAX  INSTEAD
            
            int value = moveValueCounter.moveValueCountAlphaBeta(move, Side.BLACK, checkBoard, this.depth);
            
            if (value < changeNow) { 
                // set the changeNow value to the new best value for Black (hence the -1 multiplier)
                changeNow = value; 
                moveToReturn = move; // set the returnable move to new best
            }
        }
        return moveToReturn; 
    }
    
    /**
     *
     * Counts the best move for white player.
     * Made public for testing purposes.
     * 
     * @param moves all possible moves given as an array
     * @param checkBoard the board that we want to check all the moves from
     * @return the best move for white from the array of moves given the Board situation
     */
    public String countBestMoveForWhite(String[] moves, Board checkBoard) {
        // current value of the checkBoard is set to 0. This counts the change in value, not the actual value of Board.
        int changeNow = 0; 
        String[] movesWithoutChecks = new String[0];
        
        // remove all moves where White king is left unchecked
        for (String move : moves) {
            
            if (!kingChecked.kingInCheck(move, Side.BLACK, checkBoard)) {
                movesWithoutChecks = arrayModifier.addNewMoveToArray(movesWithoutChecks, move);
            }
        }

        // If all moves are equal in value, we want to return random move, and not for example the first move.
        String moveToReturn = movesWithoutChecks[(random.nextInt(movesWithoutChecks.length))]; 

        for (String move : movesWithoutChecks) {
            // White player wants to maximize the Board value. 
            // This means that there is a move that has better value for white than previous best (or initial 0)
            // if (moveValueCounter.moveValueCount(move, 1, checkBoard) > changeNow) { 
            int value = moveValueCounter.moveValueCountAlphaBeta(move, Side.WHITE, checkBoard, this.depth);
            if (value > changeNow) { 
                // set the changeNow value to the new best for White
                changeNow = value; 
                moveToReturn = move; // set the returnable move to new best
            }
        }
        return moveToReturn; 
    }
    
}
