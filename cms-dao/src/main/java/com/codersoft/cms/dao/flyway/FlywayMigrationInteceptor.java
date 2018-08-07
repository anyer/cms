package com.codersoft.cms.dao.flyway;

import lombok.Setter;
import org.flywaydb.core.Flyway;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @program: FlywayMigrationInteceptor
 * @author: Alex.D
 * @create: 2018-08-07 09:35
 * @description: 数据库迁移拦截器
 **/
@Setter
public class FlywayMigrationInteceptor {

    private Flyway flyway;
    private DataSource dataSource;
    private String table;
    private String encoding;
    private boolean baselineOnMigrate;
    private String location;

    @PostConstruct
    public void migrate() {
        //设置加载数据库的相关配置信息
        flyway.setDataSource(dataSource);
        //设置接受flyway进行版本管理的多个数据库
//        flyway.setSchemas("cms");
        //设置存放flyway metadata数据的表名，默认"schema_version"，可不写
        flyway.setTable(table);
        //设置基线，已经开发过一段时间的数据库需要设置为true
        flyway.setBaselineOnMigrate(baselineOnMigrate);
        //设置flyway扫描sql升级脚本、java升级脚本的目录路径或包路径，默认"db/migration"，可不写
        flyway.setLocations(location);
        //设置sql脚本文件的编码，默认"UTF-8"，可不写
        flyway.setEncoding(encoding);

        flyway.migrate();
    }
}
