/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elipticcurve;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author ivan
 */
public class Main2 {
    
    private static final BigInteger k = new BigInteger("20");
    private static final BigInteger secretKey = new BigInteger("10320885690046317857");
    
    private static Pair encrypt(BigInteger m, Point _base, Point publicKey){
        
        Point pM = new Point(m);
        Point A = new Point(_base.x,_base.y);
        A.times(k);
        
        Point B = new Point(publicKey.x,publicKey.y);
        
        B.times(k);
        B.add(pM);
        System.out.println(new Pair(A,B));
        return new Pair(A,B);
    }
    
    private static String getString(BigInteger big){
        String ret = "";
        byte[] z = big.toByteArray();
        System.out.println("z length" + z.length);
        
        for (int i = 0; i < z.length; i++)
            ret+= (char)(z[i] & 0xFF);
        
        if (ret.length() == 1)
            ret = (char)256 + ret;
        
        System.out.println("ret: " + ret);
        return ret;
    }
    
    private static BigInteger backToBig(String st){
        System.out.println("st: " + st + " length: " + st.length());
        byte[] z = new byte[2];
        for (int i = 0; i<2; i++)
            z[i] = (byte)st.charAt(i);
        return new BigInteger(z);
    }
    
    private static BigInteger decrypt(Pair cipher, Point _base){
        
        Point item1 = new Point(_base.x,_base.y);
        item1.times(k);
        item1.times(secretKey);
        
        Point item2 = new Point(cipher.B.x,cipher.B.y);
        item2.subtract(item1);
        return item2.x;
        
    }
    
    private static String encryptString(String plainteks, Point base, Point publicKey) throws UnsupportedEncodingException{
        String encryptedText = "";
        int count  = 0;
        
        for (int i = 0; i < plainteks.length(); i++) {
            String str = "" + plainteks.charAt(i);
            BigInteger m = new BigInteger(str.getBytes());
            Pair encryptedPair = encrypt(m,base,publicKey);
            System.out.println(encryptedPair);
            if(count == 0){
                BigInteger X = encryptedPair.A.x;
                BigInteger Y = encryptedPair.A.y;
//                System.out.println("tes" + backToBig(getString(X)));
                encryptedText += getString(X);
                encryptedText += getString(Y);
                count++;
            }
            encryptedText += getString(encryptedPair.B.x);
            encryptedText += getString(encryptedPair.B.y);
        }
//        System.out.println(encryptedText);
        System.out.println(encryptedText);
        return DatatypeConverter.printHexBinary(encryptedText.getBytes());
    }
    
    private static String decryptString(String cipherteks,Point base){
        cipherteks = new String(DatatypeConverter.parseHexBinary(cipherteks));
        System.out.println(cipherteks);
        List<String> strings = new ArrayList<String>();
        int index = 0;
        while (index < cipherteks.length()) {
            strings.add(cipherteks.substring(index, Math.min(index + 2,cipherteks.length())));
            index += 2;
        }
        String keyA = strings.get(0);
        String keyB = strings.get(1);
        String plainteks = ""; 
        System.out.println("--decrypt");
        for(int i = 2; i<strings.size(); i+=2){
            String cipherLeft = strings.get(i);
            String cipherRight = strings.get(i+1);
            Pair cipher = new Pair(new Point(backToBig(keyA),backToBig(keyB)),new Point(backToBig(cipherLeft),backToBig(cipherRight)));
            
            System.out.println(cipher);
            BigInteger plain = decrypt(cipher,base);
            plainteks += new String(plain.toByteArray());
        }
        return plainteks;
    }
    
    public static void main(String[] args) throws IOException {
        //private key a 64 bits in size
        Scanner s = new Scanner(System.in);
        
        Point base = new Point(new BigInteger("12345678"));
        GeneratePublicKey gen = new GeneratePublicKey(secretKey,base);

//        String plain = "abcdefghijklmnopqrstuvwxyz-0123456789-ABCDEFGHIJKLMNOPQRSTUVWXYZ-!@#$%^&*()";
//
//        String cipher = encryptString(plain,base,gen.getPublicKey());
//        System.out.println("cipherteks " + cipher);
//        System.out.println(decryptString(cipher,base));
//        System.out.println("tole");
        
        String originalFileStr = FileHelper.read(s.nextLine());
        String cipherText = encryptString(originalFileStr, base, gen.getPublicKey());
        FileHelper.write(cipherText, "cipher");
        
        String cipherFileStr = FileHelper.read("cipher");
        String decryptedText = decryptString(cipherFileStr, base);
        FileHelper.write(decryptedText, "decrypted");
        
    }
}
