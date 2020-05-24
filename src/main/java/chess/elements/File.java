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
     * String A.
     * Integer 1.
     */
    File_A("A", 1),

    /**
     * String B.
     * Integer 2.
     */
    File_B("B", 2),

    /**
     * String C.
     * Integer 3.
     */
    File_C("C", 3),

    /**
     * String D.
     * Integer 4.
     */
    File_D("D", 4),

    /**
     * String E.
     * Integer 5.
     */
    File_E("E", 5),

    /**
     * String F.
     * Integer 6.
     */
    File_F("F", 6),

    /**
     * String G.
     * Integer 7.
     */
    File_G("G", 7),

    /**
     * String H.
     * Integer 8.
     */
    File_H("H", 8);

    /**
     *
     * Every file has a string from A to H that is associated with given file integer.
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
     * Numbers grow from A = 1, B = 2 etc. to H = 8.
     * Numbers are used in the calculations of new Files.
     * 
     * @return integer value of given File.
     */
    public int getIntegerFile() {
        return numberFile;
    }
    
    /**
     *
     * Return the File of given number value. Numbers grow from A = 1, B = 2 etc. to H = 8
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

