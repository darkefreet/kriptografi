/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author Hp
 */
public class Splitter {
    private String word;
    private int hop;
    public Splitter(String _word,int _hop){
        word = _word.toUpperCase();
        hop = _hop;
    }
    
    public void printSplit(){
        char[] charArray = word.toCharArray();
        for(int i = 0; i<charArray.length ; i++){
            if(i%hop==0){
                System.out.print(" ");
            }
            System.out.print(charArray[i]);
        }
        System.out.println("");
    }
    
    public void printOnly(int n){
        char[] charArray = word.toCharArray();
        for(int i = 0; i<charArray.length ; i+=hop){
            if(i%hop==0){
                System.out.print(charArray[i+n]);
            }
        }
        System.out.println("");
    }
}
