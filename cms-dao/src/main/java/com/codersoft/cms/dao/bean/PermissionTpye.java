package com.codersoft.cms.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: PermissionTpye
 * @author: Alex.D
 * @create: 2018-07-12 12:48
 * @description: 权限类型枚举
 **/
@ToString
@AllArgsConstructor
public enum PermissionTpye {

    DOCUMENT(new Byte("0"), "目录"),
    MENU(new Byte("1"),"菜单"),
    BUTTON(new Byte("2"),"按钮"),
    API(new Byte("3"), "API");

    @Getter
    @Setter
    private Byte code;
    @Getter
    @Setter
    private String desc;

}
