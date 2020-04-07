package com.lxy.mbp.generator.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Data;

/**
 * @Author: lxy
 * @Date: 2020-03-26 16:59
 * @Description:
 */
@Data
public class GeneratorCondition {
    /**
     * 数据库类型
     */
    private DbType dbType = DbType.MYSQL;
    /**
     * 数据库驱动
     */
    private String driver = "com.mysql.cj.jdbc.Driver";
    /**
     * 数据库连接url
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 作者
     */
    private String author;
    /**
     * 是否多模块
     */
    private Boolean isMultiModule = false;
    /**
     * 模块名
     */
    private String modelName;
    /**
     * 项目包名 com.xxx.xxx
     */
    private String parentPackage;
    /**
     * 是否支持AR
     */
    private Boolean isActiveRecord = true;
    /**
     * 实体属性是否需要Swagger2注解
     */
    private Boolean isSwagger2 = false;
    /**
     * 是否覆盖
     */
    private Boolean isFileOverride = false;
    /**
     * 是否生成实体类
     */
    private Boolean isEntity = true;
    /**
     * 是否生成mapper
     */
    private Boolean isMapper = true;
    /**
     * 是否生成XML
     */
    private Boolean isXml = true;
    /**
     * 是否生成service
     */
    private Boolean isService = true;
    /**
     * 是否生成serviceImpl
     */
    private Boolean isServiceImpl = true;
    /**
     * 是否生成controller
     */
    private Boolean isController = true;
    /**
     * 是否指定绝对路径
     */
    private Boolean isRealPath = false;
    /**
     * 项目绝对路径
     */
    private String realPath;

    /**
     * 是否需要BaseController基础父类
     */
    private Boolean isSuperController = false;
    /**
     * 基础父类
     */
    private BaseEntityEnum baseEntity;
    /**
     * 自定义基础父类路径 com.xxx.xxx.SelfEntity
     */
    private String selfBaseEntityPath;
    /**
     * 自定义基础父类字段 id,create_time,update_time
     */
    private String[] selfBaseEntityCommon;
    /**
     * 表前缀
     */
    private String[] tablePrefix;
    /**
     * 表名
     */
    private String[] tableName;

    public enum BaseEntityEnum {
        DATE("date"),
        BASIC("basic"),
        LOGIC_DEL("logicDel"),
        SELF("self"),
        ;
        private String baseEntity;

        BaseEntityEnum(String baseEntity) {
            this.baseEntity = baseEntity;
        }

        public String getValue() {
            return baseEntity;
        }
    }
}
