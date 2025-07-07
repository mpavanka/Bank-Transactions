package java_Practice.spring_test_demo.practice;

public class swape {

    public static void swapeNumbers(){
        int i = 12;
        int j = 21;
        System.out.println("before -------------");

        System.out.println(i);
        System.out.println(j);

        i = i + j;
        j = i - j;
        i = i - j;
        System.out.println("After -------------");
        System.out.println(i);
        System.out.println(j);

    }

    public static void main(String[] args) {
        swapeNumbers();
    }
}
