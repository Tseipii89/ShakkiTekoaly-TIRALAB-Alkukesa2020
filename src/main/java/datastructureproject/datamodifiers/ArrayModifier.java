/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructureproject.datamodifiers;

/**
 *
 * @author juhop
 */
public class ArrayModifier {
    
    public ArrayModifier() {
        
    }


    public String[] addNewMoveToArray(String[] moves, String move) {
        int n = moves.length;
        String[] newMoves = new String[n + 1];
       
        for (int j = 0; j < n; j++) {
            newMoves[j] = moves[j];
            
        }
        
        newMoves[n] = move;
        
        return newMoves;
    }
    
    public String[] addNewArrayToArray(String[] firstArray, String[] secondArray) {
        int n = firstArray.length;
        int m = secondArray.length;
        String[] newMoves = new String[n + m];
       
        for (int i = 0; i < n; i++) {
            newMoves[i] = firstArray[i]; 
        }
        
        for (int j = 0; j < m; j++) {
            newMoves[n+j] = secondArray[j]; 
        }

        return newMoves;
    }
    
}
