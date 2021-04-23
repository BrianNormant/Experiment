package dataStruct;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

public class ResizableList<E> implements Iterable<E>{
    private Object[] elements;

    public ResizableList() {
        this(0);
    }
    public ResizableList(int size) {
        this.elements = new Object[size];
    }

    protected final void grow() {
        grow(1);
    }
    protected final void grow(int i) {
        Object[] temp = new Object[elements.length+i];
        System.arraycopy(elements, 0, temp, 0, temp.length);
        elements = temp;
    }

    public final int getSize() {
        return elements.length;
    }
    protected final boolean setElement(E element,int index) {
        try {
            elements[index] = element;
            return true;
        } catch ( ArrayIndexOutOfBoundsException ignored) {}
        return false;
    }

    protected final E getElement(int index) {
        try {
            return (E) elements[index];
        } catch (ArrayIndexOutOfBoundsException ignored) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     *
     * @param element the element to add
     * @param index where element will be added
     * @return if the operation success
     */
    protected final boolean addElement(E element, int index) {
        try {
            Object[] start = new Object[index],
                    end = new Object[elements.length - index],
                    temp = new Object[elements.length + 1];
            System.arraycopy(elements, 0, start, 0, start.length);
            System.arraycopy(elements, index, end, 0, end.length);

            System.arraycopy(start, 0, temp, 0, start.length);
            temp[index] = element;
            System.arraycopy(end, 0, temp, index + 1, end.length);

            elements = temp;
            return true;
        } catch ( ArrayIndexOutOfBoundsException ignored) {}
        return false;
    }
    protected final boolean containsElement(E element) {
        for (Object e : elements) {
            if (e.equals(element)) return true;
        }
        return false;
    }

    protected final E removeElement(int index) {
        E toReturn = (E)elements[index];
        Object[] start = new Object[index],
                end = new Object[elements.length - (index + 1)],
                temp = new Object[elements.length - 1];
        System.arraycopy(elements, 0, start, 0, start.length);
        System.arraycopy(elements, index + 1, end, 0, end.length);

        System.arraycopy(start, 0, temp, 0, start.length);
        System.arraycopy(end, 0, temp, index, end.length);

        elements = temp;
        return toReturn;
    }

    protected final int removeElement(E element) {
        int i = 0;
        while (i < this.getSize()) {
            if (elements[i].equals(element)) {
                this.removeElement(i);
                return i;
            }
            i++;
        }
        return -1;
    }

    protected final boolean removeAllElement(E element) {
        boolean foundOne = false;
        for (int i = 0; i < this.getSize(); i++) {
            if (elements[i].equals(element)) {
                if (!foundOne) foundOne = true;
                this.removeElement(i);
            }
        }
        return foundOne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResizableList<?> that = (ResizableList<?>) o;
        return Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

    @Override
    public String toString() {
        return "ResizableList{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        for (E element : this) action.accept(element);
    }

    private final class CustomIterator implements Iterator<E> {
        int index;

        public CustomIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return getSize() > index;
        }

        @Override
        public E next() {
            index++;
            return getElement(index-1);
        }
    }
}
