/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.bot;

import chess.elements.Board;
import chess.elements.File;
import chess.elements.Rank;
import chess.engine.GameState;
import chess.model.Side;
import chess.rules.KingCheckedCounter;
import chess.rules.MovementGenerator;
import datastructureproject.AlphaBetaPruner;
import datastructureproject.BoardValueCalculator;
import datastructureproject.MoveValueCounter;
import java.util.Random;
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
public class TiraBotTest {
    
    /**
     *
     */
    TiraBot tirabot;
    MovementGenerator movementGenerator;
    KingCheckedCounter kingChecked;
    MoveValueCounter moveValueCounter;
    Random random;
    AlphaBetaPruner alphabeta;
    BoardValueCalculator boardValueCounter;
    
    /**
     *
     */
    public TiraBotTest() {
        movementGenerator = new MovementGenerator();
        kingChecked = new KingCheckedCounter();
        moveValueCounter = new MoveValueCounter();
        alphabeta = new AlphaBetaPruner();
        this.boardValueCounter = new BoardValueCalculator();
    }
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
        tirabot = new TiraBot();
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
        tirabot = null;
        
    }

    /**
     *
     */
    @Test
    public void NextMoveReturnsString() {
        GameState gs = new GameState();
        gs.setMoves("a2a4");
        gs.playing = Side.BLACK;
        gs.turn = Side.BLACK;
        tirabot.nextMove(gs);
        String move = tirabot.nextMove(gs);

        assertEquals(move.getClass(), String.class);
    }
    
    /**
     *
     */
    @Test
    public void NextMoveIsOfCorrectForm() {
        GameState gs = new GameState();
        gs.setMoves("a2a4");
        gs.playing = Side.BLACK;
        gs.turn = Side.BLACK;
        
        tirabot.nextMove(gs);
        
        assertTrue(tirabot.nextMove(gs).matches("[a-h][1-8][a-h][1-8][nbrq]?"));
    }
    
    /**
     *
     */
    @Test
    public void nextMoveUpdatesBoardRightNormalCaseStepByStep() {
        GameState gs = new GameState();
        gs.setMoves("a2a4");
        gs.playing = Side.BLACK;
        gs.turn = Side.BLACK;
        
        tirabot.updateGameStateMove(gs, tirabot.getBoard());
        
        assertNull(tirabot.getBoard().getTile(File.File_A, Rank.Rank_2).getPiece());
        assertThat(tirabot.getBoard().getTile(File.File_A, Rank.Rank_4).getPiece().getPieceType(), is(PieceType.Pawn));
        assertThat(tirabot.getBoard().getTile(File.File_A, Rank.Rank_4).getPiece().getSide(), is(Side.WHITE));
        
        String[] moves = new String[0];
        moves = movementGenerator.countAllMoves(Side.BLACK, tirabot.getBoard(), moves);
        assertThat(moves.length, is(20));
        assertNull(tirabot.getBoard().getTile(File.File_A, Rank.Rank_2).getPiece());

        String[] testMoves = new String[1];
        testMoves[0] = moves[17];

        moveValueCounter.movementGenerator.updateMovementOnBoard(testMoves[0], tirabot.getBoard());
        String[] movesFiltered = alphabeta.allMovesKingCheckFiltered(Side.WHITE, tirabot.getBoard());
        
        assertThat(movesFiltered.length, is(22));
        
        
        alphabeta.boardStatusSaver.savePieces(movesFiltered[1], tirabot.getBoard());
        assertNull(tirabot.getBoard().getTile(File.File_A, Rank.Rank_2).getPiece());
        alphabeta.boardStatusSaver.putSavedPiecesBack();
        assertThat(movesFiltered[1], is("a4b5"));
        assertNull(tirabot.getBoard().getTile(File.File_A, Rank.Rank_2).getPiece());
        

        
        alphabeta.boardStatusSaver.savePieces(movesFiltered[20], tirabot.getBoard());
        assertNull(tirabot.getBoard().getTile(File.File_A, Rank.Rank_2).getPiece());
        alphabeta.boardStatusSaver.putSavedPiecesBack();
        assertThat(movesFiltered[20], is("a1a2"));
        assertNull(tirabot.getBoard().getTile(File.File_A, Rank.Rank_2).getPiece());
    }
    
        @Test
    public void nextMoveUpdatesBoardRightNormalCase() {
        GameState gs = new GameState();
        gs.setMoves("a2a4");
        gs.playing = Side.BLACK;
        gs.turn = Side.BLACK;

        
                
        tirabot.nextMove(gs);
        
        
        
        assertNull(tirabot.getBoard().getTile(File.File_A, Rank.Rank_2).getPiece());
        assertThat(tirabot.getBoard().getTile(File.File_A, Rank.Rank_4).getPiece().getPieceType(), is(PieceType.Pawn));
        assertThat(tirabot.getBoard().getTile(File.File_A, Rank.Rank_4).getPiece().getSide(), is(Side.WHITE));
    }
    
    
    
    /**
     *
     */
    @Test
    public void nextMoveUpdatesBoardRightNormalCase2Moves() {
        GameState gs = new GameState();
        gs.setMoves("a2a4, b7b5");
        gs.playing = Side.WHITE;
        gs.turn = Side.WHITE;
        
        movementGenerator.updateMovementOnBoard(gs.moves.get(0), tirabot.getBoard());

        
        tirabot.nextMove(gs);
        
        assertNull(tirabot.getBoard().getTile(File.File_A, Rank.Rank_2).getPiece());      
        assertNull(tirabot.getBoard().getTile(File.File_B, Rank.Rank_7).getPiece());
        assertNull(tirabot.getBoard().getTile(File.File_A, Rank.Rank_4).getPiece());


        assertThat(tirabot.getBoard().getTile(File.File_B, Rank.Rank_5).getPiece().getPieceType(), is(PieceType.Pawn));
        assertThat(tirabot.getBoard().getTile(File.File_B, Rank.Rank_5).getPiece().getSide(), is(Side.WHITE));
    }
    
    /**
     *
     */
    @Test
    public void nextMoveUpdatesBoardRightAttackCase() {
        GameState gs = new GameState();
        gs.setMoves("a2a4, b7b5, a4b5");
        gs.playing = Side.BLACK;
        gs.turn = Side.BLACK;
        
        movementGenerator.updateMovementOnBoard(gs.moves.get(0), tirabot.getBoard());
        movementGenerator.updateMovementOnBoard(gs.moves.get(1), tirabot.getBoard());
        
        tirabot.nextMove(gs);
        
        int blackPawns = 0;
        
        for (int i = 0; i < 64; i++) {
            if (tirabot.getBoard().getTilesList()[i].getPiece() == null) {
                
            } else if (tirabot.getBoard().getTilesList()[i].getPiece().getPieceType() == PieceType.Pawn
                    && tirabot.getBoard().getTilesList()[i].getPiece().getSide() == Side.BLACK) {
                blackPawns++;
            }            
        }
        assertThat(blackPawns, is(7));
        
        assertNull(tirabot.getBoard().getTile(File.File_A, Rank.Rank_4).getPiece());
        assertThat(tirabot.getBoard().getTile(File.File_B, Rank.Rank_5).getPiece().getPieceType(), is(PieceType.Pawn));
        assertThat(tirabot.getBoard().getTile(File.File_B, Rank.Rank_5).getPiece().getSide(), is(Side.WHITE));
    }
    
    /**
     *
     */
    @Test
    public void promotionToQueenWorksOnCorrectSituation() {
        GameState gs = new GameState();
        gs.setMoves("A8A4, a2a8q");
        gs.playing = Side.BLACK;
        gs.turn = Side.BLACK;
        
        movementGenerator.updateMovementOnBoard(gs.moves.get(0), tirabot.getBoard());

        
        tirabot.nextMove(gs);
            
        assertThat(tirabot.getBoard().getTile(File.File_A, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.Queen));
        assertThat(tirabot.getBoard().getTile(File.File_A, Rank.Rank_8).getPiece().getSide(), is(Side.WHITE));
    }
    
    /**
     *
     */
    @Test
    public void promotionToRookWorksOnCorrectSituation() {
        GameState gs = new GameState();
        gs.setMoves("A8A4, a2a8r");
        gs.playing = Side.BLACK;
        gs.turn = Side.BLACK;
        
        movementGenerator.updateMovementOnBoard(gs.moves.get(0), tirabot.getBoard());

        
        tirabot.nextMove(gs);

        assertThat(tirabot.getBoard().getTile(File.File_A, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.Rook));
        assertThat(tirabot.getBoard().getTile(File.File_A, Rank.Rank_8).getPiece().getSide(), is(Side.WHITE));
    }
    
    /**
     *
     */
    @Test
    public void promotionToKnightWorksOnCorrectSituation() {
        GameState gs = new GameState();
        gs.setMoves("A8A4, a2a8n");
        gs.playing = Side.BLACK;
        gs.turn = Side.BLACK;
        
        movementGenerator.updateMovementOnBoard(gs.moves.get(0), tirabot.getBoard());
        
        tirabot.nextMove(gs);
            
        assertThat(tirabot.getBoard().getTile(File.File_A, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.Knight));
        assertThat(tirabot.getBoard().getTile(File.File_A, Rank.Rank_8).getPiece().getSide(), is(Side.WHITE));
    }
    
    /**
     *
     */
    @Test
    public void promotionToBishopWorksOnCorrectSituation() {
        GameState gs = new GameState();
        gs.setMoves("A8A4, a2a8b");
        gs.playing = Side.BLACK;
        gs.turn = Side.BLACK;
        
        movementGenerator.updateMovementOnBoard(gs.moves.get(0), tirabot.getBoard());
        
        tirabot.nextMove(gs);
            
        assertThat(tirabot.getBoard().getTile(File.File_A, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.Bishop));
        assertThat(tirabot.getBoard().getTile(File.File_A, Rank.Rank_8).getPiece().getSide(), is(Side.WHITE));
    }
    
 
    @Test
    public void kingInCheckWorksCorrectly() {
        GameState gs = new GameState();
        gs.setMoves("d2d4");
        gs.playing = Side.WHITE;
        gs.turn = Side.WHITE;
        
        movementGenerator.updateMovementOnBoard(gs.moves.get(0), tirabot.getBoard());
        
        boolean isKingChecked = kingChecked.kingInCheck("f8b4", Side.BLACK, tirabot.getBoard());

        assertThat(isKingChecked, is(true) );
    }
    
    
    @Test
    public void whiteWontletKingToBeChecked() {
        GameState gs = new GameState();
        gs.setMoves("d2d4, f8b4");
        gs.playing = Side.WHITE;
        gs.turn = Side.WHITE;
        
        movementGenerator.updateMovementOnBoard(gs.moves.get(0), tirabot.getBoard());
        
        tirabot.nextMove(gs);
        
        int whiteProtectors = 0;
        

        if (tirabot.getBoard().getTile(File.File_C, Rank.Rank_3).getPiece() == null) {

        } else if (tirabot.getBoard().getTile(File.File_C, Rank.Rank_3).getPiece().getSide() == Side.WHITE) {
            whiteProtectors++;
        }  
        
        if (tirabot.getBoard().getTile(File.File_D, Rank.Rank_2).getPiece() == null) {

        } else if (tirabot.getBoard().getTile(File.File_D, Rank.Rank_2).getPiece().getSide() == Side.WHITE) {
            whiteProtectors++;
        } 

        assertThat(whiteProtectors, is(1) );
    }
    

    // MINIMAX

    
    @Test
    public void alphaBetaCountsRightRookCapturesBishop() {

        Board testBoard = new Board();

        testBoard.getTile(File.File_A, Rank.Rank_4).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_C, Rank.Rank_4).setPiece(new Bishop(Side.BLACK));
        testBoard.getTile(File.File_A, Rank.Rank_5).setPiece(new Rook(Side.BLACK));

        int value = alphabeta.minimax(Side.BLACK, testBoard, 1, false);
        
        
        assertThat(value, is(-80) );
    }
    
    @Test
    public void alphaBetaCountsRightRookCapturesKing() {

        Board testBoard = new Board();

        testBoard.getTile(File.File_A, Rank.Rank_4).setPiece(new King(Side.WHITE));
        testBoard.getTile(File.File_C, Rank.Rank_4).setPiece(new Bishop(Side.BLACK));
        testBoard.getTile(File.File_A, Rank.Rank_5).setPiece(new Rook(Side.BLACK));

        int value = alphabeta.minimax(Side.BLACK, testBoard, 1, false);
        
        
        assertThat(value, is(-80) );
    }
    
    @Test
    public void BlackContainsTheBestMoveBishop() {

        Board testBoard = new Board();

        testBoard.getTile(File.File_A, Rank.Rank_4).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_A, Rank.Rank_5).setPiece(new Rook(Side.BLACK));
       
        
        String[] moves = alphabeta.allMovesKingCheckFiltered(Side.BLACK, testBoard);
        
        assertThat(moves.length, is(11) );
        assertThat(moves[7], is("a5a4") );
    }
    
    @Test
    public void BlackContainsTheBestMoveKing() {

        Board testBoard = new Board();

        testBoard.getTile(File.File_A, Rank.Rank_4).setPiece(new King(Side.WHITE));
        testBoard.getTile(File.File_A, Rank.Rank_5).setPiece(new Rook(Side.BLACK));
       
        
        String[] moves = alphabeta.allMovesKingCheckFiltered(Side.BLACK, testBoard);
        
        assertThat(moves.length, is(11) );
        assertThat(moves[7], is("a5a4") );
    }
    
    @Test
    public void nextMoveWorksWithMinMaxInitCheckBishop() {

        Board testBoard = new Board();
        
        testBoard.getTile(File.File_B, Rank.Rank_3).setPiece(new Bishop(Side.WHITE));
        testBoard.getTile(File.File_C, Rank.Rank_4).setPiece(new Bishop(Side.BLACK));
        testBoard.getTile(File.File_A, Rank.Rank_4).setPiece(new Knight(Side.BLACK));
        testBoard.getTile(File.File_A, Rank.Rank_5).setPiece(new Rook(Side.BLACK));

        int move1 = moveValueCounter.moveValueCountMinMax("b3a4", Side.WHITE, testBoard);
        int move2 = moveValueCounter.moveValueCountMinMax("b3c4", Side.WHITE, testBoard);
        
        assertThat(move1, is(-80) );
        assertThat(move2, is(-50) );
    }
    
    @Test
    public void nextMoveWorksWithMinMaxInitCheckKing() {

        Board testBoard = new Board();
        
        testBoard.getTile(File.File_B, Rank.Rank_3).setPiece(new King(Side.WHITE));
        testBoard.getTile(File.File_C, Rank.Rank_4).setPiece(new Bishop(Side.BLACK));
        testBoard.getTile(File.File_A, Rank.Rank_4).setPiece(new Knight(Side.BLACK));
        testBoard.getTile(File.File_A, Rank.Rank_5).setPiece(new Rook(Side.BLACK));

        int move1 = moveValueCounter.moveValueCountMinMax("b3a4", Side.WHITE, testBoard);
        int move2 = moveValueCounter.moveValueCountMinMax("b3c4", Side.WHITE, testBoard);
        
        assertThat(move1, is(-80) );
        assertThat(move2, is(820) );
    }
      
    @Test
    public void nextMoveWorksWithMinMaxBishop() {
        GameState gs = new GameState();
        gs.setMoves("c1b3, b8a4, a8a5, c8c4");
        gs.playing = Side.WHITE;
        gs.turn = Side.WHITE;

        movementGenerator.updateMovementOnBoard(gs.moves.get(0), tirabot.getBoard());
        movementGenerator.updateMovementOnBoard(gs.moves.get(1), tirabot.getBoard());
        movementGenerator.updateMovementOnBoard(gs.moves.get(2), tirabot.getBoard());

        String move = tirabot.nextMove(gs);
        assertThat(move, is("b3c4") );
    }
    
    @Test
    public void nextMoveWorksWithMinMaxKing() {
        GameState gs = new GameState();
        gs.setMoves("e1b3, b8a4, a8a5, c8c4");
        gs.playing = Side.WHITE;
        gs.turn = Side.WHITE;

        movementGenerator.updateMovementOnBoard(gs.moves.get(0), tirabot.getBoard());
        movementGenerator.updateMovementOnBoard(gs.moves.get(1), tirabot.getBoard());
        movementGenerator.updateMovementOnBoard(gs.moves.get(2), tirabot.getBoard());

        String move = tirabot.nextMove(gs);
        assertThat(move, is("b3c4") );
    }
    
}
