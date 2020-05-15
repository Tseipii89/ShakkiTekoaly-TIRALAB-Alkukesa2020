package gameElements;

import chess.model.Side;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pieces.Pawn;


public class TileTest {
    
    Tile testTile;
    
    public TileTest() {  
        testTile = new Tile(File.File_E, Rank.Rank_2);
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
    public void getFileReturnsRightSyntax() {
        assertTrue(testTile.getFile().getStringFile().matches("[A-H]"));
    }
    
    @Test
    public void getRankReturnsRightSyntax() {
        int high = 9;
        int low = 0;
        assertTrue("Error, Rank is too high", high > testTile.getRank().getIntegerRank());
        assertTrue("Error, Rank is too low",  low  < testTile.getRank().getIntegerRank());
    }
    
    @Test
    public void getPiecerReturnsRightSyntax() {
        Pawn testPawn = new Pawn(Side.BLACK);
        testTile.setPiece(testPawn);
        
        assertThat(testTile.getPiece(), is(testPawn));
    }
}
