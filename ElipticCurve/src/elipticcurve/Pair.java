/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elipticcurve;

/**
 *
 * @author G74SX-PC
 */
public class Pair {
    public Point A;
    public Point B;
    public Pair(Point _A,Point _B){
        this.A = new Point(_A.x,_A.y);
        this.B = new Point(_B.x,_B.y);
    }
    
    @Override
    public String toString(){
        return "<"+A+" , "+B+">";
    }
}
