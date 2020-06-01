/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

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

/**
 *
 * @author juhop
 */
public class RookTest {
    
    public RookTest() {
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
    public void rookCanAttack() {
        
        Board testBoard = new Board();

        testBoard.getTile(File.File_A, Rank.Rank_4).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_A, Rank.Rank_5).setPiece(new Rook(Side.BLACK));
        
        String[] rookMoves = testBoard.getTile(File.File_A, Rank.Rank_5).getPiece().getMoves(testBoard, testBoard.getTile(File.File_A, Rank.Rank_5), -1);
        
        assertThat(rookMoves.length, is(11));
        assertThat(rookMoves[7], is("a5a4"));
        
    }
}
