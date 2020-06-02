
package chess.rules;

import chess.elements.Board;
import chess.elements.Tile;
import datastructureproject.datamodifiers.ArrayModifier;
import pieces.Piece;

/**
 *
 * Class checks the possible moves for given tile and creates the String presentation of the move.
 * There are three possible moves: vector move, a move with given direction and pawn moves.
 * Movement can be done if:  if the tile is free or the tile is occupied by opponent's piece.
 * 
 * @author juhop
 */
public class MoveRules {

    /**
     * Counts the new tile from given rank and file integer change.
     */
    private final NewTileCounter newTileCounter;

    /**
     * Adds movement to the given array.
     */
    private final ArrayModifier arrayModifier;

    /**
     * Checks if it possible to move to the new tile.
     */
    public MoveRules() {
        newTileCounter = new NewTileCounter();
        arrayModifier = new ArrayModifier();
    }
    
    /**
     *
     * Checks if the given tile is occupied by opponent. 
     * If it is, then the piece on it can be captured.
     * 
     * @param moves the String where all the moves are added.
     * @param gameBoard The board on which to check the moves.
     * @param tile the start tile, where the piece is at the moment.
     * @param sideMultiplier 1 for White and -1 for Black. Makes White and Black pieces to move in opposite directions.
     * @param fileToAdd file movement in integer that is added to given tile.
     * @param rankToAdd rank movement in integer that is added to given tile.
     * @param canPromotion holds value of the rank where white and black can promote.
     * @return moves list where opponent's tile is added
     */
    public String[] isTileOkToAddAttack(String[] moves, 
                                        Board gameBoard,
                                        Tile tile, 
                                        int sideMultiplier, 
                                        int fileToAdd,
                                        int rankToAdd, 
                                        double canPromotion) {
        Piece tilesPiece = gameBoard.getTile(tile.getFile(), tile.getRank()).getPiece();
        Tile tileToCheck = newTileCounter.countNewTile(tile, sideMultiplier, fileToAdd, rankToAdd);
        if (tileToCheck == null) {
            return moves;
        } else if (gameBoard.getTile(tileToCheck.getFile(), tileToCheck.getRank()).getPiece() != null) {
            if (gameBoard.getTile(tileToCheck.getFile(), 
                    tileToCheck.getRank()).getPiece().getSide() != tilesPiece.getSide()) {
                String move = createMovementString(tile, tileToCheck, canPromotion);
                moves = arrayModifier.addNewMoveToArray(moves, move);
            }
        } 
        return moves;
    }
    
    /**
     *
     * Checks the tile, and if it is empty adds it to the possible moves list.
     * Also checks for possible promotion of pawn piece.
     * 
     * @param moves the String where all the moves are added.
     * @param gameBoard The board on which to check the moves.
     * @param tile the start tile, where the piece is at the moment.
     * @param sideMultiplier 1 for White and -1 for Black. Makes White and Black pieces to move in opposite directions.
     * @param fileToAdd file movement in integer that is added to given tile.
     * @param rankToAdd rank movement in integer that is added to given tile.
     * @param canPromotion holds value of the rank where white and black can promote.
     * @return moves list where empty tile is added, if it were empty.
     */
    public String[] tileEmptyUSEWithPawn(String[] moves, 
                                      Board gameBoard,
                                      Tile tile, 
                                      int sideMultiplier, 
                                      int fileToAdd,
                                      int rankToAdd, 
                                      double canPromotion) {
        Tile tileToCheck = newTileCounter.countNewTile(tile, sideMultiplier, fileToAdd, rankToAdd);
        if (tileToCheck != null) {
            if (gameBoard.getTile(tileToCheck.getFile(), tileToCheck.getRank()).getPiece() == null) {
                String move = createMovementString(tile, tileToCheck, canPromotion);
                moves = arrayModifier.addNewMoveToArray(moves, move);
            } 
        }
        return moves;
    }
    
    /**
     *
     * Checks the tile, and if it is empty adds it to the possible moves list.
     * 
     * @param moves the String where all the moves are added.
     * @param gameBoard The board on which to check the moves.
     * @param tile the start tile, where the piece is at the moment.
     * @param sideMultiplier 1 for White and -1 for Black. Makes White and Black pieces to move in opposite directions.
     * @param fileToAdd file movement in integer that is added to given tile.
     * @param rankToAdd rank movement in integer that is added to given tile.
     * @return moves list where empty tile is added, if it were empty (dah).
     */
    public String[] tileEmptyNOTUsedWithPawn(String[] moves, 
                                              Board gameBoard,
                                              Tile tile,
                                              int sideMultiplier,
                                              int fileToAdd,
                                              int rankToAdd) {
        Tile tileToCheck = newTileCounter.countNewTile(tile, sideMultiplier, fileToAdd, rankToAdd);
        if (tileToCheck != null) {
            if (gameBoard.getTile(tileToCheck.getFile(), tileToCheck.getRank()).getPiece() == null) {
                String move = createMovementString(tile, tileToCheck, 0); // no other piece can promote than Pawn
                moves = arrayModifier.addNewMoveToArray(moves, move);
            } 
        }
        return moves;
    }
    
    /**
     *
     * Transforms the given movement tiles to a String presentation of the move.
     * 
     * @param start The tile where the piece moves from.
     * @param finish The tile where the piece moves to.
     * @param canPromotion If piece is pawn and can promote. 
     * @return the String presentation of the movement.
     */
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
    
    /**
     *
     * Checks all moves for pieces that move in vectors (like rook, bishop, queen).
     * 
     * @param moves the String to add the moves.
     * @param movePairsFile vector movement of File values.
     * @param movePairsRank vector movement of rank value.
     * @param gameBoard the gameBoard on which to check to movements.
     * @param tile the tile where the piece is.
     * @param sideMultiplier -1 for Black and 1 for White. Black moves in opposite direction than White.
     * @return all moves this piece can move.
     */
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
                moves = this.tileEmptyNOTUsedWithPawn(moves, 
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
