package java_Practice.spring_test_demo.practice;

import java_Practice.spring_test_demo.DAO.trasactionDetails;
import java_Practice.spring_test_demo.model.transactionDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class saveTransactionsDetails {

    @Autowired
    trasactionDetails tranDtl ;

    public String saveTransactionDetailsInDb(int accountFrom, int  accountTO, double amountTransferred,String transactionDate) {
        transactionDetailsEntity transaction = new transactionDetailsEntity();
        transaction.setToAccountNumber(accountTO);
        transaction.setFromAccountNumber(accountFrom);
        transaction.setAmountTransferred(amountTransferred);
        transaction.setTransactionDate(transactionDate);
        tranDtl.save(transaction);
        return "Transaction saved for account number ";
    }
}
