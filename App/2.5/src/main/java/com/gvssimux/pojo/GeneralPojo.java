package com.gvssimux.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GeneralPojo {

    private String value1;

    private Integer value2;

    private List<String> value3;

}
