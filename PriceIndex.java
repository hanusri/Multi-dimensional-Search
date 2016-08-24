import java.util.TreeMap;

/**
 * Collection of item counts indexed by price
 * 
 * @author G94
 */
public class PriceIndex {
    TreeMap<Long, Count> map; /* item counts indexed by price */

    public PriceIndex() {
	this.map = new TreeMap<>();
    }

    void increment(long price) {
	Count count = map.get(price);
	if (count == null)
	    map.put(price, new Count(1));
	else
	    count.value++;
    }

    void decrement(long price) {
	Count count = map.get(price);
	if (count != null) {
	    if (count.value == 1)
		map.remove(price);
	    else
		count.value--;
	}
    }

    long findMinPrice() {
	return map.size() > 0 ? map.firstKey() : 0;
    }

    long findMaxPrice() {
	return map.size() > 0 ? map.lastKey() : 0;
    }

    int range(long lowPrice, long highPrice) {
	int count = 0;
	for (Count c : map.subMap(lowPrice, true, highPrice, true).values())
	    count += c.value;
	return count;
    }
}