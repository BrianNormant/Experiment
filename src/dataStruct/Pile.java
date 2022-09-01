package dataStruct;

public class Pile<E> extends ResizableList<E> {
    public Pile() {
        super();
    }
    public E getFirst() {
        return super.getElement(0);
    }
    public E getLast() {
        return super.getElement(super.getSize()-1);
    }
    public boolean addFirst(E element) {
        return super.addElement(element, 0);
    }
    public boolean addLast(E element) {
        return super.addElement(element, super.getSize()-1);
    }
    public E removeFirst() {
        return super.removeElement(0);
    }
    public E removeLast() {
        return super.removeElement(super.getSize()-1);
    }
    public boolean contains(E element) {
        return super.containsElement(element);
    }

    @Override
    public String toString() {
        return "Pile{} " + super.toString();
    }
}
