
package pieces;

import chess.model.Side;
import chess.rules.MoveRules;
import chess.elements.Board;
import chess.elements.Tile;

/**
 * Rooks are the guys who move in horizontal and vertical axis.
 * @author juhop
 */
public class Rook implements Piece {

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
     * Creates new Rook ready to be used.
     * @param side The side this piece belongs to.
     */
    public Rook(Side side) {
        this.side = side;
        this.pieceType = PieceType.Rook;
        moveRules = new MoveRules();
        this.movePairsFile = new Integer[]{-1, 0, 1, 0};
        this.movePairsRank = new Integer[]{0, 1, 0, -1};
        this.positionValue = new double[]{
            0,  0,  0,  1,  1,  0,  0,  0,
            -1,  0,  0,  0,  0,  0,  0, -1,
            -1,  0,  0,  0,  0,  0,  0, -1,
            -1,  0,  0,  0,  0,  0,  0, -1,
            -1,  0,  0,  0,  0,  0,  0, -1,
            -1,  0,  0,  0,  0,  0,  0, -1,
            1,  1,  1,  1,  1,  1,  1, -1,
            0,  0,  0,  0,  0,  0,  0,  0};
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
        return moveRules.vectorMoves(moves, movePairsFile, movePairsRank, gameBoard, tile, sideMultiplier);
 
    }

    /**
     *
     * @return 50 for white rook and -50 for black rook.
     */
    @Override
    public int getValue() {
        return 50;
    }

    @Override
    public double getPositionValue(int position) {
        return this.positionValue[position];    
    }
    
}
