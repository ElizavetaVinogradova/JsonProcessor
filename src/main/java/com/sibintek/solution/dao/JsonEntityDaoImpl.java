package com.sibintek.solution.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sibintek.solution.mapper.JsonEntityRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JsonEntityDaoImpl implements JsonEntityDao {
    private ObjectMapper mapper;
    private NamedParameterJdbcTemplate template;

    public JsonEntityDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
        mapper = new ObjectMapper();
    }

    @Override
    public List<Map<String, Object>> findAll() {
        return template.query("select * from sibintek", new JsonEntityRowMapper()); //У меня нет такого конструктора в классе!
    }

    @Override
    public Map<String, Object> findById(Long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return template.queryForObject(
                "select * from sibintek where id = :id",
                params,
                new JsonEntityRowMapper());
    }

    @Override
    public void insertJsonEntity(Map<String, Object> request) throws JsonProcessingException {
        final String sql = "insert into sibintek(plain_json) values(cast(:plain_json as json))";
        String convertedRequest = mapper.writeValueAsString(request);
        KeyHolder pk = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", request.get("id"))
                .addValue("plain_json", convertedRequest);
        template.update(sql, param, pk);
    }
}
