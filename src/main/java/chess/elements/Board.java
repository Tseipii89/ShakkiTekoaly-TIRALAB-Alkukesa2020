
package chess.elements;

import chess.model.Side;
import pieces.Bishop;
import pieces.Knight;
import pieces.Pawn;
import pieces.Rook;


public class Board {
    
    private Tile[] tiles;
    
    public Board() {
        tiles = new Tile[64];
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                File fileToAdd = File.valueOfLabel(j);
                Rank rankToAdd = Rank.valueOfLabel(i);
                Tile tileToAdd = new Tile(fileToAdd, rankToAdd);
                int index = 8 * (i - 1) + j - 1;
                tiles[index] = tileToAdd;
            } 
        }
    }
    
    public Tile[] getTilesList() {
        return tiles;
    }

    public Tile getTile(File newFile, Rank newRank) {
        int index = 8 * (newRank.getIntegerRank() - 1) + newFile.getIntegerFile() - 1;
        return tiles[index];
    }
    
    public void initBoard() {

        // Init white pieces
        
        this.tiles[0].setPiece(new Rook(Side.WHITE));
        this.tiles[1].setPiece(new Knight(Side.WHITE));
        this.tiles[2].setPiece(new Bishop(Side.WHITE));
        
        
        this.tiles[5].setPiece(new Bishop(Side.WHITE));
        this.tiles[6].setPiece(new Knight(Side.WHITE));
        this.tiles[7].setPiece(new Rook(Side.WHITE));
        for (int i = 8; i < 16; i++) {
            this.tiles[i].setPiece(new Pawn(Side.WHITE));  
        }
        
        // Init black pieces
        
        this.tiles[56].setPiece(new Rook(Side.BLACK));
        this.tiles[57].setPiece(new Knight(Side.BLACK));
        this.tiles[58].setPiece(new Bishop(Side.BLACK));
        
              
        this.tiles[61].setPiece(new Bishop(Side.BLACK));
        this.tiles[62].setPiece(new Knight(Side.BLACK));
        this.tiles[63].setPiece(new Rook(Side.BLACK));
        for (int i = 48; i < 56; i++) {
            this.tiles[i].setPiece(new Pawn(Side.BLACK));  
        }
    }
    
}
