package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {
    /**
     * 数据的集合
     */
    private List rows;
    /**
     * 总条数
     */
    private Long total;
}
