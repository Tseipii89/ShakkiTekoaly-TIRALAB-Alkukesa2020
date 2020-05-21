
package pieces;

import chess.model.Side;
import chess.rules.MoveRules;
import chess.elements.Board;
import chess.elements.Tile;

public class Knight implements Piece {
    
    private Side side;
    private PieceType pieceType;
    private final MoveRules moveRules;
    private final Integer[] movePairsFile;
    private final Integer[] movePairsRank;
    
    public Knight(Side side) {
        this.side = side;
        this.pieceType = PieceType.Knight;
        moveRules = new MoveRules();
        this.movePairsFile = new Integer[]{-2, -1, 1, 2, 2, 1, -1, -2};
        this.movePairsRank = new Integer[]{1, 2, 2, 1, -1, -2, -2, -1};
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
        
        //basic moves
        for (int i = 0; i < movePairsFile.length; i++) {
            moves = moveRules.isTileOkToAddEveryoneElse(moves, gameBoard, tile, sideMultiplier, movePairsFile[i], movePairsRank[i]);
            moves = moveRules.isTileOkToAddAttack(moves, gameBoard, tile, sideMultiplier, movePairsFile[i], movePairsRank[i], 0); // no other piece than pawn can promote
        }
        
        return moves;
    }
    
}
