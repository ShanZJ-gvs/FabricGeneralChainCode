package com.gvssimux.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;



@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class QueryResult {
    private String key;

    private String json;
}
