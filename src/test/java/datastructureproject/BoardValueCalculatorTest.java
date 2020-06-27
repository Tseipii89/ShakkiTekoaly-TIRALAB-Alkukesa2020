/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructureproject;

import chess.elements.Board;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhop
 */
public class BoardValueCalculatorTest {
    
    private Board testBoard;
    private BoardValueCalculator valueCalc;
    
    public BoardValueCalculatorTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.testBoard = new Board();
        this.valueCalc = new BoardValueCalculator();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void initBoardHasEqualValue() {
        testBoard.initBoard();
        
        assertThat(valueCalc.allTilesBoardValue(testBoard), is(0) );
    }
}
