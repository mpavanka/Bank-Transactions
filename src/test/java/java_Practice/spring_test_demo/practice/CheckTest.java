package java_Practice.spring_test_demo.practice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckTest {

    @InjectMocks
    check check;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckPrimeWithPrimeNumber() {
        ResponseEntity<String> response = check.checkPrime(7);
        assertEquals("given number is prime for value: 7", response.getBody());
    }

    @Test
    void testCheckPrimeWithNonPrimeNumber() {
        ResponseEntity<String> response = check.checkPrime(4);
        assertEquals("given number is not prime for value: 4", response.getBody());
    }

    @Test
    void testCheckPrimeWithValueOne() {
        ResponseEntity<String> response = check.checkPrime(1);
        assertEquals("given number is not prime for value: 1", response.getBody());
    }

    @Test
    void testCheckPrimeWithNegativeNumber() {
        ResponseEntity<String> response = check.checkPrime(-5);
        assertEquals("given number is not prime for value: -5", response.getBody());
    }

    @Test
    void testPalindromeWithNormalString() {
        String result = check.palindrome("hello");
        assertEquals("olleh", result);
    }

    @Test
    void testPalindromeWithPalindromeString() {
        String result = check.palindrome("madam121211089000#$#");
        assertEquals("madam", result);
    }

    @Test
    void testPalindromeWithPalindromeStringNegative() {
        String result = check.palindrome(null);
        assertEquals("Please provide a valid string to reverse.", result);
    }

}