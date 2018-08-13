#  CMS
![][1]
![][2]
![][3]
![][4]


---

## 项目简介

- 使用SSM实现通用CMS系统
- 实现通用的系统管理模块功能，包含：用户、角色、权限、字典管理。
- 使用Maven对创建的多模块项目，提高项目的易开发性、扩展性。

## 项目模块说明

|   模块  |   说明  |
| --- | --- |
|   cms-common  |  通用工具类等   |
|   cms-dao  |   数据库操作相关  |
|   cms-service  |  业务处理相关   |
|   cms-web   |   控制操作相关  |

## 技术选型

- Spring核心框架：Spring Framework 5.0.2
- 权限框架：Apache Shiro 1.3.2
- 数据库：MySQL 5.1.43
- 数据库连接池：Alibaba Druid 1.1.10
- 数据库版本管理：Flyway 5.1.0
- 持久层框架：Mybatis 3.4.0 | Mybatis-plus 1.3.6
- 日志管理：Log4j 2.10.0
- 快速开发工具：Lombok 1.18.0
- 模板引擎：Thymeleaf 3.0.9
- API文档：Swagger 2.5.0
- 邮件发送：mail 1.4.7
- 后台UI框架：LayUI 2.3.0 | zTree

## 开发环境要求

- 开发环境：Maven3.x + JDK1.8.x + Tomcat8.x + MySQL5.x
- 开发工具：Intellij IDEA 2018.1
- Eclipse或MyEclipse可以导入，但可能会存在问题，需要自己调试，推荐使用 IDEA

## 使用说明

### 项目启动

项目启动相关注意内容查看 [wiki #项目启动][5]



[1]:https://img.shields.io/badge/IDE-IDEA%20%7C%20ECLIPSE-lightgrey.svg
[2]:https://img.shields.io/badge/spring-v5.0.2-green.svg
[3]:https://img.shields.io/badge/Apache%20Shiro-v1.3.2-orange.svg
[4]:https://img.shields.io/badge/LayUI-v2.3.0-blue.svg
[5]:https://gitee.com/purelove00/cms/wikis/%E9%A1%B9%E7%9B%AE%E5%90%AF%E5%8A%A8?sort_id=642666



