import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * Class to contain a DoublyLinkedList indexed using a TreeMap
 * 
 * @author G94
 */
public class TreeList<K, V> implements Iterable<V> {
    TreeMap<K, Entry<V>> index;
    DoublyLinkedList<V> list;

    public TreeList() {
	index = new TreeMap<>();
	list = new DoublyLinkedList<>();
    }

    public int size() {
	return list.size;
    }

    public void add(K k, V v) {
	java.util.Map.Entry<K, Entry<V>> pe = index.lowerEntry(k);
	Entry<V> p;
	if (pe != null)
	    p = pe.getValue();
	else
	    p = list.header;
	Entry<V> e = p.next;
	if (e == null) {
	    index.put(k, list.add(v));
	} else {
	    if (e.element == v)
		return;
	    index.put(k, list.addAfter(v, p));
	}
    }

    public boolean contains(K k) {
	return index.containsKey(k);
    }

    public V get(K k) {
	return index.get(k).element;
    }

    public V delete(K k) {
	Entry<V> entry = index.remove(k);
	list.remove(entry);
	return entry.element;
    }

    @Override
    public Iterator<V> iterator() {
	return list.iterator();
    }

    public Iterator<V> rangeIterator(K startKey, K endKey) {
	java.util.Map.Entry<K, Entry<V>> pe = index.lowerEntry(startKey);
	Entry<V> p;
	if (pe == null)
	    p = list.header;
	else
	    p = pe.getValue();
	return new RangeIterator(p, index.get(endKey));
    }

    private class RangeIterator implements Iterator<V> {
	Entry<V> p;
	Entry<V> e;

	public RangeIterator(Entry<V> s, Entry<V> e) {
	    this.p = s;
	    this.e = e;
	}

	@Override
	public boolean hasNext() {
	    return p != e;
	}

	@Override
	public V next() {
	    if (!hasNext())
		throw new NoSuchElementException();
	    p = p.next;
	    return p.element;
	}

	@Override
	public void remove() {
	    // TODO Auto-generated method stub
	}
    }
}