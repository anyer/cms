/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-08-13 12:56:29
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
  `status` tinyint(2) unsigned DEFAULT '1' COMMENT '状态（0：隐藏；1：显示）',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `is_delete` tinyint(2) unsigned DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建于',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) DEFAULT NULL COMMENT '更新于',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础数据表';

-- ----------------------------
-- Records of t_sys_data
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_data_category
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_data_category`;
CREATE TABLE `t_sys_data_category` (
  `cate_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '基础数据类别ID',
  `cate_code` varchar(255) DEFAULT NULL COMMENT '类别Code',
  `cate_name` varchar(255) DEFAULT NULL COMMENT '类别名称',
  `status` tinyint(2) unsigned DEFAULT '1' COMMENT '状态（0：隐藏；1：显示）',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `is_delete` tinyint(2) unsigned DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建于',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) DEFAULT NULL COMMENT '更新于',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`cate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础数据类别表';

-- ----------------------------
-- Records of t_sys_data_category
-- ----------------------------

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
  `status` tinyint(2) unsigned DEFAULT '1' COMMENT '是否有效（0：无效；1：有效）',
  `is_delete` tinyint(2) unsigned DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建于',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) DEFAULT NULL COMMENT '更新于',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_organization`;
CREATE TABLE `t_sys_organization` (
  `org_id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '组织ID',
  `parent_id` bigint(11) unsigned NOT NULL DEFAULT '0' COMMENT '父ID',
  `org_code` varchar(255) NOT NULL COMMENT '组织编码',
  `org_name` varchar(255) NOT NULL COMMENT '组织名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` tinyint(2) unsigned DEFAULT '1' COMMENT '状态（0：隐藏；1：显示）',
  `is_delete` tinyint(2) unsigned DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建于',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) DEFAULT NULL COMMENT '更新于',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='组织信息表';

-- ----------------------------
-- Records of t_sys_organization
-- ----------------------------
INSERT INTO `t_sys_organization` VALUES ('1', '0', 'ORG-sfomnjiuujd', '123', '', '1', '0', 'admin', '2018-08-06 18:26:40', 'admin', '2018-08-06 18:28:38');

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
  `per_level` tinyint(2) unsigned NOT NULL COMMENT '权限级别',
  `status` tinyint(2) unsigned DEFAULT '1' COMMENT '状态（0：隐藏；1：显示）',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `is_delete` tinyint(2) unsigned DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建于',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) DEFAULT NULL COMMENT '更新于',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of t_sys_permission
-- ----------------------------
INSERT INTO `t_sys_permission` VALUES ('1', '0', 'DIC-z4bg079wt7n', '系统管理', '#', 'larry-diannao3', '0', '0', '1', '', '0', 'admin', '2018-07-12 11:51:47', 'admin', '2018-08-03 13:19:50');
INSERT INTO `t_sys_permission` VALUES ('2', '1', 'MEN-gdm899auq7k', '用户管理', '#', 'larry-10103', '1', '1', '1', '', '0', 'admin', '2018-07-12 12:31:33', 'admin', '2018-08-03 22:23:29');
INSERT INTO `t_sys_permission` VALUES ('3', '2', 'MEN-7kel4wn55u3', '用户管理', '/admin/user/toListPage', 'larry-gerenxinxi4', '1', '2', '1', '', '0', 'admin', '2018-07-12 12:31:33', 'admin', '2018-08-03 22:47:53');
INSERT INTO `t_sys_permission` VALUES ('4', '2', 'MEN-vpwjcj0dor7', '角色管理', '/admin/role/toListPage', 'larry-jiaoseguanli1', '1', '2', '1', '', '0', 'admin', '2018-07-12 12:32:39', 'admin', '2018-07-12 12:32:39');
INSERT INTO `t_sys_permission` VALUES ('5', '2', 'MEN-snqfsdoa6vi', '权限管理', '/admin/permission/toListPage', 'larry-caidanguanli', '1', '2', '1', '', '0', 'admin', '2018-07-12 12:36:13', 'admin', '2018-07-12 12:36:13');
INSERT INTO `t_sys_permission` VALUES ('6', '1', 'MEN-v9jq453nec', '数据管理', '#', 'larry-caidanguanli', '1', '1', '1', '', '0', 'admin', '2018-07-12 15:44:13', 'admin', '2018-08-03 13:24:09');
INSERT INTO `t_sys_permission` VALUES ('8', '6', 'MEN-u180au99l7n', '数据类别', '#', 'larry-kechengguanli', '1', '2', '1', '', '0', 'admin', '2018-07-12 15:45:49', 'admin', '2018-08-03 13:23:16');
INSERT INTO `t_sys_permission` VALUES ('9', '6', 'MEN-7ketcr3et9o', '基础数据', '#', 'larry-caidanguanli1', '1', '2', '1', '', '0', 'admin', '2018-07-12 15:46:37', 'admin', '2018-08-03 13:23:54');
INSERT INTO `t_sys_permission` VALUES ('10', '6', 'MEN-cvt6k7jvjli', '接口管理', '#', 'larry-jiekouguanli', '1', '2', '1', null, '0', 'admin', '2018-07-12 15:48:11', 'admin', '2018-07-12 15:48:11');
INSERT INTO `t_sys_permission` VALUES ('11', '1', 'MEN-wt5uo2neta', '系统配置', '#', 'larry-zhandianguanli', '1', '1', '1', '', '0', 'admin', '2018-07-17 13:45:26', 'admin', '2018-08-03 13:24:41');
INSERT INTO `t_sys_permission` VALUES ('12', '11', 'MEN-w32k18kki1', '后台参数', '#', 'larry-circularxiangxi', '1', '2', '1', '', '0', 'admin', '2018-07-17 13:46:15', 'admin', '2018-08-03 13:25:50');
INSERT INTO `t_sys_permission` VALUES ('13', '11', 'MEN-cev1sq2rd0q', '邮箱配置', '#', 'larry-duanxin1', '1', '2', '1', '', '0', 'admin', '2018-07-17 13:46:57', 'admin', '2018-08-03 13:26:38');
INSERT INTO `t_sys_permission` VALUES ('14', '11', 'MEN-51xql966mh9', '任务配置', '#', 'larry-rizhi1', '1', '2', '1', '', '0', 'admin', '2018-07-17 13:48:01', 'admin', '2018-08-03 13:28:04');
INSERT INTO `t_sys_permission` VALUES ('23', '0', 'DIC-w1co3w298lr', '测试目录', '#', 'larry-shuaxin4', '0', '0', '0', '测试目录。。。。。', '0', 'admin', '2018-08-03 22:26:18', 'admin', '2018-08-04 09:06:29');
INSERT INTO `t_sys_permission` VALUES ('24', '2', 'MEN-akigounnao5', '组织管理', '/admin/organization/toListPage', 'larry-10103', '1', '2', '1', '', '0', 'admin', '2018-08-03 22:47:22', 'admin', '2018-08-03 22:50:46');
INSERT INTO `t_sys_permission` VALUES ('27', '1', 'MEN-p8qq2vulyda', '系统监控', '#', 'larry-jiankong', '1', '1', '1', '', '0', 'admin', '2018-08-08 13:16:17', null, '2018-08-08 13:16:17');
INSERT INTO `t_sys_permission` VALUES ('28', '27', 'MEN-9riqjd5l8mu', 'Druid监控', '/druid/index.html', 'larry-jiankong', '1', '2', '1', 'Druid监控系统情况', '0', 'admin', '2018-08-08 13:29:24', null, '2018-08-08 13:29:24');
INSERT INTO `t_sys_permission` VALUES ('29', '27', 'MEN-duajkualgkj', 'API接口文档', '/swagger-ui.html', 'larry-wangzhanneirong', '1', '2', '1', 'Swagger2-UI实现的接口文档', '0', 'admin', '2018-08-08 13:31:52', null, '2018-08-08 13:31:52');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `role_id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_code` varchar(255) NOT NULL COMMENT '角色编码',
  `role_name` varchar(100) NOT NULL COMMENT '角色名',
  `status` tinyint(2) unsigned DEFAULT '1' COMMENT '状态（0：隐藏；1：显示）',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `is_delete` tinyint(2) unsigned DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建于',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) DEFAULT NULL COMMENT '更新于',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', 'ROLE-lsvsvovmwur', '超级管理员', '1', '超级管理员，最高权限角色', '0', 'admin', '2018-08-06 10:27:03', null, '2018-08-06 10:27:03');
