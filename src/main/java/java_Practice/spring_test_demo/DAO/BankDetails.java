package java_Practice.spring_test_demo.DAO;

import java_Practice.spring_test_demo.model.AccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface BankDetails extends JpaRepository<AccountDetailsEntity,Integer> {


    @Query(value = "SELECT * FROM account_details WHERE account_number = ?1", nativeQuery = true)
    AccountDetailsEntity getAmount(int accountNumber);

}
