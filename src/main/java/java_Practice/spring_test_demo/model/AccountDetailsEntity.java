package java_Practice.spring_test_demo.model;

import jakarta.persistence.*;

@Entity
@lombok.Data
@Table(name = "account_details")
public class AccountDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_number")
    private int accountNumber;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "balance")
    private double balance;

    @Column(name = "bank_name")
    private String bankName;
}
