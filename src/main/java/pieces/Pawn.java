package pieces;

import chess.model.Side;

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
    public String[] getMoves() {
        return null;
    }

}
