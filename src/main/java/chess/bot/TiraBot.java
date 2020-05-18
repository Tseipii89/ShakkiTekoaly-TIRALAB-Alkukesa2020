package chess.bot;

import chess.engine.GameState;

/**
 *
 * This class gets the gamestate and returns the next move.
 * 
 * @author juhop
 */
public class TiraBot implements ChessBot {
    
    /**
     * TiraBot is the AI bot that is given to the chess library 
     * provided by the course.
     */
    public TiraBot() {
        
    }

    /**
     *
     * @param gamestate holds the information about the state of the game
     * @return the next move in Algebraic notation that bot player is going to do
     */
    @Override
    public String nextMove(GameState gamestate) {
        return "b2b3"; 
    }
    
}
