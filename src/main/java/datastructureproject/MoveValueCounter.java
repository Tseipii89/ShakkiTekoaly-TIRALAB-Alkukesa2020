package datastructureproject;

import chess.elements.Board;
import chess.elements.Tile;
import chess.model.Side;
import chess.rules.MovementGenerator;
import chess.rules.NewTileCounter;

/**
 *
 * TiraBot uses this class to value different possible moves to find the best move.
 * 
 * @author juhop
 */
public class MoveValueCounter {

    /**
     * Calculates the new tile.
     */
    private final NewTileCounter newTileCounter;

    /**
     * Calculates the value of board.
     */
    private final BoardValueCalculator valueCalc;

    /**
     * Saves the state before the move.
     */
    public BoardStatusSaver boardStatusSaver; // Public for testing

    /**
     * The movement generator to generate all possible moves.
     */
    public final MovementGenerator movementGenerator; // Public for testing

    /**
     * The AlphaBetaPruner used in this class.
     */
    public AlphaBetaPruner alphabeta; // Public for testing
    
    /**
     * Creates new move MoveValueCounter ready to be used.
     */
    public MoveValueCounter() {
        newTileCounter = new NewTileCounter();
        valueCalc = new BoardValueCalculator();
        boardStatusSaver = new BoardStatusSaver();
        movementGenerator = new MovementGenerator();
    }
    
     /**
     *
     * Counts the value of given move. 
     * The value depends on position, promotion and capturing of enemy pieces. 
     * Each Piece has a unique value that enables the evaluating of different Board situations.
     * 
     * @param move the Move is to be evaluated
     * @param multiplier Side multiplier. White wants maximum points, and Black wants minimum points.
     * @param board count the move's value from this board.
     * @return the integer value of the change in value in regards of this move. 
     */
    public int moveValueCount(String move, int multiplier, Board board) {

        Tile startTile = newTileCounter.moveToTile(move, 0, 2, board);
        Tile finishTile = newTileCounter.moveToTile(move, 2, 4, board);

        int promotion = 0;
        if (move.length() > 4) {
            promotion = 80 * multiplier;
        }
        
        return valueCalc.boardValue(startTile, finishTile, promotion);
    }
    
    /**
     *
     * Using alpha-beta to count best move for given depth in min-max tree.
     * 
     * @param move The move to be done.
     * @param playing Side that is currently playing.
     * @param board The board that is being updated.
     * @param depth How deep we go into alpha-beta algo.
     * @return the board value of best move.
     */
    public int moveValueCountAlphaBeta(String move, Side playing, Board board, int depth) {
        alphabeta = new AlphaBetaPruner();
        // save pieces
        this.boardStatusSaver.savePieces(move, board);

        // update board
        this.movementGenerator.updateMovementOnBoard(move, board);
        
        // count minmax value
        
        Side opponent = this.alphabeta.getOpponent(playing);

        int value = this.alphabeta.alphabeta(opponent, board, depth, -10000000, 10000000); 
        //value = this.alphabeta.minimax(opponent, board, depth, maximizingPlayer); 
        //return pieces
        this.boardStatusSaver.putSavedPiecesBack();
        
        // return minmax value
        return value;
    }
    
    /**
     * Counts the nodes checked by Alpha-beta algorithm.
     * 
     * @param move The move to be done.
     * @param playing Side that is currently playing.
     * @param board The board that is being updated.
     * @param depth How deep we go into alpha-beta algo.
     * @return How many leaves were checked.
     */
    public int moveValueCountAlphaBetaTest(String move, Side playing, Board board, int depth) {
        alphabeta = new AlphaBetaPruner();
        // save pieces
        this.boardStatusSaver.savePieces(move, board);

        // update board
        this.movementGenerator.updateMovementOnBoard(move, board);
        
        // count minmax value
        
        Side opponent = this.alphabeta.getOpponent(playing);

        int value = this.alphabeta.alphabetaForTesting(opponent, board, depth, -10000000, 10000000, 0); 
        
        //return pieces
        this.boardStatusSaver.putSavedPiecesBack();
        
        // return minmax value
        return value;
    }
    
    /**
     *
     * Counts the nodes checked by Min-Max algorithm.
     * 
     * @param move The move to be done.
     * @param playing Side that is currently playing.
     * @param board The board that is being updated.
     * @param depth How deep we go into min-max algo.
     * @return How many leaves were checked.
     */
    public int moveValueCountMinMaxTest(String move, Side playing, Board board, int depth) {
        alphabeta = new AlphaBetaPruner();
        // save pieces
        this.boardStatusSaver.savePieces(move, board);

        // update board
        this.movementGenerator.updateMovementOnBoard(move, board);
        
        // count minmax value
        
        Side opponent = this.alphabeta.getOpponent(playing);

        int value = this.alphabeta.minimaxTest(opponent, board, depth, 0); 
        
        //return pieces
        this.boardStatusSaver.putSavedPiecesBack();
        
        // return minmax value
        return value;
    }
    

    
}
