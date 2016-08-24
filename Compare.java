import java.util.Iterator;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class Compare {
    public static void main(String[] args) {
	System.out.println("Testing SkipList:");
	test(new ConcurrentSkipListSet<Long>(), new Random());
	System.out.println("Testing RB Tree:");
	test(new TreeSet<Long>(), new Random());
	System.out.println("Testing TreeList:");
	testTL(new TreeList<Long, Long>(), new Random());
    }

    public static void testTL(TreeList<Long, Long> s, Random r) {
	System.out.println("Add:");
	timer();
	for (int i = 0; i < 1000000; i++) {
	    long n = r.nextLong();
	    s.add(n, n);
	}
	timer();
	System.out.println("Contains:");
	timer();
	for (int i = 0; i < 1000000; i++)
	    s.contains(r.nextLong());
	timer();
	System.out.println("Iterate:");
	timer();
	Iterator<Long> i = s.rangeIterator((long) r.nextInt(), s.index.lastKey());
	while (i.hasNext())
	    i.next();
	timer();
    }

    public static void test(SortedSet<Long> s, Random r) {
	System.out.println("Add:");
	timer();
	for (int i = 0; i < 1000000; i++)
	    s.add(r.nextLong());
	timer();
	System.out.println("Contains:");
	timer();
	for (int i = 0; i < 1000000; i++)
	    s.contains(r.nextLong());
	timer();
	System.out.println("Iterate:");
	timer();
	for (Long e : s.tailSet((long) r.nextInt()))
	    ;
	timer();
    }

    private static int phase = 0;
    private static long startTime, endTime, elapsedTime;

    /**
     * Timer to calculate the running time
     */
    public static void timer() {
	if (phase == 0) {
	    startTime = System.currentTimeMillis();
	    phase = 1;
	} else {
	    endTime = System.currentTimeMillis();
	    elapsedTime = endTime - startTime;
	    System.out.println("Time: " + elapsedTime + " msec.");
	    memory();
	    phase = 0;
	}
    }

    /**
     * This method determines the memory usage
     */
    public static void memory() {
	long memAvailable = Runtime.getRuntime().totalMemory();
	long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
	System.out.println("Memory: " + memUsed / 1000000 + " MB / " + memAvailable / 1000000 + " MB.");
    }
}
