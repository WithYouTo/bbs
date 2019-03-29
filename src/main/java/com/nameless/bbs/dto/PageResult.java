package com.nameless.bbs.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 后台翻页响应数据
 *
 * @Author LHR
 * Create By 2017/8/10
 */
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 2575409524838590642L;

    public PageResult() {
    }

    private String draw;//表示请求次数

    private Long recordsTotal;//总记录数

    private Long recordsFiltered;//过滤后的总记录数

    private T data;//具体的数据

    public PageResult(String draw, Long recordsTotal, Long recordsFiltered, T data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

}
