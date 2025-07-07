package java_Practice.spring_test_demo.practice;

import java.util.*;

public class uniqueElements {

    public static Set<String> duplicteNumbers(List<String> nums){
        Set<String> uniqueuSet = new HashSet<>();
        Set<String> duplicate = new HashSet<>();
        for (String num : nums){

            if(!uniqueuSet.add(num)){
                duplicate.add(num);
            }
        }
        return duplicate;
    }

    public static void main(String[] args) {

        List<String> nums = new ArrayList<>();
        Collections.addAll(nums,"pavan","rishi","pavan","chinnu","nani","chinnu","nani");
        Collections.sort(nums);
        System.out.println(nums);
        Set<String> duplicateValues  = duplicteNumbers(nums);
        System.out.println(duplicateValues);
    }
}
