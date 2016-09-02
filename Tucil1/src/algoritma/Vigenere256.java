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
                result += (char)(asciiCode % 255);
            } else {
                System.out.println("zzzz");
                result += text.charAt(i);
            }
        }
        return result;
    }

    public static String decrypt(String text, String key) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if (helpers.Character.isAsciiCharacter(text.charAt(i))) {
                int asciiCode = (int)text.charAt(i) - (int)(key.charAt(i % key.length())) + 255;
                result += (char)(asciiCode % 255);
            } else {
                result += text.charAt(i);
            }
        }
        return result;
    }

}
