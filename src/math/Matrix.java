package math;

public class Matrix<T> {
    //Properties
    private final Object[][] array;

    //Constructor
    public Matrix(Matrix<T> matrix) {
        array = matrix.array;
    }

    /**
     * Create a empty matrix sized by x and y
     * @param xSize number of line
     * @param ySize number of column per line
     */
    public Matrix(int xSize, int ySize) {
        this.array = new Object[xSize][ySize];
    }

    /**
     * Create a Matrix sized by x and y
     * this Matrix is full of defaultValue
     * @param xSize number of line
     * @param ySize number of column per line
     * @param defaultValue value to fill the matrix
     */
    public Matrix(int xSize, int ySize, T defaultValue) {
        this.array = new Object[xSize][ySize];
        this.fill(defaultValue);
    }

    //Getters

    /**
     * @return number of line
     */
    public int getXSize() {
        return array.length;
    }

    /**
     * @return number of column per line
     */
    public int getYSize() {
        return array[0].length;
    }

    /**
     * Get element of the matrix
     * @param x the line
     * @param y the column
     * @return element at line, column
     */
    public T getElement(int x, int y) {
        return (T) array[x][y];
    }

    /**
     * @return Raw value of current Matrix
     */
    public Object[][] toArray() {
        return array;
    }

    //Setters

    /**
     * Set a value in a specific case of the matrix
     * @param x the line index
     * @param y the column index
     * @param value the value to put in
     */
    public void setElement(int x, int y, T value) {
        this.array[x][y] = value;
    }

    /**
     * Fill the matrix with one value
     * @param value the value to fill in
     */
    public void fill(T value) {
        for (int i = 0; i < this.getXSize(); i++) {
            for (int j = 0; j < this.getYSize(); j++) {
                this.setElement(i,j,value);
            }
        }
    }

    //Methods specifics to matrix

    /**
     * Show the content of the matrix in the console
     */
    public void show() {
        for (int i = 0; i < this.getXSize(); i++) {
            for (int j = 0; j < this.getYSize(); j++){
                System.out.print(this.getElement(i,j).toString()+" ");
            }
            System.out.print("\n");
        }
    }

    /**
     * add every element of the two matrix in a new matrix
     * <ul>
     *     <li>The two matrix shall have the same dimension</li>
     *     <li>Type of the matrix shall be <ul>
     *         <li>Long</li>
     *         <li>Integer</li>
     *         <li>Short</li>
     *         <li>Double</li>
     *         <li>Float</li>
     *     </ul></li>
     *     <li>Use the toString() methods of T</li>
     * </ul>
     * @param matrix a matrix with the same type
     * @return a new Matrix<Double> with the result of the addition
     */
    public Matrix<Number> add(Matrix<T> matrix) {
        Matrix<Number> result = new Matrix<>(this.getXSize(), this.getYSize());
        for (int i = 0; i < this.getXSize(); i++) {
            for (int j = 0; j < this.getYSize(); j++) {
                result.setElement(i,j,
                        Double.parseDouble(this.getElement(i,j).toString())+
                                Double.parseDouble(matrix.getElement(i,j).toString())
                );
            }
        }
        return result;
    }

    /**
     * mul every element of the two matrix in a new matrix
     * <ul>
     *     <li>A, B are Matrix, A.getYSize shall be equal to B.getXSize</li>
     *     <li>Type of the matrix shall be <ul>
     *         <li>Long</li>
     *         <li>Integer</li>
     *         <li>Short</li>
     *         <li>Double</li>
     *         <li>Float</li>
     *     </ul></li>
     *     <li>Use the toString() methods of T</li>
     * </ul>
     * @param matrix a matrix with the same type
     * @return a new Matrix<Double> with the result of the multiplication
     */
    public Matrix<Number> mul(Matrix<T> matrix) {
        Matrix<Number> result = new Matrix<>(this.getXSize(),matrix.getYSize());
        for (int i = 0; i < this.getXSize(); i++) {
            for (int j = 0; j < this.getYSize(); j++) {
                double element = 0;
                //On multiplie dans l'ordre, élément par élément, chaque élément d'une ligne de la première matrice
                //A par chaque élément d'une colonne de la deuxième matrice B
                for (int k = 0; k < this.getYSize(); k++) {
                    element +=  Double.parseDouble(this.getElement(i,k).toString())*
                                Double.parseDouble(matrix.getElement(k,j).toString());

                }
                result.setElement(i,j,element);
            }
        }
        return result;
    }

    //Override object methods

    /**
     * Compare this Matrix and the Other
     * <ul>
     *     <li>Compare number of line</li>
     *     <li>Compare number of column per line</li>
     *     <li>Compare every element in the matrix, use the .equals() method</li>
     * </ul>
     * @param matrix the Matrix to compare
     * @return if the two matrix are the same
     */
    public boolean equals(Matrix<T> matrix) {
        boolean equal = true;
        if (this.getXSize() != matrix.getXSize()) equal = false;
        if (this.getYSize() != matrix.getYSize()) equal = false;

        int x = 0;
        while (x < this.getXSize() && equal) {
            int y = 0;
            while (y < this.getYSize() && equal) {
                equal = this.getElement(x,y).equals(matrix.getElement(x,y));
                y++;
            }
            x++;
        }
        return equal;
    }
    /**
     * @return A string representation of the matrix
     */
    @Override
    public String toString() {
        String s = "Matrix{array=[";
        for (int i = 0; i < this.getXSize(); i++) {
            s += "[";
            for (int j = 0; j < this.getYSize(); j++) {
                s += this.getElement(i,j).toString();
                if (j == this.getYSize()-1) {
                    s += "]";
                } else s += ", ";
            }
            if (i == this.getXSize()-1) {
                s += "]";
            } else s += ", ";
        }
        return s+'}';
    }
}
