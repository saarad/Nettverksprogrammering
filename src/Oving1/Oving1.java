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

    static int[] joinTwoArrays(int[] one, int[] two){

        int[] newArray = new int[one.length + two.length];
        for(int i = 0; i<newArray.length; i++){
            if(i<one.length){
                newArray[i] = one[i];
            } else {
                newArray[i] = two[i - one.length];
            }
        }

        for(int number : newArray){
            System.out.println(number + ",");
        }

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


}//end class

/*
 1 funksjon for å sortere
 1 funksjon som tar imot to tall (interval) og et gitt antall tråder (done)
 1 funksjon for å dele opp intervallet likt fordelt basert på antall tråder(done)
 1 funksjon for å lage et gitt antall threads
 */
