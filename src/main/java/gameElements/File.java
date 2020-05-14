/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameElements;

/**
 *
 * @author juhop
 */

enum File {
    File_A("A"),
    File_B("B"),
    File_C("C"),
    File_D("D"),
    File_E("E"),
    File_F("F"),
    File_G("G"),
    File_H("H");

    private final String file;

    File(String file) {
        this.file = file;
    }

    public String getStringFile() {
        return file;
    }
}

