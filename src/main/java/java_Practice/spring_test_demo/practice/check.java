package java_Practice.spring_test_demo.practice;// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Scanner;

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
}