package com.codersoft.cms.service.admin.impl;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.dao.dto.DirectoryPermissionDto;
import com.codersoft.cms.dao.dto.MenuPermissionDto;
import com.codersoft.cms.dao.entity.SysPermission;
import com.codersoft.cms.dao.mapper.SysPermissionMapper;
import com.codersoft.cms.service.admin.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @program: SysPermissionServiceImpl
 * @author: Alex.D
 * @create: 2018-07-12 15:16
 * @description: 权限相关业务操作实现类
 **/
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 获取目录类型权限集合
     *
     * @return
     */
    @Override
    public List<DirectoryPermissionDto> selectDirectoryPermissionList() {

        List<DirectoryPermissionDto> directoryPermissionDtoList = sysPermissionMapper.selectDirectoryPermissionList();
        if (directoryPermissionDtoList != null && !directoryPermissionDtoList.isEmpty()) {
            return directoryPermissionDtoList;
        }
        return null;
    }

    /**
     * 根据父Id查询各级菜单类型权限集合
     *
     * @param parentId 父ID
     * @return
     */
    @Override
    public List<DirectoryPermissionDto> selectMenuPermissionListByParentId(Long parentId) {

        //二级菜单
        List<SysPermission> secondLevelMenuPermissionList = sysPermissionMapper.selectMenuPermissionListByParentId(parentId);
        if (secondLevelMenuPermissionList != null && !secondLevelMenuPermissionList.isEmpty()) {

            List<DirectoryPermissionDto> directoryPermissionDtoList = new ArrayList<>();

            for (SysPermission secondLevelMenuPermission : secondLevelMenuPermissionList) {
                DirectoryPermissionDto menuDto = new DirectoryPermissionDto();
                menuDto.setTitle(secondLevelMenuPermission.getPerName());
                menuDto.setIcon(secondLevelMenuPermission.getIconName());
                menuDto.setUrl(secondLevelMenuPermission.getUri());

                //三级菜单
                List<SysPermission> thirdLevelMenuPermissionList = sysPermissionMapper.selectMenuPermissionListByParentId(secondLevelMenuPermission.getPermissionId());
                if (thirdLevelMenuPermissionList != null && !thirdLevelMenuPermissionList.isEmpty()) {

                    List<MenuPermissionDto> menuPermissionDtoList = new ArrayList<>();
                    for (SysPermission thirdLevelMenuPermission : thirdLevelMenuPermissionList) {

                        MenuPermissionDto menuPermissionDto = new MenuPermissionDto();
                        menuPermissionDto.setTitle(thirdLevelMenuPermission.getPerName());
                        menuPermissionDto.setIcon(thirdLevelMenuPermission.getIconName());
                        menuPermissionDto.setUrl(thirdLevelMenuPermission.getUri());
                        menuPermissionDtoList.add(menuPermissionDto);
                    }
                    menuDto.setMenuPermissionDtoList(menuPermissionDtoList);
                }
                directoryPermissionDtoList.add(menuDto);
            }
            return directoryPermissionDtoList;
        }
        return null;
    }

    /**
     * 获取带分页的权限集合
     *
     * @param sysPermission
     * @return
     */
    @Override
    public Map<String, Object> selectPermissionPageList(SysPermission sysPermission) {

        List<SysPermission> sysPermissionList = null;
        Long count = 0L;
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            sysPermissionList = sysPermissionMapper.selectPermissionPageList(sysPermission);
            count = sysPermissionMapper.selectPermissionCount(sysPermission);

            map.put("code", 0);
            map.put("msg", MessageCode.SUCCESS.getMsg());
            map.put("count", count);
            map.put("data", sysPermissionList);

            return map;
        } catch (Exception ex) {

            map.put("msg", MessageCode.EXCEPTION.getMsg() + " : " + ex.getMessage());
            map.put("code", MessageCode.EXCEPTION.getCode());
            map.put("data", null);
            map.put("count", count);
            return map;
        }
    }

    /**
     * 添加权限信息
     *
     * @param sysPermission
     * @return
     */
    @Override
    public int addSysPermission(SysPermission sysPermission) {
        if(StringUtils.isEmpty(sysPermission.getUri())) {
            sysPermission.setUri("#");
        }
        return sysPermissionMapper.insertSelective(sysPermission);
    }

    /**
     * 获取对应ID的权限信息
     *
     * @param permissionId 权限ID
     * @return
     */
    @Override
    public SysPermission selectSysPermissionById(Long permissionId) {
        return sysPermissionMapper.selectPermissionByPrimaryKey(permissionId);
    }

    /**
     * 获取菜单权限级别对应的父权限
     *
     * @param perLevel     权限级别
     * @return
     */
    @Override
    public List<SysPermission> selectParentPermissionListByPerLevel(Integer perLevel) {
        return sysPermissionMapper.selectParentListByPerLevel(perLevel);
    }

    /**
     * 更新权限信息
     *
     * @param sysPermission
     * @return
     */
    @Override
    public int updatePermission(SysPermission sysPermission) {
        sysPermission.setModifyTime(new Date());
        return sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
    }

    /**
     * 删除权限信息
     *
     * @param permissionId 权限ID
     * @return
     */
    @Override
    public int deletePermission(Long permissionId) {
        return sysPermissionMapper.deleteByPrimaryKey(permissionId);
    }
}
