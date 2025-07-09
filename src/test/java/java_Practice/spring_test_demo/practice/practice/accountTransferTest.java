package java_Practice.spring_test_demo.practice.practice;

import java_Practice.spring_test_demo.DAO.BankDetails;
import java_Practice.spring_test_demo.model.AccountDetailsEntity;
import java_Practice.spring_test_demo.pojo.transferMoneyDTO;
import java_Practice.spring_test_demo.practice.accountTransfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class accountTransferTest {

    @InjectMocks
    private accountTransfer accountTransfer;

    @Mock
    private BankDetails bankDetails;

    private transferMoneyDTO transferRequest;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.openMocks(this);
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
    void testValidateAccountDetails_AccountNotFound() {
        when(bankDetails.getAmount(12345)).thenReturn(null);

        String result = accountTransfer.validateAccountDetails(transferRequest);
        assertEquals("Account not found for account number: 12345", result);
    }

    @Test
    void testValidateAccountDetails_InsufficientBalance() {
        AccountDetailsEntity accountDetails = new AccountDetailsEntity();
        accountDetails.setBalance(300);
        when(bankDetails.getAmount(12345)).thenReturn(accountDetails);

        String result = accountTransfer.validateAccountDetails(transferRequest);
        assertEquals("Insufficient balance in the account.", result);
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
}