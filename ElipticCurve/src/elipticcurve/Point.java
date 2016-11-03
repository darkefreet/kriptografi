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
    
    //dengan menggunakan persamaan garis y^2 = (x^3 - 12x + 13)mod 17
    private BigInteger a = new BigInteger("-12");
    private BigInteger b = new BigInteger("13");
    private BigInteger p = new BigInteger("17");
    
    public Point(BigInteger _x, BigInteger _y){
        this.x = _x;
        this.y = _y;
        this.isInfinite = false;
    }
    
    private BigInteger countGradient(Point A){
        BigInteger difY = A.y.subtract(this.y);
        System.out.println("difY = "+difY);
        BigInteger difX = A.x.subtract(this.x);
        System.out.println("difX = "+difX);
        BigInteger hasil = difY.divide(difX);
        return hasil.mod(this.p);
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
            System.out.println(gradient);
            BigInteger oldX = this.x;
            BigInteger oldY = this.y;
            this.x = gradient.pow(2).subtract(oldX).subtract(A.x).mod(this.p);
            this.y = gradient.multiply(oldX.subtract(this.x)).subtract(oldY).mod(this.p);
  
        }
    }
    
    public void subtract(Point A){
        Point dummy = new Point(A.x.multiply(new BigInteger("-1")),A.y.multiply(new BigInteger("-1")));
        add(dummy);
        System.out.println(this.x);
    }
    public void doubles(){
    
    }
    
    @Override
    public String toString(){
        return "["+x+" , "+y+"]";
    }
    
}
