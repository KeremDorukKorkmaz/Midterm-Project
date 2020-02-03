import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bin {
    private int dx;
    private int x = 0;
    private int y = 0;
    private int w;
    private int h;
    private Image image[] = new Image[3];
    private int state;

    public Bin() {

        loadImage();
    }

    private void loadImage() {

        image[0] = (new ImageIcon("src/Images/bin_1.png")).getImage();
        image[1] = (new ImageIcon("src/Images/bin_2.png")).getImage();
        image[2] = (new ImageIcon("src/Images/bin_3.png")).getImage();

        state = 0;

        w = image[0].getWidth(null);
        h = image[0].getHeight(null);

        y = 500 - h;
    }

    public void move() {

        x += dx;
        if(x<-170){
            x = -170;
        }
        if(x>670){
            x = 670;
        }
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }

    public int getWidth() {

        return w;
    }

    public int getHeight() {

        return h;
    }

    public int getState(){
        return state;
    }

    public Image getImage() {

        return image[state];
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -10;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 10;
        }

        if(key == KeyEvent.VK_SPACE) {
            state++;
            if(state == 3){
                state = 0;
            }
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
}
