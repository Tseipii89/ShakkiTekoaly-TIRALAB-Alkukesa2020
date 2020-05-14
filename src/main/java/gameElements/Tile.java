
package gameElements;

public class Tile {
   
   
    private final File file;
    private final Rank rank;

    public Tile(File file, Rank rank) {
        this.rank = rank;
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    public Rank getRank() {
        return this.rank;
    }
   

   
}
