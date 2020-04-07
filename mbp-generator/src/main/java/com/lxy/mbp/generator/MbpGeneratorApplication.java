package com.lxy.mbp.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.lxy.mbp.generator.generator.Generator;
import com.lxy.mbp.generator.generator.GeneratorCondition;

/**
 * @Author: lxy
 * @Date: 2020-03-26 16:59
 * @Description: mybatis plus生成器主类
 */
public class MbpGeneratorApplication extends Generator {

    private static String AUTHOR = "lxy";
    private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/lxy?characterEncoding=utf-8&useSSL=false";
    private static String USER_NAME = "root";
    private static String PASS_WORD = "root";
    private static String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static String PACKAGE_PATH = "com.lxy.mybatis.plus";

    private static String[] TABLE_NAME = {"t_user"};
    private static String[] TABLE_PREFIX = {"t_"};

    /**
     * 当数据库中表字段为大写时会在实体类上生成@TableField("xxx")以及@TableId("xxx")
     * 如果表字段为小写则不会生成这2个注解，需要手动添加
     * @param args
     */
    public static void main(String[] args) {
        //绝对地址生成
        GeneratorCondition realPathCondition = new GeneratorCondition();
        realPathCondition.setAuthor(AUTHOR);
        realPathCondition.setUrl(JDBC_URL);
        realPathCondition.setUsername(USER_NAME);
        realPathCondition.setPassword(PASS_WORD);
        realPathCondition.setDbType(DbType.MYSQL);
        realPathCondition.setDriver(MYSQL_DRIVER);
        //生成的文件是否覆盖
        realPathCondition.setIsFileOverride(Boolean.FALSE);
        realPathCondition.setParentPackage(PACKAGE_PATH);
        realPathCondition.setTableName(TABLE_NAME);
        realPathCondition.setTablePrefix(TABLE_PREFIX);
        realPathCondition.setIsSwagger2(Boolean.FALSE);
        realPathCondition.setIsActiveRecord(Boolean.TRUE);
        realPathCondition.setIsController(Boolean.TRUE);
        realPathCondition.setIsEntity(Boolean.TRUE);
        realPathCondition.setIsService(Boolean.TRUE);
        realPathCondition.setIsServiceImpl(Boolean.TRUE);
        realPathCondition.setIsXml(Boolean.TRUE);
        realPathCondition.setIsMapper(Boolean.TRUE);

        //如需继承类时设置
//        realPathCondition.setBaseEntity(GeneratorCondition.BaseEntityEnum.SELF);
//        realPathCondition.setSelfBaseEntityPath("com.lxy.mbp.example.bean.BasicEntity");
//        String[] selfEntity = {"create_time", "update_time", "is_del"};
//        realPathCondition.setSelfBaseEntityCommon(selfEntity);

        //指定项目地址(不加这2行是在当前项目下生成)
//        realPathCondition.setIsRealPath(Boolean.TRUE);
//        realPathCondition.setRealPath("E:\\demo\\mbp-example");
        generator(realPathCondition);
    }
}
