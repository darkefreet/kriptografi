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
    
    public String Encrypt(String plaintext, String key, int option){
        String result = "";
        //remove all whitespaces
        key = key.replaceAll("\\s+","");
        char[] charArray = plaintext.toLowerCase().toCharArray();
        char[] charKey = key.toLowerCase().toCharArray();
        
        int j = 0;
        for(int i = 0; i < charArray.length; i++){
            switch(option){
            /*option 0 : data ditampilkan apa adanya*/
            case 0 :
                if(charArray[i]>='a' && charArray[i]<='z'){
                    char encrypted = (char) (((charKey[j % charKey.length]+charArray[i]-'a'-'a')%26)+'a');
                    result+= encrypted;
                    j++;
                }else{result+= charArray[i];}
                break;
                
            /*option 1 : data ditampilkan tanpa spasi*/
            case 1 :
                if(charArray[i]>='a' && charArray[i]<='z'){
                    char encrypted = (char) (((charKey[j % charKey.length]+charArray[i]-'a'-'a')%26)+'a');
                    result+= encrypted;
                    j++;
                }
                break;
                
            /*option 2 : data ditampilkan dalam lima lima*/
            case 2 :
                if(charArray[i]>='a' && charArray[i]<='z'){
                    char encrypted = (char) (((charKey[j % charKey.length]+charArray[i]-'a'-'a')%26)+'a');
                    result+= encrypted;
                    j++;
                }
                if(j%5 == 0)result += ' ';
                break;
            }
        }
        return result;
    }
    
    public String Decrypt(String encrypted, String key){
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
