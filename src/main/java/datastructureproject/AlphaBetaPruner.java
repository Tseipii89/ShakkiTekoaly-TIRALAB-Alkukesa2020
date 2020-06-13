
package datastructureproject;

import chess.elements.Board;
import chess.model.Side;
import chess.rules.KingCheckedCounter;
import chess.rules.MovementGenerator;
import datastructureproject.datamodifiers.ArrayModifier;

/**
 *
 * Alpha-beta pruner checks the min-max tree down a certain level, and returns the calculated board value. 
 * It is very effective in zero-sum games.
 * 
 * @author juhop
 */
public class AlphaBetaPruner {

    /**
     * Generates all moves.
     */
    private final MovementGenerator movementGenerator;

    /**
     * Checks that is there a possibility to check the king after certain move.
     */
    private final KingCheckedCounter kingChecker;

    /**
     * Modifies the arrays given to it.
     */
    private final ArrayModifier arrayMod;

    /**
     * Counts the value of the board.
     */
    private final BoardValueCalculator boardValueCounter;

    /**
     * Saves the state of the board before move, so that the state can be returned.
     */
    public BoardStatusSaver boardStatusSaver;

    /**
     * AlphabetaPruners have other alphabetapruners as their variables since the alpha-beta and min-max has been done with recursive method.
     */
    private AlphaBetaPruner alphabeta;

    /**
     * How many nodes have we checked so far.
     */
    public int steps;

    /**
     * How deep to go in min-max and alpha-beta checks.
     */
    private int depth;
    
    /**
     * Initialises new AlphaBetaPruner.
     */
    public AlphaBetaPruner() {
        this.movementGenerator = new MovementGenerator();
        this.kingChecker = new KingCheckedCounter();
        this.arrayMod = new ArrayModifier();
        this.boardValueCounter = new BoardValueCalculator();
        boardStatusSaver = new BoardStatusSaver();
        this.steps = 0;
    }
    
    /**
     *
     * Second constructor that is used with testing.
     * The difference is that it changes the steps value.
     * 
     * @param stepsSoFar set steps to this value.
     */
    public AlphaBetaPruner(int stepsSoFar) {
        this.movementGenerator = new MovementGenerator();
        this.kingChecker = new KingCheckedCounter();
        this.arrayMod = new ArrayModifier();
        this.boardValueCounter = new BoardValueCalculator();
        boardStatusSaver = new BoardStatusSaver();
        this.steps = stepsSoFar;
    }
    
    /**
     *
     * 
     * Min-max goes through the min-max tree and returns the value of min-max tree to given side for given depth.
     * 
     * @param side to check.
     * @param board that contains all the state info about the game.
     * @param depth how deep we want to check the situations.
     * @return the value of min-max tree.
     */
    public int minimax(Side side, Board board, int depth) {
        
        int value = 100000;
        
        if (side == Side.WHITE) {
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

            if (side == Side.WHITE) {  
                value = Math.max(value, alphabeta.minimax(Side.BLACK, board, depth - 1));
            } else {
                value = Math.min(value, alphabeta.minimax(Side.WHITE, board, depth - 1)); 
            }
            boardStatusSaver.putSavedPiecesBack();
            
        }
        return value;
    }
    
    /**
     *
     * Used for testing only.
     * This class checks how many nodes the min-max algorithm check.
     * 
     * @param side for which we want to calculate the move value.
     * @param board that contains all the pieces and current state.
     * @param depth how deep we want to run the alpha-beta pruning.
     * @param steps how many steps or nodes the alpha-beta pruning has checked.
     * @return the number of nodes the min-max checked.
     */
    public int minimaxTest(Side side, Board board, int depth, int steps) {
        
        int value = 100000;
        
        if (side == Side.WHITE) {
            value = -100000;
        }    
        

        String[] moves = this.allMovesKingCheckFiltered(side, board);
        if (depth == 0 || moves.length == 0) { 
            this.steps++;
            return boardValueCounter.allTilesBoardValue(board);
        } else {
            alphabeta = new AlphaBetaPruner(this.steps); 
        }  
        
        for (String move : moves) {
            boardStatusSaver.savePieces(move, board);
            movementGenerator.updateMovementOnBoard(move, board);
            if (side == Side.WHITE) {  
                value = Math.max(value, alphabeta.minimaxTest(Side.BLACK, board, depth - 1, this.steps));
                this.steps = alphabeta.steps;
                this.steps++;
            } else {
                value = Math.min(value, alphabeta.minimaxTest(Side.WHITE, board, depth - 1, this.steps)); 
                this.steps = alphabeta.steps;
                this.steps++;
            }
            boardStatusSaver.putSavedPiecesBack();
            
        }
        return value;
    }
    
