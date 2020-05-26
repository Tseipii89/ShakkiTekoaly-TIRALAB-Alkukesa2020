
package chess.elements;

/**
 * Rank is associated with the vertical axis of game Board.
 * @author juhop
 */
public enum Rank {

    /**
     * Rank 1.
     */
    Rank_1(1),

    /**
     * Rank 2.
     */
    Rank_2(2),

    /**
     * Rank 3.
     */
    Rank_3(3),

    /**
     * Rank 4.
     */
    Rank_4(4),

    /**
     * Rank 5.
     */
    Rank_5(5),

    /**
     * Rank 6.
     */
    Rank_6(6),

    /**
     * Rank 7.
     */
    Rank_7(7),

    /**
     * Rank 8.
     */
    Rank_8(8);

    /**
     * Ranks only have the associated integer value. 
     */
    private int rank;

    /**
     *
     * Sets the Rank's integer value.
     * 
     * @param rank the value to be added to Rank.
     */
    Rank(int rank) {
        this.rank = rank;
    }

    /**
     *
     * @return Rank's integer value.
     */
    public int getIntegerRank() {
        return rank;
    }
    
    /**
     *
     * Returns the Rank associated with given integer.
     * 
     * @param rankInteger the integer to be checked.
     * @return the Rank associated with given rankInteger.
     */
    public static Rank valueOfInteger(int rankInteger) {
        for (Rank e : values()) {
            if (e.getIntegerRank() == rankInteger) {
                return e;
            }
        }
        return null;
    }
    
    @Override
    public String toString() { 
        return Integer.toString(this.getIntegerRank());
    }  
}
