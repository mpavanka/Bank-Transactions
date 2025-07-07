package java_Practice.spring_test_demo.practice;

public class porintStar {



    public static void star(int num){

        for(int i = num ; i >= 1; i--){
            for(int j = i ; j >= 1 ; j--){
                System.out.print("*");
                System.out.print(" ");
            }
            System.out.println(" ");
        }

        for(int i = 1 ; i<=num; i++){
            for(int j = 1 ; j<=i ; j++){
                System.out.print("* ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        star(5);
    }
}
