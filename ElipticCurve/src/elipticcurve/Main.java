/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elipticcurve;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.List;
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
    
    private static Pair encrypt(BigInteger m, Point _base, Point publicKey){
        
        Point pM = new Point(m);
        Point A = new Point(_base.x,_base.y);
        A.times(k);
        
        Point B = new Point(publicKey.x,publicKey.y);
        
        B.times(k);
        B.add(pM);
        
        return new Pair(A,B);
    }
//    //only for max 64 bit number
    private static String getString(BigInteger big){
        String ret = "";
        byte[] z = big.toByteArray();
        for(int i = 0;i<8;i++)
            ret+= (char)(z[i] & 0xFF);
        return ret;
    }
    
    private static BigInteger backToBig(String st){
        byte[] z = new byte[8];
        for(int i = 0;i<8;i++)
            z[i] = (byte)st.charAt(i);
        return new BigInteger(z);
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
//            System.out.println(encryptedPair);s
            if(count == 0){
                BigInteger X = encryptedPair.A.x;
                BigInteger Y = encryptedPair.A.y;
//                System.out.println("tes" + backToBig(getString(X)));
                encryptedText += getString(X);
                encryptedText += getString(Y);
                count++;
            }
            encryptedText += getString(encryptedPair.B.x);
            encryptedText += getString(encryptedPair.B.y);
        }
//        System.out.println(encryptedText);
        return DatatypeConverter.printHexBinary(encryptedText.getBytes());
    }
    
    private static String decryptString(String cipherteks,Point base){
        cipherteks = new String(DatatypeConverter.parseHexBinary(cipherteks));
        List<String> strings = new ArrayList<String>();
        int index = 0;
        while (index < cipherteks.length()) {
            strings.add(cipherteks.substring(index, Math.min(index + 8,cipherteks.length())));
            index += 8;
        }
        String keyA = strings.get(0);
        String keyB = strings.get(1);
        String plainteks = ""; 
//        System.out.println("--decrypt");
        for(int i = 2; i<strings.size(); i+=2){
            String cipherLeft = strings.get(i);
            String cipherRight = strings.get(i+1);
            Pair cipher = new Pair(new Point(backToBig(keyA),backToBig(keyB)),new Point(backToBig(cipherLeft),backToBig(cipherRight)));
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
        System.out.println("cipherteks " + cipher);
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
