
package pieces;

import chess.model.Side;
import chess.rules.MoveRules;
import chess.elements.Board;
import chess.elements.Tile;

/**
 * Knights have this unique movement pattern on board.  
 * @author juhop
 */
public class Knight implements Piece {
    
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
     * Creates new Knight ready to be used.
     * @param side The side this piece belongs to.
     */
    public Knight(Side side) {
        this.side = side;
        this.pieceType = PieceType.Knight;
        moveRules = new MoveRules();
        this.movePairsFile = new Integer[]{-2, -1, 1, 2, 2, 1, -1, -2};
        this.movePairsRank = new Integer[]{1, 2, 2, 1, -1, -2, -2, -1};
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
        
        //basic moves
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
     * @return 30 for white knight and -30 for black knight.
     */
    @Override
    public int getValue() {
        int sideMultiplier = 1;
        if (this.side == Side.BLACK) {
            sideMultiplier = -1;
        }
        
        return 30 * sideMultiplier;
    }
    
}
