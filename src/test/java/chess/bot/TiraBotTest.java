/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.bot;

import chess.elements.File;
import chess.elements.Rank;
import chess.engine.GameState;
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
public class TiraBotTest {
    
    TiraBot tirabot;
    
    public TiraBotTest() {

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tirabot = new TiraBot();
    }
    
    @After
    public void tearDown() {
    }


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
    
    @Test
    public void NextMoveIsOfCorrectForm() {
        GameState gs = new GameState();
        gs.setMoves("a2a4");
        gs.playing = Side.BLACK;
        gs.turn = Side.BLACK;
        
        tirabot.nextMove(gs);
        
        assertTrue(tirabot.nextMove(gs).matches("[a-h][1-8][a-h][1-8][nbrq]?"));
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
    
    @Test
    public void nextMoveUpdatesBoardRightNormalCase2Moves() {
        GameState gs = new GameState();
        gs.setMoves("a2a4, b7b5");
        gs.playing = Side.WHITE;
        gs.turn = Side.WHITE;
        
        tirabot.updateMovementOnBoard(gs.moves.get(0));
        
        tirabot.nextMove(gs);
        
        assertNull(tirabot.getBoard().getTile(File.File_A, Rank.Rank_2).getPiece());      
        assertNull(tirabot.getBoard().getTile(File.File_B, Rank.Rank_7).getPiece());
        assertNull(tirabot.getBoard().getTile(File.File_A, Rank.Rank_4).getPiece());
        assertThat(tirabot.getBoard().getTile(File.File_B, Rank.Rank_5).getPiece().getPieceType(), is(PieceType.Pawn));
        assertThat(tirabot.getBoard().getTile(File.File_B, Rank.Rank_5).getPiece().getSide(), is(Side.WHITE));
    }
    
    @Test
    public void nextMoveUpdatesBoardRightAttackCase() {
        GameState gs = new GameState();
        gs.setMoves("a2a4, b7b5, a4b5");
        gs.playing = Side.BLACK;
        gs.turn = Side.BLACK;
        
        tirabot.updateMovementOnBoard(gs.moves.get(0));
        tirabot.updateMovementOnBoard(gs.moves.get(1));
        
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
    
    @Test
    public void promotionToQueenWorksOnCorrectSituation() {
        GameState gs = new GameState();
        gs.setMoves("a2a4, b7b5, a4b5, b8a6,b5b6,c7c6,b6b7,a6b4,b7b8q");
        gs.playing = Side.BLACK;
        gs.turn = Side.BLACK;
        
        tirabot.updateMovementOnBoard(gs.moves.get(0));
        tirabot.updateMovementOnBoard(gs.moves.get(1));
        tirabot.updateMovementOnBoard(gs.moves.get(2));
        tirabot.updateMovementOnBoard(gs.moves.get(3));
        tirabot.updateMovementOnBoard(gs.moves.get(4));
        tirabot.updateMovementOnBoard(gs.moves.get(5));
        tirabot.updateMovementOnBoard(gs.moves.get(6));
        tirabot.updateMovementOnBoard(gs.moves.get(7));
        
        tirabot.nextMove(gs);
            
        assertNull(tirabot.getBoard().getTile(File.File_B, Rank.Rank_7).getPiece());
        assertThat(tirabot.getBoard().getTile(File.File_B, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.Queen));
        assertThat(tirabot.getBoard().getTile(File.File_B, Rank.Rank_8).getPiece().getSide(), is(Side.WHITE));
    }
    
    @Test
    public void promotionToRookWorksOnCorrectSituation() {
        GameState gs = new GameState();
        gs.setMoves("a2a4, b7b5, a4b5, b8a6,b5b6,c7c6,b6b7,a6b4,b7b8r");
        gs.playing = Side.BLACK;
        gs.turn = Side.BLACK;
        
        tirabot.updateMovementOnBoard(gs.moves.get(0));
        tirabot.updateMovementOnBoard(gs.moves.get(1));
        tirabot.updateMovementOnBoard(gs.moves.get(2));
        tirabot.updateMovementOnBoard(gs.moves.get(3));
        tirabot.updateMovementOnBoard(gs.moves.get(4));
        tirabot.updateMovementOnBoard(gs.moves.get(5));
        tirabot.updateMovementOnBoard(gs.moves.get(6));
        tirabot.updateMovementOnBoard(gs.moves.get(7));
        
        tirabot.nextMove(gs);
            
        assertNull(tirabot.getBoard().getTile(File.File_B, Rank.Rank_7).getPiece());
        assertThat(tirabot.getBoard().getTile(File.File_B, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.Rook));
        assertThat(tirabot.getBoard().getTile(File.File_B, Rank.Rank_8).getPiece().getSide(), is(Side.WHITE));
    }
    
    @Test
    public void promotionToKnightWorksOnCorrectSituation() {
        GameState gs = new GameState();
        gs.setMoves("A2A4, B7B5, A4B5, B8a6,b5b6,c7c6,b6b7,a6b4,b7b8n");
        gs.playing = Side.BLACK;
        gs.turn = Side.BLACK;
        
        tirabot.updateMovementOnBoard(gs.moves.get(0));
        tirabot.updateMovementOnBoard(gs.moves.get(1));
        tirabot.updateMovementOnBoard(gs.moves.get(2));
        tirabot.updateMovementOnBoard(gs.moves.get(3));
        tirabot.updateMovementOnBoard(gs.moves.get(4));
        tirabot.updateMovementOnBoard(gs.moves.get(5));
        tirabot.updateMovementOnBoard(gs.moves.get(6));
        tirabot.updateMovementOnBoard(gs.moves.get(7));
        
        tirabot.nextMove(gs);
            
        assertNull(tirabot.getBoard().getTile(File.File_B, Rank.Rank_7).getPiece());
        assertThat(tirabot.getBoard().getTile(File.File_B, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.Knight));
        assertThat(tirabot.getBoard().getTile(File.File_B, Rank.Rank_8).getPiece().getSide(), is(Side.WHITE));
    }
    
    @Test
    public void promotionToBishopWorksOnCorrectSituation() {
        GameState gs = new GameState();
        gs.setMoves("A2A4, B7B5, A4B5, B8a6,b5b6,c7c6,b6b7,a6b4,b7b8b");
        gs.playing = Side.BLACK;
        gs.turn = Side.BLACK;
        
        tirabot.updateMovementOnBoard(gs.moves.get(0));
        tirabot.updateMovementOnBoard(gs.moves.get(1));
        tirabot.updateMovementOnBoard(gs.moves.get(2));
        tirabot.updateMovementOnBoard(gs.moves.get(3));
        tirabot.updateMovementOnBoard(gs.moves.get(4));
        tirabot.updateMovementOnBoard(gs.moves.get(5));
        tirabot.updateMovementOnBoard(gs.moves.get(6));
        tirabot.updateMovementOnBoard(gs.moves.get(7));
        
        tirabot.nextMove(gs);
            
        assertNull(tirabot.getBoard().getTile(File.File_B, Rank.Rank_7).getPiece());
        assertThat(tirabot.getBoard().getTile(File.File_B, Rank.Rank_8).getPiece().getPieceType(), is(PieceType.Bishop));
        assertThat(tirabot.getBoard().getTile(File.File_B, Rank.Rank_8).getPiece().getSide(), is(Side.WHITE));
    }
    
    
    @Test
    public void FirstMoveIsCorrect() {
        GameState gs = new GameState();
        gs.playing = Side.WHITE;
        gs.turn = Side.WHITE;
        
        tirabot.nextMove(gs);
       assertTrue(     tirabot.nextMove(gs).matches("[a-h]2[a-h][3-4]")
                    || tirabot.nextMove(gs).matches("b1[ac]3")
                    || tirabot.nextMove(gs).matches("g1[fh]3"));
    }
    
    
}
