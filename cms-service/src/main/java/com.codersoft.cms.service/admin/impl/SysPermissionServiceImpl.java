package com.codersoft.cms.service.admin.impl;

import com.alibaba.fastjson.JSON;
import com.codersoft.cms.dao.dto.DirectoryPermissionDto;
import com.codersoft.cms.dao.dto.MenuPermissionDto;
import com.codersoft.cms.dao.entity.SysPermission;
import com.codersoft.cms.dao.mapper.SysPermissionMapper;
import com.codersoft.cms.service.admin.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
     * @return
     */
    @Override
    public List<DirectoryPermissionDto> selectDirectoryPermissionList() {

        List<DirectoryPermissionDto> directoryPermissionDtoList = sysPermissionMapper.selectDirectoryPermissionList();
        if(directoryPermissionDtoList != null && !directoryPermissionDtoList.isEmpty()){
            return directoryPermissionDtoList;
        }
        return null;
    }
    /**
     * 根据父Id查询各级菜单类型权限集合
     * @param parentId 父ID
     * @return
     */
    @Override
    public List<DirectoryPermissionDto> selectMenuPermissionListByParentId(Long parentId) {

        //二级菜单
        List<SysPermission> secondLevelMenuPermissionList = sysPermissionMapper.selectMenuPermissionListByParentId(parentId);
        if(secondLevelMenuPermissionList != null && !secondLevelMenuPermissionList.isEmpty()){

            List<DirectoryPermissionDto> directoryPermissionDtoList = new ArrayList<>();

            for (SysPermission secondLevelMenuPermission : secondLevelMenuPermissionList) {
                DirectoryPermissionDto menuDto = new DirectoryPermissionDto();
                menuDto.setTitle(secondLevelMenuPermission.getPerName());
                menuDto.setIcon(secondLevelMenuPermission.getIconName());
                menuDto.setUrl(secondLevelMenuPermission.getUri());

                //三级菜单
                List<SysPermission> thirdLevelMenuPermissionList = sysPermissionMapper.selectMenuPermissionListByParentId(secondLevelMenuPermission.getPermissionId());
                if(thirdLevelMenuPermissionList != null && !thirdLevelMenuPermissionList.isEmpty()) {

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
}
