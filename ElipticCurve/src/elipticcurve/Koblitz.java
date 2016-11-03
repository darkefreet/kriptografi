/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elipticcurve;

import java.math.BigInteger;
import java.nio.charset.Charset;

/**
 *
 * @author ivan
 */
public class Koblitz {
    
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static BigInteger a;
    private static BigInteger b;
    private static BigInteger p;
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
            BigInteger c_bi = BigInteger.valueOf(new Integer(c).intValue());
            BigInteger x = m.multiply(k).add(c_bi);
            BigInteger x3 = x.pow(3);
            BigInteger ax = a.multiply(x);
            BigInteger y2 = x3.add(ax).add(b).mod(p);
            if (BigIntegerHelper.isSquare(y2)) {
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
        BigInteger a = new BigInteger("2");
        String encoded = encode("abcdefghijklmnopqrstuvwxyz");
        System.out.println(encoded);
        System.out.println(decode(encoded));
//        System.out.println(sqrt(a));
    }
    
}
