/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elipticcurve;
import java.math.BigInteger;

/**
 *
 * @author Hp
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //private key a 256 bits in size
        BigInteger secretKey = new BigInteger("54934800194295125130027198403007499669433624690246747465573904234214271660537");
        
        Point A = new Point(new BigInteger("2"),new BigInteger("4"));
        Point B = new Point(new BigInteger("2"),new BigInteger("4"));
        
        A.add(B);
        System.out.println(A);
        A.subtract(B);
        System.out.println(A);
        
        A.times(new BigInteger("4"));
        A.subtract(B);
        A.subtract(B);
        A.subtract(B);
        System.out.println(A);
        
        GeneratePublicKey gen = new GeneratePublicKey(secretKey);
        
    }
    
}
