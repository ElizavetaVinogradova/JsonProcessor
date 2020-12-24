package com.sibintek.solution.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JsonEntity implements Serializable {

    private Long id;
    private String plain_json;
    private String binary_json;
}



