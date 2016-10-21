/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts;

import java.io.IOException;

/**
 *
 * @author ivan
 */
public class UTS {

    // 2
    public static int sisip (char c) {
        int fourLeftBits = c & 0xF0;
        int fourRightBits = c & 0x0F;
        int shiftedFourLeftBits = fourLeftBits & 0x80 | (fourLeftBits >> 1) & 0x20 | (fourLeftBits >> 2) & 0x8 | (fourLeftBits >> 3) & 0x2;
        int shiftedFourRightBits = fourRightBits & 0x01 | (fourRightBits << 1) & 0x04 | (fourLeftBits << 2) & 0x10 | (fourLeftBits << 3) & 0x40;
        int joinedLeftAndRight = shiftedFourLeftBits | shiftedFourRightBits;
        return joinedLeftAndRight;
    }

    // 3.1
    public static int xorLeftRight (int c) {
        int fourLeftBits = c >> 4;
        int fourRightBits = c & 0x0F;
        return fourLeftBits ^ fourRightBits;
    }

    // 3.2 - 3.4
    public static int xorHasilSementara (int c, char key) {
        int keyFourLeftBits = key >> 4;
        int keyFourRightBits = key & 0x0F;
        int xoredkeyLeft = c ^ keyFourLeftBits;
        int xoredkeyRight = c ^ keyFourRightBits;
        System.out.println(Integer.toBinaryString((xoredkeyRight << 4) | xoredkeyLeft));
        return (xoredkeyRight << 4) | xoredkeyLeft;
    }

    public static void main (String args[]) throws IOException {
        //int res = xorHasilSementara(xorLeftRight(sisip('s')),'k');
        SBox sBox = new SBox();
    }

}
