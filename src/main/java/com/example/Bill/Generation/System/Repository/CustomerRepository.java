package com.example.Bill.Generation.System.Repository;

import com.example.Bill.Generation.System.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer , Integer> {
    Customer findBymobileNo(long mobileNo);
}
