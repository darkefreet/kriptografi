/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tucil1;

import algoritma.*;
/**
 *
 * @author Hp
 */
public class Tucil1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Vigenere26 vig = new Vigenere26();
        System.out.println(vig.decrypt("hkfzn heafg iakdl larnc i", "akulahsangkakala"));
        
        Playfair pf = new Playfair();
        pf.encrypt("test", "dbcgv");
        pf.encrypt("test", "alamozabkbtd");
        // TODO code application logic here
    }
    
}