    /**
     *
     * Alpha-beta prunes the min-max tree and returns the value of min-max tree to given side for given depth.
     * 
     * @param side to check.
     * @param board that contains all the state info about the game.
     * @param depth how deep we want to check the situations.
     * @param alpha value of alpha, in the beginning it is -10000000.
     * @param beta value of beta, in the beginning it is 10000000.
     * @return the value of min-max tree.
     */
    public int alphabeta(
            Side side, 
            Board board, 
            int depth, 
            int alpha, 
            int beta
    ) {
        
        int value = 1000000;
        
        if (side == Side.WHITE) {
            value = -1000000;
        }    

        this.depth = depth;
        
        String[] moves = this.allMovesKingCheckFiltered(side, board);
        
        if (this.depth == 0 || moves.length == 0) { 
            return boardValueCounter.allTilesBoardValue(board);
        } else {
            alphabeta = new AlphaBetaPruner(); 
        }  
        if (side == Side.WHITE) { 
            for (String move : moves) {
                
                boardStatusSaver.savePieces(move, board);
                movementGenerator.updateMovementOnBoard(move, board);
                value = Math.max(value, alphabeta.alphabeta(Side.BLACK, board, this.depth-1, alpha, beta));
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
                value = Math.min(value, alphabeta.alphabeta(Side.WHITE, board, this.depth-1, alpha, beta)); 
                beta = Math.min(beta, value);
                boardStatusSaver.putSavedPiecesBack(); 
                if (alpha >= beta) {
                    break;
                }  
                
            }
        }
        return value;
    }
    
    /**
     *
     * Used only for testing.
     * 
     * @param side for which we want to calculate the move value.
     * @param board that contains all the pieces and current state.
     * @param depth how deep we want to run the alpha-beta pruning.
     * @param alpha value of alpha, in the beginning it is -10000000.
     * @param beta value of beta, in the beginning it is 10000000.
     * @param steps how many steps or nodes the alpha-beta pruning has checked.
     * @return the number of nodes the alpha-beta checked.
     */
    public int alphabetaForTesting(
            Side side, 
            Board board, 
            int depth, 
            int alpha, 
            int beta, 
            int steps
    ) {
        
        int value = 1000000;
        if (side == Side.WHITE) {
            value = -1000000;
        }    

        this.depth = depth;
        
        String[] moves = this.allMovesKingCheckFiltered(side, board);
        
        if (this.depth == 0 || moves.length == 0) { 
            this.steps++;
            return boardValueCounter.allTilesBoardValue(board);
        } else {
            alphabeta = new AlphaBetaPruner(this.steps); 
        }  
        if (side == Side.WHITE) { 
            for (String move : moves) {
                boardStatusSaver.savePieces(move, board);
                movementGenerator.updateMovementOnBoard(move, board);
                value = Math.max(value, alphabeta.alphabetaForTesting(Side.BLACK, board, this.depth-1, alpha, beta, this.steps));
                alpha = Math.max(alpha, value);
                boardStatusSaver.putSavedPiecesBack(); 
                this.steps = alphabeta.steps;
                this.steps++;
                if (alpha >= beta) {
                    break;
                }
                   
            }        
        } else {
            for (String move : moves) {
                boardStatusSaver.savePieces(move, board);
                movementGenerator.updateMovementOnBoard(move, board);                
                value = Math.min(value, alphabeta.alphabetaForTesting(Side.WHITE, board, this.depth-1, alpha, beta, this.steps)); 
                beta = Math.min(beta, value);
                boardStatusSaver.putSavedPiecesBack();
                this.steps = alphabeta.steps;
                this.steps++;
                if (alpha >= beta) {
                    break;
                }            
            }
        }
        return value;
    }
    
    /**
     *
     * Returns the opponent Side of given Side.
     * 
     * @param player Side that we want to check.
     * @return opponent's Side.
     */
    public Side getOpponent(Side player) {
        if (player == Side.WHITE) {
            return Side.BLACK;
        } else {
            return Side.WHITE;
        }
    }
    
    /**
     *
     * Filters all king check moves out and returns list of possible moves.
     * 
     * @param side who is playing.
     * @param board current game board.
     * @return list of all possible moves without our king being checked.
     */
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
