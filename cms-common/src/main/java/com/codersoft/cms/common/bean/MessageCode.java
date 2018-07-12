package com.codersoft.cms.common.bean;

/**
 * 消息Code 枚举
 */
public enum MessageCode {

    // 成功
    SUCCESS("SUCCESS", "成功"),
    //失败
    FAILURE("FAILURE", "失败"),

    //错误
    ERROR("ERROR", "错误"),
    //警告
    WARNING("WARNING", "警告"),
    //异常
    EXCEPTION("EXCEPTION", "异常"),
    //信息
    INFO("INFO","信息"),

    /**
     * 登陆验证相关
     */
    LOGIN_NAME_IS_NULL("LOGIN_NAME_IS_NULL", "用户名不能为空"),
    LOGIN_PASSWORD_IS_NULL("LOGIN_PASSWORD_IS_NULL", "密码不能为空"),
    LOGIN_FAIL("LOGIN_FAIL", "用户名或密码不匹配"),
    LOGIN_ERROR("LOGIN_ERROR", "系统登录异常"),
    CAPTCHA_IS_NULL("CAPTCHA_IS_NULL", "验证码不能为空"),
    CAPTCHA_IS_ERROR("CAPTCHA_IS_ERROR", "验证码输入错误"),

    /**
     * 权限相关
     */
    RES_SAVE_ERROR("RES_SAVE_ERROR", "资源信息保存失败"),
    ROLE_SAVE_ERROR("ROLE_SAVE_ERROR", "角色信息保存失败"),
    USER_SAVE_ERROR("USER_SAVE_ERROR", "用户信息保存失败"),
    USER_ROLE_SAVE_ERROR("USER_ROLE_SAVE_ERROR", "用户分配角色信息失败"),
    USER_FAIL_ERROR("USER_FAIL_ERROR", "失效用户失败,程序异常"),
    ROLE_FAILK_ERROR("ROLE_FAILK_ERROR", "失效角色失败,程序异常"),
    RES_FAILK_ERROR("RES_FAILK_ERROR", "失效资源失败,程序异常"),
    USER_LOGIN_NAME_EXIST("USER_LOGIN_NAME_EXIST", "用户账号已存在，请重新输入"),
    ROLE_RES_SAVE_ERROR("ROLE_RES_SAVE_ERROR", "角色分配菜单失败"),
    ROLE_NAME_EXIST("ROLE_NAME_EXIST", "角色名称已存在，请重新输入"),
    ANNOUNCEMENT_SAVE_ERROR("ANNOUNCEMENT_SAVE_ERROR", "公告信息保存失败"),
    ANNOUNCEMENT_DEL_ERROR("ANNOUNCEMENT_DEL_ERROR", "删除公告失败,程序异常"),
    ANNOUNCEMENT_USER_INSERT_ERROR("ANNOUNCEMENT_USER_INSERT_ERROR", "标记为已读失败,程序异常");

    MessageCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 消息Code
     */
    private String code;

    /**
     * 消息信息
     */
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
