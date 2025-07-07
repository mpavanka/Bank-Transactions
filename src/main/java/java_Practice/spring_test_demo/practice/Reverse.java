package java_Practice.spring_test_demo.practice;


import java.util.Scanner;

public class Reverse {

    public static String reverseStringWithBuffer(String val) {
        StringBuilder reversed = new StringBuilder(val);
//        reversed.reverse();
        System.out.println( "--------------------------" + reversed + "--------------------------");
        for(int i = val.length() - 1; i >= 0; i--){
            reversed.append(val.charAt(i));
        }
        return reversed.toString();
    }

    public static String reverseString(String val) {
        String reversed = "";
        System.out.println("     ---------------------    ");
        for(int i = val.length() - 1 ; i >= 0 ; i--){
            reversed = reversed + val.charAt(i);
        }
        return reversed;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter value ti reverse");
        String name  = sc.nextLine();
        String string = reverseString(name);
        String string1 = reverseStringWithBuffer(name);
        System.out.println("###############################");
        System.out.println("Reversed String : "  + string);
        System.out.println("###############################");
        System.out.println("Reversed String : "  + string1);
    }
}