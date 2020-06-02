
package pieces;

import chess.elements.Board;
import chess.elements.Tile;
import chess.model.Side;
import chess.rules.MoveRules;

public class King implements Piece {
    private final Side side;
    private final PieceType pieceType;
    private final MoveRules moveRules;
    private final Integer[] movePairsFile;
    private final Integer[] movePairsRank;
    
    public King(Side side) {
        this.side = side;
        this.pieceType = PieceType.King;
        moveRules = new MoveRules();
        this.movePairsFile = new Integer[]{-1, -1, 0, 1, 1, 1, 0, -1};
        this.movePairsRank = new Integer[]{0, 1, 1, 1, 0, -1, -1, -1};
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
        for (int i = 0; i < movePairsFile.length; i++) {
            moves = moveRules.tileEmptyNOTUsedWithPawn(moves, gameBoard, tile, sideMultiplier, movePairsFile[i], movePairsRank[i]);
            moves = moveRules.isTileOkToAddAttack(moves, gameBoard, tile, sideMultiplier, movePairsFile[i], movePairsRank[i], 0); // no other piece than pawn can promote
        }
        return moves;
    }

    @Override
    public int getValue() {
        int sideMultiplier = 1;
        if (this.side == Side.BLACK) {
            sideMultiplier = -1;
        }
        
        return 900 * sideMultiplier;
    }
    
}
