
package pieces;

import chess.elements.Board;
import chess.elements.Tile;
import chess.model.Side;
import chess.rules.MoveRules;

/**
 * King is the guys who everyone tries to protect. 
 * @author juhop
 */
public class King implements Piece {

    /**
     * Side of this piece.
     */
    private final Side side;

    /**
     * Piece type of this piece.
     */
    private final PieceType pieceType;

    /**
     * Tells what moves are possible to do.
     */
    private final MoveRules moveRules;

    /**
     * All moves are in rank and file pairs, 
     * where the rank and file is looked from same positions in each Integer Array (eg. position 1 for both)
     */
    private final Integer[] movePairsFile;

    /**
     * All moves are in rank and file pairs, 
     * where the rank and file is looked from same positions in each Integer Array (eg. position 1 for both)
     */
    private final Integer[] movePairsRank;
    
    /**
     * Valuates the different positions of the piece.
     */
    private final double[] positionValue;
    
    /**
     * Creates new King ready to be used.
     * @param side The side this piece belongs to.
     */
    public King(Side side) {
        this.side = side;
        this.pieceType = PieceType.King;
        moveRules = new MoveRules();
        this.movePairsFile = new Integer[]{-1, -1, 0, 1, 1, 1, 0, -1};
        this.movePairsRank = new Integer[]{0, 1, 1, 1, 0, -1, -1, -1};
        this.positionValue = new double[]{
            2,  3,  1,  0,  0,  1,  3,  2,
            2,  2,  0,  0,  0,  0,  2,  2,
            -1, -2, -2, -2, -2, -2, -2, -1,
            -2, -3, -3, -4, -4, -3, -3, -2,
            -3, -4, -4, -5, -5, -4, -4, -3,
            -3, -4, -4, -5, -5, -4, -4, -3,
            -3, -4, -4, -5, -5, -4, -4, -3,
            -3, -4, -4, -5, -5, -4, -4, -3};
    }

    /**
     *
     * @return side of this piece.
     */
    @Override
    public Side getSide() {
        return this.side;  
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
        for (int i = 0; i < movePairsFile.length; i++) {
            moves = moveRules.tileEmptyNOTUsedWithPawn(
                    moves, 
                    gameBoard, 
                    tile, 
                    sideMultiplier, 
                    movePairsFile[i], 
                    movePairsRank[i]
            );
            moves = moveRules.isTileOkToAddAttack(
                    moves, 
                    gameBoard, 
                    tile, 
                    sideMultiplier, 
                    movePairsFile[i], 
                    movePairsRank[i], 
                    0 // no other piece than pawn can promote
            ); 
        }
        return moves;
    }

    /**
     *
     * @return 900 for white king and -900 for black king.
     */
    @Override
    public int getValue() {        
        return 900;
    }

    @Override
    public double getPositionValue(int position) {
        return this.positionValue[position];    
    }
    
}
