
package gameElements;

import pieces.Piece;

public class Tile {
   
   
    private final File file;
    private final Rank rank;
    private Piece piece;

    public Tile(File file, Rank rank) {
        this.rank = rank;
        this.file = file;
        piece = null;
    }

    public File getFile() {
        return this.file;
    }

    public Rank getRank() {
        return this.rank;
    }
    
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
   
    public Piece getPiece() {
        return this.piece;
    }
   
}
