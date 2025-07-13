package java_Practice.spring_test_demo.practice;// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;



@Component
public class check {
    public ResponseEntity<String> checkPrime(int value) {
        if (value <= 1) {
            return ResponseEntity.ok("given number is not prime for value: " + value);
        }
        for (int i = 2; i <= value/2; i++) {
            if (value % i == 0) {
                return ResponseEntity.ok("given number is not prime for value: " + value);
            }
        }
        return ResponseEntity.ok("given number is prime for value: " + value);
    }

    public String palindrome(String value) {
        if ((value  == null)) {
            return "Please provide a valid string to reverse.";
        }
        String s = value.replaceAll("[^a-zA-Z]", "");
        StringBuilder newS = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            newS.append(s.charAt(i));
        }
        return newS.toString();
    }
}