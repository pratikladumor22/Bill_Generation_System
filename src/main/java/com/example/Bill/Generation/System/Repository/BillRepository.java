package com.example.Bill.Generation.System.Repository;

import com.example.Bill.Generation.System.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill , Long> {
}
