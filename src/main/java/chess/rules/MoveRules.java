
package chess.rules;

import chess.elements.Board;
import chess.elements.File;
import chess.elements.Rank;
import chess.elements.Tile;
import pieces.Piece;

/**
 * MoveRules class handles the "dirty" things needed to add the movements to the arrays.
 * For example: adding new move to a full array, returning new tile given to movement parameters.
 * 
 * @author juhop
 */
public class MoveRules {
    
    /**
     * Initiates the MoveRules for the things to come.
     */
    public MoveRules() {
        
    }
    
    /**
     *
     * @param currentTile the tile the movement starts
     * @param sideMultiplier 1 for white and -1 for black. Changes the orientation of the movements.
     * @param fileMovement how many files to move
     * @param rankMovement how many ranks to move
     * @return the value of new tile, or null if the tile is outside the board
     */
    public Tile countNewTile(Tile currentTile, int sideMultiplier, int fileMovement, int rankMovement) {
        
        int newFileInt = currentTile.getFile().getIntegerFile() + fileMovement * sideMultiplier;
        int newRankInt = currentTile.getRank().getIntegerRank() + rankMovement * sideMultiplier;
        
        if (newFileInt < 1 || newFileInt > 8) {
            return null;
        }
        if (newRankInt < 1 || newRankInt > 8) {
            return null;
        }
        return new Tile(File.valueOfLabel(newFileInt), Rank.valueOfLabel(newRankInt));
    }

    /**
     *
     * @param moves the full array to add new move
     * @param move the move to add to the full array
     * @return old array plus the new move added to the end
     */
    public String[] addNewMoveToArray(String[] moves, String move) {
        int n = moves.length;
        String[] newMoves = new String[n + 1];
       
        for (int j = 0; j < n; j++) {
            newMoves[j] = moves[j];
            
        }
        
        newMoves[n] = move;
        
        return newMoves;
    }
    
    public String[] isTileOkToAddAttack(String[] moves, 
                                        Board gameBoard,
                                        Tile tile, 
                                        int sideMultiplier, 
                                        int filesToAdd,
                                        int ranksToAdd, 
                                        double canPromotion) {
        Piece tilesPiece = gameBoard.getTile(tile.getFile(), tile.getRank()).getPiece();
        Tile tileToCheck = this.countNewTile(tile, sideMultiplier, filesToAdd, ranksToAdd);
        if (tileToCheck == null) { 
        } else if (gameBoard.getTile(tileToCheck.getFile(), tileToCheck.getRank()).getPiece() != null) {
            if (gameBoard.getTile(tileToCheck.getFile(), 
                    tileToCheck.getRank()).getPiece().getSide() != tilesPiece.getSide()) {
                String move = createMovementString(tile, tileToCheck, canPromotion);
                moves = this.addNewMoveToArray(moves, move);
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
        Tile tileToCheck = this.countNewTile(tile, sideMultiplier, filesToAdd, ranksToAdd);
        if (tileToCheck != null) {
            if (gameBoard.getTile(tileToCheck.getFile(), tileToCheck.getRank()).getPiece() == null) {
                String move = createMovementString(tile, tileToCheck, canPromotion);
                moves = this.addNewMoveToArray(moves, move);
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
        Tile tileToCheck = this.countNewTile(tile, sideMultiplier, filesToAdd, ranksToAdd);
        if (tileToCheck != null) {
            if (gameBoard.getTile(tileToCheck.getFile(), tileToCheck.getRank()).getPiece() == null) {
                String move = createMovementString(tile, tileToCheck, 0); // no other piece can promote than Pawn
                moves = this.addNewMoveToArray(moves, move);
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
                    + "Q"; // all pawns are promoted to Queens
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
