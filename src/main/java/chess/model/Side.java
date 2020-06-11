/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.model;

public enum Side {
    WHITE(1),
    BLACK(-1);
    
    private final int multiplier;
    
    Side(int multiplier) {
        this.multiplier = multiplier;
    }
    
    public int getMultiplier() {
        return this.multiplier;
    }
}
