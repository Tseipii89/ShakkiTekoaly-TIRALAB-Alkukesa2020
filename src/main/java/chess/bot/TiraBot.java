package chess.bot;

import chess.elements.Board;
import chess.elements.File;
import chess.elements.Rank;
import chess.elements.Tile;
import chess.engine.GameState;
import chess.model.Side;
import chess.rules.MoveRules;
import chess.rules.MovementGenerator;
import datastructureproject.BoardValueCalculator;
import java.util.Random;
import pieces.Bishop;
import pieces.Knight;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

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
     * At the moment this class uses moverules to only combine two arrays.
     * Should probably make that method into it's own class.
     */
    private final MoveRules moverules;
    
    /**
     * Counts the value of the Board, given the positions of all pieces.
     */
    private final BoardValueCalculator valueCalc;



    /**
     * Tirabot is the implementation for the algorithms and data structures course.
     * It counts the best next move for the given the limitations. 
     */
    public TiraBot() {
        this.random = new Random();
        this.currentGameBoard = new Board();
        currentGameBoard.initBoard();
        movementgenerator = new MovementGenerator();
        moverules = new MoveRules();
        valueCalc = new BoardValueCalculator();
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
     * 
     * @param gameState the GameState that holds the game log data, and from which the latest move will be parsed
     * @param boardToUpdate The Board that should be updated with given move from GameState
     */
    private void updateGameStateMove(GameState gameState, Board boardToUpdate) {
        // if given GameState is not empty we update the Board given in the method with latest move
        if (gameState.myTurn() && !gameState.moves.isEmpty()) {
            String latestMove = gameState.getLatestMove();
            this.updateMovementOnBoard(latestMove, boardToUpdate);
        }
    }
    
    /**
     *
     * Calculates and returns the best move for given Side with given Board situation.
     * 
     * @param side for which to check to which to check the best move.
     * @param boardToUpdate The Board from which to check the best move.
     * @return the best move as a String 
     */
    private String calculateBestMoveForGivenSide(Side side, Board boardToUpdate) {
        // The moves always start from beginning, so all moves should be calculated with new array
        String[] moves = new String[0]; 
        // countAllMoves method calculates all legal moves for given side and given board
        moves = this.countAllMoves(side, boardToUpdate, moves); 
         
        if (moves.length > 0) { // if moves has even 1 legal move, we can do something.
            // We calculate the best move from given "all-moves" -list
            String moveToReturn = this.moveToDo(moves, side, boardToUpdate); 
            // the move needs to be updated on the Board
            this.updateMovementOnBoard(moveToReturn, boardToUpdate);          
            return moveToReturn; // we also want the API used to gaming to know our move
        } else { // game is lost, and we can't move any piece. GameOver.
            return null; 
        } 
    }
            
    /**
     *
     * Checks all moves from given array and returns the move with best value.
     * 
     * @param moves all the moves for player's side.
     * @param sideToCheck The side who's moves we want to check.
     * @param checkBoard The Board from which the moves should be checked from.
     * @return the move that holds the best value within the game. Or if all moves are equal, a random move.
     */
    private String moveToDo(String[] moves, Side sideToCheck, Board checkBoard) {
        
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
            if (!this.kingInCheck(move, Side.WHITE, checkBoard)) {
                movesWithoutChecks = moverules.addNewMoveToArray(movesWithoutChecks, move);
            }
        }
        // If all moves are equal in value, we want to return random move, and not for example the first move.
        String moveToReturn = movesWithoutChecks[(random.nextInt(movesWithoutChecks.length))]; 

        for (String move : movesWithoutChecks) {
            // Black player wants to minimize the Board value. 
            // This means that there is a move that has better value for black than previous best (or initial 0)
            if (this.moveValueCount(move, -1, checkBoard) < changeNow) { 
                // set the changeNow value to the new best value for Black (hence the -1 multiplier)
                changeNow = this.moveValueCount(move, -1, checkBoard); 
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
            if (!this.kingInCheck(move, Side.BLACK, checkBoard)) {
                movesWithoutChecks = moverules.addNewMoveToArray(movesWithoutChecks, move);
            }
        }

        // If all moves are equal in value, we want to return random move, and not for example the first move.
        String moveToReturn = movesWithoutChecks[(random.nextInt(movesWithoutChecks.length))]; 

        for (String move : movesWithoutChecks) {
            // White player wants to maximize the Board value. 
            // This means that there is a move that has better value for white than previous best (or initial 0)
            if (this.moveValueCount(move, 1, checkBoard) > changeNow) { 
                // set the changeNow value to the new best for White
                changeNow = this.moveValueCount(move, 1, checkBoard); 
                moveToReturn = move; // set the returnable move to new best
            }
        }
        return moveToReturn; 
    }
    
    
    /**
     *
     * We want to check a move without the possibility of our king being in danger. 
     * This method is used by the method kingInCheck to see if the king will be left vulnerable after a certain move.
     * @see chess.bot.TiraBot#kingInCheck(java.lang.String, chess.model.Side, chess.elements.Board) 
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
                if (this.moveValueCount(move, -1, checkBoard) < changeNow) { 
                    // set the changeNow value to the new best value for Black (hence the -1 multiplier)
                    changeNow = this.moveValueCount(move, -1, checkBoard); 
                    moveToReturn = move; // set the returnable move to new best
                }
            }
        } else { // the side is White
            for (String move : moves) {
                // White player wants to maximize the Board value. 
                // This means that there is a move that has better value for white than previous best (or initial 0)
                if (this.moveValueCount(move, 1, checkBoard) > changeNow) { 
                    // set the changeNow value to the new best for White
                    changeNow = this.moveValueCount(move, 1, checkBoard); 
                    moveToReturn = move; // set the returnable move to new best
                }
            }
        }
        return moveToReturn;
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
        Tile startTile = this.moveToTile(move, 0, 2, checkBoard);
        Tile finishTile = this.moveToTile(move, 2, 4, checkBoard);
        Piece startTilePiece = checkBoard.getTile(startTile.getFile(), startTile.getRank()).getPiece();
        Piece finishTilePiece = checkBoard.getTile(finishTile.getFile(), finishTile.getRank()).getPiece();

        // update the movement on Board to see what happens next
        this.updateMovementOnBoard(move, checkBoard); 

        // Check all moves for opponent player and check if they will capture the king
        String[] movesToCheck = new String[0];
        movesToCheck = this.countAllMoves(opponent, checkBoard, movesToCheck);
        // Let's see what is the best move for the opponent, if he/she doesn't do KingCheck
        String moveToDo = this.moveToDoWithoutKingCheck(movesToCheck, opponent, checkBoard); 
        boolean kingCaptured = this.isKingCaptured(opponent, moveToDo, checkBoard);

        // set pieces back to situation before check
        startTile.setPiece(startTilePiece);
        finishTile.setPiece(finishTilePiece);

        // is our king free or captured
        return kingCaptured;
    }
    
    /**
     *
     * Checks that will given opponent capture the opponents king.
     * 
     * @param opponent the opponent's Side who's possible king's capture we want to avoid
     * @param move the move that we want to check isn't capturing our king
     * @param board the Board from which we want to check that our king is not captured
     * @return true if opponent will capture our king, false otherwise
     */
    private boolean isKingCaptured(Side opponent, String move, Board board) {
        if (opponent == Side.WHITE) {
            if (this.moveValueCount(move, 1, board) >= 900) { // opponent will capture our King
                return true;
            }
        } else {
            if (this.moveValueCount(move, -1, board) <= -900) { // opponent will capture our king
                return true;
            }
        }
        return false;
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
    private int moveValueCount(String move, int multiplier, Board board) {

        Tile startTile = this.moveToTile(move, 0, 2, board);
        Tile finishTile = this.moveToTile(move, 2, 4, board);

        int promotion = 0;
        if (move.length() > 4) {
            promotion = 80 * multiplier;
        }
        
        return valueCalc.boardValue(startTile, finishTile, promotion);

    }
 
    /**
     *
     * Method goes through the game Board, and returns all possible moves for given player's Side.
     * Made public for testing purposes.
     * 
     * @param sideToPlay count all moves for this Side.
     * @param boardToCheck count all moves from this board given the board's placement situation.
     * @param moves add all the moves to this String array
     * @return return new array where all the legal moves are added
     */
    public String[] countAllMoves(Side sideToPlay, Board boardToCheck, String[] moves) {
        Tile[] tilesList = boardToCheck.getTilesList();
        for (int i = 0; i < 64; i++) {
            if (tilesList[i].getPiece() != null) {
                if (tilesList[i].getPiece().getSide() == sideToPlay) {
                    String[] thisTileMoves = movementgenerator.pieceMovement(boardToCheck, tilesList[i]);
                    moves = moverules.addNewArrayToArray(thisTileMoves, moves);
                }
            }
            
        }
        return moves;
    }

    
    

    /**
     *
     * This method is only public for testing purposes. This method should not be called straight from other classes.
     * Counts the start tile and finish tile and possible promotion from given move String.
     * 
     * @param move the move in universal chess interface format.
     * @param board the Board against into which the move should be updated
     */
    public void updateMovementOnBoard(String move, Board board) {

        Tile startTile = this.moveToTile(move, 0, 2, board);

        Tile finishTile = this.moveToTile(move, 2, 4, board);
        
        
        String promote = "";
        if (move.length() > 4) {
            promote =  move.substring(4);
            promote = promote.toLowerCase();
        } 
        
        this.updateBoard(startTile, finishTile, promote);
        
    }
    
    /**
     *
     * Transforms the move String's tile to a actual Tile on Board.
     * 
     * @param move the move that holds the information about the Tile
     * @param start index of the starting point of the Tile info
     * @param end index of the end point of Tile info
     * @param board The Board on which the move should be updated 
     * @return The Tile referenced on the move. The Tile is associated with the game Board.
     */
    private Tile moveToTile(String move, int start, int end, Board board) {
        
        String currentFile = move.substring(start, start + 1);
        currentFile = currentFile.toLowerCase();
        int currentRank = Integer.parseInt(move.substring(end - 1, end));
        
        return board.getTile(File.valueOfLabel(currentFile), Rank.valueOfInteger(currentRank));
    }
    
    /**
     *
     * Method sets the tile the piece moves from having no piece, and sets the piece to the new tile.
     * Method also takes care of the promotion if needed.
     * 
     * @param startTile the tile where the piece moves from
     * @param finishTile the tile where the piece moves to
     * @param promote empty string if no promotion is done, otherwise it gets value "Q", "R", "N" or "B"
     */
    private void updateBoard(Tile startTile, Tile finishTile, String promote) {
        Piece pieceToMove = startTile.getPiece();
        startTile.setPiece(null);
        
        finishTile.setPiece(pieceToMove);
        switch (promote) {
            case "q": // queen
                finishTile.setPiece(new Queen(pieceToMove.getSide()));
                break;
            case "r": // rook
                finishTile.setPiece(new Rook(pieceToMove.getSide()));
                break;
            case "n": // knight
                finishTile.setPiece(new Knight(pieceToMove.getSide()));
                break;
            case "b": // bishop
                finishTile.setPiece(new Bishop(pieceToMove.getSide()));
                break;
            default:
        }
    }
}
