/**
 * Class to implement Multi-Dimensional Search
 * 
 * @author rbk
 */
public class MDS {
    ItemIndex iIndex;
    PriceIndex pIndex;
    DescPriceIndex dpIndex;
    DescriptionIndex dIndex;

    public MDS() {
	this.iIndex = new ItemIndex();
	this.pIndex = new PriceIndex();
	this.dIndex = new DescriptionIndex();
	this.dpIndex = new DescPriceIndex();
    }

    /**
     * Insert a new item. If an entry with the same id already exists, its
     * description and price are replaced by the new values. If description is
     * empty, then just the price is updated.
     * 
     * @return Returns 1 if the item is new, and 0 otherwise.
     */
    public int insert(long id, double price, long[] description, int size) {
	Item item = new Item(id, dollarsToCents(price), description, size);
	Item oldItem = iIndex.insert(item);
	int isNew = 1;
	if (oldItem != null) {
	    pIndex.decrement(oldItem.price);
	    dIndex.decrement(oldItem.descriptions);
	    dpIndex.decrement(oldItem.descriptions, oldItem.price);
	    isNew = 0;
	}
	pIndex.increment(item.price);
	dIndex.increment(item.descriptions);
	dpIndex.increment(item.descriptions, item.price);
	return isNew;
    }

    /**
     * Search for the item with the given id.
     * 
     * @return Returns price of item with given id (or 0, if not found).
     */
    public double find(long id) {
	Item item = iIndex.find(id);
	return item != null ? centsToDollars(item.price) : 0;
    }

    /**
     * Delete item from storage.
     * 
     * @return Returns the sum of the long ints that are in the description of
     *         the item deleted (or 0, if such an id did not exist).
     */
    public long delete(long id) {
	Item item = iIndex.delete(id);
	long sum = 0;
	if (item != null) {
	    pIndex.decrement(item.price);
	    dIndex.decrement(item.descriptions);
	    for (Long description : item.descriptions) {
		dpIndex.decrement(description, item.price);
		sum += description;
	    }
	}
	return sum;
    }

    /**
     * Find items whose description contains n (exact match with one of the long
     * ints in the item's description).
     * 
     * @return Returns lowest price of those items.
     */
    public double findMinPrice(long des) {
	return centsToDollars(dpIndex.findMinPrice(des));
    }

    /**
     * Find items whose description contains n.
     * 
     * @return Returns highest price of those items.
     */
    public double findMaxPrice(long des) {
	return centsToDollars(dpIndex.findMaxPrice(des));
    }

    /**
     * Find the items whose description contains n, and their prices fall within
     * the given range, [low, high].
     * 
     * @return Returns the number of those items.
     */
    public int findPriceRange(long des, double lowPrice, double highPrice) {
	return dpIndex.findPriceRange(des, dollarsToCents(lowPrice), dollarsToCents(highPrice));
    }

    /**
     * Increases the price of every product, whose id is in the range [l,h], by
     * r% and any fractional pennies in the new prices of items are discarded.
     * 
     * @return Returns the sum of the net increases of the prices.
     */
    public double priceHike(long minId, long maxId, double rate) {
	long netHike = 0;
	for (Item item : iIndex.range(minId, maxId)) {
	    long oldPrice = item.price;
	    long hike = (long) Math.floor(rate * oldPrice / 100);
	    item.price += hike;
	    netHike += hike;
	    pIndex.decrement(oldPrice);
	    pIndex.increment(item.price);
	    dpIndex.decrement(item.descriptions, oldPrice);
	    dpIndex.increment(item.descriptions, item.price);
	}
	return centsToDollars(netHike);
    }

    /**
     * Find the items whose price is at least "low" and at most "high".
     * 
     * @return Returns the number of those items.
     */
    public int range(double lowPrice, double highPrice) {
	return pIndex.range(dollarsToCents(lowPrice), dollarsToCents(highPrice));
    }

    /**
     * Find the items that satisfy all of the following conditions:
     * 
     * 1. The description of the item contains 8 or more numbers, and,
     * 
     * 2. The description of the item contains exactly the same set of numbers
     * as another item.
     * 
     * @return Returns the number of those items.
     */
    public int sameSame() {
	return dIndex.sameSame();
    }

    private long dollarsToCents(double price) {
	return (long) Math.round(price * 100);
    }

    private double centsToDollars(double price) {
	return price / 100.0;
    }
}