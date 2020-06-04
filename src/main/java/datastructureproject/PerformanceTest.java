package datastructureproject;


import chess.bot.TiraBot;
import chess.elements.Board;
import chess.engine.GameState;
import chess.model.Side;

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
            // Test run inside the given depth
            Board testBoard = new Board();
            testBoard.initBoard();
            AlphaBetaPruner alphabeta;
            alphabeta = new AlphaBetaPruner();
            alphabeta.minimax(Side.WHITE, testBoard, 0, true);
            for (int i = 0; i < TESTRUNS; i++) {
                alphabeta = new AlphaBetaPruner();
                testBoard.initBoard();
                t = System.nanoTime();
                alphabeta.minimax(Side.WHITE, testBoard, depth, true);
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

}
