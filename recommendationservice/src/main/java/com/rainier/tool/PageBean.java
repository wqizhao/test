package com.rainier.tool;

/**
 * 分页bean
 */

import java.util.List;

public class PageBean<T> {
    private List<T> list;	//每页记录
    private Integer totalCount;	//总记录数
    private Integer totalPages;	//总页数

    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
    public Integer getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    public Integer getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}