import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private Container cp;
    private ImagePanel jpn = new ImagePanel();
    private JPanel toolPane = new JPanel(new GridLayout(1,2,5,5));
    private JButton jbtnAddBoat = new JButton("Add Boat");
    private JButton jbtnExit = new JButton("Exit");
    private Image mouseCursor = new ImageIcon("cursor.jpg").getImage();
    private Boat selectedBoat;
    private boolean flag = false;
    private int imgW,imgH;
    private ArrayList<Boat> boatList = new ArrayList<Boat>();
    private ArrayList<Thread> threadList = new ArrayList<Thread>();
    final Point hotSpot = new Point(0,0);
    final String name = "My Cursor";

    public MainFrame(){
        imgW = jpn.getImgWidth();
        imgH = jpn.getImgHeight();
        this.setBounds(350,100,imgW,imgH+30);
        this.setResizable(false);
        jpn.setLayout(null);
        toolPane.add(jbtnAddBoat);
        toolPane.add(jbtnExit);
        jbtnAddBoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boatList.add(new Boat(MainFrame.this, imgH, imgW));
                jpn.add(boatList.get(boatList.size()-1));
                threadList.add(new Thread(boatList.get(boatList.size()-1)));
                threadList.get(threadList.size()-1).start();
            }
        });
        jbtnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jpn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(flag){
                    selectedBoat.movingShip(e.getX(), e.getY());
                    flag = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                jpn.setCursor(getToolkit().createCustomCursor(mouseCursor, hotSpot , name));
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void setSelectedBoat (Boat boat1){
        selectedBoat = boat1;
        flag = true;
    }

    class ImagePanel extends JPanel{
        private BufferedImage image;
        private int imgW,imgH;
    public ImagePanel(){
            try {
                image = ImageIO.read(new File("img.jpg"));
            }catch (IOException ex){ }
        }
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(image,0,0,null);
        }
    public int getImgHeight(){
        return imgH;
    } public int getImgWidth(){
        return imgW;
    }

}
}

