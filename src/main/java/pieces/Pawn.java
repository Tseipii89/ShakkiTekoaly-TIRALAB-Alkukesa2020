package pieces;

import chess.model.Side;
import chess.rules.MoveRules;
import chess.elements.Board;
import chess.elements.Tile;

/**
 * Pawns are the workers of the Board.
 * @author juhop
 */
public class Pawn implements Piece {
    
    /**
     * Side of this piece.
     */
    private Side side;

    /**
     * Piece type of this piece.
     */
    private PieceType pieceType;

    /**
     * Tells what moves are possible to do.
     */
    private final MoveRules moveRules;
    
    /**
     * Valuates the different positions of the piece.
     */
    private final double[] positionValue;
    
    /**
     * Creates new Pawn ready to be used.
     * @param side The side this piece belongs to.
     */
    public Pawn(Side side) {
        this.side = side;
        this.pieceType = PieceType.Pawn;
        moveRules = new MoveRules();
        this.positionValue = new double[]{
            0,  0,  0,  0,  0,  0,  0, 0, 
            1,  1,  1, -2, -2,  1,  1, 1, 
            1, -1, -1,  0,  0, -1, -1, 1,
            0,  0,  0,  2,  2,  0,  0, 0, 
            1,  1,  1,  3,  3,  1,  1, 1, 
            1,  1,  2,  3,  3,  2,  1, 1, 
            5,  5,  5,  5,  5,  5,  5, 5, 
            0,  0,  0,  0,  0,  0,  0, 0};
    }

    /**
     * @return side of this piece.
     */
    @Override
    public Side getSide() {
        return this.side;
    }

    /**
     * Used in promotion situations.
     * @param type of piece the pawn should be promoted.
     */
    public void setPieceType(PieceType type) {
        this.pieceType = type;
    }

    /**
     *
     * @return type of this piece.
     */
    @Override
    public PieceType getPieceType() {
        return this.pieceType;
    }

    /**
     * Returns all the moves this piece can do.
     * 
     * @param gameBoard the gameBoard to check all the moves.
     * @param tile the tiles where this piece is (the piece itself doesn't know where it is)
     * @param sideMultiplier -1 for black and 1 for white.
     * @return All the possible moves in String array.
     */
    @Override
    public String[] getMoves(Board gameBoard, Tile tile, int sideMultiplier) {
        
        
        String[] moves = new String[0];
        double canPromotion = (4.5 + sideMultiplier * 2.5);
        // Basic move
        moves = moveRules.tileEmptyUSEWithPawn(moves, gameBoard, tile, sideMultiplier, 0, 1, canPromotion);
        
        // Start move 2 tiles
        //canpromotion is on the wrong side of the board
        if (moves.length == 1 && tile.getRank().getIntegerRank() == (9 - canPromotion)) { 
            moves = moveRules.tileEmptyUSEWithPawn(moves, gameBoard, tile, sideMultiplier, 0, 2, canPromotion);
        }

        // Pawn attacks
        moves = moveRules.isTileOkToAddAttack(moves, gameBoard, tile, sideMultiplier, -1, 1, canPromotion);
        
        moves = moveRules.isTileOkToAddAttack(moves, gameBoard, tile, sideMultiplier, 1, 1, canPromotion);

        
        return moves;
    }

    /**
     *
     * @return 10 for white pawn and -10 for black pawn.
     */
    @Override
    public int getValue() {
        return 10;
    }

    @Override
    public double getPositionValue(int position) {
        return this.positionValue[position];    
    }

}
