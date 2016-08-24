/**
 * Class to contain a single entry in the DoublyLinked List
 * 
 * @author G94
 */
public class Entry<T> {
    T element;
    Entry<T> next;
    Entry<T> prev;

    Entry(T x, Entry<T> prev, Entry<T> next) {
	element = x;
	this.next = next;
	this.prev = prev;
    }
}