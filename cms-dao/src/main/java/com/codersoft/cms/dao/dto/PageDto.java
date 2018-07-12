package com.codersoft.cms.dao.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: PageDto
 * @author: Alex.D
 * @create: 2018-07-12 22:34
 * @description: 分页dto
 **/
@Getter
@Setter
public class PageDto {

    //当前页
    private int page;
    //每页显示记录数
    private int limit;
    //每页开始记录数
    private int startNum;
    //总数
    private int count;
    //分页数量
    private long pageSize;

    public Long getPageSize() {
        if (this.count < 0L) {
            return -1L;
        }
        long count = this.count / this.limit;
        if (this.count % this.limit > 0L) {
            count += 1L;
        }
        return count;
    }
}
