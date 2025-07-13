package java_Practice.spring_test_demo.practice.controller;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class practice {

    private MockMvc mockMvc;

    @Mock
    private check check;

    @Mock
    private transferMoney accountTransferRequest;

    @Mock
    private accountTransfer accountTransfer;

    @InjectMocks
    private practice practiceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(practiceController).build();
    }

    @Test
    void testGetCheckPrimeNumber() throws Exception {
        when(check.checkPrime(7)).thenReturn(ResponseEntity.ok("given number is prime for value: 7"));

        mockMvc.perform(get("/practice/API/v1/Problem/getCheckPrimeNumber/7"))
                .andExpect(status().isOk())
                .andExpect(content().string("given number is prime for value: 7"));

        verify(check, times(1)).checkPrime(7);
    }

    @Test
    void testTransferMoney_ValidDetails() throws Exception {
        transferMoneyDTO transferRequest = new transferMoneyDTO();
        transferRequest.setBankAccountNumber(12345);
        transferRequest.setAmountToTransfer(500);
        transferRequest.setBankAccountHolderName("John Doe");
        transferRequest.setBankName("Test Bank");

        when(accountTransfer.validateDetails(any(transferMoneyDTO.class))).thenReturn("no issue");
        when(accountTransferRequest.performTransfer(any(transferMoneyDTO.class), eq(11))).thenReturn("Transfer completed successfully");

        mockMvc.perform(post("/practice/API/v1/Problem/transferMoney/11")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bankAccountNumber\":12345,\"amountToTransfer\":500,\"bankAccountHolderName\":\"John Doe\",\"bankName\":\"Test Bank\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Transfer completed successfully"));

        verify(accountTransfer, times(1)).validateDetails(any(transferMoneyDTO.class));
        verify(accountTransferRequest, times(1)).performTransfer(any(transferMoneyDTO.class), eq(11));
    }

    @Test
    void testTransferMoney_InvalidDetails() throws Exception {
        transferMoneyDTO transferRequest = new transferMoneyDTO();
        transferRequest.setBankAccountNumber(-1);

        when(accountTransfer.validateDetails(any(transferMoneyDTO.class))).thenReturn("Invalid bank account number.");

        mockMvc.perform(post("/practice/API/v1/Problem/transferMoney/11")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bankAccountNumber\":-1,\"amountToTransfer\":500,\"bankAccountHolderName\":\"John Doe\",\"bankName\":\"Test Bank\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid bank account number."));

        verify(accountTransfer, times(1)).validateDetails(any(transferMoneyDTO.class));
        verify(accountTransferRequest, never()).performTransfer(any(transferMoneyDTO.class), eq(11));
    }

    @Test
    void testReplaceString_ValidString() throws Exception {
        reverseStringValue reverseStringValue = new reverseStringValue();
        reverseStringValue.setStringValue("hello");

        when(accountTransferRequest.replaceString(any(reverseStringValue.class))).thenReturn("olleh");

        mockMvc.perform(post("/practice/API/v1/Problem/reversestring")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"stringValue\":\"hello\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("olleh"));

        verify(accountTransferRequest, times(1)).replaceString(any(reverseStringValue.class));
    }

    @Test
    void testReplaceString_NullString() throws Exception {
        mockMvc.perform(post("/practice/API/v1/Problem/reversestring")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"stringValue\":null}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Please provide a valid string to reverse."));

        verify(accountTransferRequest, never()).replaceString(any(reverseStringValue.class));
    }
}