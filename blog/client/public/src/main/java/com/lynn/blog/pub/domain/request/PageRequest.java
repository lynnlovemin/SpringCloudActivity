package com.lynn.blog.pub.domain.request;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 分页参数
 */
public class PageRequest implements Serializable {

    //当前页
    @NotNull
    private Integer pageNumber = 1;

    //页大小
    @NotNull
    private Integer pageSize = 10;

    private int start;

    private int limit;

    public int getStart() {
        return (this.pageNumber - 1) * this.pageSize;
    }

    public int getLimit() {
        return this.pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
