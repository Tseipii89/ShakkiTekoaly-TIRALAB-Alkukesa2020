package gameElements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import chess.model.Side;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pieces.PieceType;

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
    public void assertAllTilesAreSet() {
        assertThat(testBoard.getTilesList()[0].toString(), is("A1"));
        assertThat(testBoard.getTilesList()[1].toString(), is("B1"));
        assertThat(testBoard.getTilesList()[2].toString(), is("C1"));
        assertThat(testBoard.getTilesList()[3].toString(), is("D1"));
        assertThat(testBoard.getTilesList()[4].toString(), is("E1"));
        assertThat(testBoard.getTilesList()[5].toString(), is("F1"));
        assertThat(testBoard.getTilesList()[6].toString(), is("G1"));
        assertThat(testBoard.getTilesList()[7].toString(), is("H1"));

        assertThat(testBoard.getTilesList()[8].toString(), is("A2"));
        assertThat(testBoard.getTilesList()[9].toString(), is("B2"));
        assertThat(testBoard.getTilesList()[10].toString(), is("C2"));
        assertThat(testBoard.getTilesList()[11].toString(), is("D2"));
        assertThat(testBoard.getTilesList()[12].toString(), is("E2"));
        assertThat(testBoard.getTilesList()[13].toString(), is("F2"));
        assertThat(testBoard.getTilesList()[14].toString(), is("G2"));
        assertThat(testBoard.getTilesList()[15].toString(), is("H2"));
        
        assertThat(testBoard.getTilesList()[16].toString(), is("A3"));
        assertThat(testBoard.getTilesList()[17].toString(), is("B3"));
        assertThat(testBoard.getTilesList()[18].toString(), is("C3"));
        assertThat(testBoard.getTilesList()[19].toString(), is("D3"));
        assertThat(testBoard.getTilesList()[20].toString(), is("E3"));
        assertThat(testBoard.getTilesList()[21].toString(), is("F3"));
        assertThat(testBoard.getTilesList()[22].toString(), is("G3"));
        assertThat(testBoard.getTilesList()[23].toString(), is("H3"));
        
        assertThat(testBoard.getTilesList()[24].toString(), is("A4"));
        assertThat(testBoard.getTilesList()[25].toString(), is("B4"));
        assertThat(testBoard.getTilesList()[26].toString(), is("C4"));
        assertThat(testBoard.getTilesList()[27].toString(), is("D4"));
        assertThat(testBoard.getTilesList()[28].toString(), is("E4"));
        assertThat(testBoard.getTilesList()[29].toString(), is("F4"));
        assertThat(testBoard.getTilesList()[30].toString(), is("G4"));
        assertThat(testBoard.getTilesList()[31].toString(), is("H4"));
       
        assertThat(testBoard.getTilesList()[32].toString(), is("A5"));
        assertThat(testBoard.getTilesList()[33].toString(), is("B5"));
        assertThat(testBoard.getTilesList()[34].toString(), is("C5"));
        assertThat(testBoard.getTilesList()[35].toString(), is("D5"));
        assertThat(testBoard.getTilesList()[36].toString(), is("E5"));
        assertThat(testBoard.getTilesList()[37].toString(), is("F5"));
        assertThat(testBoard.getTilesList()[38].toString(), is("G5"));
        assertThat(testBoard.getTilesList()[39].toString(), is("H5"));
        
        assertThat(testBoard.getTilesList()[40].toString(), is("A6"));
        assertThat(testBoard.getTilesList()[41].toString(), is("B6"));
        assertThat(testBoard.getTilesList()[42].toString(), is("C6"));
        assertThat(testBoard.getTilesList()[43].toString(), is("D6"));
        assertThat(testBoard.getTilesList()[44].toString(), is("E6"));
        assertThat(testBoard.getTilesList()[45].toString(), is("F6"));
        assertThat(testBoard.getTilesList()[46].toString(), is("G6"));
        assertThat(testBoard.getTilesList()[47].toString(), is("H6"));
      
        assertThat(testBoard.getTilesList()[48].toString(), is("A7"));
        assertThat(testBoard.getTilesList()[49].toString(), is("B7"));
        assertThat(testBoard.getTilesList()[50].toString(), is("C7"));
        assertThat(testBoard.getTilesList()[51].toString(), is("D7"));
        assertThat(testBoard.getTilesList()[52].toString(), is("E7"));
        assertThat(testBoard.getTilesList()[53].toString(), is("F7"));
        assertThat(testBoard.getTilesList()[54].toString(), is("G7"));
        assertThat(testBoard.getTilesList()[55].toString(), is("H7"));
        
        assertThat(testBoard.getTilesList()[56].toString(), is("A8"));
        assertThat(testBoard.getTilesList()[57].toString(), is("B8"));
        assertThat(testBoard.getTilesList()[58].toString(), is("C8"));
        assertThat(testBoard.getTilesList()[59].toString(), is("D8"));
        assertThat(testBoard.getTilesList()[60].toString(), is("E8"));
        assertThat(testBoard.getTilesList()[61].toString(), is("F8"));
        assertThat(testBoard.getTilesList()[62].toString(), is("G8"));
        assertThat(testBoard.getTilesList()[63].toString(), is("H8"));
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
    
    @Test
    public void initBoardSets16Pawns() {
        int pawns = 0;
        testBoard.initBoard();
        
        for (int i = 0; i < 63; i++) {
            if (testBoard.getTilesList()[i].getPiece() == null) {
                
            } else if (testBoard.getTilesList()[i].getPiece().getPieceType() == PieceType.Pawn) {
                pawns++;
            }            
        }
        assertThat(pawns, is(16));
    }
    
    @Test
    public void whitePawnsCorrectPositionInitially() {
        int pawns = 0;
        testBoard.initBoard();
        
        for (int i = 0; i < 64; i++) {
            if (testBoard.getTilesList()[i].getPiece() == null) {
                
            } else if (testBoard.getTilesList()[i].getPiece().getPieceType() == PieceType.Pawn
                    && testBoard.getTilesList()[i].getRank() == Rank.Rank_2
                    && testBoard.getTilesList()[i].getPiece().getSide()== Side.WHITE) {
                pawns++;
            }
        }
        
         assertThat(pawns, is(8));
    }
    
    @Test
    public void blackPawnsCorrectPositionInitially() {
        int pawns = 0;
        testBoard.initBoard();
        
        for (int i = 0; i < 64; i++) {
            if (testBoard.getTilesList()[i].getPiece() == null) {
                
            } else if (testBoard.getTilesList()[i].getPiece().getPieceType() == PieceType.Pawn
                    && testBoard.getTilesList()[i].getRank() == Rank.Rank_7
                    && testBoard.getTilesList()[i].getPiece().getSide()== Side.BLACK) {
                pawns++;
            }
        }
        
         assertThat(pawns, is(8));
    }
}
