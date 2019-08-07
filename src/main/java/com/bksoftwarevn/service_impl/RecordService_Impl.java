package com.bksoftwarevn.service_impl;

import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.repository.RecordRepository;
import com.bksoftwarevn.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService_Impl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;


    @Override
    public Record findByName(String name) {
        return recordRepository.findByName(name);
    }

    @Override
    public boolean saveRecord(Record record) {
        recordRepository.save(record);
        return true;
    }
}
