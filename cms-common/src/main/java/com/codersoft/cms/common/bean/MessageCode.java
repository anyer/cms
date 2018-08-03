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
    ACCOUNT_DISABLE("ACCOUNT_DISABLE", "您的账号已停用，请联系管理员处理"),
    ACCOUNT_NO_INACTIVE("ACCOUNT_NO_INACTIVE", "您的账号因为激活，已停用，请联系管理员处理"),

    /**
     * 注册相关
     */
    REGISTER_USER_NAME_IS_EXIST("REGISTER_USER_NAME_IS_EXIST", "用户名已存在，请尝试其他用户名"),
    REGISTER_EMAIL_IS_EXIST("REGISTER_EMAIL_IS_EXIST", "注册邮箱已存在，请直接登录"),
    REGISTER_FAIL("REGISTER_FAIL", "注册失败"),
    REGISTER_ERROR("REGISTER_ERROR", "注册异常"),

    /**
     * 权限相关
     */
    PER_SAVE_FAIL("PER_SAVE_FAIL", "权限信息保存失败"),
    PER_DELETE_FAIL("PER_DELETE_FAIL", "权限信息删除失败"),
    PER_UPDATE_FAIL("PER_UPDATE_FAIL", "权限信息更新失败"),
    PER_DATA_LOAD_FAIL("PER_DATA_LOAD_FAIL", "权限数据加载失败"),
    ROLE_SAVE_FAIL("ROLE_SAVE_ERROR", "角色信息保存失败"),
    USER_SAVE_FAIL("USER_SAVE_ERROR", "用户信息保存失败"),
    USER_ROLE_SAVE_FAIL("USER_ROLE_SAVE_ERROR", "用户分配角色信息失败"),
    USER_DISABLE_FAIL("USER_FAIL_ERROR", "失效用户失败,程序异常"),
    ROLE_FAILK_ERROR("ROLE_FAILK_ERROR", "失效角色失败,程序异常"),
    RES_FAILK_ERROR("RES_FAILK_ERROR", "失效资源失败,程序异常"),
    ROLE_RES_SAVE_ERROR("ROLE_RES_SAVE_ERROR", "角色分配菜单失败"),
    ROLE_NAME_EXIST("ROLE_NAME_EXIST", "角色名称已存在，请重新输入"),
    ANNOUNCEMENT_SAVE_ERROR("ANNOUNCEMENT_SAVE_ERROR", "公告信息保存失败"),
    ANNOUNCEMENT_DEL_ERROR("ANNOUNCEMENT_DEL_ERROR", "删除公告失败,程序异常"),
    ANNOUNCEMENT_USER_INSERT_ERROR("ANNOUNCEMENT_USER_INSERT_ERROR", "标记为已读失败,程序异常"),

    /**
     * 分页相关
     */
    GET_PAGES_LIST_ERROR("GET_PAGES_LIST_FAIL", "获取分页数据获取异常");

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
