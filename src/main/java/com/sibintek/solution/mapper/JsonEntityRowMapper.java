package com.sibintek.solution.mapper;

import com.sibintek.solution.entity.JsonEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class JsonEntityRowMapper implements RowMapper<JsonEntity> {
    @Override
    public JsonEntity mapRow(ResultSet rs, int arg) throws SQLException {
        JsonEntity jsonEntity = new JsonEntity();
        jsonEntity.setId(rs.getLong("id"));
        jsonEntity.setPlain_json(rs.getString("plain_json"));

        return jsonEntity;
    }
}
