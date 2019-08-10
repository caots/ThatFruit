package com.bksoftwarevn.service;


import com.bksoftwarevn.entities.Record;

import java.util.List;

public interface RecordService {

    List<Record> findAllRecord();

    Record findByName(String name);

    boolean saveRecord(Record record);
}
