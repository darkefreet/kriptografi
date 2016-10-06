/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static tubes.AddWatermarkGUI.frame;
import static tubes.AddWatermarkGUI.imgPanelWidth;
import static tubes.AddWatermarkGUI.keyField;
import static tubes.AddWatermarkGUI.loadFileBtn;

/**
 *
 * @author ivan
 */
public class ExtractWatermarkGUI {
    static int imgPanelWidth = 300;
    static int imgPanelHeight = 300;
    static int keyWidth = 50;
    static JFrame frame;
    static JPanel watermarkedImagePanel;
    static JPanel watermarkImagePanel;
    static JTextField keyField;
    static JTextField outputField;
    static JLabel keyLabel;
    static JLabel outputLabel;
    static JButton extractWatermarkBtn;
    static BufferedImage watermarkedImage;
    static JButton loadFileBtn;

    public static void main(String[] arguments) throws IOException {
        // Main window
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("JPanel Example");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Image panel
        watermarkedImagePanel = new JPanel();
        watermarkedImagePanel.setBounds(20,50,imgPanelWidth,imgPanelHeight);
        watermarkedImagePanel.setBackground(Color.white);
        
        // Key
        keyField = new JTextField();
        keyField.setBounds(390, 20, 100, 20);
        keyLabel = new JLabel();
        keyLabel.setText("Kunci");
        keyLabel.setBounds(340, 20, 100, 20);
        
        
        // Output
        outputField = new JTextField();
        outputField.setBounds(440, 360, 100, 20);
        outputLabel = new JLabel();
        outputLabel.setText("Output");
        outputLabel.setBounds(340, 360, 100, 20);
        
        // Watermarked Image Panel
        watermarkImagePanel = new JPanel();
        watermarkImagePanel.setBounds(340, 50, imgPanelWidth,imgPanelHeight);
        watermarkImagePanel.setBackground(Color.white);
        
        // Load Image Button
        loadFileBtn = new JButton("Load Image");
        loadFileBtn.setBounds(20, 20, 150, 20);
        loadFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                watermarkedImage = selectAndLoadImage(watermarkedImagePanel);
            }
        });

        // Load Watermark Image Button
        extractWatermarkBtn = new JButton("Extract Watermark");
        extractWatermarkBtn.setBounds(150 + keyWidth + imgPanelWidth, 20, 150, 20);
        extractWatermarkBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    extractWatermark();
                } catch (IOException ex) {
                    Logger.getLogger(AddWatermarkGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // add the Jpanel to the main window
        frame.add(loadFileBtn);
        frame.add(extractWatermarkBtn);
        frame.add(keyField);
        frame.add(keyLabel);
        frame.add(outputField);
        frame.add(outputLabel);
        frame.add(watermarkedImagePanel);
        frame.add(watermarkImagePanel);
        frame.setSize(1000, 500);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public static void loadImage(JPanel imagePanel, BufferedImage img) {
        imagePanel.removeAll();
        ImageIcon newImg = new ImageIcon (img);
        JLabel label = new JLabel(newImg);
        imagePanel.add(label);
        imagePanel.repaint();
        frame.setVisible(true);
    }
    
    public static BufferedImage selectAndLoadImage(JPanel imagePanel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            System.out.println(path);
            BufferedImage img = null;
            try {
                img = ImageHelper.loadImage(path);
            } catch (IOException ex) {
                Logger.getLogger(ExtractWatermarkGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedImage cloneImg = ImageHelper.deepImageClone(img);
            cloneImg = ImageHelper.getScaledImage(img, imgPanelWidth, imgPanelHeight);
            loadImage(imagePanel, cloneImg);
            return img;
        } else {
            return null;
        }
    }
    
    public static void extractWatermark() throws IOException {
        String key = keyField.getText().trim();
        String outputPath = outputField.getText().trim();
        Thread execute = new Thread() {
            public void run() {
                // Extract watermark
                BufferedImage extractedWatermark = ImageHelper.extractWatermark(watermarkedImage);
                
                try {
                    // Save extracted watermark
                    ImageHelper.saveImage(extractedWatermark, "png", "extracted-watermark-file.png");
                } catch (IOException ex) {
                    Logger.getLogger(ExtractWatermarkGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                //Decrypt watermark
                BufferedImage realWatermark;
                try {
                    realWatermark = ImageHelper.decryptWatermark(watermarkedImage, key);
                    BufferedImage realWatermarkClone = ImageHelper.deepImageClone(realWatermark);
                    realWatermarkClone = ImageHelper.getScaledImage(realWatermarkClone, imgPanelWidth, imgPanelHeight);

                    ImageHelper.saveImage(realWatermark, "png", outputPath);
                    
                    loadImage(watermarkImagePanel, realWatermarkClone);
                } catch (IOException ex) {
                    Logger.getLogger(ExtractWatermarkGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
        };

        if ((watermarkedImage != null) && (outputPath.length() > 0) && (key.length() > 0)) {
            execute.run();
        }
    }
}
