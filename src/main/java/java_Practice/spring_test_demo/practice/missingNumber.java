package java_Practice.spring_test_demo.practice;

public class missingNumber {


    public static void missing(int[] nums) {
        int sum = (6 * (6 + 1)) / 2;
        int count = 0;
        for (int num : nums) {
            count = count + num;
        }
        System.out.println(sum - count);

    }

    public static void main(String[] args) {
        int nuums[] = {1, 2, 4, 5, 6};
        missing(nuums);
    }
}