INSERT INTO `t_sys_role` VALUES ('3', 'ROLE-bax5socaoao', '管理员', '1', '管理员权限角色', '0', 'admin', '2018-08-06 14:41:57', null, '2018-08-06 14:41:57');
INSERT INTO `t_sys_role` VALUES ('5', 'ROLE-4qy709sz83a', '测试', '0', '测试', '0', 'admin', '2018-08-06 17:52:56', 'admin', '2018-08-06 17:53:07');

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
-- Records of t_sys_role_permission
-- ----------------------------

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
  `status` tinyint(2) unsigned DEFAULT '1' COMMENT '状态（0：正常；1：未激活；2：停用）',
  `header_img` varchar(255) DEFAULT 'face.jpg' COMMENT '头像图片',
  `is_delete` tinyint(2) unsigned DEFAULT '0' COMMENT '删除标识（0：不删除； 1：删除）',
  `last_login_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建于',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `modify_by` varchar(100) DEFAULT NULL COMMENT '更新于',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'admin', '22e6f0eab77b777be302d5e9be517de1', '1799688a806532a2623fbd0efc191c20', '13112345678', 'maple_6392@163.com', '0', 'face.jpg', '0', '2018-08-08 12:55:58', 'system', '2018-07-07 18:00:47', 'admin', '2018-08-06 16:31:41');
INSERT INTO `t_sys_user` VALUES ('11', 'Test', 'bc729751cf2e6e7b2f08f5cf9942366d', 'eb29cfcab2ebd9604050f451aab14e59', '13212345678', 't@13.com', '2', 'face.jpg', '0', '2018-08-06 17:34:24', 'Test', '2018-07-16 19:57:41', 'Test', '2018-08-06 17:34:32');
INSERT INTO `t_sys_user` VALUES ('19', 'maple', 'b43f147bc6f2cfdd22167d1d41c8b84a', '59b63bd46ab3a5e4989030ee7cb97519', null, 'maple6392@aliyun.com', '0', 'face.jpg', '0', '2018-08-10 13:34:20', null, '2018-08-10 13:34:20', null, '2018-08-10 13:34:20');

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

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
