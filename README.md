# Multi-Threaded Collatz Stopping Time Generator Project
This Java program calculates the Collatz stopping times for all integers in the range from 1 to N, using multiple threads to parallelize the work. The results are displayed as a histogram showing the frequency of each stopping time.

## Overview

The Collatz conjecture involves applying the following process to a positive integer:

- If the number is even, divide it by 2.
- If the number is odd, multiply by 3 and add 1.

This continues until the number is reduced to 1. The Collatz stopping time is the number of steps required to reach 1.

This program parallelizes the calculation by:
- Splitting the workload across multiple threads.
- Using a shared counter with synchronization.
- Storing results in a thread-safe histogram.

## Files

- `MTCollatz.java`: Entry point of the program.
- `Collatz.java`: Contains the method for computing Collatz stopping time.
- `Counter.java`: Provides synchronized access to the next number to process.
- `Worker.java`: Runnable class used by threads to compute stopping times.

## How to Compile and Run

### Compile

Make sure all `.java` files are in the same folder and do not use a `package` declaration.

javac *.java

### Run

java MTCollatz <numRange> <numWorkers>

- numRange: The maximum value to compute Collatz stopping times for.

- numWorkers: Number of worker threads to use.

## Output
- A histogram array of stopping times is printed to standard output.

- Execution details (numRange, numWorkers, elapsed time in milliseconds) are printed to standard error.

### Example Output

1 1
2 1
3 1
4 1
. .
. .
. .

100,4,2.000000000

## Challenges Faced

- Project structure and execution: Initially, the use of Java packages (package collatz;) made command-line compilation and execution more complex. Flattening the structure and removing package declarations simplified development and testing.

## Authors
- Pamela Mishaw
- Zafeerah Hamid

## Course Information
COP5518 Computing Essentials