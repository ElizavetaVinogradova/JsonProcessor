package com.sibintek.solution.dao;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

/**
 * Набор операций с таблицей sibintek
 */
public interface JsonEntityDao {
    List<Map<String, Object>> findAll();
    Map<String, Object> findById(Long id);
    void insertJsonEntity(Map<String, Object> plain_json) throws JsonProcessingException;
}
