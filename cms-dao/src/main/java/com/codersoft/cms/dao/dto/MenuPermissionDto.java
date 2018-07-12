package com.codersoft.cms.dao.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: DirectoryPermissionDto
 * @author: Alex.D
 * @create: 2018-07-12 17:44
 * @description: 菜单类型权限dto
 **/
@Getter
@Setter
public class MenuPermissionDto implements Serializable {

    private String title;
    private String icon;
    private String url;

}
