/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.rules;

import chess.model.Side;
import gameElements.Board;
import gameElements.File;
import gameElements.Rank;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pieces.Pawn;

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
    
}
