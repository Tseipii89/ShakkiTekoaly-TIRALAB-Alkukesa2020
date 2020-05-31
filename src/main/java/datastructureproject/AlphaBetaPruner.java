
package datastructureproject;

import chess.elements.Board;
import chess.model.Side;
import chess.rules.KingCheckedCounter;
import chess.rules.MovementGenerator;
import datastructureproject.datamodifiers.ArrayModifier;


public class AlphaBetaPruner {
    private MovementGenerator movementGenerator;
    private KingCheckedCounter kingChecker;
    private ArrayModifier arrayMod;
    private final BoardValueCalculator boardValueCounter;
    public BoardStatusSaver boardStatusSaver; //public for testing purposes
    private AlphaBetaPruner alphabeta;
    
    public AlphaBetaPruner () {
        this.movementGenerator = new MovementGenerator();
        this.kingChecker = new KingCheckedCounter();
        this.arrayMod = new ArrayModifier();
        this.boardValueCounter = new BoardValueCalculator();
        boardStatusSaver = new BoardStatusSaver();
    }
    
    public int minimax(Side side, Board board, int depth, boolean maximizingPlayer) {
        
        int value = 0;
        
        Side opponent = this.getOpponent(side);

        String[] moves = this.allMovesKingCheckFiltered(side, board);
        
        for (String move : moves) {
            if (depth == 0 || moves.length == 0) { 
                return boardValueCounter.allTilesBoardValue(board);
            } else {
                alphabeta = new AlphaBetaPruner();
            }            
            boardStatusSaver.savePieces(move, board);
            movementGenerator.updateMovementOnBoard(move, board);

            if (maximizingPlayer) {
                value = -10000;
                value = Math.max(value, alphabeta.minimax(opponent, board, depth-1, false));
            } else {
                value = 10000;
                value = Math.min(value, alphabeta.minimax(opponent, board, depth-1, true)); 
            }
            boardStatusSaver.putSavedPiecesBack();
            
        }
        return value;
    }
    
    public Side getOpponent(Side player) {
        if (player == Side.WHITE) {
            return Side.BLACK;
        } else {
            return Side.WHITE;
        }
    }
    
    public String[] allMovesKingCheckFiltered(Side side, Board board) {
        String[] moves = new String[0];
        String[] allMoves = this.movementGenerator.countAllMoves(side, board, moves);
        
        for (String move : allMoves) {
            if(!kingChecker.kingInCheck(move, this.getOpponent(side), board)) {
                moves = arrayMod.addNewMoveToArray(moves, move);
            }
        }
        return moves;
    }
    

}
