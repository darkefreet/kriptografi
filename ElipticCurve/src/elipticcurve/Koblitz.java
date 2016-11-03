/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elipticcurve;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Date;

/**
 *
 * @author ivan
 */
public class Koblitz {
    
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static BigInteger a = new BigInteger("1298168629042284227");
    private static BigInteger b = new BigInteger("1623552840218452387");
    private static BigInteger p = new BigInteger("197642558969933863176093209873160239227");
//    private static BigInteger a = new BigInteger("-1");
//    private static BigInteger b = new BigInteger("188");
//    private static BigInteger p = new BigInteger("751");
    private static BigInteger k = new BigInteger("20");
    
    public Koblitz(BigInteger a, BigInteger b, BigInteger p, BigInteger k, String text) {
        this.a = a;
        this.b = b;
        this.p = p;
        this.k = k;
    }
    
    private static BigInteger getX (BigInteger m) {
        boolean found = false;
        BigInteger validX = new BigInteger("-1");
        int c = 1;

        while(!found) {
            System.out.println("c: " + c);
            BigInteger c_bi = BigInteger.valueOf(new Integer(c).intValue());
            BigInteger x = m.multiply(k).add(c_bi);
            System.out.println("x: " + x);
            BigInteger x3 = x.pow(3);
            BigInteger ax = a.multiply(x);
            BigInteger right = x3.add(ax).add(b);
            BigInteger rightMod = right.mod(p);
            System.out.println("right: " + right);        
            
            BigInteger power = (p.add((BigInteger.ONE))).divide(new BigInteger("4"));
            System.out.println("power: " + power);
            //BigInteger rightPow = right.pow(power.intValue()); // power bisa jadi negatif -> ga bisa pake cara ini
            BigInteger rightPow =  BigIntegerHelper.pow(right, power); // Ga selesai2
            BigInteger y = rightPow.mod(p);
            System.out.println("y: " + y);
            if (y.pow(2).mod(p).compareTo(rightMod) == 0) {
                validX = x;
                found = true;
            }
            c++;    
        }
        System.out.println("found " + found);
        return validX;
    }


    public static String encode (String text) {
        String encoded = "";

        String[] splitBy16Chars = text.split("(?<=\\G................)");
        for (int i = 0; i < splitBy16Chars.length; i++) {
            String str = splitBy16Chars[i];
            System.out.println(str);
            int strLength = str.length();
            if (strLength < 16) {
                for (int j = 0; j < (16 - strLength); j++) {
                    str += " ";
                }
            }
            BigInteger bi = new BigInteger(str.getBytes());
            encoded += new String(getX(bi).toByteArray());
        }
        
        return encoded;
    }
    
    public static String decode (String text) {
        String decoded = "";
        
        String[] splitBy16Chars = text.split("(?<=\\G................)");
        for (int i = 0; i < splitBy16Chars.length; i++) {
            String str = splitBy16Chars[i];
            BigInteger bi = new BigInteger(str.getBytes());
            decoded += new String(bi.subtract(BigInteger.ONE).divide(k).toByteArray());
        }
        
        return decoded;
    }
    
    public static void main(String args[]) {

        long startTimeEncode = System.currentTimeMillis();
        
        System.out.println(getX(new BigInteger("600")));
        
//        String encoded = encode("abcdefghijklmnopqrstuvwxyz");
//        long elapsedTimeEncode = (new Date()).getTime() - startTimeEncode;
//        System.out.println(elapsedTimeEncode + "ms");
//        System.out.println(encoded);
        
//        long startTimeDecode = System.currentTimeMillis();
//        String decoded = decode(encoded);
//        long elapsedTimeDecode = (new Date()).getTime() - startTimeDecode;
//        System.out.println(elapsedTimeDecode + "ms");
//        System.out.println(decoded);

    }
    
}
