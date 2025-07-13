package java_Practice.spring_test_demo.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class transferDTO {
    private int transactionId;
    private int fromAccountNumber;
    private int toAccountNumber;
    private double amountTransferred;
    private String transactionDate;
}
