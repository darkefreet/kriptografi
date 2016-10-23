/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ivan
 */
public class UTS {

    public static void main (String args[]) throws IOException {
        System.out.println("TriTOLE");
        Encryptor e = new Encryptor("ab", "12");
        System.out.println(e.encrypt());
    }

}
