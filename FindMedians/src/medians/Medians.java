package medians;


public class Medians {

	public static void main(String[] args) {

		int[] ar = {9,1,3,2,6,12,7,8,32,5};
		int t = Median(ar);
		
		System.out.print(t);

	}
	
	private static int bruteForceMedian(int[] A){
		// Returns the median value in a given array A of n numbers. This is 
		// the kth element, where k = n/2, if the array was sorted.
		
		double n = A.length;
		double k = Math.ceil(n/2);
		int med = -1;
		
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
	
	private static int bruteForceMedian_counter(int[] A){
		// Returns the median value in a given array A of n numbers. This is 
		// the kth element, where k = n/2, if the array was sorted.
		
		double n = A.length;
		double k = Math.ceil(n/2);
		int med = -1;
		int counter = 0;
		
		for(int i = 0; i < n-1; i++){
			int numsmaller = 0; //How many elements are smaller than A[i]
			int numequal = 0; //How many elements are equal to A[i]
			
			for(int j = 0; j < n-1; j++){
				if(A[j] < A[i]){
					counter++;
					numsmaller++;
				} else if (A[j] == A[i]){
					counter += 2;
					numequal++;
				}

			}
			if(numsmaller < k && k <= (numsmaller + numequal)){
				med = A[i];
				break;
	}
	}
		return counter;
	}
	
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
		System.out.print(pivotloc);
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
	
}
