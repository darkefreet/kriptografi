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
 * @author Hp
 */
public class Decryptor {
    private String cipherText;
    private String key;
    private SBox sBox;
    

    public Decryptor(String cipherText, String key) throws IOException {
        this.cipherText = cipherText;
        this.key = key;
        sBox = new SBox();
    }
    
    // 3.1
    public int xorLeftRight (int c) {
        int fourLeftBits = c >> 4;
        int fourRightBits = c & 0x0F;
        return fourLeftBits ^ fourRightBits;
    }
    
    public int xorHasil(int key,int keyRight, char c){
        int charFourLeftBits = c >> 4;
        int charFourRightBits = c & 0x0F;
        int xoredkeyLeft = key ^ charFourLeftBits;
        int xoredkeyRight = keyRight ^ charFourRightBits;
        return ((xoredkeyLeft|0x00) << 4) | (xoredkeyRight & 0x0F);
    }
    
    private String langkahTiga(String text){
        String res = "";
        for(int i = 0;i<text.length();i++){
            int textChar = (int) text.charAt(i);
            int leftShifted = textChar & 0x80 | (textChar & 0x20) << 1 | (textChar & 0x08) << 2 | (textChar & 0x02) << 3;
            int rightShifted = (textChar & 0x40 )>>3 | (textChar & 0x10 ) >> 2 | (textChar & 0x04 )>>1 | (textChar & 0x01 );
            int left = leftShifted & 0xF0;
            int right = rightShifted & 0x0F;
            res += (char) (left | right);
        }
        return res;
    }
    
    private String langkahDua(String text) {
        String res = "";
        for (int i = 0; i < text.length(); i++) {
            char textChar = text.charAt(i);
            char keyChar = key.charAt(i % key.length());
            res += (char)(xorHasil(xorLeftRight(keyChar),(keyChar&0x0F), textChar));
        }
        return res;
    }
    
    private String langkahSatu(String text){
        return sBox.findSub(text);
    }
    
    private String langkahSatuSampaiTiga(String text){
        String res = "";
        res = langkahSatu(text);
        res = langkahDua(res);
        res = langkahTiga(res);
        return res;
    }
    
    private String geserTigaKeKiri(String s){
        String res = "";
        int length = s.length();
        String a = s.substring(0,1);
        String b = s.substring(1);
        s = b+a;
        
        ArrayList<Integer> chars = new ArrayList<Integer>();
        for(int i = 0;i<s.length();i++){
            int c = (int)s.charAt(i);
            chars.add(c>>4);
            chars.add(c&0xF);
        }
        
        for(int i = 1;i<chars.size()-1;i+=2){
            int joined = (chars.get(i) << 4) | chars.get(i+1);
            res+= (char) joined;
        }
        res+= (char) ((chars.get(chars.size()-1) << 4) | chars.get(0));
        return res;
    }
    
    public String decrypt(){
        String res = cipherText;
        
        for(int i = 0;i<3;i++){
            res = langkahSatuSampaiTiga(res);
            res = geserTigaKeKiri(res);
        }
        return langkahSatuSampaiTiga(res);
    }
}
