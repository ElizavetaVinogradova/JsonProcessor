package com.sibintek.solution.dao;

import com.sibintek.solution.entity.JsonEntity;

import java.util.List;

/**
 * Набор операций с таблицей sibintek
 */
public interface JsonEntityDao {
    List<JsonEntity> findAll();
    JsonEntity findById(Long id);
    void insertJsonEntity(JsonEntity jsonEntity);
}
