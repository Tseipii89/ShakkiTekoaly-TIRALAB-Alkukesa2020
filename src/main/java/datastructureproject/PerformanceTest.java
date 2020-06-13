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
 * Performance test counts the average run time and standard deviation of the run time on different steps.
 */
public class PerformanceTest {

    /**
     * How deep to go through the min-max tree.
     * Each step is gone through.
     */
    private static final int[] NUMSMINMAX = {0, 1, 2, 3};

    /**
     * How many test runs to do in one step.
     */
    private static final int TESTRUNS = 20;

    /**
     * Array to hold the average of the test runs of different steps.
     */
    private static final double[] AVERAGERUNTIMES = new double[NUMSMINMAX.length];

    /**
     * Array to hold the average of the test runs of different steps.
     */
    private static final double[] AVERAGESTD = new double[NUMSMINMAX.length];

    /**
     * Array to hold the run lengths of given depth.
     */
    private static final long[] TIMES = new long[TESTRUNS];
    
    /**
     *
     * Runs TESTRUNS number of test runs on different depths on min-max tree specified on NUMSMINMAX.
     * The average and standard deviation of the tests runs are printed out.
     * 
     * @param args the initial parameter used with main -method.
     */
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
                TIMES[i] = t;
            }
            AVERAGERUNTIMES[run] = getAverage(TIMES);
            AVERAGESTD[run] = getStd(TIMES, AVERAGERUNTIMES[run]);

        }
        
        System.out.println(printResults());
    }
    
    /**
     *
     * Creates the results print out.
     * 
     * @return the string builder that holds the ready build message.
     */
    public static String printResults() {
        StringBuilder sb = new StringBuilder();

        sb.append("Average run times:\n");
        appendResults(sb, AVERAGERUNTIMES, "ns", AVERAGESTD);
        
        return sb.toString();
    }
    
    /**
     *
     * Creates the print of the message that is shown in terminal.
     * 
     * @param sb the string builder on which to add the texts.
     * @param arr array holds the average run times.
     * @param suffix in this case is nanoseconds or ns.
     * @param std array holds the standard deviations.
     */
    private static void appendResults(
            StringBuilder sb, 
            double[] arr, 
            String suffix, 
            double[] std
    ) {
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
    
    /**
     *
     * Counts the standard deviation of given values.
     * 
     * @param times the array from which to count the standard deviation.
     * @param mean the mean of the array values.
     * @return the standrd deviation of the values in the array.
     */
    private static double getStd(long[] times, double mean) {
        double s = 0;
        for (long time : times) {
            s += Math.pow(time - mean, 2);
        }
        return Math.sqrt(s / (times.length - 1));
    }

    /**
     *
     * Counts the average of given long array.
     * 
     * @param times the array from which to count the average.
     * @return the average of the array values.
     */
    private static double getAverage(long[] times) {
        double s = 0;
        for (long time : times) {
            s += time;
        }
        return s / times.length;
    }
    
    /**
     *
     * The initial setting of chess Board didn't emphasize the difference between different improvements enough.
     * This Board setting will show the alpha-beta vs. min-max improvement very cleary.
     * 
     * @param boardToSet the board on which to set the pieces accordingly.
     */
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
