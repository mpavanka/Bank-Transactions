package java_Practice.spring_test_demo.service;

import java_Practice.spring_test_demo.pojo.transferMoneyDTO;
import java_Practice.spring_test_demo.practice.accountTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class transferMoney {

    @Autowired
    accountTransfer accountTransfer;


    public String performTransfer(transferMoneyDTO transferRequest, int id) {
        return accountTransfer.performTransaction(transferRequest, id);

    }
}
