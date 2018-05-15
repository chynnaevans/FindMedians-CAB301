package medians;

import java.util.Arrays;
import java.util.Random;

public class Medians {
	static int median_counter;
	
	public static void main(String[] args) {

//		int[] arr = create_array(95000);
//		bruteForceMedian_counter(arr);

		System.out.print("n, bruteForceMedian_time, bruteForceMedian_counter, Median_time, Median_counter \n");
//
//		
		// create arrays
		for(int n = 1; n <= 100000; n+= 5000){
			
			//conduct 20 tests per n
//			int[][] arrays = new int[20][];
			double brute_force_time = 0;
			double median_time = 0;
			double start_time;

			int brute_counter = 0;
			median_counter = 0;
			for(int j = 0; j < 20; j++){
			int[] arr = create_array(n);
			int true_median = test_array(arr);
			start_time = System.currentTimeMillis();
			int b = bruteForceMedian(arr);
			brute_force_time = brute_force_time + (System.currentTimeMillis() - start_time);
			if(b != true_median){
				System.out.println("---------- brute force error ----------");
			}
			brute_counter += bruteForceMedian_counter(arr);
			
			start_time = System.currentTimeMillis();
			int m = Median(arr);
			median_time = median_time + (System.currentTimeMillis() - start_time);
			if(m != true_median){
				System.out.println("---------- median/partition/select error ----------");
			}
			Median_counter(arr);
			
			}
			
			System.out.print(n + ", " + brute_force_time/20 + ", " + (brute_counter/20)*100 + ", "+ median_time/20 + ", " + median_counter/20 + "\n");

		}

	}
	
//
//
// --------- bruteForceMedian ----------
//

// ---- No counter ----
	
	private static int bruteForceMedian(int[] A){
		// Returns the median value in a given array A of n numbers. This is 
		// the kth element, where k = n/2, if the array was sorted.
		
		double n = A.length;
		double k = Math.ceil(n/2);
		int med = A[0];
		
		for(int i = 0; i < n-1; i++){
			int numsmaller = 0; //How many elements are smaller than A[i]
			int numequal = 0; //How many elements are equal to A[i]
			
			for(int j = 0; j < n-1; j++){
				if(A[j] < A[i]){
					numsmaller++;
				} else if (A[j] == A[i]){
					numequal++;
				}

			}
			if(numsmaller < k && k <= (numsmaller + numequal)){
				med = A[i];
				break;
	}
	}
		return med;
	}

// ----- with counter -----
	
	private static int bruteForceMedian_counter(int[] A){
		// Returns the median value in a given array A of n numbers. This is 
		// the kth element, where k = n/2, if the array was sorted.
//		System.out.println(Arrays.toString(A));
		double n = A.length;
		double k = Math.ceil(n/2);
		int med = 0;
		int counter = 0;
		
		for(int i = 0; i < n-1; i++){
			int numsmaller = 0; //How many elements are smaller than A[i]
			int numequal = 0; //How many elements are equal to A[i]
//			System.out.print("i: " + i + "\n");

		
			for(int j = 0; j < n-1; j++){
//				System.out.print("counter: " + counter + "\n");

				counter++;
				if(A[j] < A[i]){
					numsmaller++;
				} else if (A[j] == A[i]){

					numequal++;
				}

			}
			if(numsmaller < k && k <= (numsmaller + numequal)){
				med = A[i];
				break;
	}
	}
//		System.out.print("med: " + med + "counter: " + counter);
		return counter/100;
	}

//
//
// ------ Median -----
//

// ---- Partition without counter 
	
	private static int Partition(int[] A, int l, int h){
		//Partition array slice A[l...h] by moving element A[l] to the position
		//it would have if the array slice was sorted, and by moving all
		//values in the slice smaller than A[l] to earlier positions, and all values
		//larger than or equal to A[l] to later positions. Returns the index at which
		//the 'pivot' element formerly at location A[l] is placed.
		
		int pivotval = A[l]; //Choose first value in slice as pivot value
		int pivotloc = l; //Location to insert pivot value
		
		for(int j = l + 1;j <= h; j++){
			if(A[j] < pivotval){
				pivotloc++;
				int temp = A[pivotloc];
				A[pivotloc] = A[j];
				A[j] = temp;
			}
		}
		int temp = A[l];
		A[l] = A[pivotloc];
		A[pivotloc] = temp;
		return pivotloc;
	}

	
	public static int Select(int[] A, int l, int m, int h){
		//Returns the value at index m in array slice A[l...h], if the slice
		//were sorted into non-descreasing order.
		int pos = Partition(A, l, h);
		int ret = -1;
		if(pos == m){
			ret = A[pos];
		}
		if(pos > m){
			ret = Select(A,l,m,pos-1);
		}
		if(pos < m){
			ret = Select(A, pos+1, m, h);
		}
//		System.out.print(ret);
		return ret;

	}
	
