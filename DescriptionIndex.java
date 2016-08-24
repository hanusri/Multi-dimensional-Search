import java.util.HashMap;
import java.util.Set;

/**
 * Collection of descriptions as a whole and count of items with the same
 * descriptions
 * 
 * @author G94
 */
public class DescriptionIndex {
    HashMap<Set<Long>, Count> map; /*
				    * item counts indexed by description set
				    */
    final int MAGIC_SIZE = 8;

    public DescriptionIndex() {
	this.map = new HashMap<>();
    }

    void increment(Set<Long> descriptions) {
	if (descriptions.size() >= MAGIC_SIZE) {
	    Count count = map.get(descriptions);
	    if (count == null)
		map.put(descriptions, new Count(1));
	    else
		count.value++;
	}
    }

    void decrement(Set<Long> descriptions) {
	if (descriptions.size() >= MAGIC_SIZE) {
	    Count count = map.get(descriptions);
	    if (count != null) {
		if (count.value == 1)
		    map.remove(descriptions);
		else
		    count.value--;
	    }
	}
    }

    public int sameSame() {
	int result = 0;
	for (Count c : map.values())
	    if (c.value > 1)
		result += c.value;
	return result;
    }
}