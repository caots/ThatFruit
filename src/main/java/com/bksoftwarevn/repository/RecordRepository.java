package com.bksoftwarevn.repository;

import com.bksoftwarevn.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {

    Record findByName(String name);

}
