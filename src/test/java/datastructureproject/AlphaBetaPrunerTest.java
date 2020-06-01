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
import chess.rules.KingCheckedCounter;
import chess.rules.MovementGenerator;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.PieceType;
import pieces.Rook;


/**
 *
 * @author juhop
 */
public class AlphaBetaPrunerTest {
    private AlphaBetaPruner alphabeta;
    private Board testBoard;
    private BoardValueCalculator boardValue;
    private MovementGenerator movementGenerator;
    private KingCheckedCounter kingChecker;
    
    
    public AlphaBetaPrunerTest() {
        alphabeta = new AlphaBetaPruner();
        testBoard = new Board();
        boardValue = new BoardValueCalculator();
        this.movementGenerator = new MovementGenerator();
        this.kingChecker = new KingCheckedCounter();
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
    public void minimaxReturnsRightOpponent() {
        assertThat(alphabeta.getOpponent(Side.BLACK), is(Side.WHITE));
        assertThat(alphabeta.getOpponent(Side.WHITE), is(Side.BLACK));
    }
    
    @Test
    public void minimaxReturnsAllMovesWhenNoCheck() {
        testBoard.getTile(File.File_B, Rank.Rank_1).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_A, Rank.Rank_2).setPiece(new Knight(Side.BLACK));
        testBoard.getTile(File.File_A, Rank.Rank_3).setPiece(new Rook(Side.BLACK));
        testBoard.getTile(File.File_C, Rank.Rank_2).setPiece(new Bishop(Side.BLACK));
        
        assertThat(alphabeta.allMovesKingCheckFiltered(Side.WHITE, testBoard).length, is(2));
    }
    
    @Test
    public void minimaxReturnsAllMovesWhenNoCheckTest2() {
        testBoard.getTile(File.File_A, Rank.Rank_2).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_A, Rank.Rank_3).setPiece(new Rook(Side.BLACK));
        testBoard.getTile(File.File_C, Rank.Rank_2).setPiece(new Bishop(Side.BLACK));
        
        String[] moves = new String[0];
        moves = movementGenerator.countAllMoves(Side.BLACK, testBoard, moves);
        
        assertThat(moves.length, is(22));
        
        // Rook movement vertical
        assertThat(kingChecker.kingInCheck("a3a2", Side.WHITE, testBoard), is(false));
        
        
        
        // Rook movement horizontal
        
