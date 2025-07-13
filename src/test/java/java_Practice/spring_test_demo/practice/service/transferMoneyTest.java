package java_Practice.spring_test_demo.practice.service;

import java_Practice.spring_test_demo.pojo.reverseStringValue;
import java_Practice.spring_test_demo.pojo.transferMoneyDTO;
import java_Practice.spring_test_demo.practice.accountTransfer;
import java_Practice.spring_test_demo.practice.check;
import java_Practice.spring_test_demo.service.transferMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class transferMoneyTest {

    @InjectMocks
    private transferMoney transferMoneyService;

    @Mock
    private accountTransfer accountTransfer;

    @Mock
    private check check;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPerformTransfer_Success() {
        transferMoneyDTO transferRequest = new transferMoneyDTO();
        transferRequest.setBankAccountNumber(12345);
        transferRequest.setAmountToTransfer(500);
        transferRequest.setBankAccountHolderName("John Doe");
        transferRequest.setBankName("Test Bank");

        when(accountTransfer.performTransaction(transferRequest, 11)).thenReturn("Transfer completed successfully");

        String result = transferMoneyService.performTransfer(transferRequest, 11);

        assertEquals("Transfer completed successfully", result);
        verify(accountTransfer, times(1)).performTransaction(transferRequest, 11);
    }

    @Test
    void testPerformTransfer_Failure() {
        transferMoneyDTO transferRequest = new transferMoneyDTO();
        transferRequest.setBankAccountNumber(-1);

        when(accountTransfer.performTransaction(transferRequest, 11)).thenReturn("Invalid bank account number.");

        String result = transferMoneyService.performTransfer(transferRequest, 11);

        assertEquals("Invalid bank account number.", result);
        verify(accountTransfer, times(1)).performTransaction(transferRequest, 11);
    }

    @Test
    void testReplaceString_ValidString() {
        reverseStringValue reverseStringValue = new reverseStringValue();
        reverseStringValue.setStringValue("hello");

        when(check.palindrome("hello")).thenReturn("olleh");

        String result = transferMoneyService.replaceString(reverseStringValue);

        assertEquals("olleh", result);
        verify(check, times(1)).palindrome("hello");
    }

    @Test
    void testReplaceString_NullString() {
        reverseStringValue reverseStringValue = new reverseStringValue();
        reverseStringValue.setStringValue(null);

        when(check.palindrome(null)).thenReturn("Please provide a valid string to reverse.");

        String result = transferMoneyService.replaceString(reverseStringValue);

        assertEquals("Please provide a valid string to reverse.", result);
        verify(check, times(1)).palindrome(null);
    }
}