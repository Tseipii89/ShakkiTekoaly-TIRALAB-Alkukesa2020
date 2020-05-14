package gameElements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class BoardTest {
    
    private final Board testBoard;
    
    public BoardTest() {
        testBoard = new Board();
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
    public void boardHas64Tiles() {
        assertThat(testBoard.getTilesList().length, is(64));
    }
    
    @Test
    public void getTileReturnsRightTile() {
        
        // A1
        
        int fileToTest = testBoard.getTile(File.File_A, Rank.Rank_1).getFile().getIntegerFile();
        int rankToTest = testBoard.getTile(File.File_A, Rank.Rank_1).getRank().getIntegerRank();
        assertThat(fileToTest, is(new Tile(File.File_A, Rank.Rank_1).getFile().getIntegerFile()));
        assertThat(rankToTest, is(new Tile(File.File_A, Rank.Rank_1).getRank().getIntegerRank()));
        
        // B5
        
        int fileToTest2 = testBoard.getTile(File.File_B, Rank.Rank_5).getFile().getIntegerFile();
        int rankToTest2 = testBoard.getTile(File.File_B, Rank.Rank_5).getRank().getIntegerRank();
        assertThat(fileToTest2, is(new Tile(File.File_B, Rank.Rank_5).getFile().getIntegerFile()));
        assertThat(rankToTest2, is(new Tile(File.File_B, Rank.Rank_5).getRank().getIntegerRank()));
        
        // C7
        
        int fileToTest3 = testBoard.getTile(File.File_C, Rank.Rank_7).getFile().getIntegerFile();
        int rankToTest3 = testBoard.getTile(File.File_C, Rank.Rank_7).getRank().getIntegerRank();
        assertThat(fileToTest3, is(new Tile(File.File_C, Rank.Rank_7).getFile().getIntegerFile()));
        assertThat(rankToTest3, is(new Tile(File.File_C, Rank.Rank_7).getRank().getIntegerRank()));
        
        // H8
        
        int fileToTest4 = testBoard.getTile(File.File_H, Rank.Rank_8).getFile().getIntegerFile();
        int rankToTest4 = testBoard.getTile(File.File_H, Rank.Rank_8).getRank().getIntegerRank();
        assertThat(fileToTest4, is(new Tile(File.File_H, Rank.Rank_8).getFile().getIntegerFile()));
        assertThat(rankToTest4, is(new Tile(File.File_H, Rank.Rank_8).getRank().getIntegerRank()));
        
    }
}
