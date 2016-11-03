/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elipticcurve;

import java.math.BigInteger;

/**
 *
 * @author ivan
 */
public class Tonelli {

    private final BigInteger ZERO = BigInteger.ZERO;
    private final BigInteger ONE = BigInteger.ONE;
    private final BigInteger TWO = new BigInteger("2");
    private final BigInteger FOUR = new BigInteger("4");
    
    public BigInteger legendre (BigInteger a, BigInteger p) {
        return a.modPow(p.subtract(ONE).divide(TWO), p);
        
    }
    
    public BigInteger tonelli (BigInteger n, BigInteger p) {
        assert(legendre(n, p).compareTo(ONE) == 0); // asersi
        BigInteger q = p.subtract(ONE);
        BigInteger s = ZERO;

        while(q.mod(TWO).compareTo(ZERO) == 0) {
            q = q.divide(TWO);
            s = s.add(ONE);
        }
        
        if (s.compareTo(ONE) == 0) {
            return n.modPow(p.add(ONE).divide(FOUR), p);
        }
        
        BigInteger z = TWO;
        while ((z.compareTo(p) == -1) || (z.compareTo(p) == 0)) {
            if (p.subtract(ONE).compareTo(legendre(z, p)) == 0) {
                break;
            }
            z = z.add(ONE);
        }
        
        BigInteger c = z.modPow(q, p);
        BigInteger r = n.modPow(q.add(ONE).divide(TWO), p);
        BigInteger t = n.modPow(q, p);
        BigInteger m = s;
        BigInteger t2 = ZERO;
        int count  = 0;
    
        
        while (t.subtract(ONE).mod(p).compareTo(ZERO) != 0) {
            t2 = t.multiply(t).mod(p);
            
            BigInteger i = ONE;
            while ((i.compareTo(m) == -1) || (i.compareTo(m) == 0)) {
                if (t2.subtract(ONE).mod(p).compareTo(ZERO) == 0) {
                    break;
                }
                t2 = t2.multiply(t2).mod(p);
                i = i.add(ONE);
            }
            System.out.println("tes " + count);
            BigInteger b = c.modPow(ONE.shiftLeft(m.subtract(i).subtract(ONE).intValue()), p);
            r = r.multiply(b).mod(p);
            c = b.multiply(b).mod(p);
            t = t.multiply(c).mod(p);
            m = i;     
            count++;
        }
        
        return r;
    }
    
}
