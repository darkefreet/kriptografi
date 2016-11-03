/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elipticcurve;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author ivan
 */
public class GUI {
    
    static JFrame frame;
    static JButton loadFileBtn;
    static JTextArea inputFileSize;
    static JTextArea inputFileText;
    static JScrollPane inputFileTextPane;
    
    static final int frameWidth = 1200;
    static final int frameHeight = 500;
    
    public static void main(String[] arguments) throws IOException {
        // Main window
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("RATA");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Load Image Button
        loadFileBtn = new JButton("Load File");
        loadFileBtn.setBounds(20, 20, 150, 20);
        loadFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    selectFile();
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // Input file size field
        inputFileSize = new JTextArea();
        inputFileSize.setBounds(20, 60, 150, 20);
        inputFileSize.setText("");
        
        // Input file text field
        inputFileText = new JTextArea();
        inputFileText.setColumns(20);
        inputFileText.setRows(5);
        inputFileTextPane = new JScrollPane();
        inputFileTextPane.setBounds(20, 100, 150, 300);
        inputFileTextPane.setViewportView(inputFileText);
 
        frame.add(loadFileBtn);
        frame.add(inputFileSize);
        frame.add(inputFileTextPane);
        frame.setSize(frameWidth, frameHeight);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public static File selectFile() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            Path pathObject = FileSystems.getDefault().getPath(selectedFile.getPath());
            BasicFileAttributes bfa = Files.readAttributes(pathObject, BasicFileAttributes.class);
            inputFileSize.setText(String.valueOf(bfa.size()) + " byte");
            byte[] bytes = Files.readAllBytes(pathObject);
            StringBuilder buffer = new StringBuilder(); 
            for(int i = 0; i < bytes.length; i++){
                buffer.append((char)bytes[i]);
            }
            inputFileText.setText(buffer.toString());
            return selectedFile;
        } else {
            return null;
        }
    }
    
}
