package com.codersoft.cms.dao.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @program: DirectoryPermissionDto
 * @author: Alex.D
 * @create: 2018-07-12 17:44
 * @description: 目录类型权限dto
 **/
@Setter
@Getter
public class DirectoryPermissionDto implements Serializable {

    private Long id;
    private String title;
    private String icon;
    private String url;

    List<MenuPermissionDto> menuPermissionDtoList;

}
