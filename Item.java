import java.util.Set;
import java.util.TreeSet;

/**
 * Class to contain the details of a single product
 * 
 * @author G94
 */
public class Item implements Comparable<Item> {
    long id; /* product id */
    Set<Long> descriptions; /* product descriptions */
    long price; /* product price in cents */

    public Item(long id, long price, long[] descriptions, int size) {
	this.id = id;
	this.price = price;
	this.descriptions = new TreeSet<>();
	for (int i = 0; i < size; i++)
	    this.descriptions.add(descriptions[i]);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (id ^ (id >>> 32));
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Item other = (Item) obj;
	if (id != other.id)
	    return false;
	return true;
    }

    @Override
    public int compareTo(Item anotherItem) {
	return (id < anotherItem.id ? -1 : (id == anotherItem.id ? 0 : 1));
    }

    @Override
    public String toString() {
	return "Item [id=" + id + ", descriptions=" + descriptions + ", price=" + price + "]";
    }
}