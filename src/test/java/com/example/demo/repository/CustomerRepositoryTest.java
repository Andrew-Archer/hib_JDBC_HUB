package com.example.demo.repository;

import com.example.demo.DemoApplication;
import com.example.demo.model.Customer;
import configuration.JdbcHubConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = {DemoApplication.class, JdbcHubConfig.class})
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testHibernate() {
        var user = new Customer();
        user.setFirstName("John");
        user.setLastName("Doe");
        customerRepository.save(user);
    }
}