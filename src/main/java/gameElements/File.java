/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template letterFile, choose Tools | Templates
 * and open the template in the editor.
 */
package gameElements;

/**
 *
 * @author juhop
 */

public enum File {
    File_A("A", 1),
    File_B("B", 2),
    File_C("C", 3),
    File_D("D", 4),
    File_E("E", 5),
    File_F("F", 6),
    File_G("G", 7),
    File_H("H", 8);

    private final String letterFile;
    private final int numberFile;

    File(String file, int numberFile) {
        this.letterFile = file;
        this.numberFile = numberFile;
    }

    public String getStringFile() {
        return letterFile;
    }
    
    public int getIntegerFile() {
        return numberFile;
    }
    
    public static File valueOfLabel(int fileInteger) {
        for (File e : values()) {
            if (e.getIntegerFile() == fileInteger) {
                return e;
            }
        }
        return null;
    }
    
    @Override
    public String toString(){ 
        return this.getStringFile();
    }  
}

