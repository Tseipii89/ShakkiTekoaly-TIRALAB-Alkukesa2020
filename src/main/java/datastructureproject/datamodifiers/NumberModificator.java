package datastructureproject.datamodifiers;

public class NumberModificator {
    
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
    
}
