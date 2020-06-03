package datastructureproject;

import chess.elements.Board;
import chess.elements.Tile;
import chess.model.Side;
import chess.rules.MovementGenerator;
import chess.rules.NewTileCounter;


public class MoveValueCounter {
    private final NewTileCounter newTileCounter;
    private final BoardValueCalculator valueCalc;
    public BoardStatusSaver boardStatusSaver; // Public for testing
    public final MovementGenerator movementGenerator; // Public for testing
    public AlphaBetaPruner alphabeta; // Public for testing
    
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
    
    public int moveValueCountMinMax(String move, Side playing, Board board, int depth) {
        alphabeta = new AlphaBetaPruner();
        // save pieces
        this.boardStatusSaver.savePieces(move, board);

        // update board
        this.movementGenerator.updateMovementOnBoard(move, board);
        
        // count minmax value
        
        Side opponent = this.alphabeta.getOpponent(playing);
        
        int value;
        boolean maximizingPlayer = true;
        if (opponent == Side.BLACK) {
            maximizingPlayer = false;
        }
        value = this.alphabeta.minimax(opponent, board, depth, maximizingPlayer); // we go one minmax deep first
        
        //return pieces
        this.boardStatusSaver.putSavedPiecesBack();
        
        // return minmax value
        return value;
    }
    
    

    
}
