/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-07-12 22:53:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_data
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_data`;
CREATE TABLE `t_sys_data` (
  `data_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '基础数据ID',
  `cate_id` int(10) unsigned NOT NULL COMMENT '基础数据类别ID',
  `data_code` varchar(255) DEFAULT NULL COMMENT '基础数据Code',
  `data_name` varchar(255) DEFAULT NULL COMMENT '基础数据名称',
  `dataSort` int(11) unsigned DEFAULT NULL COMMENT '基础数据排序',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `is_delete` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `create_by` varchar(100) NOT NULL COMMENT '创建于',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) NOT NULL COMMENT '更新于',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础数据表';

-- ----------------------------
-- Table structure for t_sys_data_category
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_data_category`;
CREATE TABLE `t_sys_data_category` (
  `cate_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '基础数据类别ID',
  `cate_code` varchar(255) DEFAULT NULL COMMENT '类别Code',
  `cate_name` varchar(255) DEFAULT NULL COMMENT '类别名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `is_delete` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `create_by` varchar(100) NOT NULL COMMENT '创建于',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) NOT NULL COMMENT '更新于',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`cate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础数据类别表';

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log` (
  `log_id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `user_id` bigint(11) unsigned NOT NULL COMMENT '用户ID',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `ip_address` varchar(50) NOT NULL COMMENT 'IP地址',
  `uri` varchar(255) NOT NULL COMMENT '访问路径',
  `req_method` varchar(255) DEFAULT NULL COMMENT '请求方法（GET、POST、PUT、DELETE等）',
  `operate` varchar(255) DEFAULT NULL COMMENT '操作',
  `method` varchar(255) DEFAULT NULL COMMENT '调用方法',
  `args` varchar(255) DEFAULT NULL COMMENT '参数',
  `method_return` varchar(255) DEFAULT NULL COMMENT '方法返回',
  `deal_time` bigint(11) unsigned DEFAULT NULL COMMENT '处理时间（ms）',
  `exception_info` varchar(255) DEFAULT NULL COMMENT '异常信息',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否有效（0：无效；1：有效）',
  `is_delete` tinyint(2) unsigned DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建于',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) DEFAULT NULL COMMENT '更新于',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_organization`;
CREATE TABLE `t_sys_organization` (
  `org_id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '组织ID',
  `parent_id` bigint(11) unsigned NOT NULL COMMENT '父ID',
  `org_code` varchar(255) NOT NULL COMMENT '组织编码',
  `org_name` varchar(255) NOT NULL COMMENT '组织名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `is_delete` tinyint(2) unsigned DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建于',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) DEFAULT NULL COMMENT '更新于',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织信息表';

-- ----------------------------
-- Table structure for t_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission` (
  `permission_id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id` bigint(11) unsigned NOT NULL COMMENT '父ID',
  `per_code` varchar(255) NOT NULL COMMENT '权限编码',
  `per_name` varchar(255) NOT NULL COMMENT '权限名称',
  `uri` varchar(255) NOT NULL DEFAULT '#' COMMENT '地址',
  `icon_name` varchar(100) DEFAULT NULL COMMENT '图标名称',
  `per_type` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '类型（0：目录；1：菜单；2：按钮；3：API）',
  `order_num` int(11) unsigned NOT NULL COMMENT '优先级',
  `status` tinyint(2) unsigned DEFAULT '1' COMMENT '显示隐藏（0：隐藏；1：显示）',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `is_delete` tinyint(2) unsigned DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建于',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) DEFAULT NULL COMMENT '更新于',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `role_id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_code` varchar(255) NOT NULL COMMENT '角色编码',
  `role_name` varchar(100) NOT NULL COMMENT '角色名',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `is_delete` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `create_by` varchar(100) NOT NULL COMMENT '创建于',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) NOT NULL COMMENT '更新于',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for t_sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_permission`;
CREATE TABLE `t_sys_role_permission` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` bigint(11) unsigned NOT NULL COMMENT '角色ID',
  `permission_id` bigint(11) unsigned NOT NULL COMMENT '权限ID',
  `is_delete` tinyint(2) unsigned DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建于',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) DEFAULT NULL COMMENT '更新于',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_RP_PID` (`permission_id`),
  KEY `FK_RP_RID` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `user_id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '加密盐值',
  `phone_number` varchar(50) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `status` tinyint(2) unsigned DEFAULT NULL COMMENT '状态',
  `is_delete` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `last_login_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `create_by` varchar(100) NOT NULL COMMENT '创建于',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) NOT NULL COMMENT '更新于',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(11) unsigned NOT NULL COMMENT '用户ID',
  `role_id` bigint(11) unsigned NOT NULL COMMENT '角色ID',
  `is_delete` tinyint(2) unsigned DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建于',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) DEFAULT NULL COMMENT '更新于',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_UR_UID` (`user_id`),
  KEY `KF_UR_RID` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';
