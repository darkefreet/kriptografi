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
public class GeneratePublicKey {

    private BigInteger secret;
    
    public GeneratePublicKey(BigInteger _secret){
        this.secret = _secret;
    }
    
    public Point Generate(BigInteger x){
        
        return new Point(BigInteger.ZERO,BigInteger.ZERO);
    }
}
