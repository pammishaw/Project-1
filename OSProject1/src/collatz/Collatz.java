package collatz;

public class Collatz {
	public static int computeCollatzStoppingTime(int n) {
		int counter = 0;
		// Add check for values less than 1, non-whole numbers, and negative values.
		/* while (n != 1) {
			if (n & 1 == 0){
				n = n / 2;
			}else {
				n = (3 * n) + 1;
			}
   		another way to possibly do if n>1 does not work?
		*/
		while (n > 1) {
			if (n % 2 == 0) {
				n = n / 2;
			}
			else if (n % 2 != 0) {
				n = (3 * n) + 1;
			}
			counter++;
		}
		
		return counter;
	}

}
