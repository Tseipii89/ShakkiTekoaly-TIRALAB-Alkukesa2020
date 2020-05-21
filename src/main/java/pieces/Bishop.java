package pieces;

import chess.elements.Board;
import chess.elements.Tile;
import chess.model.Side;
import chess.rules.MoveRules;

public class Bishop implements Piece {
    private final Side side;
    private final PieceType pieceType;
    private final MoveRules moveRules;
    private final Integer[] movePairsFile;
    private final Integer[] movePairsRank;
    
    public Bishop(Side side) {
        this.side = side;
        this.pieceType = PieceType.Bishop;
        moveRules = new MoveRules();
        this.movePairsFile = new Integer[]{-1, 1, 1, -1};
        this.movePairsRank = new Integer[]{1, 1, -1, -1};
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
    
}
