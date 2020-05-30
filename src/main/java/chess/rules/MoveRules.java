
package chess.rules;

import chess.elements.Board;
import chess.elements.Tile;
import datastructureproject.datamodifiers.ArrayModifier;
import pieces.Piece;


public class MoveRules {
    private NewTileCounter newTileCounter;
    private ArrayModifier arrayModifier;

    public MoveRules() {
        newTileCounter = new NewTileCounter();
        arrayModifier = new ArrayModifier();
    }
    
    
    public String[] isTileOkToAddAttack(String[] moves, 
                                        Board gameBoard,
                                        Tile tile, 
                                        int sideMultiplier, 
                                        int filesToAdd,
                                        int ranksToAdd, 
                                        double canPromotion) {
        Piece tilesPiece = gameBoard.getTile(tile.getFile(), tile.getRank()).getPiece();
        Tile tileToCheck = newTileCounter.countNewTile(tile, sideMultiplier, filesToAdd, ranksToAdd);
        if (tileToCheck == null) { 
        } else if (gameBoard.getTile(tileToCheck.getFile(), tileToCheck.getRank()).getPiece() != null) {
            if (gameBoard.getTile(tileToCheck.getFile(), 
                    tileToCheck.getRank()).getPiece().getSide() != tilesPiece.getSide()) {
                String move = createMovementString(tile, tileToCheck, canPromotion);
                moves = arrayModifier.addNewMoveToArray(moves, move);
            }
        } 
        return moves;
    }
    
    public String[] isTileOkToAddPawn(String[] moves, 
                                      Board gameBoard,
                                      Tile tile, 
                                      int sideMultiplier, 
                                      int filesToAdd,
                                      int ranksToAdd, 
                                      double canPromotion) {
        Tile tileToCheck = newTileCounter.countNewTile(tile, sideMultiplier, filesToAdd, ranksToAdd);
        if (tileToCheck != null) {
            if (gameBoard.getTile(tileToCheck.getFile(), tileToCheck.getRank()).getPiece() == null) {
                String move = createMovementString(tile, tileToCheck, canPromotion);
                moves = arrayModifier.addNewMoveToArray(moves, move);
            } 
        }
        return moves;
    }
    
    public String[] isTileOkToAddEveryoneElse(String[] moves, 
                                              Board gameBoard,
                                              Tile tile,
                                              int sideMultiplier,
                                              int filesToAdd,
                                              int ranksToAdd) {
        Tile tileToCheck = newTileCounter.countNewTile(tile, sideMultiplier, filesToAdd, ranksToAdd);
        if (tileToCheck != null) {
            if (gameBoard.getTile(tileToCheck.getFile(), tileToCheck.getRank()).getPiece() == null) {
                String move = createMovementString(tile, tileToCheck, 0); // no other piece can promote than Pawn
                moves = arrayModifier.addNewMoveToArray(moves, move);
            } 
        }
        return moves;
    }
    
    private String createMovementString(Tile start, Tile finish, double canPromotion) {
        String basic;
        if (start.getRank().getIntegerRank() == canPromotion) {
            basic = start.getFile().toString() // start file as a String
                    + start.getRank().toString() // start rank as a String 
                    + finish.getFile().toString() // finish file as a String
                    + finish.getRank().toString() // finish rank as a String
                    + "q"; // all pawns are promoted to Queens
        } else {
            basic = start.getFile().toString() // start file as a String
                    + start.getRank().toString() // start rank as a String 
                    + finish.getFile().toString() // finish file as a String
                    + finish.getRank().toString(); // finish rank as a String
        } 
        return basic;
    }
    
    public String[] vectorMoves(String[] moves,
                                Integer[] movePairsFile, 
                                Integer[] movePairsRank, 
                                Board gameBoard, 
                                Tile tile, 
                                int sideMultiplier) {
        int movesSize;
        int fileMove;
        int rankMove;

        for (int i = 0; i < movePairsFile.length; i++) {
            movesSize = -1;
            fileMove = movePairsFile[i];
            rankMove = movePairsRank[i];
            while (moves.length > movesSize) {
                movesSize = moves.length;
                moves = this.isTileOkToAddEveryoneElse(moves, 
                                                       gameBoard, 
                                                       tile, 
                                                       sideMultiplier, 
                                                       fileMove, 
                                                       rankMove);
                if (moves.length > movesSize) {
                    fileMove +=  1 * movePairsFile[i];
                    rankMove +=  1 * movePairsRank[i];
                }
            }
            moves = this.isTileOkToAddAttack(moves, 
                                             gameBoard,
                                             tile, 
                                             sideMultiplier, 
                                             fileMove, 
                                             rankMove, 
                                             0); // no other piece than pawn can promote, so value is 0
        }        
        return moves;
    }
    
    
}
