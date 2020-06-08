
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
        
        int value = 100000;
        if (maximizingPlayer) {
           value = -100000;
        }    
        

        String[] moves = this.allMovesKingCheckFiltered(side, board);
        if (depth == 0 || moves.length == 0) { 
                return boardValueCounter.allTilesBoardValue(board);
        } else {
           alphabeta = new AlphaBetaPruner(); 
        }  
        
        for (String move : moves) {
            boardStatusSaver.savePieces(move, board);
            movementGenerator.updateMovementOnBoard(move, board);

            if (maximizingPlayer) {  
                value = Math.max(value, alphabeta.minimax(Side.BLACK, board, depth-1, false));
            } else {
                value = Math.min(value, alphabeta.minimax(Side.WHITE, board, depth-1, true)); 
            }
            boardStatusSaver.putSavedPiecesBack();
            
        }
        return value;
    }
    
    public int alphabeta(Side side, Board board, int depth, int alpha, int beta, boolean maximizingPlayer) {
        
        int value = 1000000;
        if (maximizingPlayer) {
           value = -1000000;
        }    


        String[] moves = this.allMovesKingCheckFiltered(side, board);
        
        if (depth == 0 || moves.length == 0) { 
                return boardValueCounter.allTilesBoardValue(board);
        } else {
           alphabeta = new AlphaBetaPruner(); 
        }  
        if (maximizingPlayer) { 
            for (String move : moves) {
                boardStatusSaver.savePieces(move, board);
                movementGenerator.updateMovementOnBoard(move, board);
                value = Math.max(value, alphabeta.alphabeta(Side.BLACK, board, depth-1, alpha, beta, false));
                alpha = Math.max(alpha, value);
                boardStatusSaver.putSavedPiecesBack(); 
                if (alpha >= beta) {
                    break;
                }
                   
            }        
        } else {
            for (String move : moves) {
                boardStatusSaver.savePieces(move, board);
                movementGenerator.updateMovementOnBoard(move, board);                
                value = Math.min(value, alphabeta.alphabeta(Side.WHITE, board, depth-1, alpha, beta, true)); 
                beta = Math.min(beta, value);
                boardStatusSaver.putSavedPiecesBack(); 
                if (alpha >= beta) {
                    break;
                }            
            }
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
