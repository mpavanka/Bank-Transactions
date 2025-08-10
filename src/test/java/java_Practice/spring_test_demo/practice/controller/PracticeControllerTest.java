package java_Practice.spring_test_demo.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java_Practice.spring_test_demo.DAO.BankDetails;
import java_Practice.spring_test_demo.controller.practice;
import java_Practice.spring_test_demo.model.AccountDetailsEntity;
import java_Practice.spring_test_demo.pojo.reverseStringValue;
import java_Practice.spring_test_demo.pojo.transferMoneyDTO;
import java_Practice.spring_test_demo.practice.accountTransfer;
import java_Practice.spring_test_demo.practice.check;
import java_Practice.spring_test_demo.service.transferMoney;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.relational.core.sql.When;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class PracticeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private transferMoney accountTransferRequest;

    @Mock
    private accountTransfer accountTransfer;

    @Mock
    BankDetails bankDetails;

    @Mock
    private check check;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testTransferMoney_ValidRequest() throws Exception {
        transferMoneyDTO dto = new transferMoneyDTO();
        dto.setBankAccountNumber(1234);
        dto.setAmountToTransfer(100);
        dto.setBankName("test");
        dto.setBankAccountHolderName("Jhon");

        AccountDetailsEntity senderAccount = new AccountDetailsEntity();
        senderAccount.setBalance(1000);
        senderAccount.setAccountNumber(11);
        senderAccount.setAccountHolderName("Sender");
        senderAccount.setBankName("Sender Bank");
        senderAccount.setAccountType("Saving");

        AccountDetailsEntity receiverAccount = new AccountDetailsEntity();
        receiverAccount.setBalance(500);
        receiverAccount.setAccountNumber(dto.getBankAccountNumber());
        receiverAccount.setAccountHolderName("Receiver");
        receiverAccount.setBankName("Receiver Bank");
        receiverAccount.setAccountType("Saving");

        when(accountTransfer.validateDetails(any())).thenReturn("no issue");
        when(bankDetails.getAmount(11)).thenReturn(senderAccount);
        when(bankDetails.getAmount(dto.getBankAccountNumber())).thenReturn(receiverAccount);
        when(accountTransferRequest.performTransfer(dto, 11)).thenReturn("Transfer completed successfully");

        mockMvc.perform(post("/practice/API/v1/Problem/transferMoney/11")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bankAccountNumber\":1234,\"amountToTransfer\":100,\"bankName\":\"test\",\"bankAccountHolderName\":\"Jhon\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Transfer completed successfully"));
    }

    @Test
    void testTransferMoney_InvalidDetails() throws Exception {
        transferMoneyDTO dto = new transferMoneyDTO();
        dto.setBankAccountNumber(-1);

        when(accountTransfer.validateDetails(any())).thenReturn("Invalid bank account number.");

        mockMvc.perform(post("/practice/API/v1/Problem/transferMoney/11")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk()) // Still 200 because the controller returns String, not ResponseEntity
                .andExpect(content().string("Invalid bank account number."));
    }

    @Test
    void testReplaceString_Valid() throws Exception {
        reverseStringValue input = new reverseStringValue();
        input.setStringValue("hello");

        when(accountTransferRequest.replaceString(any())).thenReturn("olleh");

        mockMvc.perform(post("/practice/API/v1/Problem/reversestring")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(content().string("olleh"));
    }

    @Test
    void testReplaceString_NullInput() throws Exception {
        reverseStringValue input = new reverseStringValue();
        input.setStringValue(null);

        mockMvc.perform(post("/practice/API/v1/Problem/reversestring")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(content().string("Please provide a valid string to reverse."));
    }

    @Test
    void testAccountById_Valid() throws Exception {
        when(accountTransferRequest.getAccountDetails(1001)).thenReturn(new ResponseEntity("Account Found", HttpStatus.OK));

        mockMvc.perform(get("/practice/API/v1/Problem/accountNumber/1001"))
                .andExpect(status().isOk())
                .andExpect(content().string("Account Found"));
    }

    @Test
    void testAccountById_Null() throws Exception {
        // Even if accountNumber is null, the method expects a path variable
        // So this test simulates a call with 0 or invalid number
        when(accountTransferRequest.getAccountDetails(0)).thenReturn(new ResponseEntity("Account Not Found", HttpStatus.OK));

        mockMvc.perform(get("/practice/API/v1/Problem/accountNumber/0"))
                .andExpect(status().isOk())
                .andExpect(content().string("Account Not Found"));
    }
}
