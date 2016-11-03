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
    private Point publicKey;
    
    public GeneratePublicKey(BigInteger _secret,Point _base){
        this.secret = _secret;
        this.publicKey = new Point(_base.x,_base.y);
        this.publicKey.times(secret);
    }
    
    public Point getPublicKey(){
        return this.publicKey;
    }
}
