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
    Double x1, y1, x2, y2;

    public Grapher(Double x1, Double x2, Double y1, Double y2) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(// X axis
                normalizeX(x1), normalizeY((y2+y1)/2),
                normalizeX(x2), normalizeY((y2+y1)/2)
        );
        g.drawLine(
                normalizeX((x1+x2)/2), normalizeY(y1),
                normalizeX((x1+x2)/2), normalizeY(y2)
        );
        g.setColor(Color.BLUE);
        for (IFunction f : functions) {
            for (int i = 0; i < this.getSize().width; i++) {
                g.drawLine(
                        i, normalizeY(-f.evaluate(unNormalizeX(i)+x1)),
                        i + 1, normalizeY(-f.evaluate(unNormalizeX(i + 1)+x1))
                );
            }
        }

    }

    public void add(IFunction... f) {
        functions.addAll(Arrays.asList(f));
    }

    public void remove(IFunction f) {
        functions.remove(f);
    }

    public int normalizeX(double value) {
        return (int) ((value+x2) * this.getSize().width / (x2 - x1));
    }

    public int normalizeY(double value) {
        return (int) ((value+y2) * this.getSize().height / (y2 - y1));
    }

    public double unNormalizeX(int value) {
        return (value * (x2 - x1) / (double) this.getSize().width);
    }

    public double unNormalizeY(int value) {
        return (value * (y2 - y1) / (double) this.getSize().height);
    }
}