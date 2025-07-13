package java_Practice.spring_test_demo.DAO;

import java_Practice.spring_test_demo.model.transactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrasactionDetails extends JpaRepository<transactionDetailsEntity, Integer> {


}

