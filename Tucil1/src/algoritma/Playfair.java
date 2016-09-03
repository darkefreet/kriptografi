/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritma;

import algoritma.Point;
import java.util.ArrayList;

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
    
    //an array for indexing purpose
    private Point[] charPosition;
    
    public Playfair(){
        charExist = new boolean[26];
        playFairKey = new char[5][5];
        charPosition = new Point[26];
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
            Point p = new Point(indexX,indexY);
            charPosition[c -'a'] = p;
            playFairKey[indexX][indexY] = c;
//            System.out.println(playFairKey[indexX][indexY]+" "+indexX + " - " + indexY);
            indexY++;
        }
    }
    
    private ArrayList makeDigraf(char[] charArray , int option){
        ArrayList digraf =  new ArrayList();
        if(charArray.length >= 1){
            boolean newPair = true;
            int i = 0;
            while(i<charArray.length){
                if(charArray[i]>='a' && charArray[i]<='z'){
                    if(newPair){
                        digraf.add(charArray[i]);
                        newPair=false;
                    }
                    else{
                        //option 0 = encryption purpose only
                        if((option==0)&&(charArray[i]==digraf.get(digraf.size()-1).toString().charAt(0))){
                            digraf.add('z');
                            digraf.add(charArray[i]);
                        }
                        else{
                            digraf.add(charArray[i]);
                            newPair = true;
                        }
                    }
                }
                i++;
            }
            if(!newPair){
                digraf.add('z');
            }
        }    
//        System.out.println(digraf);
        return digraf;
    }
   
    public String encrypt(String plaintext, String key){
        init(key);
      
        String res = "";        
        
        char[] charArray = plaintext.toLowerCase().toCharArray();
        ArrayList digraf = makeDigraf(charArray,0);
        
        //DIGRAF HAS BEEN CREATED, NOW ENCRYPTING
        for(int i=0;i<digraf.size();i+=2){
            char a = digraf.get(i).toString().charAt(0);
            char b = digraf.get(i+1).toString().charAt(0);
            
            if(charPosition[a-'a'].getX()==charPosition[b-'a'].getX()){
                res+=playFairKey[(charPosition[a-'a'].getX()+1)%5][charPosition[a-'a'].getY()];               
                res+=playFairKey[(charPosition[b-'a'].getX()+1)%5][charPosition[b-'a'].getY()];
            }else if(charPosition[a-'a'].getY()==charPosition[b-'a'].getY()){
                res+=playFairKey[charPosition[a-'a'].getX()][(charPosition[a-'a'].getY()+1)%5];               
                res+=playFairKey[charPosition[b-'a'].getX()][(charPosition[b-'a'].getY()+1)%5];
            }else{
                res+=playFairKey[charPosition[a-'a'].getX()][charPosition[b-'a'].getY()];               
                res+=playFairKey[charPosition[b-'a'].getX()][charPosition[a-'a'].getY()];
            }
        }
//        System.out.println(res);
        return res;
    }
    
    public String decrypt(String encrypted, String key){
        init(key);
        String res = "";
        char[] charArray = encrypted.toLowerCase().toCharArray();
        ArrayList digraf =  makeDigraf(charArray,1);
        //DIGRAF HAS BEEN CREATED, NOW DECRYPTING
        for(int i=0;i<digraf.size();i+=2){
            char a = digraf.get(i).toString().charAt(0);
            char b = digraf.get(i+1).toString().charAt(0);
            
            if(charPosition[a-'a'].getX()==charPosition[b-'a'].getX()){
                int x = (charPosition[a-'a'].getX()-1)%5;if(x < 0){x = 5+x;}
                int y = (charPosition[b-'a'].getX()-1)%5;if(y < 0){y = 5+y;}
                res+=playFairKey[x][charPosition[a-'a'].getY()];               
                res+=playFairKey[y][charPosition[b-'a'].getY()];
            }else if(charPosition[a-'a'].getY()==charPosition[b-'a'].getY()){
                int x = (charPosition[a-'a'].getY()-1)%5;if(x < 0){x = 5+x;}
                int y = (charPosition[b-'a'].getY()-1)%5;if(y < 0){y = 5+y;}
                res+=playFairKey[charPosition[a-'a'].getX()][x];               
                res+=playFairKey[charPosition[b-'a'].getX()][y];
            }else{
                res+=playFairKey[charPosition[a-'a'].getX()][charPosition[b-'a'].getY()];               
                res+=playFairKey[charPosition[b-'a'].getX()][charPosition[a-'a'].getY()];
            }
        }
        
        return res;
    }
}
