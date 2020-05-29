
package chess.elements;

import pieces.Piece;

/**
 *
 * Tiles are the building block of the Board.
 * Tiles have rank and Files, and they know if there is a piece on the tile.
 * 
 * @author juhop
 */
public class Tile {
   
    /**
     * The file of this tile.
     * File won't change so it is final. 
     */
    private final File file;

    /**
     * The rank of this tile. 
     * Rank won't change so it is final.
     */
    private final Rank rank;

    /**
     * The piece that is on this tile.
     */
    private Piece piece;

    /**
     *
     * Tile has file, rank and piece information.
     * Piece is set to null in the beginning and fixed later in the Board initialization phase.
     * @see chess.elements.Board#initBoard() 
     * 
     * @param file the file of this tile (a to h)
     * @param rank the rank of this tile (1 to 8)
     */
    public Tile(File file, Rank rank) {
        this.rank = rank;
        this.file = file;
        piece = null;
    }

    /**
     *
     * @return the file associated with this tile
     */
    public File getFile() {
        return this.file;
    }

    /**
     *
     * @return the rank associated with this tile
     */
    public Rank getRank() {
        return this.rank;
    }
    
    /**
     *
     * @param piece Sets this piece on this tile
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
   
    /**
     *
     * @return the Piece that is on this tile, or if no piece then null
     */
    public Piece getPiece() {
        return this.piece;
    }
    
    @Override
    public String toString() { 
        return file.getStringFile() + rank.toString();
    }  
   
}
