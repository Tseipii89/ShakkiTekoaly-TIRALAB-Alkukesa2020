
package chess.elements;

import chess.model.Side;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

/**
 *
 * Board class contains all the tiles used in the game. 
 * Tiles are not supposed to be accessed outside the Board element, since this messes the game.
 * Initialises the places of pieces on the Board as the game starts.
 * 
 * @author juhop
 */
public class Board {
    
    /**
     *
     * The array that contains all the tiles of the gameBoard.
     * 
     */
    private Tile[] tiles;
    
    /**
     *
     * Initialises all the tiles from A1 to H8 to the gameBoard and adds them to the tiles Array.
     * 
     */
    public Board() {
        tiles = new Tile[64];
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                File fileToAdd = File.valueOfInteger(j);
                Rank rankToAdd = Rank.valueOfInteger(i);
                Tile tileToAdd = new Tile(fileToAdd, rankToAdd);
                int index = 8 * (i - 1) + j - 1;
                tiles[index] = tileToAdd;
            } 
        }
    }
    
    /**
     *
     * Returns the list of Tiles in the gameBoard.
     * 
     * @return Tile[] type array of all tiles in gameBoard.
     */
    public Tile[] getTilesList() {
        return tiles;
    }
    
    public void setTilesList(Tile[] tileList) {
        this.tiles = tileList;
    }

    /**
     *
     * Returns the correct tile from the gameBoard. 
     * 
     * @param newFile the value of file of the wanted Tile.
     * @param newRank the value of the rank of the wanted Tile.
     * @return the Tile od the board that is associated with given file and rank.
     */
    public Tile getTile(File newFile, Rank newRank) {
        int index = 8 * (newRank.getIntegerRank() - 1) + newFile.getIntegerFile() - 1;
        return tiles[index];
    }
    
    /**
     *
     * All the pieces are set to board in their correct positions at the game start.
     * 
     */
    public void initBoard() {

        // Init white pieces
        
        this.tiles[0].setPiece(new Rook(Side.WHITE));
        this.tiles[1].setPiece(new Knight(Side.WHITE));
        this.tiles[2].setPiece(new Bishop(Side.WHITE));
        this.tiles[3].setPiece(new Queen(Side.WHITE));
        this.tiles[4].setPiece(new King(Side.WHITE));        
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
        this.tiles[59].setPiece(new Queen(Side.BLACK));
        this.tiles[60].setPiece(new King(Side.BLACK));              
        this.tiles[61].setPiece(new Bishop(Side.BLACK));
        this.tiles[62].setPiece(new Knight(Side.BLACK));
        this.tiles[63].setPiece(new Rook(Side.BLACK));
        for (int i = 48; i < 56; i++) {
            this.tiles[i].setPiece(new Pawn(Side.BLACK));  
        }
    }
    
}
