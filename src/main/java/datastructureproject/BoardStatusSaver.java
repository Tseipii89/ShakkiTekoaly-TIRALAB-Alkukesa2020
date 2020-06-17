
package datastructureproject;

import chess.elements.Board;
import chess.elements.Tile;
import chess.rules.NewTileCounter;
import pieces.Piece;

/**
 *
 * This class saves the situation of a Board prior to a movement.
 * The done movement can then be undone.
 * 
 * @author juhop
 */
public class BoardStatusSaver {
    
    /**
     * Saved finish tile piece.
     */
    private Piece startTilePiece;

    /**
     * Saved start tile piece.
     */
    private Piece finishTilePiece;

    /**
     * Saved start tile.
     */
    private Tile startTile;

    /**
     * Saved Finish tile.
     */
    private Tile finishTile;

    /**
     * Counts the new tile from given move.
     */
    private final NewTileCounter newTileCounter;
    
    /**
     * Initializes a new BoardStatusSaver ready to be used.
     */
    public BoardStatusSaver() {
        newTileCounter = new NewTileCounter();
        startTilePiece = null;
        finishTilePiece = null;
    }
    
    /**
     * 
     * Saves the pieces from start and finish tile before the move is done.
     * 
     * @param move the move to be done.
     * @param board the board on which the move is to be done.
     */
    public void savePieces(String move, Board board) {
        this.startTile = newTileCounter.moveToTile(move, 0, 2, board);
        this.finishTile = newTileCounter.moveToTile(move, 2, 4, board);
        if (board.getTile(startTile.getFile(), startTile.getRank()).getPiece() != null) {
            this.startTilePiece = board.getTile(startTile.getFile(), startTile.getRank()).getPiece();
        } else {
            this.startTilePiece = null;
        }
        
        if (board.getTile(finishTile.getFile(), finishTile.getRank()).getPiece() != null) {
            this.finishTilePiece = board.getTile(finishTile.getFile(), finishTile.getRank()).getPiece();
        } else {
            this.finishTilePiece = null;
        }
        

    }
    
    /**
     *
     * @return the piece in the start tile.
     */
    public Piece getStartPiece() {
        return this.startTilePiece;
    }
    
    /**
     *
     * @return the piece in the finish tile.
     */
    public Piece getFinishPiece() {
        return this.finishTilePiece;
    }
    
    /**
     *
     * @return movement start tile.
     */
    public Tile getStartPieceTile() {
        return this.startTile;
    }
    
    /**
     * 
     * @return movement finish tile.
     */
    public Tile getFinishPieceTile() {
        return this.finishTile;
    }
    
    /**
     * Puts the saved pieces back on their initial positions.
     */
    public void putSavedPiecesBack() {
        this.startTile.setPiece(startTilePiece);
        this.finishTile.setPiece(finishTilePiece);
    }
}
