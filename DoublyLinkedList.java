import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Generic Implementation of a Doubly Circular Linked List
 * 
 * @author G94
 */
public class DoublyLinkedList<T> implements Iterable<T> {
    Entry<T> header, tail;
    int size;

    DoublyLinkedList() {
	header = new Entry<>(null, null, null);
	tail = null;
	size = 0;
    }

    private void _insertEmpty(T x) {
	Entry<T> newNode = new Entry<>(x, null, null);
	newNode.next = newNode;
	newNode.prev = newNode;
	header.next = newNode;
	tail = newNode;
	size++;
    }

    private Entry<T> _insertAfter(T x, Entry<T> p) {
	Entry<T> newNode = new Entry<>(x, p, p.next);
	p.next.prev = newNode;
	p.next = newNode;
	size++;
	return newNode;
    }

    Entry<T> add(T x) {
	if (tail == null)
	    _insertEmpty(x);
	else
	    tail = _insertAfter(x, tail);
	return tail;
    }

    Entry<T> addFirst(T x) {
	if (tail == null)
	    _insertEmpty(x);
	else
	    header.next = _insertAfter(x, tail);
	return header.next;
    }

    Entry<T> addAfter(T x, Entry<T> p) {
	Entry<T> newNode = _insertAfter(x, p);
	if (p == tail)
	    /* Insert at the end */
	    tail = newNode;
	return newNode;
    }

    void remove(Entry<T> p) {
	size--;
	if (size == 0) {
	    header.next = null;
	    tail = null;
	    return;
	}
	p.prev.next = p.next;
	p.next.prev = p.prev;
	if (p == header.next)
	    header.next = p.next;
	if (p == tail)
	    tail = p.prev;
    }

    void printList() {
	Entry<T> x = header.next;
	do {
	    System.out.print(x.element + " ");
	    x = x.next;
	} while (x != header.next);
	System.out.println();
    }

    @Override
    public Iterator<T> iterator() {
	return new DLLIterator();
    }

    private class DLLIterator implements Iterator<T> {
	Entry<T> current;
	int visited;

	public DLLIterator() {
	    this.current = header;
	    this.visited = 0;
	}

	@Override
	public boolean hasNext() {
	    return visited < size;
	}

	@Override
	public T next() {
	    if (!hasNext())
		throw new NoSuchElementException();
	    current = current.next;
	    visited++;
	    return current.element;
	}

	@Override
	public void remove() {
	    throw new UnsupportedOperationException();
	}
    }
}