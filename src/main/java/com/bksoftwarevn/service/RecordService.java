package com.bksoftwarevn.service;


import com.bksoftwarevn.entities.Record;

public interface RecordService {

    Record findByName(String name);

    boolean saveRecord(Record record);
}
