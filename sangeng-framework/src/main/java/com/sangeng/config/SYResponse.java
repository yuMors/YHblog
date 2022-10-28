package com.sangeng.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SYResponse <T> {
    private Integer code;
    private String msg;
    private T data;
}
