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

        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                System.out.print(table[x][y] + " ");
            }
            System.out.println("");
        }
    }
    
    public SBox() throws IOException {
        loadFile();
    }
}
