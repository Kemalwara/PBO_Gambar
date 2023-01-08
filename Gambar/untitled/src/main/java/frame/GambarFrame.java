package frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class GambarFrame extends JFrame{
    private JPanel mainPanel;
    private JButton gambarButton;
    private JLabel lbFoto;
    JFileChooser fChooser = new JFileChooser();

    private BufferedImage resizeImage(BufferedImage originalImage, int type){
        int IMG_HEIGHT = 0;
        int IMG_WIDTH = 0;
        BufferedImage resizeImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizeImage.createGraphics();
        g.drawImage(originalImage, 8, 8, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        return resizeImage;
    }

    public GambarFrame(){
        gambarButton.addActionListener(e -> {
            FileFilter filter = new FileNameExtensionFilter("Image Files", "jpg","png", "jpeg");
            fChooser.setFileFilter(filter);
            BufferedImage img = null;
            int result = fChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fChooser.getSelectedFile();
               // img = ImageIO.read(file);
                //int type = img.getType() == 0? BufferedImage.TYPE_INT_ARGB : img.getType();
                lbFoto.setIcon(new ImageIcon(String.valueOf(file)));
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e){
                init();
            }
        });
        init();
    }
    public void init() {
        setContentPane(mainPanel);
        setTitle("Input Data");
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public BufferedImage getBufferedImage(Blob imageBlob){
        InputStream binaryStream = null;
        BufferedImage b = null;
        try {
            binaryStream = imageBlob.getBinaryStream();
            b = ImageIO.read(binaryStream);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return b;
    }
}
