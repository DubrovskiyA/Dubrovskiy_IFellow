package ru.ifellow.task2.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {
    private ObjectMapper mapper;

    public Mapper() {
        mapper = new ObjectMapper();
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}
