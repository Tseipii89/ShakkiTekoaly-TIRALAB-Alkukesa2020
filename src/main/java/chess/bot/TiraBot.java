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
    
    private String[] moves;
    
    private final MovementGenerator movementgenerator;
    
    private final MoveRules moverules;
    
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
            this.updateMovementOnBoard(latestMove);
        }
        this.countAllMoves(gameState);
        // before alpha-beta pruning let's just return random move
        if (moves.length > 0) {
            String moveToReturn = this.moveToDo(moves, gameState);
            this.updateMovementOnBoard(moveToReturn);
            return moveToReturn;
        } else {
            return null;
        }
        
    }

    private String moveToDo(String[] moves, GameState gameState) {
        int changeNow = 0;
        String moveToReturn = moves[(random.nextInt(moves.length))];
        if (gameState.playing == Side.BLACK) {
            for (String move : moves) {
                if (this.moveValueCount(move, -1) < changeNow) {
                    moveToReturn = move;
                }
            }
        } else {
            for (String move : moves) {
                if (this.moveValueCount(move, 1) > changeNow) {
                    moveToReturn = move;
                }
            }
        }

        return moveToReturn;
    }
    
    
    private int moveValueCount(String move, int multiplier) {

        Tile startTile = this.moveToTile(move, 0, 2);
        Tile finishTile = this.moveToTile(move, 2, 4);

        int promotion = 0;
        if(move.length() > 4) {
            promotion = 90 * multiplier;
        }
        
        return valueCalc.boardValue(startTile, finishTile, promotion);

    }
 
    private void countAllMoves(GameState gameState) {
        Side sideToPlay = gameState.playing;
        moves = new String[0];
        Tile[] tilesList = b.getTilesList();
        for (int i = 0; i < 64; i++) {
            if(tilesList[i].getPiece() != null) {
                if(tilesList[i].getPiece().getSide() == sideToPlay) {
                    String[] thisTileMoves = movementgenerator.pieceMovement(b, tilesList[i]);
                    moves = moverules.addNewArrayToArray(thisTileMoves, moves);
                }
            }
            
        }
    }

    
    

    /**
     *
     * This method is only public for testing purposes. This method should not be called straight from other classes.
     * Counts the start tile and finish tile and possible promotion from given move String.
     * 
     * @param move the move in universal chess interface format.
     */
    public void updateMovementOnBoard(String move) {

        Tile startTile = this.moveToTile(move, 0, 2);

        Tile finishTile = this.moveToTile(move, 2, 4);
        
        
        String promote = "";
        if (move.length() > 4) {
            promote =  move.substring(4);
            promote = promote.toLowerCase();
        } 
        
        this.updateBoard(startTile, finishTile, promote);
        
    }
    
    private Tile moveToTile(String move, int start, int end) {
        
        String currentFile = move.substring(start, start+1);
        currentFile = currentFile.toLowerCase();
        int currentRank = Integer.parseInt(move.substring(end-1, end));
        
        return b.getTile(File.valueOfLabel(currentFile), Rank.valueOfInteger(currentRank));
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
