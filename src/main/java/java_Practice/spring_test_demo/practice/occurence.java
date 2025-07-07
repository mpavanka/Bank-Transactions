package java_Practice.spring_test_demo.practice;

import java.util.HashMap;
import java.util.Map;

public class occurence {

    public static Map<Character, Integer> occuerneceString(String string){
        Map<Character, Integer> res = new HashMap<>();
        char [] str = string.toCharArray();
        for (char c: str) {
            if (c != ' ') {
                if (res.containsKey(c)) {
                    res.put(c, res.get(c) + 1);
                } else {
                    res.put(c, 1);
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {

        String string = "Hello World";
        Map<Character, Integer> map =  occuerneceString(string);
        for (Map.Entry<Character, Integer> resMap : map.entrySet()) {
            System.out.println(resMap.getKey() + " <---> " + resMap.getValue());
        }

    }
}
