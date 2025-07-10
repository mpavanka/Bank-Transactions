package java_Practice.spring_test_demo.practice.practice;

import java_Practice.spring_test_demo.model.transactionDetailsEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java_Practice.spring_test_demo.practice.saveTransactionsDetails;
import java_Practice.spring_test_demo.DAO.trasactionDetails;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class saveTransactionsDetailsTest {

    @InjectMocks
    private saveTransactionsDetails saveTransactionsDetails;

    @Mock
    private trasactionDetails trasactionDetails;

    @Test
    public void testSaveTransactionDetailsInDb() {
        // Arrange
        int accountFrom = 10;
        int accountTO = 1;
        double amountTransferred = 500;
        String transactionDate = "2023-10-01";
        transactionDetailsEntity transaction = new transactionDetailsEntity();
        transaction.setToAccountNumber(accountTO);
        transaction.setFromAccountNumber(accountFrom);
        transaction.setAmountTransferred(amountTransferred);
        transaction.setTransactionDate(transactionDate);

        when(trasactionDetails.save((transaction))).thenReturn((transaction));

        String result = saveTransactionsDetails.saveTransactionDetailsInDb(accountFrom, accountTO, amountTransferred, transactionDate);

        assertEquals("Transaction saved for account number ", result);
        verify(trasactionDetails, times(1)).save(any(transactionDetailsEntity.class));
    }
}