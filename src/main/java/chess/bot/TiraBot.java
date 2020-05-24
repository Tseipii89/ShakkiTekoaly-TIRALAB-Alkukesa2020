package chess.bot;

import chess.elements.Board;
import chess.elements.File;
import chess.elements.Rank;
import chess.elements.Tile;
import chess.engine.GameState;
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
     * Tirabot is the implementation for the algorithms and data structures course.
     * It counts the best next move for the given the limitations. 
     */
    public TiraBot() {
        this.random = new Random();
        this.b = new Board();
        b.initBoard();
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
        if (gameState.myTurn()) {
            String latestMove = gameState.getLatestMove();
            this.updateMovementOnBoard(latestMove);
        }
        return "b2b3"; 
    }
    
    

    /**
     *
     * This method is only public for testing purposes. This method should not be called straight from other classes.
     * Counts the start tile and finish tile and possible promotion from given move String.
     * 
     * @param move the move in universal chess interface format.
     */
    public void updateMovementOnBoard(String move) {
        String currentFile = move.substring(0, 1);
        currentFile = currentFile.toUpperCase();
        int currentRank = Integer.parseInt(move.substring(1, 2));
        Tile startTile = b.getTile(File.valueOfLabel(currentFile), Rank.valueOfInteger(currentRank));
        
        String finishFile = move.substring(2, 3);
        finishFile = finishFile.toUpperCase();
        int finishRank = Integer.parseInt(move.substring(3, 4));
        Tile finishTile = b.getTile(File.valueOfLabel(finishFile), Rank.valueOfInteger(finishRank));
        
        
        String promote = "";
        if (move.length() > 4) {
            promote =  move.substring(4);
            promote = promote.toUpperCase();
        } 
        
        this.updateBoard(startTile, finishTile, promote);
        
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
            case "Q":
                finishTile.setPiece(new Queen(pieceToMove.getSide()));
                break;
            case "R":
                finishTile.setPiece(new Rook(pieceToMove.getSide()));
                break;
            case "N":
                finishTile.setPiece(new Knight(pieceToMove.getSide()));
                break;
            case "B":
                finishTile.setPiece(new Bishop(pieceToMove.getSide()));
                break;
            default:
        }
    }
}
