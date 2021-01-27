package math;/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu. Brian Normant 2003 -> Today
 */

public class Vector<T> {
    //Properties
    private final Object[] array;

    //Constructor

    public Vector(Vector<T> vector) {
        array = vector.array;
    }

    /**
     * Create a empty Vector with size line
     * @param size number of line
     */
    public Vector(int size) {
        array = new Object[size];
    }

    /**
     * Create a new Vector with size line
     * fill the vector with defaultValue
     * @param size number of line
     * @param defaultValue
     */
    public Vector(int size, T defaultValue) {
        array = new Object[size];
        this.fill(defaultValue);
    }
    //Getters

    /**
     * @return size of the vector
     */
    public int getSize() {
        return array.length;
    }

    /**
     * @param index line to get
     * @return element at line index
     */
    public T getElement(int index) {
        return (T) this.array[index];
    }
    public Object[] toArray() {
        return this.array;
    }
    //Setters

    /**
     * @param index line to set
     * @param value value to set at line index
     */
    public void setElement(int index, T value) {
        this.array[index] = value;
    }

    /**
     * fill the vector with value
     * @param value the value to fill
     */
    public void fill(T value) {
        for (int i = 0; i < this.getSize(); i++) {
            this.setElement(i, value);
        }
    }
    //Methods specific to vector

    public void show() {
        for (int i = 0; i < this.getSize(); i++) {
            System.out.print(this.getElement(i).toString()+" ");
        }
        System.out.print("\n");
    }

    /**
     * add every element of the two vector into a new Vector
     * <ul>
     *     <li>The two vector shall have the same size</li>
     *     <li>Type of the vector shall be <ul>
     *         <li>Long</li>
     *         <li>Integer</li>
     *         <li>Short</li>
     *         <li>Double</li>
     *         <li>Float</li>
     *     </ul></li>
     *     <li>Use the toString() methods of T</li>
     * @param vector a vector<T>
     * @return a new Vector<Double> with the result of the two Vector addition
     */
    public Vector<Number> add(Vector<T> vector) {
        Vector<Number> result = new Vector<>(this.getSize());
        for (int i = 0; i < result.getSize(); i++) {
            result.setElement(i,
                    ((Number)this.getElement(i)).doubleValue()+
                            ((Number)vector.getElement(i)).doubleValue()
            );
        }
        return result;
    }

    //Override Object method

    /**
     * Check
     * <ul>
     *     <li>Type</li>
     *     <li>Size</li>
     *     <li>content</li>
     * </ul>
     * @param vec an object to compare
     * @return if the two object are the same
     */
    public boolean equals(Vector<T> vec) {
        boolean equal = true;
        if (vec == null) {
            equal = false;
        } else if (this.getSize() != vec.getSize()) {
            equal = false;
        } else {
            int index = 0;
            while (equal && index < this.getSize()) {
                if (this.getElement(index).equals(vec.getElement(index))) equal = false;
                index++;
            }
        }

        return equal;
    }

    /**
     * @return a String reprensentation of the vector
     */
    @Override
    public String toString() {
        String s = "Vector{array=[";
        for (int i = 0; i < this.getSize(); i++) {
            s += this.getElement(i).toString();
            if (i == this.getSize()-1) {
                s += "]}";
            } else s += ", ";
        }
        return s;
    }
}
