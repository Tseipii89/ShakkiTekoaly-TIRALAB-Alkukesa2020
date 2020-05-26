/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template letterFile, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.elements;

/**
 *
 * Files are typicially associated with the horizontal axis of chessBoard. 
 * They go from A to H.
 * The class is implemented as a enum class, so only given values are possible.
 * 
 * @author juhop
 */

public enum File {

    /**
     * String a.
     * Integer 1.
     */
    File_A("a", 1),

    /**
     * String b.
     * Integer 2.
     */
    File_B("b", 2),

    /**
     * String c.
     * Integer 3.
     */
    File_C("c", 3),

    /**
     * String d.
     * Integer 4.
     */
    File_D("d", 4),

    /**
     * String e.
     * Integer 5.
     */
    File_E("e", 5),

    /**
     * String f.
     * Integer 6.
     */
    File_F("f", 6),

    /**
     * String g.
     * Integer 7.
     */
    File_G("g", 7),

    /**
     * String h.
     * Integer 8.
     */
    File_H("h", 8);

    /**
     *
     * Every file has a string from a to h that is associated with given file integer.
     * These are defined in the File class as enums, so only certain values are possible.
     * 
     */
    private final String letterFile;

    /**
     *
     * Every file has a number from 1 to 8 that is associated with given file String.
     * These are defined in the File class as enums, so only certain values are possible.
     * 
     */
    private final int numberFile;

    /**
     *
     * Creates a new File.
     * 
     * @param file string from A to H.
     * @param numberFile Number value associated with given file string.
     */
    File(String file, int numberFile) {
        this.letterFile = file;
        this.numberFile = numberFile;
    }

    /**
     *
     * Returns the String value of given File.
     * Strings are A, B, C... to H.
     * 
     * @return String value of File
     */
    public String getStringFile() {
        return letterFile;
    }
    
    /**
     *
     * Returns the number value of given File.
     * Numbers grow from a = 1, b = 2 etc. to h = 8.
     * Numbers are used in the calculations of new Files.
     * 
     * @return integer value of given File.
     */
    public int getIntegerFile() {
        return numberFile;
    }
    
    /**
     *
     * Return the File of given number value. Numbers grow from a = 1, b = 2 etc. to h = 8
     * 
     * @param fileInteger the integer value given to the method.
     * @return File associated with given integer value.
     */
    public static File valueOfInteger(int fileInteger) {
        for (File e : values()) {
            if (e.getIntegerFile() == fileInteger) {
                return e;
            }
        }
        return null;
    }
    
    /**
     *
     * Returns the File associated with given String.
     * String takes values from a to h.
     * 
     * @param fileString the String value to be checked.
     * @return File associated with given String. 
     */
    public static File valueOfLabel(String fileString) {
        for (File e : values()) {
            if (e.getStringFile().equals(fileString)) {
                return e;
            }
        }
        return null;
    }
    
    @Override
    public String toString() { 
        return this.getStringFile();
    }  
}

