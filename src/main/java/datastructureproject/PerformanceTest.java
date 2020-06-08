package datastructureproject;


import chess.bot.TiraBot;
import chess.elements.Board;
import chess.elements.File;
import chess.elements.Rank;
import chess.engine.GameState;
import chess.model.Side;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

/**
 * Use this class to write performance tests for your bot.
 * 
 */
public class PerformanceTest {

    private static final int[] NUMSMINMAX = {0, 1, 2, 3};
    private static final int TESTRUNS = 20;
    private static final double[] averageRunTimes = new double[NUMSMINMAX.length];
    private static final double[] averageSTD = new double[NUMSMINMAX.length];
    private static final long[] times = new long[TESTRUNS];
    

    public static void main(String[] args) {
        

        for (int run = 0; run < NUMSMINMAX.length; run++) {
            int depth = NUMSMINMAX[run];
            long t;
            GameState gs = new GameState(); 
            gs.playing = Side.WHITE;
            gs.turn = Side.WHITE;
            // Test run inside the given depth
            Board testBoard = new Board();
            testBoard.initBoard();
            TiraBot tirabot;
            tirabot = new TiraBot();
            tirabot.nextMove(gs);
            for (int i = 0; i < TESTRUNS; i++) {
                gs = new GameState();
                gs.playing = Side.WHITE;
                gs.turn = Side.WHITE;
                tirabot = new TiraBot(depth);
                setTestBoard(testBoard);
                t = System.nanoTime();
                tirabot.nextMove(gs);
                t = System.nanoTime() - t;
                times[i] = t;
            }
            averageRunTimes[run] = getAverage(times);
            averageSTD[run] = getStd(times, averageRunTimes[run]);

        }
        
        System.out.println(printResults());
    }
    
    public static String printResults() {
        StringBuilder sb = new StringBuilder();

        sb.append("Average run times:\n");
        appendResults(sb, averageRunTimes, "ns", averageSTD);
        
        return sb.toString();
    }
    
    private static void appendResults(StringBuilder sb, double[] arr, String suffix, 
            double[] std) {
        for (int i = 0; i < NUMSMINMAX.length; i++) {
            String num = Integer.toString(NUMSMINMAX[i]);

            sb.append(num);
            sb.append(": ");
            sb.append(arr[i]);
            sb.append(suffix);
            if (std != null) {
                sb.append(", std: ");
                sb.append(std[i]);
                sb.append(suffix);
            }
            sb.append("\n");
        }
    }
    
    private static double getStd(long[] times, double mean) {
        double s = 0;
        for (long time : times) {
            s += Math.pow(time - mean, 2);
        }
        return Math.sqrt(s / (times.length - 1));
    }

    private static double getAverage(long[] times) {
        double s = 0;
        for (long time : times) {
            s += time;
        }
        return s / times.length;
    }
    
    private static void setTestBoard(Board boardToSet) {
        // Set Black Pieces
        boardToSet.getTile(File.File_A, Rank.Rank_8).setPiece(new Rook(Side.BLACK));
        boardToSet.getTile(File.File_B, Rank.Rank_8).setPiece(new Knight(Side.BLACK));
        boardToSet.getTile(File.File_C, Rank.Rank_8).setPiece(new Bishop(Side.BLACK));
        boardToSet.getTile(File.File_D, Rank.Rank_8).setPiece(new Queen(Side.BLACK));
        boardToSet.getTile(File.File_E, Rank.Rank_8).setPiece(new King(Side.BLACK));
        boardToSet.getTile(File.File_F, Rank.Rank_8).setPiece(new Bishop(Side.BLACK));
        boardToSet.getTile(File.File_G, Rank.Rank_8).setPiece(new Knight(Side.BLACK));
        boardToSet.getTile(File.File_H, Rank.Rank_8).setPiece(new Rook(Side.BLACK));
        boardToSet.getTile(File.File_A, Rank.Rank_7).setPiece(new Pawn(Side.BLACK));
        boardToSet.getTile(File.File_B, Rank.Rank_7).setPiece(new Pawn(Side.BLACK));
        boardToSet.getTile(File.File_C, Rank.Rank_7).setPiece(new Pawn(Side.BLACK));
        boardToSet.getTile(File.File_D, Rank.Rank_7).setPiece(new Pawn(Side.BLACK));
        boardToSet.getTile(File.File_E, Rank.Rank_7).setPiece(new Pawn(Side.BLACK));
        boardToSet.getTile(File.File_H, Rank.Rank_3).setPiece(new Pawn(Side.BLACK));
        
        // Set Black Pieces
        boardToSet.getTile(File.File_E, Rank.Rank_5).setPiece(new Knight(Side.WHITE));
        boardToSet.getTile(File.File_C, Rank.Rank_4).setPiece(new Bishop(Side.WHITE));
        boardToSet.getTile(File.File_D, Rank.Rank_4).setPiece(new Pawn(Side.WHITE));
        boardToSet.getTile(File.File_F, Rank.Rank_4).setPiece(new Bishop(Side.WHITE));
        boardToSet.getTile(File.File_G, Rank.Rank_4).setPiece(new Queen(Side.WHITE));
        boardToSet.getTile(File.File_E, Rank.Rank_3).setPiece(new Pawn(Side.WHITE));
        boardToSet.getTile(File.File_G, Rank.Rank_3).setPiece(new Pawn(Side.WHITE));
        boardToSet.getTile(File.File_A, Rank.Rank_2).setPiece(new Pawn(Side.WHITE));
        boardToSet.getTile(File.File_B, Rank.Rank_2).setPiece(new Pawn(Side.WHITE));
        boardToSet.getTile(File.File_C, Rank.Rank_2).setPiece(new Pawn(Side.WHITE));
        boardToSet.getTile(File.File_H, Rank.Rank_2).setPiece(new Pawn(Side.WHITE));
        boardToSet.getTile(File.File_A, Rank.Rank_1).setPiece(new Rook(Side.WHITE));
        boardToSet.getTile(File.File_B, Rank.Rank_1).setPiece(new Knight(Side.WHITE));
        boardToSet.getTile(File.File_E, Rank.Rank_1).setPiece(new King(Side.WHITE));
        boardToSet.getTile(File.File_H, Rank.Rank_1).setPiece(new Rook(Side.WHITE));
    }

}
