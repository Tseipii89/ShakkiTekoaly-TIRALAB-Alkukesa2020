package chess.bot;

import chess.elements.Board;
import chess.elements.File;
import chess.elements.Rank;
import chess.engine.GameState;
import chess.model.Side;
import chess.rules.KingCheckedCounter;
import chess.rules.MovementGenerator;
import datastructureproject.MoveValueCounter;
import datastructureproject.datamodifiers.ArrayModifier;
import datastructureproject.datamodifiers.NumberModificator;
import pieces.PieceType;


/**
 *
 * @author juhop
 */
public class TiraBot implements ChessBot {
    
    /**
     * The Random is used to return random move.
     */
    private final NumberModificator random; 

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
     * GameState is saved because of opponent's castling opportunity.
     */
    private GameState gameState;
    
    /**
     * Provides min, max, abs and random methods.
     */
    private NumberModificator numberMod;

    /**
     * Tirabot is the implementation for the algorithms and data structures course.
     * It counts the best next move for the given the limitations. 
     */
    public TiraBot() {
        this.random = new NumberModificator();
        this.currentGameBoard = new Board();
        currentGameBoard.initBoard();
        movementgenerator = new MovementGenerator();
        arrayModifier = new ArrayModifier();
        moveValueCounter = new MoveValueCounter();
        kingChecked = new KingCheckedCounter();
        this.depth = 2;
        this.numberMod = new NumberModificator();
    }
    
    /**
     *
     * Tirabot is the implementation for the algorithms and data structures course.
     * It counts the best next move for the given the limitations. 
     * 
     * @param depth counts the moves down this many steps.
     */
    public TiraBot(int depth) {
        this.random = new NumberModificator();
        this.currentGameBoard = new Board();
        currentGameBoard.initBoard();
        movementgenerator = new MovementGenerator();
        arrayModifier = new ArrayModifier();
        moveValueCounter = new MoveValueCounter();
        kingChecked = new KingCheckedCounter();
        this.depth = depth;
        this.numberMod = new NumberModificator();
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
        
        this.gameState = gameState;
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
        if (!gameState.moves.isEmpty()) {
            String latestMove = gameState.getLatestMove();
            this.opponentCastling(latestMove, boardToUpdate);
            movementgenerator.updateMovementOnBoard(latestMove, boardToUpdate);
        }
    }
    
    /**
     *
     * Checks if king does castling.
     * 
     * @param latestMove the move to check.
     * @param boardToUpdate the board to check for castling.
     * @return the side on which the castling is done.
     */
    private String opponentCastling(String latestMove, Board boardToUpdate) {
        File startFile = File.valueOfLabel(latestMove.substring(0, 1));
        Rank startRank = Rank.valueOfInteger(Integer.parseInt(latestMove.substring(1, 2)));
        
        File finishFile = File.valueOfLabel(latestMove.substring(2, 3));
        if (boardToUpdate.getTile(startFile, startRank).getPiece() != null) {
            if (boardToUpdate.getTile(startFile, startRank).getPiece().getPieceType() == PieceType.King 
                    && numberMod.abs(startFile.getIntegerFile() - finishFile.getIntegerFile()) > 1) {
                if (finishFile == File.File_C) {
                    return startRank + "queenside";
                }
                if (finishFile == File.File_G) {
                    return startRank + "kingside";
                }
            }
        }

        
        return latestMove;
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
        String moveToReturn = movesWithoutChecks[(random.random(movesWithoutChecks.length))]; 

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
        
        this.updateCastling(moveToReturn, Side.BLACK);
        
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
        String moveToReturn = movesWithoutChecks[(random.random(movesWithoutChecks.length))]; 

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
        
        this.updateCastling(moveToReturn, Side.WHITE);
        
        return moveToReturn; 
    }

    /**
     *
     * Updates Gamestate's castling to false, if king or rook moves.
     * To be used to prevent illegal castling.
     * 
     * @param moveToReturn The move to check.
     * @param side side that plays.
     */
    private void updateCastling(String moveToReturn, Side side) {
        File startFile = File.valueOfLabel(moveToReturn.substring(0, 1));
        Rank startRank = Rank.valueOfInteger(Integer.parseInt(moveToReturn.substring(1, 2)));
        
        if (side == Side.WHITE) {
            if (startFile == File.File_E && startRank == Rank.Rank_1) {
                this.gameState.castlingKingSideWhite = false;
                this.gameState.castlingQueenSideWhite = false;
            }
            if (startFile == File.File_A && startRank == Rank.Rank_1) {
                this.gameState.castlingQueenSideWhite = false;
            }
            if (startFile == File.File_H && startRank == Rank.Rank_1) {
                this.gameState.castlingKingSideWhite = false;
            }
            
        } else {
            if (startFile == File.File_E && startRank == Rank.Rank_8) {
                this.gameState.castlingKingSideBlack = false;
                this.gameState.castlingQueenSideBlack = false;
            }
            if (startFile == File.File_A && startRank == Rank.Rank_8) {
                this.gameState.castlingQueenSideBlack = false;
            }
            if (startFile == File.File_H && startRank == Rank.Rank_8) {
                this.gameState.castlingKingSideBlack = false;
            }
        }
        
        
    }
    
}
