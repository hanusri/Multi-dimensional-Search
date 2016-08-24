import java.util.Collection;
import java.util.TreeMap;

/**
 * Collection of items indexed by their id
 * 
 * @author G94
 */
public class ItemIndex {
    TreeMap<Long, Item> map; /* items indexed by their id */

    public ItemIndex() {
	map = new TreeMap<>();
    }

    Item insert(Item item) {
	Item oldItem = map.put(item.id, item);
	if (oldItem != null && item.descriptions.isEmpty())
	    item.descriptions = oldItem.descriptions;
	return oldItem;
    }

    Item find(long id) {
	return map.get(id);
    }

    Item delete(long id) {
	return map.remove(id);
    }

    Collection<Item> range(long minId, long maxId) {
	return map.subMap(minId, true, maxId, true).values();
    }
}