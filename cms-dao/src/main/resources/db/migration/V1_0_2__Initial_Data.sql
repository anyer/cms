INSERT INTO `t_sys_organization` VALUES ('1', '0', 'ORG-sfomnjiuujd', '123', '', '1', '0', 'admin', '2018-08-06 18:26:40', 'admin', '2018-08-06 18:28:38');

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

INSERT INTO `t_sys_role` VALUES ('1', 'ROLE-lsvsvovmwur', '超级管理员', '1', '超级管理员，最高权限角色', '0', 'admin', '2018-08-06 10:27:03', null, '2018-08-06 10:27:03');
INSERT INTO `t_sys_role` VALUES ('3', 'ROLE-bax5socaoao', '管理员', '1', '管理员权限角色', '0', 'admin', '2018-08-06 14:41:57', null, '2018-08-06 14:41:57');
INSERT INTO `t_sys_role` VALUES ('5', 'ROLE-4qy709sz83a', '测试', '0', '测试', '0', 'admin', '2018-08-06 17:52:56', 'admin', '2018-08-06 17:53:07');

INSERT INTO `t_sys_user` VALUES ('1', 'admin', '22e6f0eab77b777be302d5e9be517de1', '1799688a806532a2623fbd0efc191c20', '13112345678', 'maple_6392@163.com', '0', 'face.jpg', '0', '2018-08-06 18:44:58', 'system', '2018-07-07 18:00:47', 'admin', '2018-08-06 16:31:41');
INSERT INTO `t_sys_user` VALUES ('11', 'Test', 'bc729751cf2e6e7b2f08f5cf9942366d', 'eb29cfcab2ebd9604050f451aab14e59', '13212345678', 't@13.com', '2', 'face.jpg', '0', '2018-08-06 17:34:24', 'Test', '2018-07-16 19:57:41', 'Test', '2018-08-06 17:34:32');
