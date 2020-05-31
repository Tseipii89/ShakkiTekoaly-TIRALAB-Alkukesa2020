
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
    
    private Piece startTilePiece;
    private Piece finishTilePiece;
    private Tile startTile;
    private Tile finishTile;
    private final NewTileCounter newTileCounter;
    
    
    public BoardStatusSaver() {
        newTileCounter = new NewTileCounter();
        startTilePiece = null;
        finishTilePiece = null;
    }
    
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
    
    public Piece getStartPiece() {
        return this.startTilePiece;
    }
    
    public Piece getFinishPiece() {
        return this.finishTilePiece;
    }
    
    public Tile getStartPieceTile() {
        return this.startTile;
    }
    
    public Tile getFinishPieceTile() {
        return this.finishTile;
    }
    
    public void putSavedPiecesBack() {
        this.startTile.setPiece(startTilePiece);
        this.finishTile.setPiece(finishTilePiece);
    }
}
