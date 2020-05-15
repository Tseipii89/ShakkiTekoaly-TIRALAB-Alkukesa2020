/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructureproject;

import datastructureproject.MovesCalculator;
import gameElements.Board;
import gameElements.File;
import gameElements.Rank;
import gameElements.Tile;
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
public class MovesCalculatorTest {

    private Board testBoard;
    private MovesCalculator testMovesCalculator;
    
    public MovesCalculatorTest() {
        
        testBoard = new Board();
        testMovesCalculator = new MovesCalculator(testBoard);
        
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


    @Test
    public void calculateNewTileWorksWithinBoard() {
        
        // A1 to A2
        
        Tile tileA1 = testBoard.getTile(File.File_A, Rank.Rank_1);
        assertThat(testMovesCalculator.calculateNewTile(tileA1, 0, 1), is(testBoard.getTile(File.File_A, Rank.Rank_2)));
        
        // B5 to C5
        
        Tile tileB5 = testBoard.getTile(File.File_B, Rank.Rank_5);
        assertThat(testMovesCalculator.calculateNewTile(tileB5, 1, 0), is(testBoard.getTile(File.File_C, Rank.Rank_5)));
        
        // C7 to B6
        
        Tile tileC7 = testBoard.getTile(File.File_C, Rank.Rank_7);
        assertThat(testMovesCalculator.calculateNewTile(tileC7, -1, -1), is(testBoard.getTile(File.File_B, Rank.Rank_6)));

        
        // H8 to A4
        
        Tile tileH8 = testBoard.getTile(File.File_H, Rank.Rank_8);
        assertThat(testMovesCalculator.calculateNewTile(tileH8, -7, -4), is(testBoard.getTile(File.File_A, Rank.Rank_4)));
    }
    
    @Test
    public void calculateNewTileReturnsNullOutsideBoard() {
        
        // A1 to -A0
        
        Tile tileA1 = testBoard.getTile(File.File_A, Rank.Rank_1);
        assertNull(testMovesCalculator.calculateNewTile(tileA1, -1, -1));

    }
}
