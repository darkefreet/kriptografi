/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author ivan
 */
public class SBox {
    private static final String sBoxFilePath = "sbox.txt";
    private static int[][] table = new int[16][16];
    
    public SBox() throws IOException {
        loadFile();
    }
    
    private void loadFile() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(sBoxFilePath));
        String line = null;
        int i = 0;
        try {
            StringBuilder sb = new StringBuilder();
            do {
                line = br.readLine().trim();
                if (line.length() > 0) {
                    String[] splitBySpace = line.split(" ");   
                    for (int j = 0; j < 16; j++) {
                        table[i][j] = Integer.parseInt(splitBySpace[j], 16);
                    }
                    i++;
                }
            } while ((line != null)  && (i < 16));
        } finally {
            br.close();
        }
    }
    
    public String substitute (String str) {
        String substituted = "";
//        System.out.println(str);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            String hex = Integer.toHexString(c);
            int hexs[] = new int[2];
            if (hex.length() == 1) {
                hexs[0] = 0;
                hexs[1] = Integer.parseInt(hex.substring(0, 1), 16);
            } else {
                hexs[0] = Integer.parseInt(hex.substring(0, 1), 16);
                hexs[1] = Integer.parseInt(hex.substring(1), 16);
            }
            substituted += (char)table[hexs[0]][hexs[1]];
        }
//        System.out.println("subs = " + substituted);
        return substituted;
    }
    
    public String findSub(String str){
        String substituted = "";
//        System.out.println(str);
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            int hex = (int) c;
            
            int foundi = 0;
            int foundj = 0;
            //Find hex in matrix
            find:{
                for(int j = 0; j<table.length;j++){
                    for(int k = 0; k<table[j].length;k++){
                        if(table[j][k]==hex){
                            foundi=j;
                            foundj=k;
                            break find;
                        }
                    }
                }
            }
            int mask = 0x00 | foundi;
            mask = mask << 4;
            int res = mask | foundj;
            substituted+=(char)res;
        }
//        System.out.println("decrypt subs = " + substituted);
        return substituted;
    }
}
