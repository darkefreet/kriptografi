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

    public static String encrypt(String plaintext, String key){
        String result = "";
        //remove all whitespaces
        key = key.replaceAll("\\s+","");
        char[] charArray = plaintext.toLowerCase().toCharArray();
        char[] charKey = key.toLowerCase().toCharArray();
        
        int j = 0;
        for(int i = 0; i < charArray.length; i++) {
            if(charArray[i]>='a' && charArray[i]<='z'){
                char encrypted = (char) (((charKey[j % charKey.length]+charArray[i]-'a'-'a')%26)+'a');
                result += encrypted;
                j++;
            }else{result += charArray[i];}
        }
        return result;
    }
    
    public static String decrypt(String encrypted, String key){
        String result="";
        char[] charArray = encrypted.toLowerCase().toCharArray();
        char[] charKey = key.toLowerCase().toCharArray();
        int j = 0;
        for(int i = 0; i < charArray.length; i++){
            if(charArray[i]>='a' && charArray[i]<='z'){
                char decrypted = (char) ((((charArray[i] - 'a')-(charKey[j % charKey.length]-'a'))%26) + 'a');
                if(decrypted < 'a'){
                    decrypted = (char) (decrypted + 26);
                }
                result+= decrypted;
                j++;
            }else{
                result+= charArray[i];
            }
        }
        return result;
    }
}
