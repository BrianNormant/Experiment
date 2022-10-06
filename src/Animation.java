import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.*;

public class Animation extends JFrame implements Runnable{
    public static void main(String[] args) {
        Animation anim = new Animation(1);
    }
    private final double speed;
    private final AnimationPane anim = new AnimationPane();

    Animation(double speed) {
        super();
        this.speed = speed;
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setVisible(true);
        super.setResizable(true);
        super.setLayout(new FlowLayout());
        super.add(anim);
        super.pack();
        super.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                anim.rect = new Rectangle(e.getX()-10-50/2+170,e.getY()-35-50/2,200,200);
                anim.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                anim.changeColor();
                anim.repaint();
            }
        });
        super.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'c') run();
                System.out.println("starting rotation");
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    int timeToRotate = 0;
    public void run() {
        anim.rotate();
        anim.changeColor();
        try {
            wait(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        timeToRotate++;
        if (timeToRotate < 8) run();
        else {
            timeToRotate = 0;
            return;
        }
    }

    static class AnimationPane extends JPanel {
        private static final ArrayList<Color> colors = new ArrayList<Color>(List.of(new Color[]{BLACK, YELLOW, GREEN, RED}));
        private volatile int colorIndex = 0;
        private volatile double rotation = -Math.PI /8;
        AnimationPane() {
            super();
            super.setPreferredSize(new Dimension(1920,1080));
            this.setBorder(BorderFactory.createLineBorder(BLACK));
        }


        private Rectangle rect = new Rectangle(50,50,50,100);
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.rotate(rotation);
            g2d.setColor(colors.get(colorIndex));
            g2d.fill(rect);
        }
        public void changeColor() {
            colorIndex = (colorIndex+1 == colors.size())?0:colorIndex+1;
        }
        public void rotate() {
            rotation += Math.PI/8;
            repaint();
        }
    }
}
