/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elipticcurve;
import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Hp
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static final BigInteger k = new BigInteger("43");
    private static final BigInteger secretKey = new BigInteger("10320885690046317857");
    
    private static Pair encrypt(BigInteger m, Point _base, Point publicKey){
        
        Point pM = new Point(m);
        Point A = new Point(_base.x,_base.y);
        A.times(k);
        
        Point B = new Point(publicKey.x,publicKey.y);
        
        B.times(k);
        B.add(pM);
        
        return new Pair(A,B);
    }
    
//    private static BigInteger decrypt(Pair cipher, Point _base){
//        Point item1 = _base; 
//    }
    
    public static void main(String[] args) {
        //private key a 64 bits in size
        
        Point base = new Point(new BigInteger("11245"));
        GeneratePublicKey gen = new GeneratePublicKey(secretKey,base);
        
        //pesan yang dienkripsikan
        BigInteger m = new BigInteger("212");
        
//        Point A = new Point(BigInteger.valueOf(2),BigInteger.valueOf(4));
//        Point B = new Point(BigInteger.valueOf(2),BigInteger.valueOf(4));
        
//        A.times(BigInteger.valueOf(4));
//        A.subtract(B);
//        A.subtract(B);
//        A.subtract(B);
//        System.out.println(A);
        System.out.println(encrypt(m,base,gen.getPublicKey()));
        System.out.println(base);
        
    }

    
}
