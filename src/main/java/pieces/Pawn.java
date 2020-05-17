package pieces;

import chess.model.Side;
import gameElements.Board;
import gameElements.Rank;
import gameElements.Tile;

public class Pawn implements Piece {
    
    private Side side;
    private PieceType pieceType;
    
    public Pawn(Side side) {
        this.side = side;
        this.pieceType = PieceType.Pawn;
    }


    @Override
    public Side getSide() {
        return this.side;
    }

    @Override
    public void setSide(Side side) {
        this.side = side;
    }

    @Override
    public void setPieceType(PieceType type) {
        this.pieceType = type;
    }

    @Override
    public PieceType getPieceType() {
        return this.pieceType;
    }


    @Override
    public String[] getMoves(Board gameBoard, Tile tile, int sideMultiplier) {
        String[] moves;
        if(tile.getFile().getIntegerFile() == 2 || tile.getFile().getIntegerFile() == 7) {
            moves = new String[2];
        } else {
            moves = new String[1];
        }
        int newRankInt = tile.getRank().getIntegerRank() + 1*sideMultiplier;
        Tile newTile = new Tile(tile.getFile(), Rank.valueOfLabel(newRankInt));
        moves[0] = tile.toString() + newTile.toString();
        
        if(tile.getFile().getIntegerFile() == 2) {
            int newRankInt2 = tile.getRank().getIntegerRank() + 2*sideMultiplier;
            Tile newTile2 = new Tile(tile.getFile(), Rank.valueOfLabel(newRankInt2));
            moves[1] = tile.toString() + newTile2.toString();
        }
        
        return moves;
    }

}
