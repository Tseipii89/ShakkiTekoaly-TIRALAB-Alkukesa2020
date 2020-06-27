package pieces;

import chess.elements.Board;
import chess.elements.Tile;
import chess.model.Side;
import chess.rules.MoveRules;

/**
 * Bishops are the guys who move vertically. I have no idea in what situation they are best.
 * @author juhop
 */
public class Bishop implements Piece {

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
     * Creates new Bishop ready to be used.
     * @param side The side this piece belongs to.
     */
    public Bishop(Side side) {
        this.side = side;
        this.pieceType = PieceType.Bishop;
        moveRules = new MoveRules();
        this.movePairsFile = new Integer[]{-1, 1, 1, -1};
        this.movePairsRank = new Integer[]{1, 1, -1, -1};
        this.positionValue = new double[]{
            -2, -1, -1, -1, -1, -1, -1, -2,
            -1,  1,  0,  0,  0,  0,  1, -1,
            -1,  1,  1,  1,  1,  1,  1, -1,
            -1,  0,  1,  1,  1,  1,  0, -1,
            -1,  1,  1,  1,  1,  1,  1, -1,
            -1,  0,  0,  0,  0,  0,  0, -1,
            -1,  0,  0,  0,  0,  0,  0, -1,
            -2, -1, -1, -1, -1, -1, -1, -2};
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
     *
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
     * @return 30 for white bishop and -30 for black bishop.
     */
    @Override
    public int getValue() { 
        return 30;
    }

    @Override
    public double getPositionValue(int position) {
        return this.positionValue[position];
    }

    
}
