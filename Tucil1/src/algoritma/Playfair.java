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
public class Playfair {
    private int indexX;
    private int indexY;
    
    //excludes J
    private static char bannedChar = 'j';
    
    private boolean[] charExist;
    private char[][] playFairKey;
    
    
    public Playfair(){
        charExist = new boolean[26];
        playFairKey = new char[5][5];
    }
    
    private void init(String key){
        indexX = 0;
        indexY = 0;
        
        //initialize charexist with false values
        for (int i=0;i<=25;i++){
            charExist[i] = false;
        }
        
        //make the key
        char[] charKey = key.toLowerCase().toCharArray();
        for(int i=0; i<charKey.length;i++){
            //only from a to z characters
            if(charKey[i]>='a' && charKey[i]<='z'){
                if(charExist[charKey[i]-'a']){
                    //character exist
                }else{
                    if(charKey[i]!=bannedChar){
                        addCharToMatrix(charKey[i]);
                    }
                    charExist[charKey[i]-'a'] = true;
                }
            }
        }
        
        //fill the empty key with other characters 
        for(char i ='a'; i<='z';i++){
            if(i!=bannedChar && !charExist[i-'a']){
                addCharToMatrix(i);
            }
        }
    }
    
    private void addCharToMatrix(char c){
        if(indexY==5){
            indexX++;
            indexY = 0;
        }
        //only add char as long as there is a room for a new char
        if(indexX<5){
            playFairKey[indexX][indexY] = c;
//            System.out.println(playFairKey[indexX][indexY]+" "+indexX + " - " + indexY);
            indexY++;
        }
    }
   
    public String encrypt(String plaintext, String key){
        init(key);
        
        return "";
    }
    
    public String decrypt(String encrypted, String key){
        init(key);
        return "";
    }
}
