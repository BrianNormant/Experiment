package dataStruct;

public class File<E> extends ResizableList<E> {

    public File() {
        super();
    }
    public File(int size) {
        super(size);
    }

    public E get(int index) {
        return super.getElement(index);
    }
    public boolean set(E value, int index) {
        return super.setElement(value, index);
    }
    public boolean add(E value) {
        return add(value, super.getSize());
    }
    public boolean add(E value, int index) {
        return super.addElement(value, index);
    }
    public E remove(int index) {
        return super.removeElement(index);
    }
    public int remove(E element) {
        return super.removeElement(element);
    }
    public boolean contains(E value) {
        return super.containsElement(value);
    }

    @Override
    public String toString() {
        return "File{} " + super.toString();
    }
}
