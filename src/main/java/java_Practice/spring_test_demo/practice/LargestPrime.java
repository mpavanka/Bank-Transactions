package java_Practice.spring_test_demo.practice;


import java.util.Scanner;

public class LargestPrime {
    // Check if a number is prime
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num <= 3) {
            return true;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    // Find the largest prime less than or equal to a given number
    public static int findLargestPrime(int n) {
        for (int i = n; i >= 2; i--) {
            if (isPrime(i)) {
                return i;
            }
        }
        return -1; // No prime found (shouldn't occur for n >= 2)
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        scanner.close();

        int largestPrime = findLargestPrime(number);

        if (largestPrime != -1) {
            System.out.println("The largest prime less than or equal to " + number + " is: " + largestPrime);
        } else {
            System.out.println("No prime found less than or equal to " + number);
        }
    }
}