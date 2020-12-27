package com.sibintek.solution.controller;

import com.sibintek.solution.dao.JsonEntityDaoImpl;
import com.sibintek.solution.entity.JsonEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<JsonEntity> getAllEntities() {
        log.debug("REST request to get all JsonEntities ");
        return jsonEntityDaoImpl.findAll();
    }

    @PostMapping("/entities")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createEntity(@RequestBody JsonEntity entity) {
        log.debug("REST request to create JsonEntity ");
        jsonEntityDaoImpl.insertJsonEntity(entity);
    }

    @GetMapping("/entities/{id}")
    public JsonEntity getOne(@Param("id") Long id) {
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