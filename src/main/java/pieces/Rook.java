
package pieces;

import chess.model.Side;
import chess.rules.MoveRules;
import chess.elements.Board;
import chess.elements.Tile;

public class Rook implements Piece {
    private final Side side;
    private final PieceType pieceType;
    private final MoveRules moveRules;
    private final Integer[] movePairsFile;
    private final Integer[] movePairsRank;
    
    public Rook(Side side) {
        this.side = side;
        this.pieceType = PieceType.Rook;
        moveRules = new MoveRules();
        this.movePairsFile = new Integer[]{-1, 0, 1, 0};
        this.movePairsRank = new Integer[]{0, 1, 0, -1};
    }

    @Override
    public Side getSide() {
        return this.side;
    }

    @Override
    public PieceType getPieceType() {
        return this.pieceType;
    }

    @Override
    public String[] getMoves(Board gameBoard, Tile tile, int sideMultiplier) {
        String[] moves = new String[0];
        return moveRules.vectorMoves(moves, movePairsFile, movePairsRank, gameBoard, tile, sideMultiplier);
 
    }

    @Override
    public int getValue() {
        int sideMultiplier = 1;
        if (this.side == Side.BLACK) {
            sideMultiplier = -1;
        }
        
        return 50 * sideMultiplier;
    }
    
}
