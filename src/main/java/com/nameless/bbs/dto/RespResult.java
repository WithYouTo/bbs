package com.nameless.bbs.dto;


import java.io.Serializable;

/**
 * Rest响应数据
 *
 * @Author LHR
 * Create By 2017/8/11
 */
public class RespResult implements Serializable {

    private static final long serialVersionUID = 5543451912312306262L;
    /**
     * 响应业务状态
     */
    private Integer status;

    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 错误信息
     */
    private String error;

    /**
     * 返回总页数
     */
    private long pageSize;

    /**
     * 本页返回数量
     */
    private Integer total;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 请求成功
     *
     * @param status
     */
    public RespResult(Integer status) {
        this.status = status;
    }

    /**
     * 请求成功并携带数据
     *
     * @param status
     * @param data
     */
    public RespResult(Integer status, Object data) {
        this.status = status;
        this.data = data;
    }

    /**
     * 请求成功被携带参数与页数
     *
     * @param status
     * @param data
     * @param pageSize
     * @param total
     */
    public RespResult(Integer status, Object data, long pageSize, Integer total) {
        this.status = status;
        this.data = data;
        this.pageSize = pageSize;
        this.total = total;
    }

    /**
     * 请求失败
     *
     * @param status
     * @param error
     */
    public RespResult(Integer status, String error) {
        this.status = status;
        this.error = error;
    }

    public static RespResult ok() {
        return new RespResult(StateEnum.SUCCESS.getState());
    }

    public static RespResult ok(Object data) {
        return new RespResult(StateEnum.SUCCESS.getState(), data);
    }

    public static RespResult warn(String warn) {
        return new RespResult(StateEnum.WARN.getState(), warn);
    }

    public static RespResult error(String error) {
        return new RespResult(StateEnum.ERROR.getState(), error);
    }

    public static RespResult ok(Object data, long pageSize, Integer total) {
        return new RespResult(StateEnum.SUCCESS.getState(), data, pageSize, total);
    }
}
