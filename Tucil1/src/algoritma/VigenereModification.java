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
            int asciiCode = (int)text.charAt(i) + (int)(key.charAt(i % key.length()));
            result += (char)(asciiCode % 255);
            if ((i % key.length()) == key.length() - 1) {
                key = shiftKey(key);
            }
        }
        return result;
    }

    public static String decrypt(String text, String key) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            int asciiCode = (int)text.charAt(i) - (int)(key.charAt(i % key.length())) + 255;
            result += (char)(asciiCode % 255);
            if ((i % key.length()) == key.length() - 1) {
                key = shiftKey(key);
            }
        }
        return result;
    }

}
