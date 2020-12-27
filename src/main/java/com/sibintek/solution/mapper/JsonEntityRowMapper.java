package com.sibintek.solution.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class JsonEntityRowMapper implements RowMapper<Map<String, Object>> {
    private final Logger log = LoggerFactory.getLogger(JsonEntityRowMapper.class);
    private ObjectMapper mapper;

    public JsonEntityRowMapper() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public Map<String, Object> mapRow(ResultSet rs, int arg) throws SQLException {
        try {
            return mapper.readValue(rs.getString("plain_json"), new TypeReference<Map<String, Object>>(){});
        } catch (JsonProcessingException e) {
            log.error("Could not read value from DB");
        }
        return new HashMap<>(); //хз пока что тут вернуть
    }
}
