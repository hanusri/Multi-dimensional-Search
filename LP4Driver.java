
import java.util.*;
import java.io.*;

/**
 * Sample driver code for Project LP4. Modify as needed. Do not change
 * input/output formats.
 * 
 * @author rbk
 */
public class LP4Driver {
    static long[] description;
    static final int DLENGTH = 100000;

    public static void main(String[] args) throws FileNotFoundException {
	Scanner in;
	if (args.length > 0) {
	    in = new Scanner(new File(args[0]));
	} else {
	    in = new Scanner(System.in);
	}
	String s;
	double rv = 0;
	description = new long[DLENGTH];

	timer();
	MDS mds = new MDS();

	loop: while (in.hasNext()) {
	    s = in.next();
	    if (s.charAt(0) == '#') {
		s = in.nextLine();
		continue;
	    }

	    switch (s) {
	    case "Insert":
		long id = in.nextLong();
		double price = in.nextDouble();
		long des = in.nextLong();
		int index = 0;
		while (des != 0) {
		    description[index++] = des;
		    des = in.nextInt();
		}
		description[index] = 0;
		rv += mds.insert(id, price, description, index);
		break;

	    case "Find":
		id = in.nextLong();
		rv += mds.find(id);
		break;

	    case "Delete":
		id = in.nextLong();
		rv += mds.delete(id);
		break;

	    case "FindMinPrice":
		des = in.nextLong();
		rv += mds.findMinPrice(des);
		break;

	    case "FindMaxPrice":
		des = in.nextLong();
		rv += mds.findMaxPrice(des);
		break;

	    case "FindPriceRange":
		des = in.nextLong();
		double lowPrice = in.nextDouble();
		double highPrice = in.nextDouble();
		rv += mds.findPriceRange(des, lowPrice, highPrice);
		break;

	    case "PriceHike":
		long minid = in.nextLong();
		long maxid = in.nextLong();
		double rate = in.nextDouble();
		rv += mds.priceHike(minid, maxid, rate);
		break;

	    case "Range":
		lowPrice = in.nextDouble();
		highPrice = in.nextDouble();
		rv += mds.range(lowPrice, highPrice);
		break;

	    case "SameSame":
		rv += mds.sameSame();
		break;

	    case "End":
		break loop;

	    default:
		System.out.println("Houston, we have a problem.\nUnexpected line in input: " + s);
		System.exit(0);
	    }
	}
	in.close();
	System.out.printf("%.2f\n", rv);
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