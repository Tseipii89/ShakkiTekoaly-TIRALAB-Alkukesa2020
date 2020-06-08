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
        
        assertThat(testMoveValueCounter.moveValueCountAlphaBeta("b1a2", Side.WHITE, testBoard, 2), is(ab.alphabeta(Side.WHITE, testBoard, 2, -10000000, 10000000, true)));
        assertThat(testMoveValueCounter.moveValueCountAlphaBeta("b1a2", Side.WHITE, testBoard, 2), is(-50));
        
        testMoveValueCounter = new MoveValueCounter();
        testMoveValueCounter.moveValueCountAlphaBetaTest("b1b1", Side.BLACK, testBoard, 2);
        assertThat(testMoveValueCounter.alphabeta.steps, is(22));
        
        testMoveValueCounter = new MoveValueCounter();
        testMoveValueCounter.moveValueCountMinMaxTest("b1b1", Side.BLACK, testBoard, 2);
        assertThat(testMoveValueCounter.alphabeta.steps, is(41));
    }
}
