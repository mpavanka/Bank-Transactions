package java_Practice.spring_test_demo.controller;

import java_Practice.spring_test_demo.pojo.transferMoneyDTO;
import java_Practice.spring_test_demo.practice.accountTransfer;
import java_Practice.spring_test_demo.practice.check;
import java_Practice.spring_test_demo.service.transferMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/practice/API/v1/Problem")
public class practice {

    @Autowired
    check check;

    @Autowired
    transferMoney accountTransferRequest;

    @Autowired
    accountTransfer accountTransfer;
    @GetMapping("/getCheckPrimeNumber/{id}")
    public String hello(@PathVariable Integer id) {
        return check.checkPrime(id).getBody();
    }

    @PostMapping("/transferMoney/{id}")
    public String transferMoney(@RequestBody transferMoneyDTO transferRequest, @PathVariable Integer id) {
        String validationMessage = accountTransfer.validateDetails(transferRequest);
        if (!Objects.equals(validationMessage, "no issue")) {
            return validationMessage;
        }
        return accountTransferRequest.performTransfer(transferRequest, id);
        // Here you would typically call a service to process the transfer
    }
}
