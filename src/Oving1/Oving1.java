package Oving1;

import java.util.Scanner;

public class Oving1 {

    public static void main(String[] args){
        System.out.println("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Your username is " + username);
    }
}

/*
 1 funksjon for å sortere
 1 funksjon som tar imot to tall (interval) og et gitt antall tråder
 1 funksjon for å dele opp intervallet likt fordelt basert på antall tråder
 1 funksjon for å lage et gitt antall threads
 1 funksjon som tar imot et intervall, og finner alle primtallene i intervallet
 */