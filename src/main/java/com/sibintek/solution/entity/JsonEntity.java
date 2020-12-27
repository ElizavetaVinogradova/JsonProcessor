package com.sibintek.solution.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JsonEntity implements Serializable {

    private Long id;
    private Map<String, Object> plain_json;
}



