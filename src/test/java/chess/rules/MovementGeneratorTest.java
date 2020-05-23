/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.rules;

import chess.model.Side;
import chess.elements.Board;
import chess.elements.File;
import chess.elements.Rank;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

/**
 *
 * @author juhop
 */
public class MovementGeneratorTest {
    
    private final Board testBoard;
    private final MovementGenerator testMovementGenerator;
    
    public MovementGeneratorTest() {
        testBoard = new Board();
        testMovementGenerator = new MovementGenerator();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    /* MOVEMENT PAWN */

    @Test
    public void whitePawnMovementReturn2MovesAsFirstMove() {
        testBoard.initBoard();
        String[] pawnMovesB2 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_B, Rank.Rank_2));
        assertThat(pawnMovesB2.length, is(2));
    }
    
    
    @Test
    public void whitePawnReturnsRightTwoMoves() {
        testBoard.initBoard();
        String[] pawnMovesB2 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_B, Rank.Rank_2));
        assertThat(pawnMovesB2[0], is("B2B3"));
        assertThat(pawnMovesB2[1], is("B2B4"));
    }
    
    @Test
    public void whitePawnMovementReturn0MoveAsFirstMoveIfEnemyIsInfront() {
        testBoard.getTile(File.File_D, Rank.Rank_2).setPiece(new Pawn(Side.WHITE));
        testBoard.getTile(File.File_D, Rank.Rank_3).setPiece(new Pawn(Side.BLACK));
        String[] pawnMovesD2 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_D, Rank.Rank_2));
        assertThat(pawnMovesD2.length, is(0));
    }
    
    @Test
    public void whitePawnMovementReturn1MovesWhenFirstMoveDone() {
        testBoard.getTile(File.File_D, Rank.Rank_3).setPiece(new Pawn(Side.WHITE));
        String[] pawnMovesD3 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_D, Rank.Rank_3));
        assertThat(pawnMovesD3[0], is("D3D4"));
    }

    
    @Test
    public void blackPawnMovementReturn2MovesAsFirstMove() {
        testBoard.initBoard();
        String[] pawnMovesB7 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_B, Rank.Rank_7));
        assertThat(pawnMovesB7.length, is(2));
    }
    
    
    @Test
    public void blackPawnReturnsRightTwoMoves() {
        testBoard.initBoard();
        String[] pawnMovesB7 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_B, Rank.Rank_7));
        assertThat(pawnMovesB7[0], is("B7B6"));
        assertThat(pawnMovesB7[1], is("B7B5"));
    }
    
    @Test
    public void blackPawnMovementReturn1MoveAsFirstMoveIfEnemyIsInfront() {
        testBoard.getTile(File.File_D, Rank.Rank_6).setPiece(new Pawn(Side.WHITE));
        testBoard.getTile(File.File_D, Rank.Rank_7).setPiece(new Pawn(Side.BLACK));
        String[] pawnMovesB2 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_D, Rank.Rank_7));
        assertThat(pawnMovesB2.length, is(0));
    }
    
    @Test
    public void blackPawnMovementReturn1MovesWhenFirstMoveDone() {
        testBoard.getTile(File.File_D, Rank.Rank_6).setPiece(new Pawn(Side.BLACK));
        String[] pawnMovesD6 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_D, Rank.Rank_6));
        assertThat(pawnMovesD6[0], is("D6D5"));
    }
    
    @Test
    public void whitePawnAttacksRightAmountOfMoves() {
        testBoard.getTile(File.File_D, Rank.Rank_4).setPiece(new Pawn(Side.WHITE));
        testBoard.getTile(File.File_C, Rank.Rank_5).setPiece(new Pawn(Side.BLACK));
        testBoard.getTile(File.File_D, Rank.Rank_5).setPiece(new Pawn(Side.BLACK));
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new Pawn(Side.BLACK));
        String[] pawnMovesD4 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_D, Rank.Rank_4));
        assertThat(pawnMovesD4.length, is(2));
    }
    
    @Test
    public void whitePawnAttacksRightMoves() {
        testBoard.getTile(File.File_D, Rank.Rank_4).setPiece(new Pawn(Side.WHITE));
        testBoard.getTile(File.File_C, Rank.Rank_5).setPiece(new Pawn(Side.BLACK));
        testBoard.getTile(File.File_D, Rank.Rank_5).setPiece(new Pawn(Side.BLACK));
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new Pawn(Side.BLACK));
        String[] pawnMovesD4 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_D, Rank.Rank_4));
        assertThat(pawnMovesD4[0], is("D4C5"));
        assertThat(pawnMovesD4[1], is("D4E5"));
    }
    
    @Test
    public void blackPawnAttacksRightAmountOfMoves() {
        testBoard.getTile(File.File_D, Rank.Rank_5).setPiece(new Pawn(Side.BLACK));
        testBoard.getTile(File.File_C, Rank.Rank_4).setPiece(new Pawn(Side.WHITE));
        testBoard.getTile(File.File_D, Rank.Rank_4).setPiece(new Pawn(Side.WHITE));
        testBoard.getTile(File.File_E, Rank.Rank_4).setPiece(new Pawn(Side.WHITE));
        String[] pawnMovesD4 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_D, Rank.Rank_5));
        assertThat(pawnMovesD4.length, is(2));
    }
    
    
    @Test
    public void blackPawnAttacksRightMoves() {
        testBoard.getTile(File.File_D, Rank.Rank_5).setPiece(new Pawn(Side.BLACK));
        testBoard.getTile(File.File_C, Rank.Rank_4).setPiece(new Pawn(Side.WHITE));
        testBoard.getTile(File.File_D, Rank.Rank_4).setPiece(new Pawn(Side.WHITE));
        testBoard.getTile(File.File_E, Rank.Rank_4).setPiece(new Pawn(Side.WHITE));
        String[] pawnMovesD4 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_D, Rank.Rank_5));
        assertThat(pawnMovesD4[0], is("D5E4"));
        assertThat(pawnMovesD4[1], is("D5C4"));
    }
    
    @Test
    public void whitePawnsCantGoOutsideBoard() {
        testBoard.getTile(File.File_D, Rank.Rank_8).setPiece(new Pawn(Side.WHITE));
        String[] pawnMovesD8 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_D, Rank.Rank_8));
        assertThat(pawnMovesD8.length, is(0));
    }
    
    @Test
    public void blackPawnsCantGoOutsideBoard() {
        testBoard.getTile(File.File_D, Rank.Rank_1).setPiece(new Pawn(Side.BLACK));
        String[] pawnMovesD8 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_D, Rank.Rank_1));
        assertThat(pawnMovesD8.length, is(0));
    }
    
    @Test
    public void whitePawnsPromotesToQueen() {
        testBoard.getTile(File.File_D, Rank.Rank_7).setPiece(new Pawn(Side.WHITE));
        String[] pawnMovesD8 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_D, Rank.Rank_7));
        assertThat(pawnMovesD8[0], is("D7D8Q"));
    }
    
    @Test
    public void blackPawnsPromotesToQueen() {
        testBoard.getTile(File.File_D, Rank.Rank_2).setPiece(new Pawn(Side.BLACK));
        String[] pawnMovesD8 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_D, Rank.Rank_2));
        assertThat(pawnMovesD8[0], is("D2D1Q"));
    }
    
    @Test
    public void whitePawnsAttacksAndPromotesToQueen() {
        testBoard.getTile(File.File_D, Rank.Rank_7).setPiece(new Pawn(Side.WHITE));
        testBoard.getTile(File.File_C, Rank.Rank_8).setPiece(new Pawn(Side.BLACK));
        String[] pawnMovesD8 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_D, Rank.Rank_7));
        assertThat(pawnMovesD8[0], is("D7D8Q"));
        assertThat(pawnMovesD8[1], is("D7C8Q"));
    }
    
    @Test
    public void blackPawnsAttacksAndPromotesToQueen() {
        testBoard.getTile(File.File_D, Rank.Rank_2).setPiece(new Pawn(Side.BLACK));
        testBoard.getTile(File.File_C, Rank.Rank_1).setPiece(new Pawn(Side.WHITE));
        String[] pawnMovesD8 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_D, Rank.Rank_2));
        assertThat(pawnMovesD8[0], is("D2D1Q"));
        assertThat(pawnMovesD8[1], is("D2C1Q"));
    }
    
    /* MOVEMENT KNIGHT */
    
    @Test
    public void whiteKnightMovementReturn2MovesAsFirstMove() {
        testBoard.initBoard();
        String[] knightMovesB1 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_B, Rank.Rank_1));
        assertThat(knightMovesB1.length, is(2));
    }
    
    @Test
    public void whiteKnightReturnsRightTwoMoves() {
        testBoard.initBoard();
        String[] knightMovesB1 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_B, Rank.Rank_1));
        assertThat(knightMovesB1[0], is("B1A3"));
        assertThat(knightMovesB1[1], is("B1C3"));
    }
    
    @Test
    public void blackKnightMovementReturn2MovesAsFirstMove() {
        testBoard.initBoard();
        String[] knightMovesB1 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_B, Rank.Rank_1));
        assertThat(knightMovesB1.length, is(2));
    }
    
    @Test
    public void blackKnightReturnsRightTwoMoves() {
        testBoard.initBoard();
        String[] knightMovesG8 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_G, Rank.Rank_8));
        assertThat(knightMovesG8[0], is("G8H6"));
        assertThat(knightMovesG8[1], is("G8F6"));
    }
    
    @Test
    public void blackKnightReturnRight8moves() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new Knight(Side.BLACK));
        String[] knightMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        assertThat(knightMovesE5[0], is("E5G4"));
        assertThat(knightMovesE5[1], is("E5F3"));
        assertThat(knightMovesE5[2], is("E5D3"));
        assertThat(knightMovesE5[3], is("E5C4"));
        assertThat(knightMovesE5[4], is("E5C6"));
        assertThat(knightMovesE5[5], is("E5D7"));
        assertThat(knightMovesE5[6], is("E5F7"));
        assertThat(knightMovesE5[7], is("E5G6"));
    }
    
    @Test
    public void knightReturnRight4movesIfNextToEdge() {
        testBoard.getTile(File.File_H, Rank.Rank_5).setPiece(new Knight(Side.WHITE));
        String[] knightMovesH5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_H, Rank.Rank_5));

        assertThat(knightMovesH5.length, is(4));
        assertThat(knightMovesH5[0], is("H5F6"));
        assertThat(knightMovesH5[1], is("H5G7"));
        assertThat(knightMovesH5[2], is("H5G3"));
        assertThat(knightMovesH5[3], is("H5F4"));
    }
    
    @Test
    public void knightReturnRight4movesIfNextToEdgeAndEnemy() {
        testBoard.getTile(File.File_H, Rank.Rank_5).setPiece(new Knight(Side.WHITE));
        testBoard.getTile(File.File_F, Rank.Rank_6).setPiece(new Knight(Side.BLACK));
        String[] knightMovesH5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_H, Rank.Rank_5));

        assertThat(knightMovesH5.length, is(4));
        assertThat(knightMovesH5[0], is("H5F6"));
        assertThat(knightMovesH5[1], is("H5G7"));
        assertThat(knightMovesH5[2], is("H5G3"));
        assertThat(knightMovesH5[3], is("H5F4"));
    }
    
    @Test
    public void knightReturnRight3movesIfNextToEdgeAndOwnPiece() {
        testBoard.getTile(File.File_H, Rank.Rank_5).setPiece(new Knight(Side.WHITE));
        testBoard.getTile(File.File_F, Rank.Rank_6).setPiece(new Knight(Side.WHITE));
        String[] knightMovesH5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_H, Rank.Rank_5));

        assertThat(knightMovesH5.length, is(3));
        assertThat(knightMovesH5[0], is("H5G7"));
        assertThat(knightMovesH5[1], is("H5G3"));
        assertThat(knightMovesH5[2], is("H5F4"));
    }
    
    /* MOVEMENT ROOK */
    
    @Test
    public void whiteRookMovementReturn0MovesAsFirstMove() {
        testBoard.initBoard();
        String[] rookMovesA1 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_A, Rank.Rank_1));
        assertThat(rookMovesA1.length, is(0));
    }
    
    @Test
    public void blackRookReturnRightAmountOfMoves() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new Rook(Side.BLACK));
        String[] rookMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        assertThat(rookMovesE5.length, is(14));
    }
    
    @Test
    public void blackRookReturnRightMoves() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new Rook(Side.BLACK));
        String[] rookMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        assertThat(rookMovesE5[0], is("E5F5"));
        assertThat(rookMovesE5[1], is("E5G5"));
        assertThat(rookMovesE5[2], is("E5H5"));
        assertThat(rookMovesE5[3], is("E5E4"));
        assertThat(rookMovesE5[4], is("E5E3"));
        assertThat(rookMovesE5[5], is("E5E2"));
        assertThat(rookMovesE5[6], is("E5E1"));
        assertThat(rookMovesE5[7], is("E5D5"));
        assertThat(rookMovesE5[8], is("E5C5"));
        assertThat(rookMovesE5[9], is("E5B5"));
        assertThat(rookMovesE5[10], is("E5A5"));
        assertThat(rookMovesE5[11], is("E5E6"));
        assertThat(rookMovesE5[12], is("E5E7"));
        assertThat(rookMovesE5[13], is("E5E8"));
    }

    @Test
    public void blackRookReturnsRightMovesEnemyandOwnPlayerOnPaths() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new Rook(Side.BLACK));
        testBoard.getTile(File.File_H, Rank.Rank_5).setPiece(new Pawn(Side.BLACK));
        testBoard.getTile(File.File_E, Rank.Rank_7).setPiece(new Pawn(Side.WHITE));
        String[] rookMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        assertThat(rookMovesE5.length, is(12));
        
        assertThat(rookMovesE5[0], is("E5F5"));
        assertThat(rookMovesE5[1], is("E5G5"));
        assertThat(rookMovesE5[2], is("E5E4"));
        assertThat(rookMovesE5[3], is("E5E3"));
        assertThat(rookMovesE5[4], is("E5E2"));
        assertThat(rookMovesE5[5], is("E5E1"));
        assertThat(rookMovesE5[6], is("E5D5"));
        assertThat(rookMovesE5[7], is("E5C5"));
        assertThat(rookMovesE5[8], is("E5B5"));
        assertThat(rookMovesE5[9], is("E5A5"));
        assertThat(rookMovesE5[10], is("E5E6"));
        assertThat(rookMovesE5[11], is("E5E7"));
    }
    
    @Test
    public void whiteRookReturnsRightMovesEnemyandOwnPlayerOnPaths() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new Rook(Side.WHITE));
        testBoard.getTile(File.File_H, Rank.Rank_5).setPiece(new Pawn(Side.WHITE));
        testBoard.getTile(File.File_E, Rank.Rank_7).setPiece(new Pawn(Side.BLACK));
        String[] rookMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        assertThat(rookMovesE5.length, is(12));
        
        assertThat(rookMovesE5[0], is("E5D5"));
        assertThat(rookMovesE5[1], is("E5C5"));
        assertThat(rookMovesE5[2], is("E5B5"));
        assertThat(rookMovesE5[3], is("E5A5"));
        assertThat(rookMovesE5[4], is("E5E6"));
        assertThat(rookMovesE5[5], is("E5E7"));
        assertThat(rookMovesE5[6], is("E5F5"));
        assertThat(rookMovesE5[7], is("E5G5"));
        assertThat(rookMovesE5[8], is("E5E4"));
        assertThat(rookMovesE5[9], is("E5E3"));
        assertThat(rookMovesE5[10], is("E5E2"));
        assertThat(rookMovesE5[11], is("E5E1"));
    }
    
    /* MOVEMENT BISHOP */
    
    @Test
    public void whiteBishopMovementReturn0MovesAsFirstMove() {
        testBoard.initBoard();
        String[] BishopMovesA1 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_A, Rank.Rank_1));
        assertThat(BishopMovesA1.length, is(0));
    }
    
    @Test
    public void whiteRookReturnRightAmountOfMoves() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new Bishop(Side.WHITE));
        String[] bishopMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        assertThat(bishopMovesE5.length, is(13));
    }
    
    @Test
    public void whiteBishopReturnRightMoves() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new Bishop(Side.WHITE));
        String[] rookMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        assertThat(rookMovesE5[0], is("E5D6"));
        assertThat(rookMovesE5[1], is("E5C7"));
        assertThat(rookMovesE5[2], is("E5B8"));
        assertThat(rookMovesE5[3], is("E5F6"));
        assertThat(rookMovesE5[4], is("E5G7"));
        assertThat(rookMovesE5[5], is("E5H8"));
        assertThat(rookMovesE5[6], is("E5F4"));
        assertThat(rookMovesE5[7], is("E5G3"));
        assertThat(rookMovesE5[8], is("E5H2"));
        assertThat(rookMovesE5[9], is("E5D4"));
        assertThat(rookMovesE5[10], is("E5C3"));
        assertThat(rookMovesE5[11], is("E5B2"));
        assertThat(rookMovesE5[12], is("E5A1"));
    }
    
    @Test
    public void whiteBishopReturnsRightMovesEnemyandOwnPlayerOnPaths() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_C, Rank.Rank_7).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_G, Rank.Rank_3).setPiece(new Bishop(Side.BLACK));
        String[] rookMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        assertThat(rookMovesE5.length, is(10));
        
        assertThat(rookMovesE5[0], is("E5D6"));
        assertThat(rookMovesE5[1], is("E5F6"));
        assertThat(rookMovesE5[2], is("E5G7"));
        assertThat(rookMovesE5[3], is("E5H8"));
        assertThat(rookMovesE5[4], is("E5F4"));
        assertThat(rookMovesE5[5], is("E5G3"));
        assertThat(rookMovesE5[6], is("E5D4"));
        assertThat(rookMovesE5[7], is("E5C3"));
        assertThat(rookMovesE5[8], is("E5B2"));
        assertThat(rookMovesE5[9], is("E5A1"));
    }

    @Test
    public void blackBishopReturnsRightMovesEnemyandOwnPlayerOnPaths() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new Bishop(Side.BLACK));
        testBoard.getTile(File.File_C, Rank.Rank_7).setPiece(new Bishop(Side.BLACK));
        testBoard.getTile(File.File_G, Rank.Rank_3).setPiece(new Bishop(Side.WHITE));
        String[] rookMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        assertThat(rookMovesE5.length, is(10));
        
        assertThat(rookMovesE5[0], is("E5F4"));
        assertThat(rookMovesE5[1], is("E5G3"));
        assertThat(rookMovesE5[2], is("E5D4"));
        assertThat(rookMovesE5[3], is("E5C3"));
        assertThat(rookMovesE5[4], is("E5B2"));
        assertThat(rookMovesE5[5], is("E5A1"));
        assertThat(rookMovesE5[6], is("E5D6"));
        assertThat(rookMovesE5[7], is("E5F6"));
        assertThat(rookMovesE5[8], is("E5G7"));
        assertThat(rookMovesE5[9], is("E5H8"));
    }
    
    /* MOVEMENT QUEEN */  
    
    @Test
    public void whiteQueenMovementReturn0MovesAsFirstMove() {
        testBoard.initBoard();
        String[] queenMovesD1 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_D, Rank.Rank_1));
        assertThat(queenMovesD1.length, is(0));
    }
    
    @Test
    public void whiteQueenReturnRightAmountOfMoves() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new Queen(Side.WHITE));
        String[] queenMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        assertThat(queenMovesE5.length, is(27));
    }
    
    @Test
    public void whiteQueenReturnRightMoves() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new Queen(Side.WHITE));
        String[] queenMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        assertThat(queenMovesE5[0], is("E5D5"));
        assertThat(queenMovesE5[1], is("E5C5"));
        assertThat(queenMovesE5[2], is("E5B5"));
        assertThat(queenMovesE5[3], is("E5A5"));
        assertThat(queenMovesE5[4], is("E5D6"));
        assertThat(queenMovesE5[5], is("E5C7"));
        assertThat(queenMovesE5[6], is("E5B8"));
        assertThat(queenMovesE5[7], is("E5E6"));
        assertThat(queenMovesE5[8], is("E5E7"));
        assertThat(queenMovesE5[9], is("E5E8"));
        assertThat(queenMovesE5[10], is("E5F6"));
        assertThat(queenMovesE5[11], is("E5G7"));
        assertThat(queenMovesE5[12], is("E5H8"));
        assertThat(queenMovesE5[13], is("E5F5"));
        assertThat(queenMovesE5[14], is("E5G5"));
        assertThat(queenMovesE5[15], is("E5H5"));
        assertThat(queenMovesE5[16], is("E5F4"));
        assertThat(queenMovesE5[17], is("E5G3"));
        assertThat(queenMovesE5[18], is("E5H2"));
        assertThat(queenMovesE5[19], is("E5E4"));
        assertThat(queenMovesE5[20], is("E5E3"));
        assertThat(queenMovesE5[21], is("E5E2"));
        assertThat(queenMovesE5[22], is("E5E1"));
        assertThat(queenMovesE5[23], is("E5D4"));
        assertThat(queenMovesE5[24], is("E5C3"));
        assertThat(queenMovesE5[25], is("E5B2"));
        assertThat(queenMovesE5[26], is("E5A1"));
                
    }
    
    @Test
    public void whiteQueenReturnsRightMovesEnemyandOwnPlayerOnPaths() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new Queen(Side.WHITE));
        testBoard.getTile(File.File_C, Rank.Rank_7).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_G, Rank.Rank_3).setPiece(new Bishop(Side.BLACK));
        String[] queenMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        
        assertThat(queenMovesE5.length, is(24));
        
        assertThat(queenMovesE5[0], is("E5D5"));
        assertThat(queenMovesE5[1], is("E5C5"));
        assertThat(queenMovesE5[2], is("E5B5"));
        assertThat(queenMovesE5[3], is("E5A5"));
        assertThat(queenMovesE5[4], is("E5D6"));
        assertThat(queenMovesE5[5], is("E5E6"));
        assertThat(queenMovesE5[6], is("E5E7"));
        assertThat(queenMovesE5[7], is("E5E8"));
        assertThat(queenMovesE5[8], is("E5F6"));
        assertThat(queenMovesE5[9], is("E5G7"));
        assertThat(queenMovesE5[10], is("E5H8"));
        assertThat(queenMovesE5[11], is("E5F5"));
        assertThat(queenMovesE5[12], is("E5G5"));
        assertThat(queenMovesE5[13], is("E5H5"));
        assertThat(queenMovesE5[14], is("E5F4"));
        assertThat(queenMovesE5[15], is("E5G3"));
        assertThat(queenMovesE5[16], is("E5E4"));
        assertThat(queenMovesE5[17], is("E5E3"));
        assertThat(queenMovesE5[18], is("E5E2"));
        assertThat(queenMovesE5[19], is("E5E1"));
        assertThat(queenMovesE5[20], is("E5D4"));
        assertThat(queenMovesE5[21], is("E5C3"));
        assertThat(queenMovesE5[22], is("E5B2"));
        assertThat(queenMovesE5[23], is("E5A1"));
                
    }
    
    @Test
    public void blackQueenReturnsRightMovesEnemyandOwnPlayerOnPaths() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new Queen(Side.BLACK));
        testBoard.getTile(File.File_C, Rank.Rank_7).setPiece(new Bishop(Side.BLACK));
        testBoard.getTile(File.File_G, Rank.Rank_3).setPiece(new Bishop(Side.WHITE));
        String[] queenMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        
        assertThat(queenMovesE5.length, is(24));
        
        assertThat(queenMovesE5[0], is("E5F5"));
        assertThat(queenMovesE5[1], is("E5G5"));
        assertThat(queenMovesE5[2], is("E5H5"));
        assertThat(queenMovesE5[3], is("E5F4"));
        assertThat(queenMovesE5[4], is("E5G3"));
        assertThat(queenMovesE5[5], is("E5E4"));
        assertThat(queenMovesE5[6], is("E5E3"));
        assertThat(queenMovesE5[7], is("E5E2"));
        assertThat(queenMovesE5[8], is("E5E1"));
        assertThat(queenMovesE5[9], is("E5D4"));
        assertThat(queenMovesE5[10], is("E5C3"));
        assertThat(queenMovesE5[11], is("E5B2"));
        assertThat(queenMovesE5[12], is("E5A1"));
        assertThat(queenMovesE5[13], is("E5D5"));
        assertThat(queenMovesE5[14], is("E5C5"));
        assertThat(queenMovesE5[15], is("E5B5"));
        assertThat(queenMovesE5[16], is("E5A5"));
        assertThat(queenMovesE5[17], is("E5D6"));
        assertThat(queenMovesE5[18], is("E5E6"));
        assertThat(queenMovesE5[19], is("E5E7"));
        assertThat(queenMovesE5[20], is("E5E8"));
        assertThat(queenMovesE5[21], is("E5F6"));
        assertThat(queenMovesE5[22], is("E5G7"));
        assertThat(queenMovesE5[23], is("E5H8"));        
    }
    
    /* MOVEMENT KING */
    
    
    @Test
    public void whiteKingMovementReturn0MovesAsFirstMove() {
        testBoard.initBoard();
        String[] kingMovesD1 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_1));
        assertThat(kingMovesD1.length, is(0));
    }
    

    @Test
    public void whiteKingReturnRightAmountOfMoves() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new King(Side.WHITE));
        String[] kingMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        assertThat(kingMovesE5.length, is(8));
    }
  
    @Test
    public void whiteKingReturnRightMoves() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new King(Side.WHITE));
        String[] kingMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        assertThat(kingMovesE5[0], is("E5D5"));
        assertThat(kingMovesE5[1], is("E5D6"));
        assertThat(kingMovesE5[2], is("E5E6"));
        assertThat(kingMovesE5[3], is("E5F6"));
        assertThat(kingMovesE5[4], is("E5F5"));
        assertThat(kingMovesE5[5], is("E5F4"));
        assertThat(kingMovesE5[6], is("E5E4"));
        assertThat(kingMovesE5[7], is("E5D4"));

                
    }
   
    @Test
    public void whiteKingReturnsRightMovesEnemyandOwnPlayerOnPaths() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new King(Side.WHITE));
        testBoard.getTile(File.File_D, Rank.Rank_6).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_E, Rank.Rank_6).setPiece(new Bishop(Side.BLACK));
        String[] kingMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        
        assertThat(kingMovesE5.length, is(7));
        
        assertThat(kingMovesE5[0], is("E5D5"));
        assertThat(kingMovesE5[1], is("E5E6"));
        assertThat(kingMovesE5[2], is("E5F6"));
        assertThat(kingMovesE5[3], is("E5F5"));
        assertThat(kingMovesE5[4], is("E5F4"));
        assertThat(kingMovesE5[5], is("E5E4"));
        assertThat(kingMovesE5[6], is("E5D4"));

                
    }

    @Test
    public void blackKingReturnsRightMovesEnemyandOwnPlayerOnPaths() {
        testBoard.getTile(File.File_E, Rank.Rank_5).setPiece(new King(Side.BLACK));
        testBoard.getTile(File.File_D, Rank.Rank_6).setPiece(new Bishop(Side.BLACK));
        testBoard.getTile(File.File_E, Rank.Rank_6).setPiece(new Bishop(Side.WHITE));
        String[] kingMovesE5 = testMovementGenerator.pieceMovement(testBoard, testBoard.getTile(File.File_E, Rank.Rank_5));

        
        assertThat(kingMovesE5.length, is(7));
        
        assertThat(kingMovesE5[0], is("E5F5"));
        assertThat(kingMovesE5[1], is("E5F4"));
        assertThat(kingMovesE5[2], is("E5E4"));
        assertThat(kingMovesE5[3], is("E5D4"));
        assertThat(kingMovesE5[4], is("E5D5"));
        assertThat(kingMovesE5[5], is("E5E6"));
        assertThat(kingMovesE5[6], is("E5F6"));

      
    }
}
