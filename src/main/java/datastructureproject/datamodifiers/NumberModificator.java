package datastructureproject.datamodifiers;

/**
 *
 * NumberModificator does min, max, abs and random operations.
 * 
 * @author juhop
 */
public class NumberModificator {
    
    /**
     * Random multiplier with which the random number is multiplied.
     */
    static int randomMultiplier = 1;

    /**
     * Random increment to be added on the random number every loop.
     */
    static int randomIncrement = 1;

    /**
     * The random iterations to do in random loop.
     */
    static int randomHowManyIterations = 1;
    
    /**
     * New NumberModificator ready to be used.
     */
    public NumberModificator() {
        
    }
    
    /**
     *
     * Returns the higher number of the two.
     * 
     * @param first number to compare.
     * @param second number to compare.
     * @return the value of highest number.
     */
    public int max(int first, int second) {
        
        int max = first;
        if (second > first) {
            max = second;
        }
        return max;
    }
    
    /**
     *
     * Returns the smaller number of the two.
     * 
     * @param first number to compare.
     * @param second number to compare.
     * @return the value of smallest number.
     */
    public int min(int first, int second) {
        
        int min = first;
        if (second < first) {
            min = second;
        }
        return min;
    }
    
    /**
     *
     * Returns the absolute value of number.
     * 
     * @param number to be given absolute value.
     * @return the absolute value of given number.
     */
    public int abs(int number) {
        
        int abs = number;
        
        if (number < 0) {
            abs *= -1;
        }
            
        return abs;
    }
    
    /**
     *
     * Returns pseudo-random number.
     * The random numbers go within the same number array loop if the same parameter n is used. 
     * However with chess I highly doubt there would be many random calls with same parameter n.
     * 
     * @param n the number bandwidth that the random number should be taken from.
     * @return the random number from 0 to n - 1
     */
    public int random(int n) {
        
        int seed = 32767 % n;
        
        int random = (randomMultiplier * seed + randomIncrement) % n;
        
        for (int i = 0; i < randomHowManyIterations; i++) {
            random = (randomMultiplier * random + randomIncrement) % n;  
        }
        
        randomIncrement++;
        randomIncrement %= 10;

        randomMultiplier++;
        randomMultiplier %= 10;

        randomHowManyIterations++;
        randomHowManyIterations %= 10;
       
       this.abs(random);
       
        if (random == n) {
            random--;
        }
        
        return random % n;
  
    }

    
    
}
