/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritma;

/**
 *
 * @author Hp
 */
public class Vigenere26 {
    
    public Vigenere26(){
        
    }
    
    public String Encrypt(String plaintext, String key){
        String result = "";
        char[] charArray = plaintext.toLowerCase().toCharArray();
        char[] charKey = key.toLowerCase().toCharArray();
        for(int i = 0; i < charArray.length; i++){
            char encrypted = (char) (((charKey[i % charKey.length]+charArray[i]-'a'-'a')%26)+'a');
            result+= encrypted;
        }
        return result;
    }
}
