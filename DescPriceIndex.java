import java.util.Collection;
import java.util.TreeMap;

/**
 * Collection of descriptions and count of items indexed by their price
 * 
 * @author G94
 */
public class DescPriceIndex {
    TreeMap<Long, PriceIndex> map; /*
				    * item counts by price indexed again by
				    * descriptions
				    */

    public DescPriceIndex() {
	this.map = new TreeMap<>();
    }

    void increment(long description, long price) {
	PriceIndex counts = map.get(description);
	if (counts == null) {
	    counts = new PriceIndex();
	    map.put(description, counts);
	}
	counts.increment(price);
    }

    void increment(Collection<Long> descriptions, long price) {
	for (Long description : descriptions)
	    increment(description, price);
    }

    void decrement(long description, long price) {
	PriceIndex counts = map.get(description);
	if (counts != null)
	    counts.decrement(price);
    }

    void decrement(Collection<Long> descriptions, long price) {
	for (Long description : descriptions)
	    decrement(description, price);
    }

    long findMinPrice(long des) {
	PriceIndex counts = map.get(des);
	return counts != null ? counts.findMinPrice() : 0;
    }

    long findMaxPrice(long des) {
	PriceIndex counts = map.get(des);
	return counts != null ? counts.findMaxPrice() : 0;
    }

    int findPriceRange(long des, long lowPrice, long highPrice) {
	PriceIndex counts = map.get(des);
	return counts != null ? counts.range(lowPrice, highPrice) : 0;
    }
}