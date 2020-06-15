/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructureproject.datamodifiers;

import chess.elements.Board;
import chess.model.Side;
import chess.rules.KingCheckedCounter;
import chess.rules.MovementGenerator;
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
public class NumberModificatorTest {
    
    private static NumberModificator numberTest;
    private final MovementGenerator movementgenerator;
    private Board currentGameBoard; 
    private ArrayModifier arrayModifier;
    private KingCheckedCounter kingChecked;
    private NumberModificator random; 
    
    public NumberModificatorTest() {
        numberTest = new NumberModificator();
        movementgenerator = new MovementGenerator();
        arrayModifier = new ArrayModifier();
        kingChecked = new KingCheckedCounter();
        this.random = new NumberModificator();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       this.currentGameBoard = new Board();
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void randomReturnsPositiveValues() {
        assertTrue(numberTest.random(20) > -1);
    }
    
    @Test
    public void randomReturnsNotTooHighValues() {
        assertTrue(numberTest.random(20) < 21);
    }
    
    @Test
    public void RandomWorksInitialBoard() {
        String[] moves = new String[0]; 
        
        this.currentGameBoard.initBoard();
                
        moves = movementgenerator.countAllMoves(Side.WHITE, this.currentGameBoard, moves); 
        String[] movesWithoutChecks = new String[0];
        
        // remove all moves where Black king is left unchecked
        for (String move : moves) {
            if (!kingChecked.kingInCheck(move, Side.WHITE, this.currentGameBoard)) {
                movesWithoutChecks = arrayModifier.addNewMoveToArray(movesWithoutChecks, move);
            }
        }
        // If all moves are equal in value, we want to return random move, and not for example the first move.
       int randomResult = random.random(movesWithoutChecks.length); 
       assertTrue(randomResult > -1);
    }
    
    @Test
    public void RandomWorksInitialBoardNotTooHighValues() {
        String[] moves = new String[0]; 
        
        this.currentGameBoard.initBoard();
                
        moves = movementgenerator.countAllMoves(Side.WHITE, this.currentGameBoard, moves); 
        String[] movesWithoutChecks = new String[0];
        
        // remove all moves where Black king is left unchecked
        for (String move : moves) {
            if (!kingChecked.kingInCheck(move, Side.WHITE, this.currentGameBoard)) {
                movesWithoutChecks = arrayModifier.addNewMoveToArray(movesWithoutChecks, move);
            }
        }
        // If all moves are equal in value, we want to return random move, and not for example the first move.
       int randomResult = random.random(movesWithoutChecks.length); 
       assertTrue(randomResult < movesWithoutChecks.length);
    }
    
    @Test
    public void RandomWorksInitialBoard2() {
        String[] moves = new String[0]; 
        
        this.currentGameBoard.initBoard();
                
        moves = movementgenerator.countAllMoves(Side.WHITE, this.currentGameBoard, moves); 
        String[] movesWithoutChecks = new String[0];
        
        // remove all moves where Black king is left unchecked
        for (String move : moves) {
            if (!kingChecked.kingInCheck(move, Side.WHITE, this.currentGameBoard)) {
                movesWithoutChecks = arrayModifier.addNewMoveToArray(movesWithoutChecks, move);
            }
        }
        // If all moves are equal in value, we want to return random move, and not for example the first move.
       int randomResult = random.random(movesWithoutChecks.length); 
       assertTrue(randomResult > -1);
    }
    
}
