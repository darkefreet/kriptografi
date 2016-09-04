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
public class VigenereModification {
    
    /* In vigenere, if the key is not as length as the text, it will be repeated
     * In this modification, the key is not only repeated, but each character is incremented
     * Makes it more difficult to perform cryptanalysis
     */
    
    private static String shiftKey(String key) {
        String newKey = "";
        for (int i = 0; i < key.length(); i++) {
            newKey += (char)((key.charAt(i) + 1) % 255);
        }
        return newKey;
    }
    
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
            if ((i % key.length()) == key.length() - 1) {
                key = shiftKey(key);
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
            if ((i % key.length()) == key.length() - 1) {
                key = shiftKey(key);
            }
        }
        return result;
    }

}
