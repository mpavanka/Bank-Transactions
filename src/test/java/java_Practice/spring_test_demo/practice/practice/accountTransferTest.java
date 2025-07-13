package java_Practice.spring_test_demo.practice.practice;

import java_Practice.spring_test_demo.DAO.BankDetails;
import java_Practice.spring_test_demo.model.AccountDetailsEntity;
import java_Practice.spring_test_demo.pojo.transferMoneyDTO;
import java_Practice.spring_test_demo.practice.accountTransfer;
import java_Practice.spring_test_demo.practice.saveTransactionsDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class accountTransferTest {

    @InjectMocks
    private accountTransfer accountTransfer;

    @Mock
    private BankDetails bankDetails;

    @Mock
    private saveTransactionsDetails saveTransactionsDetails;

    private transferMoneyDTO transferRequest;

    @BeforeEach
    void setUp() {

        transferRequest = new transferMoneyDTO();
        transferRequest.setBankAccountNumber(12345);
        transferRequest.setAmountToTransfer(500);
        transferRequest.setBankAccountHolderName("John Doe");
        transferRequest.setBankName("Test Bank");
    }

    @Test
    void testValidateDetails_ValidDetails() {
        String result = accountTransfer.validateDetails(transferRequest);
        assertEquals("no issue", result);
    }

    @Test
    void testValidateDetails_InvalidAccountNumber() {
        transferRequest.setBankAccountNumber(-1);
        String result = accountTransfer.validateDetails(transferRequest);
        assertEquals("Invalid bank account number.", result);
    }
    @Test
    void testValidateDetails_InvalidAmount() {
        transferRequest.setAmountToTransfer(-1);
        String result = accountTransfer.validateDetails(transferRequest);
        assertEquals("Invalid amount to transfer.", result);
    }
    @Test
    void testValidateDetailsNullAccountHolderName() {
        transferRequest.setBankAccountHolderName(null);
        String result = accountTransfer.validateDetails(transferRequest);
        assertEquals("Invalid bank account holder name.", result);
    }

    @Test
    void testValidateDetailsNullAccountEmpty() {
        transferRequest.setBankAccountHolderName("");
        String result = accountTransfer.validateDetails(transferRequest);
        assertEquals("Invalid bank account holder name.", result);
    }

    @Test
    void testValidateDetailsEmptyAccountHolderName() {
        transferRequest.setBankName("");
        String result = accountTransfer.validateDetails(transferRequest);
        assertEquals("Invalid bank name.", result);
    }

    @Test
    void testValidateDetailsNullBankAccountName() {
        transferRequest.setBankName(null);
        String result = accountTransfer.validateDetails(transferRequest);
        assertEquals("Invalid bank name.", result);
    }

    @Test
    void testPerformTransaction_Success() {
        AccountDetailsEntity senderAccount = new AccountDetailsEntity();
        senderAccount.setBalance(1000);
        AccountDetailsEntity receiverAccount = new AccountDetailsEntity();
        receiverAccount.setBalance(500);

        when(bankDetails.getAmount(11)).thenReturn(senderAccount);
        when(bankDetails.getAmount(transferRequest.getBankAccountNumber())).thenReturn(receiverAccount);

        String result = accountTransfer.performTransaction(transferRequest, 11);

        assertEquals("Transfer completed successfully", result);
        verify(bankDetails, times(1)).save(senderAccount);
        verify(bankDetails, times(1)).save(receiverAccount);
    }

    @Test
    void testPerformTransaction_InsufficientBalance() {
        AccountDetailsEntity senderAccount = new AccountDetailsEntity();
        senderAccount.setBalance(300);

        when(bankDetails.getAmount(12345)).thenReturn(senderAccount);

        String result = accountTransfer.performTransaction(transferRequest, 12345);

        assertEquals("Insufficient balance in the sender's account.", result);
    }

    @Test
    void testPerformTransaction_SenderAccountNotFound() {

        String result = accountTransfer.performTransaction(transferRequest, 12345);

        assertEquals("Sender account not found for account number: " + transferRequest.getBankAccountNumber(), result);
    }

    @Test
    void testPerformTransaction_ReceiverAccountNotFound() {

        AccountDetailsEntity senderAccount = new AccountDetailsEntity();
        senderAccount.setBalance(1000);
        when(bankDetails.getAmount(11)).thenReturn(senderAccount);


        when(bankDetails.getAmount(transferRequest.getBankAccountNumber())).thenReturn(null);

        String result = accountTransfer.performTransaction(transferRequest, 11);

        assertEquals("Receiver account not found for account number: " + transferRequest.getBankAccountNumber(), result);
    }

    @Test
    void testPerformTransactionFail() {
        AccountDetailsEntity senderAccount = new AccountDetailsEntity();
        senderAccount.setBalance(1000);
        AccountDetailsEntity receiverAccount = new AccountDetailsEntity();
        receiverAccount.setBalance(500);

        when(bankDetails.getAmount(11)).thenReturn(senderAccount);
        when(bankDetails.getAmount(transferRequest.getBankAccountNumber())).thenReturn(receiverAccount);
        when(bankDetails.save(any(AccountDetailsEntity.class))).thenThrow(new RuntimeException("Database error"));
        String result = accountTransfer.performTransaction(transferRequest, 11);

        assertEquals("Transaction failed: Database error", result);
    }

    @Test
    void testPerformTransactionNoResponse() {
        AccountDetailsEntity receiverAccount = new AccountDetailsEntity();
        receiverAccount.setAccountNumber(-500);

        when(bankDetails.getAmount(11)).thenReturn(receiverAccount);

        String result = accountTransfer.performTransaction(transferRequest, 11);

        assertEquals("Insufficient balance in the sender's account.", result);
    }

}