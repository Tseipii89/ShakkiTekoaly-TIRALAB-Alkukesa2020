/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructureproject;

import chess.elements.Board;
import chess.elements.File;
import chess.elements.Rank;
import chess.model.Side;
import chess.rules.MovementGenerator;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pieces.Bishop;
import pieces.Knight;
import pieces.Rook;

/**
 *
 * @author juhop
 */
public class MoveValueCounterTest {
    private MoveValueCounter testMoveValueCounter;
    private Board testBoard;
    private MovementGenerator moveGenerator;
    private BoardValueCalculator valueCalc;
    
    public MoveValueCounterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
  
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testMoveValueCounter = new MoveValueCounter();
        testBoard = new Board();  
        moveGenerator = new MovementGenerator();
        this.valueCalc = new BoardValueCalculator();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void moveValueCountMinMaxReturnsCorrectValue() {
        AlphaBetaPruner ab = new AlphaBetaPruner();
        testBoard.getTile(File.File_B, Rank.Rank_1).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_C, Rank.Rank_2).setPiece(new Knight(Side.BLACK));
        testBoard.getTile(File.File_C, Rank.Rank_3).setPiece(new Rook(Side.BLACK));
        testBoard.getTile(File.File_A, Rank.Rank_2).setPiece(new Bishop(Side.BLACK));
        
        assertThat(testMoveValueCounter.moveValueCountAlphaBeta("b1a2", Side.WHITE, testBoard, 2), is(ab.alphabeta(Side.WHITE, testBoard, 3, -10000000, 10000000)));
        assertThat(testMoveValueCounter.moveValueCountAlphaBeta("b1a2", Side.WHITE, testBoard, 2), is(-51));
        
        testMoveValueCounter = new MoveValueCounter();
        testMoveValueCounter.moveValueCountAlphaBetaTest("b1b1", Side.BLACK, testBoard, 2);
        assertThat(testMoveValueCounter.alphabeta.steps, is(27));
        
        testMoveValueCounter = new MoveValueCounter();
        testMoveValueCounter.moveValueCountMinMaxTest("b1b1", Side.BLACK, testBoard, 2);
        assertThat(testMoveValueCounter.alphabeta.steps, is(41));
    }
    
    
    // This is a test to check why tiraBot doesn't work correctly with position update
    @Test
    public void moveValueCountCountsCorrectlyAttackMove() {
        testBoard.initBoard();

        moveGenerator.updateMovementOnBoard("c1b3", testBoard);
        moveGenerator.updateMovementOnBoard("b8a4", testBoard);
        moveGenerator.updateMovementOnBoard("a8a5", testBoard);
        moveGenerator.updateMovementOnBoard("c8c4", testBoard);
        
        int boardValueBeforeMove = this.valueCalc.allTilesBoardValue(testBoard);
       
        
        int attackMove = testMoveValueCounter.moveValueCount("b3c4", 1, testBoard);
        int testMove = testMoveValueCounter.moveValueCount("g2g3", 1, testBoard);
        
        assertThat(attackMove, is(31) );
        assertThat(testMove, is(-2) );
    }
    
}
