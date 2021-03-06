/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tucil1;

import helpers.GroupButtonUtils;
import algoritma.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author ivanandrianto
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cipherTextFormatOptions = new javax.swing.ButtonGroup();
        algorithmOptions = new javax.swing.ButtonGroup();
        inputTextLabel = new javax.swing.JLabel();
        cipherTextFormatAsItIs = new javax.swing.JRadioButton();
        cipherTextFormatNoSpace = new javax.swing.JRadioButton();
        cipherTextFormat5Words = new javax.swing.JRadioButton();
        keyLabel = new javax.swing.JLabel();
        keyField = new javax.swing.JTextField();
        encryptButton = new javax.swing.JButton();
        decryptButton = new javax.swing.JButton();
        resultPane = new javax.swing.JScrollPane();
        result = new javax.swing.JTextArea();
        saveButton = new javax.swing.JButton();
        algorithmVigenere26 = new javax.swing.JRadioButton();
        algorithmVigenere256 = new javax.swing.JRadioButton();
        algorithmLabel = new javax.swing.JLabel();
        algorithmModifiedVigenere = new javax.swing.JRadioButton();
        Playfair = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        loadFileButton = new javax.swing.JButton();
        inputPane = new javax.swing.JScrollPane();
        input = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        inputTextLabel.setText("Masukkan text:");

        cipherTextFormatOptions.add(cipherTextFormatAsItIs);
        cipherTextFormatAsItIs.setSelected(true);
        cipherTextFormatAsItIs.setText("Apa adanya");

        cipherTextFormatOptions.add(cipherTextFormatNoSpace);
        cipherTextFormatNoSpace.setText("Tanpa spasi");

        cipherTextFormatOptions.add(cipherTextFormat5Words);
        cipherTextFormat5Words.setText("5 Huruf");

        keyLabel.setText("Masukkan kunci:");

        encryptButton.setText("Encrypt");
        encryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptButtonActionPerformed(evt);
            }
        });

        decryptButton.setText("Decrypt");
        decryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptButtonActionPerformed(evt);
            }
        });

        result.setColumns(20);
        result.setRows(5);
        resultPane.setViewportView(result);

        saveButton.setText("Save");
        saveButton.setToolTipText("");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        algorithmOptions.add(algorithmVigenere26);
        algorithmVigenere26.setSelected(true);
        algorithmVigenere26.setText("Vigenere 26");
        algorithmVigenere26.setToolTipText("");
        algorithmVigenere26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algorithmVigenere26ActionPerformed(evt);
            }
        });

        algorithmOptions.add(algorithmVigenere256);
        algorithmVigenere256.setText("Vigenere 256");
        algorithmVigenere256.setToolTipText("");
        algorithmVigenere256.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algorithmVigenere256ActionPerformed(evt);
            }
        });

        algorithmLabel.setText("Algoritma");

        algorithmOptions.add(algorithmModifiedVigenere);
        algorithmModifiedVigenere.setText("Modified Vigenere");
        algorithmModifiedVigenere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algorithmModifiedVigenereActionPerformed(evt);
            }
        });

        algorithmOptions.add(Playfair);
        Playfair.setText("Playfair");
        Playfair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayfairActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tugas Kecil 1 - BKDN (Bulu Ketek Dawa Ngruwel)");

        loadFileButton.setText("Load File");
        loadFileButton.setToolTipText("");
        loadFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFileButtonActionPerformed(evt);
            }
        });

        input.setColumns(20);
        input.setRows(5);
        inputPane.setViewportView(input);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(inputPane)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resultPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addComponent(keyField)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(algorithmLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(algorithmVigenere256)
                            .addComponent(algorithmVigenere26))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saveButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Playfair)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(algorithmModifiedVigenere)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(encryptButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(decryptButton))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(inputTextLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loadFileButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(cipherTextFormatAsItIs)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cipherTextFormatNoSpace)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cipherTextFormat5Words))
                            .addComponent(keyLabel, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputTextLabel)
                    .addComponent(loadFileButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(keyLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(keyField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decryptButton)
                    .addComponent(encryptButton)
                    .addComponent(algorithmVigenere26)
                    .addComponent(algorithmLabel)
                    .addComponent(algorithmModifiedVigenere))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Playfair)
                    .addComponent(algorithmVigenere256, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cipherTextFormatAsItIs)
                    .addComponent(cipherTextFormatNoSpace)
                    .addComponent(cipherTextFormat5Words))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resultPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void encryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptButtonActionPerformed
        // TODO add your handling code here:
        String algorithm = GroupButtonUtils.getSelectedButtonText(algorithmOptions);
        String encryptedText = encryptText(algorithm, input.getText(), keyField.getText());
        result.setText(formatText(encryptedText,GroupButtonUtils.getSelectedButtonText(cipherTextFormatOptions)));
        //result.setText("aa");
    }//GEN-LAST:event_encryptButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        String resultText = result.getText();
        if (resultText.length() > 0) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String time = dateFormat.format(new Date());
            String filename = time + ".txt";
            helpers.File.write(filename, resultText);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void decryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptButtonActionPerformed
        // TODO add your handling code here:
        String algorithm = GroupButtonUtils.getSelectedButtonText(algorithmOptions);
        String decryptedText = decryptText(algorithm, input.getText(), keyField.getText());
        result.setText(decryptedText);
    }//GEN-LAST:event_decryptButtonActionPerformed

    private void loadFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFileButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            input.setText(helpers.File.read(path));
        }
    }//GEN-LAST:event_loadFileButtonActionPerformed

    private void algorithmVigenere256ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithmVigenere256ActionPerformed
        // TODO add your handling code here:
        cipherTextFormatNoSpace.setEnabled(false);
        cipherTextFormat5Words.setEnabled(false);
        cipherTextFormatAsItIs.setSelected(true);
    }//GEN-LAST:event_algorithmVigenere256ActionPerformed

    private void algorithmModifiedVigenereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithmModifiedVigenereActionPerformed
        // TODO add your handling code here:
        cipherTextFormatNoSpace.setEnabled(false);
        cipherTextFormat5Words.setEnabled(false);
        cipherTextFormatAsItIs.setSelected(true);
    }//GEN-LAST:event_algorithmModifiedVigenereActionPerformed

    private void algorithmVigenere26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithmVigenere26ActionPerformed
        // TODO add your handling code here:
        cipherTextFormatNoSpace.setEnabled(true);
        cipherTextFormat5Words.setEnabled(true);
    }//GEN-LAST:event_algorithmVigenere26ActionPerformed

    private void PlayfairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayfairActionPerformed
        // TODO add your handling code here:
        cipherTextFormatNoSpace.setEnabled(true);
        cipherTextFormat5Words.setEnabled(true);
    }//GEN-LAST:event_PlayfairActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    private String encryptText (String type, String text, String key) {
        String encryptedText = "";
        switch(type) {
            case "Vigenere 26":
                encryptedText = Vigenere26.encrypt(text, key);
                break;
            case "Vigenere 256":
                encryptedText = Vigenere256.encrypt(text, key);
                break;
            case "Modified Vigenere":
                encryptedText = VigenereModification.encrypt(text, key);
                break;
            case "Playfair":
                Playfair pf = new Playfair();
                encryptedText = pf.encrypt(text, key);               
                break;
        }
        return encryptedText;
    }

    private String decryptText (String type, String text, String key) {
        String decryptedText = "";
        switch(type) {
            case "Vigenere 26":
                decryptedText = Vigenere26.decrypt(text, key);
                break;
            case "Vigenere 256":
                decryptedText = Vigenere256.decrypt(text, key);
                break;
            case "Modified Vigenere":
                decryptedText = VigenereModification.decrypt(text, key);
                break;
            case "Playfair":
                Playfair pf = new Playfair();
                decryptedText = pf.decrypt(text, key);
                break;
        }
        return decryptedText;
    }
    
    private String formatText (String text, String type) {
        String formattedText = "";
        switch(type) {
            case "Tanpa spasi":
                formattedText = text.replaceAll("\\s","");
                break;
            case "5 Huruf":
                String[] splittedText = text.replaceAll("\\s","").split("(?<=\\G.....)");
                for (int i = 0; i < splittedText.length - 1; i++) {
                    formattedText += splittedText[i] + " ";
                }
                formattedText += splittedText[splittedText.length - 1];
                break;
            default:
                formattedText = text;
        }
        return formattedText;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Playfair;
    private javax.swing.JLabel algorithmLabel;
    private javax.swing.JRadioButton algorithmModifiedVigenere;
    private javax.swing.ButtonGroup algorithmOptions;
    private javax.swing.JRadioButton algorithmVigenere256;
    private javax.swing.JRadioButton algorithmVigenere26;
    private javax.swing.JRadioButton cipherTextFormat5Words;
    private javax.swing.JRadioButton cipherTextFormatAsItIs;
    private javax.swing.JRadioButton cipherTextFormatNoSpace;
    private javax.swing.ButtonGroup cipherTextFormatOptions;
    private javax.swing.JButton decryptButton;
    private javax.swing.JButton encryptButton;
    private javax.swing.JTextArea input;
    private javax.swing.JScrollPane inputPane;
    private javax.swing.JLabel inputTextLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField keyField;
    private javax.swing.JLabel keyLabel;
    private javax.swing.JButton loadFileButton;
    private javax.swing.JTextArea result;
    private javax.swing.JScrollPane resultPane;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
