package math;

public class EquationSystem {
    private final double [][] coefficient;
    private final int nbUnknown;

    public EquationSystem(int nbUnknown) {
        if (nbUnknown <= 1) throw new IllegalArgumentException("Cannot create an Equation sytem with less than 2 unknows");
        this.nbUnknown = nbUnknown;
        coefficient = new double[nbUnknown][nbUnknown+1];
    }
    public void setValues(double[][] values) {
        if (values.length != coefficient.length || values[0].length != coefficient[0].length) throw new IllegalArgumentException("The values must have the right format; [nb unknowns][nb unknowns+1]");
        System.arraycopy(values,0, coefficient, 0, nbUnknown);
    }

    /**
     * A recursive Attempt will be best.
     * The recursive will need a temp coeff arrays that change and on witch the function recurse itself
     *
     * the function have to cut off the first column by cancelling the 2 to nbUnknown line with the first
     * after the System will look like this
     * [x1  y1  ... z1  = a]
     * [0   y2  ... z2  = b]
     * [0   y3  ... z3  = c]
     * :    :   :   :      :
     * [0   yn  ... zn  = z]
     *
     * After the second like this
     * [x1  y1  ... z1  = a]
     * [0   y2   ... z2  = b]
     * [0   0   ... z3  = c]
     * :    :   :   :      :
     * [0   0   ... zn  = z]
     *
     * Until the element at i > j is 0 like this
     * [x1  y1  z1  = a]
     * [0   y2  z2  = b]
     * [0   0   z3  = c]
     *
     * Then the function will have to use the last lin trivial solution
     *
     * @return
     */
    public double[] getSolutions() {
        double[] solutions = new double[nbUnknown];

        //Set every first unknown "x" to the same value
        for (int i = 1; i < coefficient.length; i++) {
            double ratio = coefficient[0][0]/coefficient[i][0];
            //if (Double.isInfinite(ratio)) ratio = 0; TODO deal with x0 might be 0
            //Lazy way to do it would be to replace the first column by the first one that doesn't have a zero in it

            for (int j = 0; j < coefficient[i].length; j++) {
                coefficient[i][j] = coefficient[i][j] * ratio;
            }
        }

        //Subtract the first lin to each line to cancel the first unknown "x";
        for (int i = 1; i < coefficient.length; i++) {
            for (int j = 0; j < coefficient[i].length; j++) {
                coefficient[i][j] = coefficient[i][j] - coefficient[0][j];
            }
        }

        return solutions;
    }
}