        assertThat(alphabeta.allMovesKingCheckFiltered(Side.BLACK, testBoard).length, is(22));
    }
    
    @Test
    public void minimaxReturnsAllMovesWhenKingChecked() {
        testBoard.getTile(File.File_B, Rank.Rank_1).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_D, Rank.Rank_1).setPiece(new King(Side.WHITE));
        testBoard.getTile(File.File_A, Rank.Rank_2).setPiece(new Knight(Side.BLACK));
        testBoard.getTile(File.File_A, Rank.Rank_3).setPiece(new Rook(Side.BLACK));
        testBoard.getTile(File.File_C, Rank.Rank_2).setPiece(new Bishop(Side.BLACK));
        
        assertThat(alphabeta.allMovesKingCheckFiltered(Side.WHITE, testBoard).length, is(5));
    }

    @Test
    public void minimaxCountsCorrectValueWhenDepthIsZero() {
        testBoard.getTile(File.File_B, Rank.Rank_1).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_A, Rank.Rank_2).setPiece(new Knight(Side.BLACK));
        testBoard.getTile(File.File_A, Rank.Rank_3).setPiece(new Rook(Side.BLACK));
        testBoard.getTile(File.File_C, Rank.Rank_2).setPiece(new Bishop(Side.BLACK));
        
        int minmaxvalue = alphabeta.minimax(Side.WHITE, testBoard, 0, true);

        assertThat(minmaxvalue, is(-80));
    }
    
    @Test
    public void minimaxSavesPiecesAndPutsThemBack() {
        testBoard.getTile(File.File_B, Rank.Rank_1).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_A, Rank.Rank_2).setPiece(new Knight(Side.BLACK));

        
        alphabeta.boardStatusSaver.savePieces("b1a2", testBoard);
        movementGenerator.updateMovementOnBoard("b1a2", testBoard);

        assertThat(alphabeta.boardStatusSaver.getStartPiece().getPieceType(), is(PieceType.Bishop));
        assertThat(alphabeta.boardStatusSaver.getStartPiece().getSide(), is(Side.WHITE));
        
        assertThat(alphabeta.boardStatusSaver.getFinishPiece().getPieceType(), is(PieceType.Knight));
        assertThat(alphabeta.boardStatusSaver.getFinishPiece().getSide(), is(Side.BLACK));
        
        assertThat(alphabeta.boardStatusSaver.getStartPieceTile().getFile(), is(File.File_B));
        assertThat(alphabeta.boardStatusSaver.getStartPieceTile().getRank(), is(Rank.Rank_1));
        
        assertThat(alphabeta.boardStatusSaver.getFinishPieceTile().getFile(), is(File.File_A));
        assertThat(alphabeta.boardStatusSaver.getFinishPieceTile().getRank(), is(Rank.Rank_2));
        
        alphabeta.boardStatusSaver.putSavedPiecesBack();
        
        assertThat(testBoard.getTile(File.File_B, Rank.Rank_1).getPiece().getPieceType(), is(PieceType.Bishop));
        assertThat(testBoard.getTile(File.File_B, Rank.Rank_1).getPiece().getSide(), is(Side.WHITE));
    
        assertThat(testBoard.getTile(File.File_A, Rank.Rank_2).getPiece().getPieceType(), is(PieceType.Knight));
        assertThat(testBoard.getTile(File.File_A, Rank.Rank_2).getPiece().getSide(), is(Side.BLACK));
   
    }
    
    @Test
    public void minimaxSavesPiecesAndPutsThemBackEvenWhenPiecesAreNull() {
        testBoard.getTile(File.File_B, Rank.Rank_1).setPiece(new Bishop(Side.WHITE));

        alphabeta.boardStatusSaver.savePieces("b1a2", testBoard);
        movementGenerator.updateMovementOnBoard("b1a2", testBoard);

        assertThat(alphabeta.boardStatusSaver.getStartPiece().getPieceType(), is(PieceType.Bishop));
        assertThat(alphabeta.boardStatusSaver.getStartPiece().getSide(), is(Side.WHITE));
        
        assertNull(alphabeta.boardStatusSaver.getFinishPiece());
        
        assertThat(alphabeta.boardStatusSaver.getStartPieceTile().getFile(), is(File.File_B));
        assertThat(alphabeta.boardStatusSaver.getStartPieceTile().getRank(), is(Rank.Rank_1));
        
        assertThat(alphabeta.boardStatusSaver.getFinishPieceTile().getFile(), is(File.File_A));
        assertThat(alphabeta.boardStatusSaver.getFinishPieceTile().getRank(), is(Rank.Rank_2));
        
        alphabeta.boardStatusSaver.putSavedPiecesBack();
        
        assertThat(testBoard.getTile(File.File_B, Rank.Rank_1).getPiece().getPieceType(), is(PieceType.Bishop));
        assertThat(testBoard.getTile(File.File_B, Rank.Rank_1).getPiece().getSide(), is(Side.WHITE));
    
        assertNull(testBoard.getTile(File.File_A, Rank.Rank_2).getPiece());
   
    }
    
    @Test
    public void minimaxCountsCorrectValueWhenDepthIsOne() {
        testBoard.getTile(File.File_B, Rank.Rank_1).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_A, Rank.Rank_2).setPiece(new Knight(Side.BLACK));
        testBoard.getTile(File.File_A, Rank.Rank_3).setPiece(new Rook(Side.BLACK));
        testBoard.getTile(File.File_C, Rank.Rank_2).setPiece(new Bishop(Side.BLACK));
        
        int minmaxvalue = alphabeta.minimax(Side.WHITE, testBoard, 1, true);

        assertThat(minmaxvalue, is(-50));
    }
}
