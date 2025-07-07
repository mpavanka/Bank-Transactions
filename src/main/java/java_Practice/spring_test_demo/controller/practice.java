package java_Practice.spring_test_demo.controller;

import java_Practice.spring_test_demo.practice.check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practice/API/v1/Problem")
public class practice {

    @Autowired
    check check;
    @GetMapping("/getCheckPrimeNumber/{id}")
    public String hello(@PathVariable Integer id) {
        return check.checkPrime(id).getBody();
    }
}
