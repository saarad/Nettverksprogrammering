package Oving1;

import java.util.ArrayList;
import java.util.Scanner;

public class Oving1 {

    public static void main(String[] args)  {

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


    }

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

        return newArray;
    }

    static String primeFinder(int start, int end){

        Runnable runnable = () -> {
            System.out.println("start: " + start + ", end: " + end);
        };
        Thread thread = new Thread(runnable);
        thread.start();

        return "start: " + start + ", end: " + end;
    }

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

}




/*
 1 funksjon for å sortere
 1 funksjon som tar imot to tall (interval) og et gitt antall tråder
 1 funksjon for å dele opp intervallet likt fordelt basert på antall tråder
 1 funksjon for å lage et gitt antall threads
 */