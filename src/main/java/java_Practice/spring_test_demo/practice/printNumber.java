package java_Practice.spring_test_demo.practice;

public class printNumber {

    public static void printNum(int num){

        for(int i = 1 ; i <= num; i++){
            for(int j = 1 ; j <= i ; j++){
                System.out.print(j + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        printNum(5);
    }
}
