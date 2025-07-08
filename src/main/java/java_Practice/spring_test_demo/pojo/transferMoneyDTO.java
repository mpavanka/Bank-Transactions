package java_Practice.spring_test_demo.pojo;

import lombok.Data;
import org.springframework.stereotype.Controller;

@Controller
@Data
public class transferMoneyDTO {

    int bankAccountNumber;
    String bankAccountHolderName;
    int amountToTransfer;
    String bankName;

}
