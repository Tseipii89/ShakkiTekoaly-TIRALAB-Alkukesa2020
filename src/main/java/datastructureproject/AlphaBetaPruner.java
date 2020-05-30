
package datastructureproject;

import chess.elements.Board;
import chess.elements.Tile;
import chess.model.Side;
import chess.rules.KingCheckedCounter;
import chess.rules.MovementGenerator;
import chess.rules.NewTileCounter;
import datastructureproject.datamodifiers.ArrayModifier;
import pieces.Piece;


public class AlphaBetaPruner {
    private MovementGenerator movementGenerator;
    private KingCheckedCounter kingChecker;
    private ArrayModifier arrayMod;
    private final BoardValueCalculator boardValueCounter;
    private final NewTileCounter newTileCounter;

    
    public AlphaBetaPruner () {
        this.movementGenerator = new MovementGenerator();
        this.kingChecker = new KingCheckedCounter();
        this.arrayMod = new ArrayModifier();
        this.boardValueCounter = new BoardValueCalculator();
        newTileCounter = new NewTileCounter();
    }
    
    public int minimax(Side side, Board board,int depth, boolean maximizingPlayer) {
        
        int value = 0;
        
        Side opponent;

        if (side == Side.WHITE) {
            opponent = Side.BLACK;
        } else {
            opponent = Side.WHITE;
        }
        
        String[] moves = new String[0];
        String[] allMoves = this.movementGenerator.countAllMoves(side, board, moves);
        
        for (String move : allMoves) {
            if(!kingChecker.kingInCheck(move, opponent, board)) {
                arrayMod.addNewMoveToArray(moves, move);
            }
        }
        
        for (String move : moves) {
            if (depth == 0 || moves.length == 0) { 
                return boardValueCounter.allTilesBoardValue(board);
            }            
            Tile startTile = newTileCounter.moveToTile(move, 0, 2, board);
            Tile finishTile = newTileCounter.moveToTile(move, 2, 4, board);
            Piece startTilePiece = board.getTile(startTile.getFile(), startTile.getRank()).getPiece();
            Piece finishTilePiece = board.getTile(finishTile.getFile(), finishTile.getRank()).getPiece();
            movementGenerator.updateMovementOnBoard(move, board);

            if (maximizingPlayer) {
                value = -10000;
                startTile.setPiece(startTilePiece);
                finishTile.setPiece(finishTilePiece);
                return value = Math.max(value, this.minimax(opponent, board, depth-1, false));
            } else {
                value = 10000;
                startTile.setPiece(startTilePiece);
                finishTile.setPiece(finishTilePiece);
                return value = Math.min(value, this.minimax(opponent, board, depth-1, true));
                
            } 
        }
        return value;
    }
}
