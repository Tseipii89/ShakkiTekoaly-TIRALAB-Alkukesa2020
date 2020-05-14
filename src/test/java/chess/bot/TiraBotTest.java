/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.bot;

import chess.engine.GameState;
import static org.hamcrest.CoreMatchers.instanceOf;
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
public class TiraBotTest {
    
    TiraBot tirabot;
    GameState gamestate;
    
    public TiraBotTest() {
        gamestate = new GameState();
        tirabot = new TiraBot();
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
    public void NextMoveReturnsString() {
        assertEquals(tirabot.nextMove(gamestate).getClass(), String.class);
    }
    
    @Test
    public void NextMoveIsOfCorrectForm() {
        assertTrue(tirabot.nextMove(gamestate).matches("[a-h][1-8][a-h][1-8][nbrq]?"));
    }
    
    
}
