package java_Practice.spring_test_demo.practice;

public class checkPrimeNumber {

    public static boolean checkPrimeNumber(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= num/2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean check = checkPrimeNumber(4);
        if (check) {
            System.out.println("prime number");
        } else {
            System.out.println("not prime number");
        }
    }
}
