import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Bin bin;
    private final int DELAY = 10;
    private int bottleDelay = 0;
    private ArrayList<Bottle> bottles = new ArrayList<Bottle>();
    private int score = 0;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        bin = new Bin();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(bin.getImage(), bin.getX(),
                bin.getY(), this);

        for (Bottle bottle : bottles) {

            g2d.drawImage(bottle.getImage(), bottle.getX(),
                    bottle.getY(), this);
        }

        points(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        bin();
        bottle();

        detectCollision();

        repaint();
    }

    private void bin() {

        bin.move();

        repaint(bin.getX()-1, bin.getY()-1,
                bin.getWidth()+2, bin.getHeight()+2);
    }
    private void bottle() {

        bottleDelay += 10;

        if(bottleDelay == 1000){
            bottles.add(new Bottle((int) (Math.random()*501),(int) (Math.random()*3)));
            bottleDelay = 0;
        }

        for (int i = 0; i < bottles.size(); i++) {

            Bottle bottle = bottles.get(i);

            if (bottle.isVisible()) {

                bottle.move();
            } else {

                bottles.remove(i);
                score -= 10;
            }
        }

    }

    private void points(Graphics g) {

        Font font = new Font("Arial", Font.BOLD, 15);

        g.setColor(Color.WHITE);
        g.setFont(font);

        g.drawString("SCORE: "+score,400,50);

        g.setColor(Color.GREEN);
        g.drawString("Plastic", 400,70);

        g.setColor(Color.RED);
        g.drawString("Glass", 400,90);

        g.setColor(Color.YELLOW);
        g.drawString("Metal", 400,110);
    }

    private void detectCollision() {

        Rectangle rBin = bin.getBounds();

        for(Bottle bottle : bottles) {
            Rectangle rBottle = bottle.getBounds();

            if(rBin.intersects(rBottle)){

                if(bin.getState()==bottle.getState()){
                    bottle.setVisible(false);
                    score += 20;
                }

                else {
                    bottle.setVisible(false);
                    score -= 10;
                }
            }
        }

    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            bin.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            bin.keyPressed(e);
        }
    }
}
