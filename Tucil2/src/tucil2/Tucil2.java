/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tucil2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author ivanandrianto
 */
public class Tucil2 {

    private static void singleAlphabetAnalysis(String text, HashMap<Integer, Integer> counter) {
        for (int i = 0; i < text.length(); i++) {
            int asciiCode = (int)text.charAt(i);
            if ((asciiCode >= 97) && (asciiCode <= 122)) {
                counter.put(asciiCode, counter.get(asciiCode) + 1);
            }
        }
    }
    
    private static void doubleAlphabetAnalysis(String text, HashMap<String, Integer> counter) {
        for (int i = 0; i < text.length() - 1; i++) {
            int asciiCode1 = (int)text.charAt(i);
            int asciiCode2 = (int)text.charAt(i + 1);
            if ((asciiCode1 >= 97) && (asciiCode1 <= 122) && (asciiCode2 >= 97) && (asciiCode2 <= 122)) {
                String key = String.valueOf((char)asciiCode1) + String.valueOf((char)asciiCode2);
                System.out.println(key);
                if (counter.containsKey(key)) {
                    counter.put(key, counter.get(key) + 1);
                } else {
                    counter.put(key, 1);
                }
            }
        }
    }
    
    private static void tripleAlphabetAnalysis(String text, HashMap<String, Integer> counter) {
        for (int i = 0; i < text.length() - 2; i++) {
            int asciiCode1 = (int)text.charAt(i);
            int asciiCode2 = (int)text.charAt(i + 1);
            int asciiCode3 = (int)text.charAt(i + 2);
            if ((asciiCode1 >= 97) && (asciiCode1 <= 122) && (asciiCode2 >= 97) && (asciiCode2 <= 122) && (asciiCode3 >= 97) && (asciiCode3 <= 122)) {
                String key = String.valueOf((char)asciiCode1) + String.valueOf((char)asciiCode2) + String.valueOf((char)asciiCode3);
                if (counter.containsKey(key)) {
                    counter.put(key, counter.get(key) + 1);
                } else {
                    counter.put(key, 1);
                }
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String test = "aabbbb";
        test = test.replaceAll("b", "B");
        System.out.println(test);
        
        // Counter declaration and initialization
        HashMap<Integer, Integer> counterSingleAlphabet;
        counterSingleAlphabet = new HashMap<Integer, Integer>();
        HashMap<String, Integer> counterDoubleAlphabet;
        counterDoubleAlphabet = new HashMap<String, Integer>();
        HashMap<String, Integer> counterTripleAlphabet;
        counterTripleAlphabet = new HashMap<String, Integer>();
        
        for (int i = 97; i <= 122; i++) {
            counterSingleAlphabet.put(i, 0);
        }
        String cipherText = helpers.File.read("C:\\\\Users\\ivanandrianto\\Documents\\cipher1.txt");
        singleAlphabetAnalysis(cipherText, counterSingleAlphabet);
        doubleAlphabetAnalysis(cipherText, counterDoubleAlphabet);
        tripleAlphabetAnalysis(cipherText, counterTripleAlphabet);
        
        //counter =   counter.entrySet().stream().sorted(Entry.comparingByValue()).collect(toHashMap(Entry::getKey, Entry::getValue, (e1,e2) -> e1, LinkedHashMap::));
        counterSingleAlphabet = sortHashMapByValues(counterSingleAlphabet);
        counterDoubleAlphabet = sortHashMapByValues2(counterDoubleAlphabet);       
        counterTripleAlphabet = sortHashMapByValues2(counterTripleAlphabet);
        
        //Print Counter
        System.out.println("------------------SINGLE------------------");
        printHashMapWithChar(counterSingleAlphabet);
        System.out.println("------------------DOUBLE------------------");
        printHashMap(counterDoubleAlphabet);
        System.out.println("------------------TRIPLE------------------");
        printHashMap(counterTripleAlphabet);
        
        HashMap<Integer, Integer> substitution;
        substitution = new HashMap<Integer, Integer>();
        boolean finish = false;
        while (!finish) {
           System.out.print("0. exit\n1.Tambah Substitusi\n2.Hapus Substitusi\n3.Cetak CipherText\n4.Cetak SubsitusiText\n5.Cetak Subsitusi");
           Scanner s = new Scanner(System.in);
           int option = s.nextInt();
           switch (option) {
               case 0:
                    finish = true;
                    break;
                case 1:
                    System.out.print("Masukkan char yang ingin disubstitusi: " );
                    char original = s.next().charAt(0);
                    System.out.print("Masukkan char yang untuk mensubstitusi: " );
                    char replacement = s.next().charAt(0);
                    substitution.put((int)original, (int)replacement);
                    printSubstituedText(cipherText, substitution);
                    break;
                case 2:
                    System.out.print("Masukkan char yang substitusinya ingin dicancel: " );
                    original = s.next().charAt(0);
                    substitution.remove((int)original);
                    printSubstituedText(cipherText, substitution);
                    break;
                case 3:
                    System.out.print(cipherText);
                    break;
                case 4:
                    printSubstituedText(cipherText, substitution);
                    break;
                case 5:
                    printSubstitutionHashMap(substitution);
                    break;
                default:
                    break;
           }
        }
    }
    
    public static void printSubstituedText (String cipherText, HashMap<Integer, Integer> substitution) {
        for(Entry<Integer, Integer> entry : substitution.entrySet()) {
            System.out.println(entry.getKey());
            char original = (char) (helpers.Number.convertIntegerToInt(entry.getKey()));
            char replacement = (char) (helpers.Number.convertIntegerToInt(entry.getValue()));
            cipherText = cipherText.replaceAll(String.valueOf(original), String.valueOf(replacement));
            // do what you have to do here
            // In your case, an other loop.
        }
        System.out.println(cipherText);
    }
    
    public static LinkedHashMap<Integer, Integer> sortHashMapByValues(
        HashMap<Integer, Integer> passedMap) {
        List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<Integer, Integer> sortedMap =
            new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            int val = valueIt.next();
            Iterator<Integer> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Integer key = keyIt.next();
                int comp1 = passedMap.get(key);
                int comp2 = val;

                if (comp1 == comp2) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }
    
    public static LinkedHashMap<String, Integer> sortHashMapByValues2(
        HashMap<String, Integer> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<String, Integer> sortedMap =
            new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            int val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                int comp1 = passedMap.get(key);
                int comp2 = val;

                if (comp1 == comp2) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }
    
    private static void printHashMap(HashMap<String, Integer> map) {
        for(Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    private static void printHashMapWithChar(HashMap<Integer, Integer> map) {
        for(Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "(" + (char)helpers.Number.convertIntegerToInt(entry.getKey()) + "):" + entry.getValue());
        }
    }
    
    private static void printSubstitutionHashMap(HashMap<Integer, Integer> map) {
        for(Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "(" + (char)helpers.Number.convertIntegerToInt(entry.getKey()) + "):" + entry.getValue() + "(" + (char)helpers.Number.convertIntegerToInt(entry.getValue()) + ")");
        }
    }
}
 