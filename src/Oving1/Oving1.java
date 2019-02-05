package Oving1;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Oving1 {
    private static int[] allPrimes;
    private static int primeIndex = 0;
    static Semaphore semaphore = new Semaphore(0);
    static Semaphore mutex = new Semaphore(1);


    public static void main(String[] args) {
        System.out.println("Enter amount of threads");
        Scanner scanner = new Scanner(System.in);
        int amountOfThreads = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter startInterval");
        int startInterval = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter endInterval");
        int endInterval = Integer.parseInt(scanner.nextLine());
        allPrimes = new int[1 + endInterval - startInterval];
        System.out.println("n threads : " + amountOfThreads + "\nstart : " + startInterval + "\nend :" + endInterval);

        try {
            delegatePrimesOnThreads(amountOfThreads, startInterval, endInterval);

        } catch (Exception e) {
            System.out.println("erorR: " + e);
        } finally {

            int[] onlyPrimes = shortenArray(allPrimes);
            Arrays.sort(onlyPrimes);
            onlyPrimes = shortenArray(onlyPrimes);
            for (int i : onlyPrimes) {
                System.out.println(i);
            }


            System.out.println(duplicates(onlyPrimes));

            int[] tall = {1, 3, 4, 5, 6, 8, 9, 7, 0};
            System.out.println(duplicates(tall));


        }


    }//end main

    static boolean duplicates(final int[] primeNumbers) {
        Set<Integer> lump = new HashSet<Integer>();
        for (int i : primeNumbers) {
            if ((i != 0) && lump.contains(i)) {
                return true;
            }
            lump.add(i);
        }
        return false;
    }

    static int[] shortenArray(int[] longArray) {
        int length = 0;
        Arrays.sort(longArray);
        int arrayStart = 0;

        for (int i = 0; i < longArray.length; i++) {
            if (longArray[i] != 0) {
                arrayStart = i;
                break;

            }
        }

        length = longArray.length - arrayStart;


        System.out.println("shortArrayLengde: " + length);
        int[] shortArray = new int[length];

        for (int i = 0; i < length; i++) {
            shortArray[i] = longArray[i + arrayStart];
        }

        return shortArray;

    }

    static int[] joinTwoArrays(int[] one, int[] two) {

        int[] newArray = new int[one.length + two.length];
        for (int i = 0; i < newArray.length; i++) {
            if (i < one.length) {
                newArray[i] = one[i];
            } else {
                newArray[i] = two[i - one.length];
            }
        }

        for (int number : newArray) {
        }
        return newArray;
    }//end method

    private static void delegatePrimesOnThreads(int threads, int intervalStart, int interValEnd) {
        int primesOnThread = 0; //number of primes
        int intervalSize = (interValEnd - intervalStart) + 1;
        int start = intervalStart;
        if (threads > intervalSize) primesOnThread = threads / intervalSize;
        else primesOnThread = intervalSize / threads;
        System.out.println("primesOnThread " + primesOnThread);
        int end = start + primesOnThread;
        int delegatedThreads = 0;


        if (threads == 1) {
            delegateIntervalOnThread(intervalStart, interValEnd);
        } else if (threads > 1) {
            for (int i = start; i < end; i++) {
                if (end >= interValEnd && intervalSize != primesOnThread) {
                    end = interValEnd;
                    delegateIntervalOnThread(start, end);
                    delegatedThreads++;
                    return;
                }
                delegateIntervalOnThread(start, end);
                delegatedThreads++;
                start = start + primesOnThread;
                if (start == end) start += 1;
                end = end + primesOnThread;
            }//end loop
        } else {
            return;
        }


    }//end method

    private static void delegateIntervalOnThread(int intervalStart, int intervalEnd) {
        primeFinder(intervalStart, intervalEnd);
    }//end method

    static void primeFinder(int start, int end) {


        Runnable runnable = () -> {
            findAllPrimesInInterval(start, end);
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }//end method

    static void findAllPrimesInInterval(int start, int end) {

        for (int i = start; i <= end; i++) {
            if (isNumberPrime(i)) {

                try {
                    mutex.acquire();

                    allPrimes[primeIndex] = i;
                    primeIndex++;
                    mutex.release();

                    // release lock
                    semaphore.release();

                } catch (Exception e) {
                    System.out.println("error: " + e);
                    mutex.release();

                    // release lock
                    semaphore.release();
                }

            }
        }

    }

    static boolean isNumberPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i <= number / 2; i++) {
            if ((number % i) == 0) {
                return false;
            }
        }
        return true;
    }

}//end class


