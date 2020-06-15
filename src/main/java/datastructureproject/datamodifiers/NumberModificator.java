package datastructureproject.datamodifiers;


public class NumberModificator {
    
    static int RandomMultiplier = 1;
    static int RandomIncrement = 1;
    static int RandomHowManyIterations = 1;
    
    public NumberModificator() {
        
    }
    
    public int max(int first, int second) {
        
        int max = first;
        if(second > first) {
            max = second;
        }
        return max;
    }
    
    public int min(int first, int second) {
        
        int min = first;
        if(second < first) {
            min = second;
        }
        return min;
    }
    
    public int abs(int number) {
        
        int abs = number;
        
        if(number < 0) {
            abs *= -1;
        }
            
        return abs;
    }
    
    

 
    public int random(int n) {
        
        int seed = 32767 % n;
        
        int random = (RandomMultiplier * seed + RandomIncrement) % n;
        
        for (int i = 0; i < RandomHowManyIterations; i++) {
            random = (RandomMultiplier * random + RandomIncrement) % n;  
        }
        
       RandomIncrement++;
       RandomIncrement %= 10;
       
       RandomMultiplier++;
       RandomMultiplier %= 10;
       
       RandomHowManyIterations++;
       RandomHowManyIterations %= 10;
       
       this.abs(random);
       
       if (random == n) {
           random--;
       }
        
        return random % n;
  
    }

    
    
}
