import java.util.LinkedList;
import java.util.Scanner;

/**
 * Sort.java<br>
 * Provides 3 sorting algorithms for integer arrays:<br>
 * Insertion Sort<br>
 * Binary Insertion Sort<br>
 * Shaker Sort
 * 
 * @author <oawofeso>
 * @version <2014/10/10>
 */

public class Sort {
	
	/**
	 * insertion sort
	 * @param a - array of integers to be sorted
	 * @return number of comparisons performed
	 */
	public static int insertionSort(int[] a) {
		// INSERTION SORT
		int count = 0;
		for(int i = 1; i < a.length; i++){
			for(int j = 0; j < i - 1; j++){
				if(a[i] < a[j]){
					//swap
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
					count++;
				}					
				else
					j = a.length;
			}
		}
		return count;
	}
	
	/**
	 * binary insertion sort
	 * @param a - array of integers to be sorted
	 * @return number of comparisons performed
	 */
	public static int binaryInsertionSort(int[] a) {
		// BINARY INSERTION SORT
		int count = 0;
		//int[] b = new int[a.length];
		//System.arraycopy( a, 0, b, 0, a.length );
		for(int i = 0; i < a.length; i++){
			int pos = i;
			int temp = a[i];
			//binary search
			for(int j = 0; j < i; j++){
				count++;
				if(a[i] < a[j]){
					pos = j;
					j = i;
				}
			}
			//insert
			for(int j = i; j > pos; j--){
				a[j] = a[j - 1];
			}
			a[pos] = temp;
		}
		return count;
	}

	/**
	 * shaker sort
	 * @param a - array of integers to be sorted
	 * @return number of comparisons performed
	 */
	public static int shakerSort(int[] a) {
		// SHAKER SORT
		int count = 0;
		for(int i = 0; i < a.length/2 + 1; i++){
			for(int j = i + 1; j < a.length; j++){
				if(a[i] > a[j]){
					//swap
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
					count++;
				}
			}
			for(int j = a.length - 1; j > i; j--){
				if(a[j - 1] > a[j]){
					//swap
					int temp = a[j];
					a[j] = a[j - 1];
					a[j - 1] = temp;
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * sort data provided via standard input
	 * @param args - command line arguments<br>
	 * -i - use insertion sort<br>
	 * -b - use binary insertion sort<br>
	 * -s - use shaker sort<br>
	 * -c - instead of sorted data, print number of comparisons<br>
	 * -t - instead of sorted data, print timing<br>
	 */
	public static void main(String[] args) {
		// read integer data from standard input
		Scanner s = new Scanner(System.in);
		LinkedList<Integer> l = new LinkedList<Integer>();
		while (s.hasNextInt())
			l.add(s.nextInt());
		s.close();
		
		// parse command line options
		boolean do_shaker_sort = false;
		boolean do_insertion_sort = false;
		boolean do_binary_insertion_sort = false;
		boolean do_comparisons = false;
		boolean do_timing = false;
		for (String o : args) {
			if (o.charAt(0) == '-') {
				for (char c : o.substring(1).toCharArray()) {
					switch (c) {
					case 's' : 
						do_shaker_sort = true;
						break;
					case 'i' : 
						do_insertion_sort = true;
						break;
					case 'b' : 
						do_binary_insertion_sort = true;
						break;
					case 'c' : 
						do_comparisons = true;
						break;
					case 't' : 
						do_timing = true;
						break;
					default:
						System.out.println("unknown option igorned: " + c);
						break;
					}
				}
			}
		}
		
		// sort data
		if (do_insertion_sort) {
			System.out.println("Insertion Sort");
			// copy data into array
			int[] a = new int[l.size()];
			int j = 0;
			for (int i : l)
				a[j++] = i;
			long t1 = System.nanoTime();
			int c = insertionSort(a);
			long t2 = System.nanoTime();
			if (do_comparisons)
				System.out.println(c + " compares");
			if (do_timing)
				System.out.println((t2-t1)/1000000.0 + " ms");
			if (!(do_comparisons || do_timing))
				for (int i : a)
					System.out.println(i);
		}
		
		if (do_binary_insertion_sort) {
			System.out.println("Binary Insertion Sort");
			// copy data into array
			int[] a = new int[l.size()];
			int j = 0;
			for (int i : l)
				a[j++] = i;
			long t1 = System.nanoTime();
			int c = binaryInsertionSort(a);
			long t2 = System.nanoTime();
			if (do_comparisons)
				System.out.println(c + " compares");
			if (do_timing)
				System.out.println((t2-t1)/1000000.0 + " ms");
			if (!(do_comparisons || do_timing))
				for (int i : a)
					System.out.println(i);
		}
		
		if (do_shaker_sort) {
			System.out.println("Shaker Sort");
			// copy data into array
			int[] a = new int[l.size()];
			int j = 0;
			for (int i : l)
				a[j++] = i;
			long t1 = System.nanoTime();
			int c = shakerSort(a);
			long t2 = System.nanoTime();
			if (do_comparisons)
				System.out.println(c + " compares");
			if (do_timing)
				System.out.println((t2-t1)/1000000.0 + " ms");
			if (!(do_comparisons || do_timing))
				for (int i : a)
					System.out.println(i);
		}
	}
}

