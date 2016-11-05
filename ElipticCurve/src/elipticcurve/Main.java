/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elipticcurve;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Scanner;

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
    
    public static String toHex(String arg) {
        return String.format("%040x", new BigInteger(1, arg.getBytes(/*YOUR_CHARSET?*/)));
    }
    
    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        System.out.println(bytes.length);
        buffer.put(bytes);
        buffer.flip();//need flip 
        return buffer.getLong();
    }
    
    private static Pair encrypt(BigInteger m, Point _base, Point publicKey){
        
        Point pM = new Point(m);
        Point A = new Point(_base.x,_base.y);
        A.times(k);
        
        Point B = new Point(publicKey.x,publicKey.y);
        
        B.times(k);
        B.add(pM);
        
        return new Pair(A,B);
    }
    //only for max 64 bit number
    private static String getString(BigInteger big){
        String ret = "";
        long z = big.longValue();
        ret = new String(longToBytes(z));
        System.out.println(longToBytes(z).length);
        return ret;
    }
    
    private static BigInteger backToBig(String st){
        BigInteger ret = new BigInteger(st.getBytes());
        return ret;
    }
    
    private static BigInteger decrypt(Pair cipher, Point _base){
        
        Point item1 = new Point(_base.x,_base.y);
        item1.times(k);
        item1.times(secretKey);
        
        Point item2 = new Point(cipher.B.x,cipher.B.y);
        item2.subtract(item1);
        return item2.x;
        
    }
    
    private static String encryptString(String plainteks, Point base, Point publicKey) throws UnsupportedEncodingException{
        String[] splitBy4Chars = plainteks.split("(?<=\\G....)");
        String encryptedText = "";
        int count  = 0;
        for (int i = 0; i < splitBy4Chars.length; i++) {
            String str = splitBy4Chars[i];
            int strLength = str.length();
            if (strLength < 4) {
                for (int j = 0; j < (4 - strLength); j++) {
                    str += " ";
                }
            }
            BigInteger m = new BigInteger(str.getBytes());
            Pair encryptedPair = encrypt(m,base,publicKey);
            if(count == 0){
                BigInteger X = encryptedPair.A.x;
                BigInteger Y = encryptedPair.A.y;
//                System.out.println(X.toByteArray());
                String cipherText = getString(X);
                System.out.println(backToBig(cipherText));
//                System.out.println("----batas");
//                for(int l = 0; l<cipherX.length();l++){
//                    System.out.println((int)cipherX.charAt(l));
//                }
//                System.out.println("----batas");
                encryptedText += getString(X);
                encryptedText += getString(Y);
                count++;
            }
            encryptedText += getString(encryptedPair.B.x);
            encryptedText += getString(encryptedPair.B.y);
        }
        return encryptedText;
    }
    
    private static String decryptString(String cipherteks,Point base){
        String[] splitBy8Chars = cipherteks.split("(?<=\\G........)");
        String keyA = splitBy8Chars[0];
//        
//        System.out.println("----batas");
//        for(int l = 0; l<keyA.length();l++){
//            System.out.println((int)keyA.charAt(l));
//        }
//            
//            System.out.println("----batas");
        String keyB = splitBy8Chars[1];
        String plainteks = ""; 
        System.out.println("--decrypt");
        System.out.println(new BigInteger(keyA.getBytes()));
        System.out.println(new BigInteger(keyB.getBytes()));
        for(int i = 2; i<splitBy8Chars.length; i+=2){
            String cipherLeft = splitBy8Chars[i];
            String cipherRight = splitBy8Chars[i+1];
            Pair cipher = new Pair(new Point(new BigInteger(keyA.getBytes()),new BigInteger(keyB.getBytes())),new Point(new BigInteger(cipherLeft.getBytes()),new BigInteger(cipherRight.getBytes())));
   
            System.out.println(cipher);
            BigInteger plain = decrypt(cipher,base);
            plainteks += new String(plain.toByteArray());
        }
        return plainteks;
    }
    
    public static void main(String[] args) throws IOException {
        //private key a 64 bits in size
        Scanner s = new Scanner(System.in);
        
        Point base = new Point(new BigInteger("11245"));
        GeneratePublicKey gen = new GeneratePublicKey(secretKey,base);
        String plain = "abcde";
        String cipher = encryptString(plain,base,gen.getPublicKey());
        System.out.println(decryptString(cipher,base));
//        
//        System.out.print("Masukkan path: ");
//        String pathStr = s.nextLine();
//        Path pathObject = FileSystems.getDefault().getPath(pathStr);
//        BasicFileAttributes bfa = Files.readAttributes(pathObject, BasicFileAttributes.class);
//        System.out.println("Ukuran file: " + bfa.size() + " bytes");
//        
//        byte[] bytes = Files.readAllBytes(pathObject);
//        StringBuilder buffer = new StringBuilder(); 
//        for(int i = 0; i < bytes.length; i++){
//            buffer.append((char)bytes[i]);
//        }
//        String fileString = buffer.toString();
//        
        
        
        
        //pesan yang dienkripsikan
        
//        Point A = new Point(BigInteger.valueOf(2),BigInteger.valueOf(4));
//        Point B = new Point(BigInteger.valueOf(2),BigInteger.valueOf(4));
        
//        A.times(BigInteger.valueOf(4));
//        A.subtract(B);
//        A.subtract(B);
//        A.subtract(B);
//        System.out.println(A);
//        System.out.println(decrypt(,base));
        
    }

    
}
