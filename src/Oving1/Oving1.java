package Oving1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Oving1 {
    private static int primesOnThread = 0; //number of primes


    public static void main(String[] args){
        System.out.println("Write number of threads you want: ");
        Scanner scanner = new Scanner(System.in);
        String numberOfThreads = scanner.nextLine();
        int threads = Integer.parseInt(numberOfThreads);
        System.out.println("Write start of interval: ");
        int intervalStart = Integer.parseInt(scanner.nextLine());
        System.out.println("Write end of interval: ");
        int intervalEnd = Integer.parseInt(scanner.nextLine());
        delegatePrimesOnThreads(threads,intervalStart,intervalEnd);
    }//end main

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
            System.out.println(number + ",");
        }
        return newArray;
    }//end method

    private static void delegatePrimesOnThreads(int threads, int intervalStart, int interValEnd){
        int intervalSize = (interValEnd-intervalStart)+1;
        int start = intervalStart;
        if(threads>intervalSize)primesOnThread = threads/intervalSize;
        else primesOnThread = intervalSize/threads;
        System.out.println("primesOnThread " + primesOnThread);
        int end = start+primesOnThread;
       for(int i = start; i<=end; i++){
           System.out.println("Start: " + start);
           System.out.println("End: " + end);
           if(end >=interValEnd){
               end=interValEnd+1;
               delegateIntervalOnThread(start,end);
               return;
           }
           delegateIntervalOnThread(start, end);
           start = start + primesOnThread;
           end = end + primesOnThread;
       }//end loop
    }//end method

    private static void delegateIntervalOnThread(int intervalStart, int intervalEnd){
        for(int i = intervalStart; i<intervalEnd; i++){
            System.out.println(i);
        }//end loop
    }//end method

    static String primeFinder(int start, int end){

        Runnable runnable = () -> {
            System.out.println("start: " + start + ", end: " + end);
        };
        Thread thread = new Thread(runnable);
        thread.start();

        return "start: " + start + ", end: " + end;
    }//end method

    static int[] findAllPrimesInInterval(int start, int end) {
        ArrayList<Integer> primes = new ArrayList<>();

        for(int i = start; i<=end; i++){
            if(isNumberPrime(i)) primes.add(i);
        }

        int[] primesArray = new int[primes.size()];
        for(int i = 0; i<primes.size(); i++){
            primesArray[i] = primes.get(i);
        }

        for(int i: primesArray){
            System.out.print(" " + i + ", ");
        }

        return primesArray;
    }

    static boolean isNumberPrime(int number){
        if(number < 2) return false;
        for(int i = 2; i <= number/2; i++){
            if((number % i) == 0){
                return false;
            }
        }
        return true;
    }


    /*
        int[] one = {0,1,2,3,4,5,6};
        int[] two = {7,8,9,10,11,12};
        joinTwoArrays(one, two);
        System.out.println("Enter amount of threads");
        Scanner scanner = new Scanner(System.in);
        int amountOfThreads = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter startInterval");
        int startInterval = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter endInterval");
        int endInterval = Integer.parseInt(scanner.nextLine());
        System.out.println("n threads : " + amountOfThreads + "\nstart : " + startInterval + "\nend :" + endInterval );
        Oving1 o = new Oving1();
        for(int i = 0; i<amountOfThreads; i++){
            findAllPrimesInInterval(startInterval, endInterval);
        }
     */

}//end class


/*
 1 funksjon for å sortere
 1 funksjon som tar imot to tall (interval) og et gitt antall tråder (done)
 1 funksjon for å dele opp intervallet likt fordelt basert på antall tråder(done)
 1 funksjon for å lage et gitt antall threads
 */
