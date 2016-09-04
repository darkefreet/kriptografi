/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritma;

/**
 *
 * @author ivanandrianto
 */
public class Vigenere256 {

    public static String encrypt(String text, String key) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if (helpers.Character.isAsciiCharacter(text.charAt(i))) {
                int asciiCode = (int)text.charAt(i) + (int)(key.charAt(i % key.length()));
                asciiCode %= 255;

                // Handle LF and CR
                if (asciiCode == 10) {
                    asciiCode = 256;
                } else if (asciiCode == 13) {
                    asciiCode = 257;
                }

                result += (char)asciiCode;
            }
        }
        return result;
    }

    public static String decrypt(String text, String key) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            int charAsciiCode = (int)text.charAt(i);
            if (charAsciiCode >=0 && charAsciiCode <= 257) {

                // Handle LF and CR
                if (charAsciiCode == 256) {
                   charAsciiCode = 10;
                } else if (charAsciiCode == 257) {
                   charAsciiCode = 13;
                }

                int asciiCode = charAsciiCode - (int)(key.charAt(i % key.length())) + 255;
                result += (char)(asciiCode % 255);
            } else {
                result += text.charAt(i);
            }
        }
        return result;
    }

}
