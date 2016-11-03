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
    static JTextArea inputFileSizeField;
    static JTextArea inputFileTextField;
    
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
        inputFileSizeField = new JTextArea();
        inputFileSizeField.setBounds(20, 60, 150, 20);
        inputFileSizeField.setText("");
        
        // Input file text field
        inputFileTextField = new JTextArea();
        inputFileTextField.setBounds(20, 100, 150, 20);
        inputFileTextField.setText("");
 
        frame.add(loadFileBtn);
        frame.add(inputFileSizeField);
        frame.add(inputFileTextField);
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
            inputFileSizeField.setText(String.valueOf(bfa.size()) + " byte");
//            inputFileTextField.setText(Files.readAllBytes(pathObject).toString());
            return selectedFile;
        } else {
            return null;
        }
    }
    
}
