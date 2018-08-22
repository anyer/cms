package com.codersoft.cms.service.admin.impl;

import com.codersoft.cms.common.bean.ResultMessage;
import com.codersoft.cms.dao.dto.DirectoryPermissionDto;
import com.codersoft.cms.dao.dto.MenuPermissionDto;
import com.codersoft.cms.dao.entity.SysPermission;
import com.codersoft.cms.dao.entity.SysPermissionExample;
import com.codersoft.cms.dao.entity.SysRolePermissionExample;
import com.codersoft.cms.dao.mapper.admin.system.SysPermissionMapper;
import com.codersoft.cms.dao.mapper.admin.system.SysRolePermissionMapper;
import com.codersoft.cms.service.admin.SysPermissionService;
import com.codersoft.cms.service.common.impl.BaseServiceImpl;
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
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission, Long> implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public int addSelective(SysPermission sysPermission) {
        if (StringUtils.isEmpty(sysPermission.getUri())) {
            sysPermission.setUri("#");
        }
        return super.addSelective(sysPermission);
    }

    @Override
    public int updateByIdSelective(SysPermission sysPermission) {

        //TODO 权限状态改变时，角色权限关系表处理
        //权限状态为0时，清除对应角色权限关系
        if(sysPermission.getStatus() == 0) {
            SysRolePermissionExample sysRolePermissionExample = new SysRolePermissionExample();
            sysRolePermissionExample.createCriteria().andPermissionIdEqualTo(sysPermission.getPermissionId());
            if(sysRolePermissionMapper.deleteByExample(sysRolePermissionExample) < 0) {
                return -2;
            }
        }

        if (StringUtils.isEmpty(sysPermission.getUri())) {
            sysPermission.setUri("#");
        }

        sysPermission.setModifyTime(new Date());
        return super.updateByIdSelective(sysPermission);
    }

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

    @Override
    public List<SysPermission> selectPermissionListByUserId(Long userId) {
        return sysPermissionMapper.selectListByUserId(userId);
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
     * 获取菜单权限级别对应的父权限
     *
     * @param perLevel 权限级别
     * @return
     */
    @Override
    public List<SysPermission> selectParentPermissionListByPerLevel(Integer perLevel) {
        return sysPermissionMapper.selectParentListByPerLevel(perLevel);
    }

    /**
     * 获取对应角色ID的权限树
     *
     * @param roleId 角色ID
     * @return
     */
    @Override
    public List<Map<String, Object>> permissionTree(Long roleId) {

        List<SysPermission> rolePermission = sysPermissionMapper.selectListByRoleId(roleId);
        List<SysPermission> permissionList = sysPermissionMapper.selectByExample(new SysPermissionExample());
        List<Map<String, Object>> permissionTreeList = new ArrayList<>();
        for (SysPermission sysPermission : permissionList) {
            Map<String, Object> permissionTree = new HashMap<>();
            permissionTree.put("id", sysPermission.getPermissionId());
            permissionTree.put("name", sysPermission.getPerName() + "  " + sysPermission.getPerCode());
            permissionTree.put("pId", sysPermission.getParentId());
            permissionTree.put("open", true);
            permissionTree.put("checked", false);
            for (SysPermission permission : rolePermission) {
                if (permission.getPermissionId().equals(sysPermission.getPermissionId())) {
                    permissionTree.put("checked", true);
                    break;
                }
            }
            permissionTreeList.add(permissionTree);
        }
        return permissionTreeList;
    }

    /**
     * 删除对应权限ID的权限及权限角色关系
     *
     * @param permissionId
     * @return
     */
    @Override
    public int deleteAndRoleById(Long permissionId) {

        int res = sysPermissionMapper.deleteByPrimaryKey(permissionId);
        if (res > 0) {
            SysRolePermissionExample sysRolePermissionExample = new SysRolePermissionExample();
            sysRolePermissionExample.createCriteria().andPermissionIdEqualTo(permissionId);
            res = sysRolePermissionMapper.deleteByExample(sysRolePermissionExample);
        } else {
            res = 0;
        }
        return res;
    }

}
