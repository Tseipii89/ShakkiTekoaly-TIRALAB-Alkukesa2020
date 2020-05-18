package pieces;

import chess.model.Side;
import chess.rules.MoveRules;
import gameElements.Board;
import gameElements.File;
import gameElements.Rank;
import gameElements.Tile;

public class Pawn implements Piece {
    
    private Side side;
    private PieceType pieceType;
    private MoveRules moveRules;
    
    public Pawn(Side side) {
        this.side = side;
        this.pieceType = PieceType.Pawn;
        moveRules = new MoveRules();
    }


    @Override
    public Side getSide() {
        return this.side;
    }

    @Override
    public void setSide(Side side) {
        this.side = side;
    }

    @Override
    public void setPieceType(PieceType type) {
        this.pieceType = type;
    }

    @Override
    public PieceType getPieceType() {
        return this.pieceType;
    }


    @Override
    public String[] getMoves(Board gameBoard, Tile tile, int sideMultiplier) {
        
        Piece tilesPiece = gameBoard.getTile(tile.getFile(), tile.getRank()).getPiece();
        
        String[] moves = new String[0];

        // Basic move
        Tile basicMovementTile = moveRules.countNewTile(tile, sideMultiplier, 0, 1);
        if (basicMovementTile != null) {
            if(gameBoard.getTile(basicMovementTile.getFile(), basicMovementTile.getRank()).getPiece() == null ) {
                double canPromotion = (4.5 + sideMultiplier * 2.5);
                String basic;
                if(tile.getRank().getIntegerRank() == canPromotion) {
                   basic = tile.getFile().toString() + tile.getRank().toString() + basicMovementTile.getFile().toString() + basicMovementTile.getRank().toString() + "Q"; 
                } else {
                   basic = tile.getFile().toString() + tile.getRank().toString() + basicMovementTile.getFile().toString() + basicMovementTile.getRank().toString();
                } 
               moves = moveRules.addNewMoveToArray(moves, basic);
            } 

        }
        
        // Start move 2 tiles
        Tile startMovementTile = moveRules.countNewTile(tile, sideMultiplier, 0, 2);
        if (startMovementTile == null) { }
        else if (gameBoard.getTile(startMovementTile.getFile(), startMovementTile.getRank()).getPiece() == null 
                && gameBoard.getTile(basicMovementTile.getFile(), basicMovementTile.getRank()).getPiece() == null) { // there can't be any pieces in between the two jump move {
                int canPromotion = (int)(4.5 + sideMultiplier * 2.5);
                String start;
                if(tile.getRank().getIntegerRank() == canPromotion) {
                   start = tile.getFile().toString() + tile.getRank().toString() + startMovementTile.getFile().toString() + startMovementTile.getRank().toString() + "Q"; 
                } else {
                   start = tile.getFile().toString() + tile.getRank().toString() + startMovementTile.getFile().toString() + startMovementTile.getRank().toString();
                }
            
            moves = moveRules.addNewMoveToArray(moves, start);
        }
        

        
        // Pawn attack
        Tile attack1MovementTile = moveRules.countNewTile(tile, sideMultiplier, -1, 1);
        if (attack1MovementTile == null) { }
        else if (gameBoard.getTile(attack1MovementTile.getFile(), attack1MovementTile.getRank()).getPiece() != null) {
            if (gameBoard.getTile(attack1MovementTile.getFile(), attack1MovementTile.getRank()).getPiece().getSide() != tilesPiece.getSide()) {
                int canPromotion = (int)(4.5 + sideMultiplier * 2.5);
                String attack1;
                if(tile.getRank().getIntegerRank() == canPromotion) {
                   attack1 = tile.getFile().toString() + tile.getRank().toString() + attack1MovementTile.getFile().toString() + attack1MovementTile.getRank().toString() + "Q"; 
                } else {
                   attack1 = tile.getFile().toString() + tile.getRank().toString() + attack1MovementTile.getFile().toString() + attack1MovementTile.getRank().toString();
                }
                
                moves = moveRules.addNewMoveToArray(moves, attack1);
            }
         } 
        
        Tile attack2MovementTile = moveRules.countNewTile(tile, sideMultiplier, 1, 1);
        if (attack2MovementTile == null) { }
        else if (gameBoard.getTile(attack2MovementTile.getFile(), attack2MovementTile.getRank()).getPiece() != null) {
            if (gameBoard.getTile(attack2MovementTile.getFile(), attack2MovementTile.getRank()).getPiece().getSide() != tilesPiece.getSide()) {
                
                int canPromotion = (int)(4.5 + sideMultiplier * 2.5);
                String attack2;
                if(tile.getRank().getIntegerRank() == canPromotion) {
                   attack2 = tile.getFile().toString() + tile.getRank().toString() + attack2MovementTile.getFile().toString() + attack2MovementTile.getRank().toString() + "Q"; 
                } else {
                   attack2 = tile.getFile().toString() + tile.getRank().toString() + attack2MovementTile.getFile().toString() + attack2MovementTile.getRank().toString();
                }
                moves = moveRules.addNewMoveToArray(moves, attack2);            
            }
        } 

        
        return moves;
    }

}
