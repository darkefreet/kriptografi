/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author ivanandrianto
 */
public class Character {
    public static boolean isAsciiCharacter(char c) {
        return (c >= 0) && (c <= 255);
    }
}
