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
     * Only before alpha-beta pruning is implemented.
     */
    private final Random random; 

    /**
     * Game board to hold the current game piece positions.
     */
    private final Board b;   
    
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
        this.b = new Board();
        b.initBoard();
        movementgenerator = new MovementGenerator();
        moverules = new MoveRules();
        valueCalc = new BoardValueCalculator();
    }

    /**
     *
     * @return game board.
     */
    public Board getBoard() {
        return this.b;
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
        if (gameState.myTurn() && !gameState.moves.isEmpty()) {
            String latestMove = gameState.getLatestMove();
            this.updateMovementOnBoard(latestMove, this.b);
        }
        String[] moves = new String[0];
        moves = this.countAllMoves(gameState.playing, this.b, moves);
        // before alpha-beta pruning let's just return random move
        if (moves.length > 0) {
            String moveToReturn = this.moveToDo(moves, gameState.playing, this.b);
            this.updateMovementOnBoard(moveToReturn, this.b);
            return moveToReturn;
        } else {
            return null;
        } 
    }

    /**
     *
     * Checks all different moves and returns the move with best value.
     * 
     * @param moves all the moves for player's side.
     * @param gameState that is associated with the given game.
     * @see chess.engine.GameState
     * @return the move that holds the best value within the game. Or if all moves are equal, a random move.
     */
    private String moveToDo(String[] moves, Side sideToCheck, Board checkBoard) {
        int changeNow = 0;
        String moveToReturn = moves[(random.nextInt(moves.length))];
        if (sideToCheck == Side.BLACK) {
            for (String move : moves) {
                if (this.moveValueCount(move, -1, checkBoard) < changeNow
                       && !this.kingInCheck(move, Side.WHITE, checkBoard)) { // the value of side is white because we want to check that White doesn't have possible check
                    moveToReturn = move;
                }
            }
        } else {
            for (String move : moves) {
                if (this.moveValueCount(move, 1, checkBoard) > changeNow
                        && !this.kingInCheck(move, Side.BLACK, checkBoard)) { // the value of side if black because we want to check that Black doesn't have possible check
                    moveToReturn = move;
                }
            }
        }

        return moveToReturn;
    }
    
    public boolean kingInCheck(String move, Side sideToCheck, Board checkBoard) {

            
            // we have to take the pieces into memory so, they can be added back
            Tile startTile = this.moveToTile(move, 0, 2, checkBoard);
            Tile finishTile = this.moveToTile(move, 2, 4, checkBoard);
            Piece startTilePiece = checkBoard.getTile(startTile.getFile(), startTile.getRank()).getPiece();
            Piece finishTilePiece = checkBoard.getTile(finishTile.getFile(), finishTile.getRank()).getPiece();
            
            this.updateMovementOnBoard(move, checkBoard);
           
            
            String[] movesToCheck = new String[0];
            movesToCheck = this.countAllMoves(sideToCheck, checkBoard, movesToCheck);
            
            if (sideToCheck == Side.WHITE) {
                for (String testMove : movesToCheck) {
                    if (this.moveValueCount(testMove, -1, checkBoard) == 900) {
                        startTile.setPiece(startTilePiece);
                        finishTile.setPiece(finishTilePiece);
                        return true;
                    }
                }
            } else {
                for (String testMove : movesToCheck) {
                    if (this.moveValueCount(testMove, 1, checkBoard) == -900) { 
                        startTile.setPiece(startTilePiece);
                        finishTile.setPiece(finishTilePiece);
                        return true;
                    }
                }
            }

            startTile.setPiece(startTilePiece);
            finishTile.setPiece(finishTilePiece);
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
     * 
     * @param gameState that is associated with the given game.
     * @see chess.engine.GameState
     */
    private String[] countAllMoves(Side sideToPlay, Board boardToCheck, String[] moves) {
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
            case "q":
                finishTile.setPiece(new Queen(pieceToMove.getSide()));
                break;
            case "r":
                finishTile.setPiece(new Rook(pieceToMove.getSide()));
                break;
            case "n":
                finishTile.setPiece(new Knight(pieceToMove.getSide()));
                break;
            case "b":
                finishTile.setPiece(new Bishop(pieceToMove.getSide()));
                break;
            default:
        }
    }
}
