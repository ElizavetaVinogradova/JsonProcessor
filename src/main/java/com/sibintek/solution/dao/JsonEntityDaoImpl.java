package com.sibintek.solution.dao;

import com.sibintek.solution.entity.JsonEntity;
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
    private NamedParameterJdbcTemplate template;

    public JsonEntityDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<JsonEntity> findAll() {
        return template.query("select * from sibintek", new JsonEntityRowMapper());
    }

    @Override
    public JsonEntity findById(Long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return template.queryForObject(
                "select * from sibintek where id = :id",
                params,
                new JsonEntityRowMapper());
    }

    @Override
    public void insertJsonEntity(JsonEntity jsonEntity) {
        final String sql = "insert into sibintek(plain_json, binary_json) values(cast(:plain_json as json), cast(:binary_json as json))";

        KeyHolder pk = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", jsonEntity.getId())
                .addValue("plain_json", jsonEntity.getPlain_json());
        template.update(sql, param, pk);
    }
}
