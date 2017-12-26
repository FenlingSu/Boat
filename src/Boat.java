import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

class Boat extends JLabel implements Runnable {
    private int frmH, frmW, x, y, r1, r;
    private ImageIcon[] imgIcon = {new ImageIcon("boat.png"),new ImageIcon("boat2.png"),new ImageIcon("boat3.png")};
    private Random rand = new Random();
    private Timer t1;

    public Boat(MainFrame frm, int frmH, int frmW){
        this.frmH = frmH;
        this.frmW = frmW;
        x = rand.nextInt(frmW-100);
        y = rand.nextInt(frmH-100);
        this.setIcon(imgIcon[r1=rand.nextInt(3)]);
        this.setBounds( x, y, this.getIcon().getIconWidth(), this.getIcon().getIconHeight() );
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frm.setSelectedBoat(Boat.this);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Boat.this.setIcon(imgIcon[r1]);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Boat.this.setIcon(imgIcon[r1]);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
    public void movingShip(int x1, int y1){
        System.out.println(">>>>" + x1 + ", " + y1);
        t1 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double m = (double)( y1-y )/(double)( x1-x );
                System.out.println("m = " + m);
                int x2 = 0 , y2 = 0;
                if(x1 > x){
                    x2 = x+10;
                    if(x2 > x1){
                        x2 = x1;
                    }
                }else {
                    x2 = x-10;
                    if(x2 < x1){
                        x2 = x1;
                    }
                }
                y2 = y + (int)(( x2 -x ) * m);
                Boat.this.setLocation(x2,y2);
                System.out.println("-->"+ x2 + ", "+ y2);
                x = x2;
                y = y2;
                if(x2 == x1){
                    t1.stop();
                }
            }
        });
        t1.start();
    }

    @Override
    public void run(){
        this.repaint();
    }
}
