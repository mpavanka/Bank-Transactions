package java_Practice.spring_test_demo.practice;

import java.util.Arrays;

public class maxNum {

    public static void checkMaxAndMin(int [] nums){

        int maxone = 0;
        int maxTwo = 0;
        Arrays.stream(nums).sorted();
        System.out.println("---------------------------------------------");
        System.out.println(nums[nums.length-1]);
        System.out.println(nums[nums.length-2]);
        System.out.println("---------------------------------------------");
        for( int num : nums){
            if (maxone < num){
                maxTwo = maxone;
                maxone = num;
            }
            else if (maxTwo < num){
                maxTwo= num;
            }
        }
        System.out.println(maxone);
        System.out.println(maxTwo);

    }

    public static void main(String[] args) {
        int list[] = { 1,2,3,5,6,4,11,9,10};
        checkMaxAndMin(list);
    }
}
