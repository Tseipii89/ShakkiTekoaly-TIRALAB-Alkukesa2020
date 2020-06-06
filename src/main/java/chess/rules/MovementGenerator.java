/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.rules;

import chess.model.Side;
import chess.elements.Board;
import chess.elements.Tile;
import datastructureproject.datamodifiers.ArrayModifier;
import pieces.Bishop;
import pieces.Knight;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

/**
 * Checks the piece in the tile and returns all the legal moves for that piece.
 * @author juhop
 */
public class MovementGenerator {
    private final NewTileCounter newTileCounter;
    private final ArrayModifier arrayModifier;
    /**
     * Creates MovementGenerator to be ready to be used.
     */
    public MovementGenerator() {
       newTileCounter = new NewTileCounter();
       arrayModifier = new ArrayModifier();
    }
    
    /**
     *
     * @param gameBoard the board used in the game. All references should be done to this board during the game.
     * @param tile the tile for which we want to check all possible moves during this turn.
     * @return array of all possible moves in Algebraic notation
     */
    public String[] pieceMovement(Board gameBoard, Tile tile) {
        
        Piece piece = gameBoard.getTile(tile.getFile(), tile.getRank()).getPiece();
        if (piece.getPieceType() == null) {
            return null;
        }
        
        int sideMultiplier = 1;
        if (piece.getSide() == Side.BLACK) {
            sideMultiplier = -1;
        }
        
        return piece.getMoves(gameBoard, tile, sideMultiplier); 
    }
    
    
     /**
     *
     * Method goes through the game Board, and returns all possible moves for given player's Side.
     * Made public for testing purposes.
     * 
     * @param sideToPlay count all moves for this Side.
     * @param boardToCheck count all moves from this board given the board's placement situation.
     * @param moves add all the moves to this String array
     * @return return new array where all the legal moves are added
     */
    public String[] countAllMoves(Side sideToPlay, Board boardToCheck, String[] moves) {
        Tile[] tilesList = boardToCheck.getTilesList();
        for (int i = 0; i < 64; i++) {
            if (tilesList[i].getPiece() != null) {
                if (tilesList[i].getPiece().getSide() == sideToPlay) {
                    String[] thisTileMoves = this.pieceMovement(boardToCheck, tilesList[i]);
                    moves = arrayModifier.addNewArrayToArray(thisTileMoves, moves);
                }
            }
            
        }
        return moves;
    }
    
    
     /**
     *
     * Counts the start tile and finish tile and possible promotion from given move String.
     * 
     * @param move the move in universal chess interface format.
     * @param board the Board against into which the move should be updated
     */
    public void updateMovementOnBoard(String move, Board board) {

        Tile startTile = newTileCounter.moveToTile(move, 0, 2, board);

        Tile finishTile = newTileCounter.moveToTile(move, 2, 4, board);
        
        
        String promote = "";
        if (move.length() > 4) {
            promote =  move.substring(4);
            promote = promote.toLowerCase();
        } 
        
        this.updateBoard(startTile, finishTile, promote);  
    }
    
        /**
     *
     * Method sets the tile the piece moves from having no piece, and sets the piece to the new tile.
     * Method also takes care of the promotion if needed.
     * 
     * @param startTile the tile where the piece moves from
     * @param finishTile the tile where the piece moves to
     * @param promote empty string if no promotion is done, otherwise it gets value "Q", "R", "N" or "B"
     */
    private void updateBoard(Tile startTile, Tile finishTile, String promote) {
        Piece pieceToMove = startTile.getPiece();
        startTile.setPiece(null);
        
        finishTile.setPiece(pieceToMove);
        switch (promote) {
            case "q": // queen
                finishTile.setPiece(new Queen(pieceToMove.getSide()));
                break;
            case "r": // rook
                finishTile.setPiece(new Rook(pieceToMove.getSide()));
                break;
            case "n": // knight
                finishTile.setPiece(new Knight(pieceToMove.getSide()));
                break;
            case "b": // bishop
                finishTile.setPiece(new Bishop(pieceToMove.getSide()));
                break;
            default:
        }
    }
}
