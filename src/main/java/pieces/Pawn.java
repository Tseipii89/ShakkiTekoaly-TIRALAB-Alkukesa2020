package pieces;

import chess.model.Side;
import chess.rules.MoveRules;
import chess.elements.Board;
import chess.elements.Tile;

public class Pawn implements Piece {
    
    private Side side;
    private PieceType pieceType;
    private final MoveRules moveRules;
    
    public Pawn(Side side) {
        this.side = side;
        this.pieceType = PieceType.Pawn;
        moveRules = new MoveRules();
    }


    @Override
    public Side getSide() {
        return this.side;
    }

    public void setPieceType(PieceType type) {
        this.pieceType = type;
    }

    @Override
    public PieceType getPieceType() {
        return this.pieceType;
    }


    @Override
    public String[] getMoves(Board gameBoard, Tile tile, int sideMultiplier) {
        
        
        String[] moves = new String[0];
        double canPromotion = (4.5 + sideMultiplier * 2.5);
        // Basic move
        moves = moveRules.tileEmptyUSEWithPawn(moves, gameBoard, tile, sideMultiplier, 0, 1, canPromotion);
        
        // Start move 2 tiles
        if(moves.length == 1 && tile.getRank().getIntegerRank() == (9 - canPromotion)) { //canpromotion is on the wrong side of the board
            moves = moveRules.tileEmptyUSEWithPawn(moves, gameBoard, tile, sideMultiplier, 0, 2, canPromotion);
        }

        // Pawn attacks
        moves = moveRules.isTileOkToAddAttack(moves, gameBoard, tile, sideMultiplier, -1, 1, canPromotion);
        
        moves = moveRules.isTileOkToAddAttack(moves, gameBoard, tile, sideMultiplier, 1, 1, canPromotion);

        
        return moves;
    }

    @Override
    public int getValue() {
        int sideMultiplier = 1;
        if (this.side == Side.BLACK) {
            sideMultiplier = -1;
        }
        
        return 10 * sideMultiplier;
    }

}
