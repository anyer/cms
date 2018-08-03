package com.codersoft.cms.dao.dto;

/**
 * @program: PageDto
 * @author: Alex.D
 * @create: 2018-07-12 22:34
 * @description: 分页dto
 **/
public class PageDto {

    /**
     * 当前页
     */
    private int page;
    /**
     * 每页显示记录数
     */
    private int limit;
    /**
     * 每页开始记录数
     */
    private int start;
    /**
     * 总数
     */
    private long totalCount = -1L;
    /**
     * 分页数量
     */
    private long totalSize = 0;

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getStart() {
        this.start = (page-1)*limit;
        return start;
    }
    public long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
    public long getTotalSize() {
        if (this.totalCount < 0L) {
            return -1L;
        }

        long count = this.totalCount / this.limit;
        if (this.totalCount % this.limit > 0L) {
            count += 1L;
        }
        return count;
    }
}
