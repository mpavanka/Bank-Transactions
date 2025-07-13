package java_Practice.spring_test_demo.controller;

import java_Practice.spring_test_demo.pojo.reverseStringValue;
import java_Practice.spring_test_demo.pojo.transferMoneyDTO;
import java_Practice.spring_test_demo.practice.accountTransfer;
import java_Practice.spring_test_demo.practice.check;
import java_Practice.spring_test_demo.service.transferMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/practice/API/v1/Problem")
public class practice {

    check check;
    transferMoney accountTransferRequest;
    accountTransfer accountTransfer;

    @Autowired
    public practice(transferMoney accountTransferRequest,accountTransfer accountTransfer, check check) {
        this.accountTransferRequest = accountTransferRequest;
        this.accountTransfer = accountTransfer;
        this.check = check;
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


    @PostMapping("/reversestring")
    public String replaceString(@RequestBody reverseStringValue reverseStringValue) {

        if ((reverseStringValue.getStringValue() ==null)) {
            return "Please provide a valid string to reverse.";
        }
        return accountTransferRequest.replaceString(reverseStringValue);
        // Here you would typically call a service to process the transfer
    }

    @GetMapping("/accountNumber/{accountNumber}")
    public ResponseEntity<?> accountById(@PathVariable Integer accountNumber) {

        if ((accountNumber == null)) {
            ResponseEntity.internalServerError().body("Please provide a valid account number.");
        }
        return accountTransferRequest.getAccountDetails(accountNumber);
    }
}
