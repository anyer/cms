package com.codersoft.cms.dao.mapper.common;

import java.util.List;

/**
 * @program: BaseMapper
 * @author: Alex.D
 * @create: 2018-08-03 20:50
 * @description: BaseMapper
 **/
public interface BaseMapper<T, Long> {

    int insert(T record);

    int insertSelective(T record);

    int deleteByPrimaryKey(Long primaryKey);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    T selectByPrimaryKey(Long primaryKey);

    /**
     * 数量统计
     *
     * @param t 数据对象
     * @return
     */
    Long count(T t);

    /**
     * 获取对应查询条件的数据数量
     *
     * @param t 数据对象
     * @return
     */
    Long selectCount(T t);

    /**
     * 查询对应数据的分页集合
     *
     * @param t
     * @return
     */
    List<T> selectPageList(T t);

}
