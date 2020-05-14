
package gameElements;

public enum Rank {
    Rank_1(1),
    Rank_2(2),
    Rank_3(3),
    Rank_4(4),
    Rank_5(5),
    Rank_6(6),
    Rank_7(7),
    Rank_8(8);

    private int rank;

    Rank(int rank) {
        this.rank = rank;
    }

    public int getIntegerRank() {
        return rank;
    }
    
    public static Rank valueOfLabel(int rankInteger) {
        for (Rank e : values()) {
            if (e.getIntegerRank() == rankInteger) {
                return e;
            }
        }
        return null;
    }
}
