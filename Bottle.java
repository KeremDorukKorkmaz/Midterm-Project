import java.awt.*;
import javax.swing.*;

public class Bottle {

    private int x;
    private int y;
    private int w;
    private int h;
    private boolean visible;
    private Image bottle[] = new Image[3];
    private int state;

    public Bottle(int x, int state) {

        this.x = x;
        this.y = 0;
        visible = true;
        this.state = state;

        loadImage();
    }

    protected void loadImage() {

        bottle[0] = (new ImageIcon("src/Images/bottle_1.png")).getImage();
        bottle[1] = (new ImageIcon("src/Images/bottle_2.png")).getImage();
        bottle[2] = (new ImageIcon("src/Images/bottle_3.png")).getImage();

        w = bottle[0].getWidth(null);
        h = bottle[0].getHeight(null);

    }

    public Image getImage() {
        return bottle[state];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getState(){
        return state;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

    public void move() {

        y += 2;

        if (y > 500) {
            visible = false;
        }
    }
}
