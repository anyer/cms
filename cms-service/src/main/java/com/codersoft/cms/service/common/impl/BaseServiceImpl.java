package com.codersoft.cms.service.common.impl;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.dao.mapper.common.BaseMapper;
import com.codersoft.cms.service.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: BaseServiceImpl
 * @author: Alex.D
 * @create: 2018-08-03 18:32
 * @description: BaseServiceImpl
 **/
@Service
public class BaseServiceImpl<T, Long> implements BaseService<T, Long> {

    @Autowired
    private BaseMapper<T, Long> baseMapper;

    /**
     * 添加数据（全参数）
     *
     * @param t 数据对象
     * @return
     */
    @Override
    public int addAll(T t) {
        return baseMapper.insert(t);
    }

    /**
     * 添加数据（可选参数）
     *
     * @param t 数据对象
     * @return
     */
    @Override
    public int addSelective(T t) {
        return baseMapper.insertSelective(t);
    }

    /**
     * 删除对应ID的数据
     *
     * @param id 数据ID
     * @return
     */
    @Override
    public int deleteById(Long id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新对应ID的数据（可选参数）
     *
     * @param t 数据对象
     * @return
     */
    @Override
    public int updateByIdSelective(T t) {
        return baseMapper.updateByPrimaryKeySelective(t);
    }

    /**
     * 更新对应ID的数据（全参数）
     *
     * @param t 数据对象
     * @return
     */
    @Override
    public int updateById(T t) {
        return baseMapper.updateByPrimaryKey(t);
    }

    /**
     * 保存或更新数据（可选参数）
     *
     * @param id 数据ID
     * @param t  数据对象
     * @return
     */
    @Override
    public int saveOrUpdateSelective(Long id, T t) {
        if (baseMapper.selectByPrimaryKey(id) == null) {
            return baseMapper.insertSelective(t);
        }
        return baseMapper.updateByPrimaryKeySelective(t);
    }

    /**
     * 保存或更新数据（全参数）
     *
     * @param id 数据ID
     * @param t  数据对象
     * @return
     */
    @Override
    public int saveOrUpdate(Long id, T t) {
        if (baseMapper.selectByPrimaryKey(id) == null) {
            return baseMapper.insert(t);
        }
        return baseMapper.updateByPrimaryKey(t);
    }

    /**
     * 查询对应ID的数据对象
     *
     * @param id 数据ID
     * @return
     */
    @Override
    public T selectBytId(Long id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    /**
     * 数量统计
     *
     * @param t 数据对象
     * @return
     */
    @Override
    public Long count(T t) {
        return baseMapper.count(t);
    }

    /**
     * 获取对应查询条件的数据数量
     *
     * @param t 数据对象
     * @return
     */
    @Override
    public Long selectCount(T t) {
        return baseMapper.selectCount(t);
    }

    /**
     * 查询对应对象的分页集合
     *
     * @param t 数据对象
     * @return
     */
    @Override
    public Map<String, Object> selectPageList(T t) {

        List<T> entityList = null;
        Long count = null;
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            entityList = baseMapper.selectPageList(t);
            count = baseMapper.selectCount(t);

            map.put("code", 0);
            map.put("msg", MessageCode.SUCCESS.getMsg());
            map.put("count", count == null ? 0L : count);
            map.put("data", entityList);
            return map;
        } catch (Exception ex) {
            map.put("msg", MessageCode.EXCEPTION.getMsg() + " : " + ex.getMessage());
            map.put("code", MessageCode.EXCEPTION.getCode());
            map.put("data", null);
            map.put("count", count == null ? 0L : count);
        }
        return map;
    }
}
