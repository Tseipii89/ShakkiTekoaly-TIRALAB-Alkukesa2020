package gameElements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import chess.elements.Board;
import chess.elements.Rank;
import chess.elements.Tile;
import chess.elements.File;
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
        assertThat(testBoard.getTilesList()[0].toString(), is("a1"));
        assertThat(testBoard.getTilesList()[1].toString(), is("b1"));
        assertThat(testBoard.getTilesList()[2].toString(), is("c1"));
        assertThat(testBoard.getTilesList()[3].toString(), is("d1"));
        assertThat(testBoard.getTilesList()[4].toString(), is("e1"));
        assertThat(testBoard.getTilesList()[5].toString(), is("f1"));
        assertThat(testBoard.getTilesList()[6].toString(), is("g1"));
        assertThat(testBoard.getTilesList()[7].toString(), is("h1"));

        assertThat(testBoard.getTilesList()[8].toString(), is("a2"));
        assertThat(testBoard.getTilesList()[9].toString(), is("b2"));
        assertThat(testBoard.getTilesList()[10].toString(), is("c2"));
        assertThat(testBoard.getTilesList()[11].toString(), is("d2"));
        assertThat(testBoard.getTilesList()[12].toString(), is("e2"));
        assertThat(testBoard.getTilesList()[13].toString(), is("f2"));
        assertThat(testBoard.getTilesList()[14].toString(), is("g2"));
        assertThat(testBoard.getTilesList()[15].toString(), is("h2"));
        
        assertThat(testBoard.getTilesList()[16].toString(), is("a3"));
        assertThat(testBoard.getTilesList()[17].toString(), is("b3"));
        assertThat(testBoard.getTilesList()[18].toString(), is("c3"));
        assertThat(testBoard.getTilesList()[19].toString(), is("d3"));
        assertThat(testBoard.getTilesList()[20].toString(), is("e3"));
        assertThat(testBoard.getTilesList()[21].toString(), is("f3"));
        assertThat(testBoard.getTilesList()[22].toString(), is("g3"));
        assertThat(testBoard.getTilesList()[23].toString(), is("h3"));
        
        assertThat(testBoard.getTilesList()[24].toString(), is("a4"));
        assertThat(testBoard.getTilesList()[25].toString(), is("b4"));
        assertThat(testBoard.getTilesList()[26].toString(), is("c4"));
        assertThat(testBoard.getTilesList()[27].toString(), is("d4"));
        assertThat(testBoard.getTilesList()[28].toString(), is("e4"));
        assertThat(testBoard.getTilesList()[29].toString(), is("f4"));
        assertThat(testBoard.getTilesList()[30].toString(), is("g4"));
        assertThat(testBoard.getTilesList()[31].toString(), is("h4"));
       
        assertThat(testBoard.getTilesList()[32].toString(), is("a5"));
        assertThat(testBoard.getTilesList()[33].toString(), is("b5"));
        assertThat(testBoard.getTilesList()[34].toString(), is("c5"));
        assertThat(testBoard.getTilesList()[35].toString(), is("d5"));
        assertThat(testBoard.getTilesList()[36].toString(), is("e5"));
        assertThat(testBoard.getTilesList()[37].toString(), is("f5"));
        assertThat(testBoard.getTilesList()[38].toString(), is("g5"));
        assertThat(testBoard.getTilesList()[39].toString(), is("h5"));
        
        assertThat(testBoard.getTilesList()[40].toString(), is("a6"));
        assertThat(testBoard.getTilesList()[41].toString(), is("b6"));
        assertThat(testBoard.getTilesList()[42].toString(), is("c6"));
        assertThat(testBoard.getTilesList()[43].toString(), is("d6"));
        assertThat(testBoard.getTilesList()[44].toString(), is("e6"));
        assertThat(testBoard.getTilesList()[45].toString(), is("f6"));
        assertThat(testBoard.getTilesList()[46].toString(), is("g6"));
        assertThat(testBoard.getTilesList()[47].toString(), is("h6"));
      
        assertThat(testBoard.getTilesList()[48].toString(), is("a7"));
        assertThat(testBoard.getTilesList()[49].toString(), is("b7"));
        assertThat(testBoard.getTilesList()[50].toString(), is("c7"));
        assertThat(testBoard.getTilesList()[51].toString(), is("d7"));
        assertThat(testBoard.getTilesList()[52].toString(), is("e7"));
        assertThat(testBoard.getTilesList()[53].toString(), is("f7"));
        assertThat(testBoard.getTilesList()[54].toString(), is("g7"));
        assertThat(testBoard.getTilesList()[55].toString(), is("h7"));
        
        assertThat(testBoard.getTilesList()[56].toString(), is("a8"));
        assertThat(testBoard.getTilesList()[57].toString(), is("b8"));
        assertThat(testBoard.getTilesList()[58].toString(), is("c8"));
        assertThat(testBoard.getTilesList()[59].toString(), is("d8"));
        assertThat(testBoard.getTilesList()[60].toString(), is("e8"));
        assertThat(testBoard.getTilesList()[61].toString(), is("f8"));
        assertThat(testBoard.getTilesList()[62].toString(), is("g8"));
        assertThat(testBoard.getTilesList()[63].toString(), is("h8"));
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
    
    /* Pieces position initially correct */
    
    /* PAWN */
    
    @Test
    public void initBoardSets16Pawns() {
        int pawns = 0;
        testBoard.initBoard();
        
        for (int i = 0; i < 64; i++) {
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
    
    /* KNIGHT */
    
    @Test
    public void initBoardSets4Knights() {
        int knights = 0;
        testBoard.initBoard();
        
        for (int i = 0; i < 64; i++) {
            if (testBoard.getTilesList()[i].getPiece() == null) {
                
            } else if (testBoard.getTilesList()[i].getPiece().getPieceType() == PieceType.Knight) {
                knights++;
            }            
        }
        assertThat(knights, is(4));
    }
    
    @Test
    public void knightsCorrectPositionInitially() {
        testBoard.initBoard();
        assertThat(testBoard.getTile(File.File_B, Rank.Rank_1).getPiece().getPieceType(), is(PieceType.Knight));     
        assertThat(testBoard.getTile(File.File_B, Rank.Rank_1).getPiece().getSide(), is(Side.WHITE));
        assertThat(testBoard.getTile(File.File_G, Rank.Rank_1).getPiece().getPieceType(), is(PieceType.Knight));
        assertThat(testBoard.getTile(File.File_G, Rank.Rank_1).getPiece().getSide(), is(Side.WHITE));
        assertThat(testBoard.getTile(File.File_B, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.Knight));
        assertThat(testBoard.getTile(File.File_B, Rank.Rank_8).getPiece().getSide(), is(Side.BLACK));
        assertThat(testBoard.getTile(File.File_G, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.Knight));
        assertThat(testBoard.getTile(File.File_G, Rank.Rank_8).getPiece().getSide(), is(Side.BLACK));
    }
    
    /* ROOK */
    
    @Test
    public void initBoardSets4Rooks() {
        int rooks = 0;
        testBoard.initBoard();
        
        for (int i = 0; i < 64; i++) {
            if (testBoard.getTilesList()[i].getPiece() == null) {
                
            } else if (testBoard.getTilesList()[i].getPiece().getPieceType() == PieceType.Rook) {
                rooks++;
            }            
        }
        assertThat(rooks, is(4));
    }
    
    @Test
    public void rooksCorrectPositionInitially() {
        testBoard.initBoard();
        assertThat(testBoard.getTile(File.File_A, Rank.Rank_1).getPiece().getPieceType(), is(PieceType.Rook));     
        assertThat(testBoard.getTile(File.File_A, Rank.Rank_1).getPiece().getSide(), is(Side.WHITE));
        assertThat(testBoard.getTile(File.File_H, Rank.Rank_1).getPiece().getPieceType(), is(PieceType.Rook));
        assertThat(testBoard.getTile(File.File_H, Rank.Rank_1).getPiece().getSide(), is(Side.WHITE));
        assertThat(testBoard.getTile(File.File_A, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.Rook));
        assertThat(testBoard.getTile(File.File_A, Rank.Rank_8).getPiece().getSide(), is(Side.BLACK));
        assertThat(testBoard.getTile(File.File_H, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.Rook));
        assertThat(testBoard.getTile(File.File_H, Rank.Rank_8).getPiece().getSide(), is(Side.BLACK));
    }
    
    /* BISHOP */
    @Test
    public void initBoardSets4Bishops() {
        int bishops = 0;
        testBoard.initBoard();
        
        for (int i = 0; i < 64; i++) {
            if (testBoard.getTilesList()[i].getPiece() == null) {
                
            } else if (testBoard.getTilesList()[i].getPiece().getPieceType() == PieceType.Bishop) {
                bishops++;
            }            
        }
        assertThat(bishops, is(4));
    }
    
    @Test
    public void bishopsCorrectPositionInitially() {
        testBoard.initBoard();
        assertThat(testBoard.getTile(File.File_C, Rank.Rank_1).getPiece().getPieceType(), is(PieceType.Bishop));     
        assertThat(testBoard.getTile(File.File_C, Rank.Rank_1).getPiece().getSide(), is(Side.WHITE));
        assertThat(testBoard.getTile(File.File_F, Rank.Rank_1).getPiece().getPieceType(), is(PieceType.Bishop));
        assertThat(testBoard.getTile(File.File_F, Rank.Rank_1).getPiece().getSide(), is(Side.WHITE));
        assertThat(testBoard.getTile(File.File_C, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.Bishop));
        assertThat(testBoard.getTile(File.File_C, Rank.Rank_8).getPiece().getSide(), is(Side.BLACK));
        assertThat(testBoard.getTile(File.File_F, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.Bishop));
        assertThat(testBoard.getTile(File.File_F, Rank.Rank_8).getPiece().getSide(), is(Side.BLACK));
    }
    
    /* QUEEN */
    
    @Test
    public void initBoardSets2Queens() {
        int queens = 0;
        testBoard.initBoard();
        
        for (int i = 0; i < 64; i++) {
            if (testBoard.getTilesList()[i].getPiece() == null) {
                
            } else if (testBoard.getTilesList()[i].getPiece().getPieceType() == PieceType.Queen) {
                queens++;
            }            
        }
        assertThat(queens, is(2));
    }
    
    @Test
    public void queensCorrectPositionInitially() {
        testBoard.initBoard();
        assertThat(testBoard.getTile(File.File_D, Rank.Rank_1).getPiece().getPieceType(), is(PieceType.Queen));     
        assertThat(testBoard.getTile(File.File_D, Rank.Rank_1).getPiece().getSide(), is(Side.WHITE));
        assertThat(testBoard.getTile(File.File_D, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.Queen));
        assertThat(testBoard.getTile(File.File_D, Rank.Rank_8).getPiece().getSide(), is(Side.BLACK));
    }
    
    /* KING */
    
    @Test
    public void initBoardSets2Kings() {
        int kings = 0;
        testBoard.initBoard();
        
        for (int i = 0; i < 64; i++) {
            if (testBoard.getTilesList()[i].getPiece() == null) {
                
            } else if (testBoard.getTilesList()[i].getPiece().getPieceType() == PieceType.King) {
                kings++;
            }            
        }
        assertThat(kings, is(2));
    }
    
    @Test
    public void kingsCorrectPositionInitially() {
        testBoard.initBoard();
        assertThat(testBoard.getTile(File.File_E, Rank.Rank_1).getPiece().getPieceType(), is(PieceType.King));     
        assertThat(testBoard.getTile(File.File_D, Rank.Rank_1).getPiece().getSide(), is(Side.WHITE));
        assertThat(testBoard.getTile(File.File_E, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.King));
        assertThat(testBoard.getTile(File.File_D, Rank.Rank_8).getPiece().getSide(), is(Side.BLACK));
    }

}
