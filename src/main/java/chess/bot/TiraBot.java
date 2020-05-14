package chess.bot;

import chess.engine.GameState;

public class TiraBot implements ChessBot {
    
    public TiraBot() {
        
    }

    @Override
    public String nextMove(GameState gamestate) {
        return "b2b3"; 
    }
    
}
