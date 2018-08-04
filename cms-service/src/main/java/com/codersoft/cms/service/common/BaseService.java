package com.codersoft.cms.service.common;

import java.util.Map;

/**
 * @program: BaseService
 * @author: Alex.D
 * @create: 2018-08-03 18:29
 * @description: BaseService
 **/
public interface BaseService<T, Long> {

    /**
     * 添加数据（全参数）
     *
     * @param t 数据对象
     * @return
     */
    int addAll(T t);

    /**
     * 添加数据（参数可选）
     *
     * @param t 数据对象
     * @return
     */
    int addSelective(T t);

    /**
     * 删除对应id的数据
     *
     * @param id 数据ID
     * @return
     */
    int deleteById(Long id);

    /**
     * 更新对应ID的数据（可选参数）
     *
     * @param t 数据对象
     * @return
     */
    int updateByIdSelective(T t);

    /**
     * 更新对应ID的数据（全参数）
     *
     * @param t 数据对象
     * @return
     */
    int updateById(T t);

    /**
     * 保存或更新数据（可选参数）
     *
     * @param id 数据ID
     * @param t  数据对象
     * @return
     */
    int saveOrUpdateSelective(Long id, T t);

    /**
     * 保存或更新数据（全参数）
     *
     * @param id 数据ID
     * @param t  数据对象
     * @return
     */
    int saveOrUpdate(Long id, T t);

    /**
     * 查询对应ID的数据
     *
     * @param id 数据ID
     * @return
     */
    T selectBytId(Long id);

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
     * 获取对应数据的分页集合
     *
     * @param t 数据对象
     * @return
     */
    Map<String, Object> selectPageList(T t);

}
