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
public class AlphaBetaPrunerTest {
    private AlphaBetaPruner alphabeta;
    private Board testBoard;
    private BoardValueCalculator boardValue;
    
    
    public AlphaBetaPrunerTest() {
        alphabeta = new AlphaBetaPruner();
        testBoard = new Board();
        boardValue = new BoardValueCalculator();
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
    public void minimaxCOuntsCorrectValue() {
        testBoard.getTile(File.File_B, Rank.Rank_1).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_A, Rank.Rank_2).setPiece(new Knight(Side.BLACK));
        testBoard.getTile(File.File_A, Rank.Rank_3).setPiece(new Rook(Side.BLACK));
        testBoard.getTile(File.File_C, Rank.Rank_2).setPiece(new Bishop(Side.BLACK));
        
        assertThat(boardValue.allTilesBoardValue(testBoard), is(-80));

        int minmaxvalue = alphabeta.minimax(Side.WHITE, testBoard, 2, true);
        
        assertThat(minmaxvalue, is(-50));
    }
}
