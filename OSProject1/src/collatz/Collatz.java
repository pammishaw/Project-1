/**
 * This program computes the Collatz stopping time, the number of steps needed to reduce a given positive integer to 1 using the Collatz algorithm.
 *
 * - If the number is even, divide it by 2
 * - If the number is odd, multiply it by 3 and add 1
 * 
 * The process repeats until the number becomes 1.
 * 
 * @author Pamela Mishaw
 * @date 06/12/2025
 * @info Course COP5518
 */

package collatz;

public class Collatz {

	/**
     * Computes the Collatz stopping time for a given positive integer.
     *
     * @param n the starting integer; must be greater than 0
     * @return the number of steps to reach 1 using the Collatz algorithm
     * @throws IllegalArgumentException if the input is less than 1
     */
	
	public static int computeCollatzStoppingTime(int n) {

		if (n < 1) {
            throw new IllegalArgumentException("Input must be a positive integer greater than 0.");
        }

		int counter = 0;
		
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
