/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

/**
 *
 * @author ivan
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.imageio.ImageIO;
 
public class AddWatermarkGUI {
 
    static int imgPanelWidth = 300;
    static int imgPanelHeight = 300;
    static int keyWidth = 50;
    static JFrame frame;
    static BufferedImage imgOriginal; // original
    static BufferedImage imgWatermark; // watermark
    static JTextField keyField;
    static JPanel imagePanel;
    static JPanel watermarkImagePanel;
    static JPanel watermarkedImagePanel;
    static JButton loadFileBtn;
    static JButton loadWatermarkFileBtn;
    static JButton addWatermarkBtn;

    public static void main(String[] arguments) throws IOException {
        // Main window
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("JPanel Example");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Image panel
        imagePanel = new JPanel();
        imagePanel.setBounds(20,50,imgPanelWidth,imgPanelHeight);
        imagePanel.setBackground(Color.white);

        // Watermarked Image Panel
        watermarkImagePanel = new JPanel();
        watermarkImagePanel.setBounds(2 * 20 + imgPanelWidth,50,imgPanelWidth,imgPanelHeight);
        watermarkImagePanel.setBackground(Color.white);

        // Watermarked Image Panel
        watermarkedImagePanel = new JPanel();
        watermarkedImagePanel.setBounds(3 * 20 + 2 * imgPanelWidth,50,imgPanelWidth,imgPanelHeight);
        watermarkedImagePanel.setBackground(Color.white);

        // Load Image Button
        loadFileBtn = new JButton("Load Image");
        loadFileBtn.setBounds(20, 20, 150, 20);
        loadFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                imgOriginal = selectAndLoadImage(imagePanel);
            }
        });

        // Load Watermark Image Button
        loadWatermarkFileBtn = new JButton("Load Watermark Image");
        loadWatermarkFileBtn.setBounds(2 * 20 + imgPanelWidth, 20, 150, 20);
        loadWatermarkFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                imgWatermark = selectAndLoadImage(watermarkImagePanel);
            }
        });
        
        // Key
        keyField = new JTextField();
        keyField.setBounds(3 * 20 + 2 * imgPanelWidth, 20, 150, 20);
        
        // Load Watermark Image Button
        addWatermarkBtn = new JButton("Add Watermark");
        addWatermarkBtn.setBounds(4 * 20 + keyWidth + 2 * imgPanelWidth, 20, 150, 20);
        addWatermarkBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    addWatermark();
                } catch (IOException ex) {
                    Logger.getLogger(AddWatermarkGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // add the Jpanel to the main window
        frame.add(loadFileBtn);
        frame.add(loadWatermarkFileBtn);
        frame.add(keyField);
        frame.add(addWatermarkBtn);
        frame.add(imagePanel);
        frame.add(watermarkImagePanel);
        frame.add(watermarkedImagePanel);
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
                Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedImage cloneImg = ImageHelper.deepImageClone(img);
            cloneImg = ImageHelper.getScaledImage(img, imgPanelWidth, imgPanelHeight);
            loadImage(imagePanel, cloneImg);
            return img;
        } else {
            return null;
        }
    }
    
    public static void addWatermark() throws IOException {
        String key = keyField.getText().trim();
        Thread execute = new Thread() {
            public void run() {        
                // Add watermark
                BufferedImage watermarkedImage = ImageHelper.addWatermark(imgOriginal, imgWatermark, key);

                try {
                    // Save watermarked image
                    ImageHelper.saveImage(watermarkedImage, "png", "watermarked-image.png");
                } catch (IOException ex) {
                    Logger.getLogger(AddWatermarkGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                BufferedImage watermarkedImageClone = ImageHelper.deepImageClone(watermarkedImage);
                watermarkedImageClone = ImageHelper.getScaledImage(watermarkedImageClone, imgPanelWidth, imgPanelHeight);
                loadImage(watermarkedImagePanel, watermarkedImageClone);
            }  
        };

        if ((imgOriginal != null) && (imgWatermark != null) && (key.length() > 0)) {
            execute.run();
        }
    }
  
}   
