package com.sibintek.solution.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sibintek.solution.dao.JsonEntityDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EntityController {
    private final Logger log = LoggerFactory.getLogger(EntityController.class);

    @Autowired
    private JsonEntityDaoImpl jsonEntityDaoImpl;

    public EntityController() {
    }
    public EntityController(JsonEntityDaoImpl jsonEntityDao) {
        this.jsonEntityDaoImpl = jsonEntityDao;
    }

    @GetMapping("/entities")
    public List<Map<String, Object>> getAllEntities() {
        log.debug("REST request to get all JsonEntities ");
        return jsonEntityDaoImpl.findAll();
    }

    @PostMapping("/entities")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createEntity(@RequestBody Map<String, Object> request) throws JsonProcessingException {
        log.debug("REST request to create JsonEntity ");
        jsonEntityDaoImpl.insertJsonEntity(request);
    }

    @GetMapping("/entities/{id}")
    public Map<String, Object> getOne(@PathVariable("id") Long id) {
        log.debug("REST request to get JsonEntity by id ");
        return jsonEntityDaoImpl.findById(id);
    }
}


/*Тестовое задание:
Создать хранилище с рест сервисами, реализующий следующие методы:
1) получить все объекты json из бд;
2) сохранить объект json в бд;
3) получить json по id;

На вход может прийти абсолютно любая структура JSON объекта, однако мы знаем, что у каждого объекта есть поле id.
Хранение объектов должно быть реализовано в БД. СУБД - любая реляционная.
Большим плюсом будет реализация метода, осуществляющего поиск по содержимому json.*/