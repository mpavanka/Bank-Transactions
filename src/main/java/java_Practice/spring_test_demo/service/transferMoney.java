package java_Practice.spring_test_demo.service;

import java_Practice.spring_test_demo.pojo.reverseStringValue;
import java_Practice.spring_test_demo.pojo.transferMoneyDTO;
import java_Practice.spring_test_demo.practice.accountTransfer;
import java_Practice.spring_test_demo.practice.check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class transferMoney {


    accountTransfer accountTransfer;

    check check;

    public transferMoney(accountTransfer accountTransfer, check check) {
        this.accountTransfer = accountTransfer;
        this.check = check;
    }


    public String performTransfer(transferMoneyDTO transferRequest, int id) {
        return accountTransfer.performTransaction(transferRequest, id);

    }

    public String replaceString(reverseStringValue reverseStringValue) {
        return check.palindrome(reverseStringValue.getStringValue());
    }

    public ResponseEntity<?> getAccountDetails(Integer id) {
        return accountTransfer.getAccountDetails(id);
    }
}
