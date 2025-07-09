package java_Practice.spring_test_demo.practice;

import java_Practice.spring_test_demo.DAO.BankDetails;
import java_Practice.spring_test_demo.model.AccountDetailsEntity;
import java_Practice.spring_test_demo.pojo.transferMoneyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class accountTransfer {

    @Autowired
    transferMoneyDTO transferRequest;

    @Autowired
    saveTransactionsDetails saveTransactionsDetails;

    @Autowired
    BankDetails bankDetails;
    public String validateDetails(transferMoneyDTO transferRequest){
        if (transferRequest.getBankAccountNumber() <= 0) {
            return "Invalid bank account number.";
        }
        if (transferRequest.getAmountToTransfer() <= 0) {
            return "Invalid amount to transfer.";
        }
        if (transferRequest.getBankAccountHolderName() == null || transferRequest.getBankAccountHolderName().isEmpty()) {
            return "Invalid bank account holder name.";
        }
        if (transferRequest.getBankName() == null || transferRequest.getBankName().isEmpty()) {
            return "Invalid bank name.";
        }
        return "no issue"; // Indicates that all validations passed
    }

    public String validateAccountDetails(transferMoneyDTO transferRequest) {
        AccountDetailsEntity accountDetails = bankDetails.getAmount(transferRequest.getBankAccountNumber());
        if (accountDetails == null) {
            return "Account not found for account number: " + transferRequest.getBankAccountNumber();
        }
        if (accountDetails.getBalance() < transferRequest.getAmountToTransfer()) {
            return "Insufficient balance in the account.";
        }
        return "Account validation successful.";
    }

    public String transferMoney(transferMoneyDTO transferRequest) {
        String validationMessage = validateDetails(transferRequest);
        if (!"no issue".equals(validationMessage)) {
            return validationMessage;
        }

        String accountValidationMessage = validateAccountDetails(transferRequest);
        if (!"Account validation successful.".equals(accountValidationMessage)) {
            return accountValidationMessage;
        }



        // Here you would typically call a service to process the transfer
        // For now, we just return a success message
        return "Transfer request is valid for account number: " + transferRequest.getBankAccountNumber();
    }


    @Transactional
    public String performTransaction(transferMoneyDTO transferRequest, int id) {
        // Validate transaction details
        String validationMessage = validateDetails(transferRequest);
        if (!"no issue".equals(validationMessage)) {
            return validationMessage;
        }

        // Validate account details
        AccountDetailsEntity senderAccount = bankDetails.getAmount(id);
        if (senderAccount == null) {
            return "Sender account not found for account number: " + transferRequest.getBankAccountNumber();
        }
        if (senderAccount.getBalance() < transferRequest.getAmountToTransfer()) {
            return "Insufficient balance in the sender's account.";
        }

        AccountDetailsEntity receiverAccount = bankDetails.getAmount(transferRequest.getBankAccountNumber());
        if (receiverAccount == null) {
            return "Receiver account not found for account number: " + transferRequest.getBankAccountNumber();
        }

        try{
            senderAccount.setBalance(senderAccount.getBalance() - transferRequest.getAmountToTransfer());
            receiverAccount.setBalance(receiverAccount.getBalance() + transferRequest.getAmountToTransfer());

            LocalDateTime transactionDate = LocalDateTime.now();
            // Update the database
            bankDetails.save(senderAccount);
            bankDetails.save(receiverAccount);
            saveTransactionsDetails.saveTransactionDetailsInDb(id, transferRequest.getBankAccountNumber(), transferRequest.getAmountToTransfer(), transactionDate.toString());
        }
        catch (Exception e) {
            return "Transaction failed: " + e.getMessage();
        }

        return "Transfer completed successfully";
    }
}
