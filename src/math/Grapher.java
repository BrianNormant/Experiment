package math;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class who graphe a function with JPanel Graphics
 */
public class Grapher extends JPanel {
    private final ArrayList<IFunction> functions = new ArrayList<>();
    double x1, y1, x2, y2;

    public Grapher(int x1, int x2,int y1,int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(// X axis
                normalizeX(x1),normalizeY((y2-y1)/2),
                normalizeX(x2), normalizeY((y2-y1)/2)
        );
        g.drawLine(
                normalizeX((x1-x2)/2),normalizeY(0),
                normalizeX((x1-x2)/2),normalizeY(y1)
        );

        for (IFunction f : functions) {
            for (int i = 0; i < this.getSize().width; i++) {
                g.drawLine(
                            i,normalizeY(f.evaluate(unNormalizeX(i))),
                        i+1,normalizeY(f.evaluate(unNormalizeX(i+1)))
                );
            }
        }

    }
    public void add(IFunction... f){
        functions.addAll(Arrays.asList(f));
    }
    public void remove(IFunction f) {
        functions.remove(f);
    }

    public int normalizeX(double value) {
        return (int)(value*this.getSize().width/x2-x1);
    }
    public int normalizeY(double value) {
        return -(int)(value*this.getSize().height/y2-y1);
    }
    public double unNormalizeX(int value) {
        return (value*(x2-x1)/(double)this.getSize().width)-x1;
    }
    public double unNormalizeY(int value) {
        return (-value*(y2-y1)/(double)this.getSize().height)-y1;
    }
    public int[] placeCoord(double x, double y) {
        return new int[]{
                normalizeX(x),//x
                normalizeY(y)//y
        };
    }
}