	private static int Median(int[] A){
		//Returns the median value in a given array A of n numbers
		int n = A.length;
		int ret = -1;
		if(n == 1){
			ret = A[0];
		} else{
			ret = Select(A, 0, (int)Math.floor(n/2), n-1);
		}
		return ret;
	}
	
// ----- Median with counter
// 
	private static int Partition_counter(int[] A, int l, int h){

		//Partition array slice A[l...h] by moving element A[l] to the position
		//it would have if the array slice was sorted, and by moving all
		//values in the slice smaller than A[l] to earlier positions, and all values
		//larger than or equal to A[l] to later positions. Returns the index at which
		//the 'pivot' element formerly at location A[l] is placed.
		
		int pivotval = A[l]; //Choose first value in slice as pivot value
		int pivotloc = l; //Location to insert pivot value
		
		for(int j = l + 1;j <= h; j++){
			if(A[j] < pivotval){
				pivotloc++;
				int temp = A[pivotloc];
				A[pivotloc] = A[j];
				A[j] = temp;
				median_counter++;

			}
		}
		int temp = A[l];
		A[l] = A[pivotloc];
		A[pivotloc] = temp;
		return pivotloc;
	}

	
	public static int Select_counter(int[] A, int l, int m, int h){
		//Returns the value at index m in array slice A[l...h], if the slice
		//were sorted into non-decreasing order.
		int pos = Partition_counter(A, l, h);
		int ret = -1;
		if(pos == m){
			ret = A[pos];
		}
		if(pos > m){
			ret = Select_counter(A,l,m,pos-1);
		}
		if(pos < m){
			ret = Select_counter(A, pos+1, m, h);
		}
//		System.out.print(ret);
		return ret;

	}
	
	public static int Median_counter(int[] A){
		//Returns the median value in a given array A of n numbers
		int n = A.length;
		int ret = -1;
		if(n == 1){
			ret = A[0];
		} else{
			ret = Select_counter(A, 0, (int)Math.floor(n/2), n-1);
		}
		return ret;
	}


	
	
//
//
// ------- create arrays ------
// 
//
	
	private static int[] create_array(int n){

		int[] this_array = new int[n];
		Random random = new Random();
		
		for(int i = 0; i < n; i++){
			int num = random.nextInt(2001) - 1000;
			this_array[i] = num;
			
		}

		return this_array;
	}
	
	private static int[] create_array_same_value(int n){

		int[] this_array = new int[n];
		
		for(int i = 0; i < n; i++){
			int num = 1;
			this_array[i] = num;
			
		}

		return this_array;
	}
	
	private static int[] create_array_all_neg(int n){

		int[] this_array = new int[n];
		Random random = new Random();
		
		for(int i = 0; i < n; i++){
			int num = random.nextInt(1000) - 1000;
			this_array[i] = num;
			
		}

		return this_array;
	}
	
	private static int[] create_array_all_pos(int n){

		int[] this_array = new int[n];
		Random random = new Random();
		
		for(int i = 0; i < n; i++){
			int num = random.nextInt(1000);
			this_array[i] = num;
			
		}

		return this_array;
	}
	
	private static int[] create_array_reverse_ordered(int n){

		int[] this_array = new int[n];
		
		for(int i = n-1; i > 0; i--){
			this_array[i] = i;
		}

		return this_array;
	}
	
	private static int[] create_array_ordered(int n){

		int[] this_array = new int[n];
		
		for(int i = 1; i < n; i++){
			this_array[i] = i;
		}

		return this_array;
	}
	
	private static int test_array(int[] A){
		Arrays.sort(A);
		int median;
		if(A.length == 1){
			median = A[0];
		} else{
		median = A[A.length/2];
		}
		
		return median;
	}
	
}
