package java_Practice.spring_test_demo.practice;

public class demo {

    public static void main(String[] args) {
        String value = "PAVAN";
        StringBuilder reverseValue = new StringBuilder();
        for(int i = value.length()-1; i >=0 ; i--){
            reverseValue.append(value.charAt(i));
        }
//        for (char c : value.toCharArray()){
//            reverseValue.append(c);
//        }
        System.out.println(reverseValue);
    }
}
