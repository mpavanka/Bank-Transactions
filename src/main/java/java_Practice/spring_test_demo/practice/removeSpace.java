package java_Practice.spring_test_demo.practice;

import java.util.StringTokenizer;

public class removeSpace {

    public static StringBuffer removeDUplicatespace(String string){

        StringTokenizer st = new StringTokenizer(string, " ");
        StringBuffer sb = new StringBuffer();
        while (st.hasMoreElements()){
            sb.append(st.nextElement()).append(" ");
        }
        return sb;
    }

    public static void main(String[] args) {
        StringBuffer value  = removeDUplicatespace("I love                           My India");
        System.out.println(value.toString());
    }
}
