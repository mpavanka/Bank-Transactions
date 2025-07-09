package java_Practice.spring_test_demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "transaction_details")
@Data
public class transactionDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "from_account_number")
    private int fromAccountNumber;

    @Column(name = "to_account_number")
    private int toAccountNumber;

    @Column(name = "amount_transferred")
    private double amountTransferred;

    @Column(name = "transaction_date")
    private String transactionDate;

    // Complete constructor
    public transactionDetailsEntity(int transactionId, int fromAccountNumber, int toAccountNumber, double amountTransferred, String transactionDate) {
        this.transactionId = transactionId;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amountTransferred = amountTransferred;
        this.transactionDate = transactionDate;
    }

    // Default constructor
    public transactionDetailsEntity() {
    }
}