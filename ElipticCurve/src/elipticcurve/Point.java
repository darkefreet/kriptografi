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
public class Point implements java.io.Serializable {
    public BigInteger x,y;
    public boolean isInfinite;
    
    //dengan menggunakan persamaan garis y^2 = (x^3 - ax + b)mod p
    private BigInteger a = new BigInteger("288937966341008974394958833000411530289");
    private BigInteger b = new BigInteger("295373872160112650229366291117588408953");
    private BigInteger p = new BigInteger("105139298820387285020279308031635816026384252718363932740720768800893694918643");
    
    public Point(BigInteger _x, BigInteger _y){
        this.x = _x;
        this.y = _y;
        this.isInfinite = false;
    }
    
    private BigInteger countGradient(Point A){
        return this.y.subtract(A.y).multiply( this.x.subtract(A.x).mod(this.p).modInverse(this.p)).mod(this.p);
    }
    
    public void add(Point A){
        if(A.x.compareTo(this.x) == 0 && A.y.compareTo(this.y) == 0){
            doubles();
        }else if(this.isInfinite && A.isInfinite){
            this.x = BigInteger.ZERO;
            this.y = BigInteger.ZERO;
        }else if(this.isInfinite && !A.isInfinite){
            //titik A dicopy ke titik ini
            this.x = A.x;this.y = A.y; this.isInfinite = A.isInfinite;
        }else if(!this.isInfinite && A.isInfinite){
            //tidak terjadi apa-apa
            //apabila x ada pada nilai sama dan y berbeda, maka tidak akan ada titik potong
        }else if(A.x.compareTo(this.x) == 0 && A.y.compareTo(this.y) != 0){
            this.x = BigInteger.ZERO; this.y = BigInteger.ZERO; this.isInfinite = true;
        }else{
            
            BigInteger gradient = countGradient(A);
            BigInteger oldX = this.x;
            BigInteger oldY = this.y;
            BigInteger newX = gradient.pow(2).subtract(oldX).subtract(A.x).mod(this.p);
            BigInteger newY = gradient.multiply(oldX.subtract(newX)).subtract(oldY).add(this.p).mod(this.p);
            
            this.x = newX;
            this.y = newY;
  
        }
    }
    
    public void subtract(Point A){
        Point dummy = new Point(A.x , A.y.multiply(new BigInteger("-1")).mod(this.p));
        add(dummy);
    }
    
    public void doubles(){
        BigInteger gradient = this.x.multiply(this.x).multiply(new BigInteger("3")).add(this.a).multiply(new BigInteger("2").multiply(this.y).modInverse(this.p)).mod(this.p);
	BigInteger oldX = this.x;
        this.x = gradient.multiply(gradient).subtract(new BigInteger("2").multiply(this.x)).mod(this.p);		
	this.y = gradient.multiply(oldX.subtract(this.x)).subtract(this.y).mod(this.p);
    }
    
    public void times(BigInteger A){
        if(A.compareTo(BigInteger.ZERO)==0){
           this.x = BigInteger.ZERO;
           this.y = BigInteger.ZERO;
           this.isInfinite = false;
        }else if(A.compareTo(BigInteger.ONE)==0){
           //do nothing
        }else{
            A = A.subtract(BigInteger.ONE);
            Point B = new Point(this.x,this.y);
            for(BigInteger i = BigInteger.ZERO;i.compareTo(A)==-1;i=i.add(BigInteger.ONE)){
                add(B);
            }
        }
    }
    
    @Override
    public String toString(){
        return "["+x+" , "+y+"]";
    }
    
}
