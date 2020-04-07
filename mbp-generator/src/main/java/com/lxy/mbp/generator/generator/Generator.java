package com.lxy.mbp.generator.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.lxy.mbp.generator.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: lxy
 * @Date: 2020-03-26 16:59
 * @Description: mybatis plus生成器
 */
@Slf4j
public class Generator {

    /**
     * 参数校验
     *
     * @param condition 生成条件
     * @return
     */
    public static boolean checkParams(GeneratorCondition condition) {
        if (null == condition) {
            log.error("请传入正确的参数!");
            return false;
        }
        if (StringUtils.isBlank(condition.getUrl())) {
            log.error("请配置数据库url!");
            return false;
        }
        if (StringUtils.isBlank(condition.getAuthor())) {
            log.error("请配置作者信息!");
            return false;
        }
        if (StringUtils.isBlank(condition.getUsername())) {
            log.error("请配置数据库用户名!");
            return false;
        }
        if (StringUtils.isBlank(condition.getPassword())) {
            log.error("请配置数据库密码!");
            return false;
        }
        if (null == condition.getTableName() || condition.getTableName().length == 0) {
            log.error("请填写要生成的表!");
            return false;
        }
        if (StringUtils.isBlank(condition.getParentPackage())) {
            log.error("请输入父包名!");
            return false;
        }
        if (condition.getIsMultiModule()) {
            if (StringUtils.isBlank(condition.getModelName())) {
                log.error("请输入模块名!");
                return false;
            }
        }
        return true;
    }

    /**
     * 生成代码
     *
     * @param condition 生成条件
     */
    public static void generator(GeneratorCondition condition) {
        //先校验参数是否正确
        if (!checkParams(condition)) {
            return;
        }

        final String projectPath;
        if (condition.getIsMultiModule()) {
            File file = new File(condition.getModelName());
            projectPath = file.getAbsolutePath();
        } else {
            projectPath = condition.getIsRealPath() ? condition.getRealPath() : System.getProperty("user.dir");
        }
        AutoGenerator autoGenerator = new AutoGenerator();
        //============================== 全局配置
        GlobalConfig gc = new GlobalConfig()
                //生成的文件是否覆盖(全新文件)
                .setFileOverride(condition.getIsFileOverride())
                //是否支持AR
                .setActiveRecord(condition.getIsActiveRecord())
                //实体属性是否需要Swagger2注解
                .setSwagger2(condition.getIsSwagger2())
                //设置作者名字
                .setAuthor(condition.getAuthor())
                //SQL映射文件
                .setBaseResultMap(true)
                //SQL片段
                .setBaseColumnList(true)
                //是否打开输出目录(默认是'I%sService',为了去掉前面的'I')
                .setServiceName("%sService")
                .setOpen(false);
        //区分windows和mac
        String osName = System.getProperty("os.name");
        if (osName != null && osName.contains("Mac")) {
            gc.setOutputDir(projectPath + "/src/main/java");
        } else {
            gc.setOutputDir(projectPath + "\\src\\main\\java");
        }
        autoGenerator.setGlobalConfig(gc);

        //============================== 数据源配置
        DataSourceConfig dsc = new DataSourceConfig()
                //数据库类型
                .setDbType(condition.getDbType())
                //数据库驱动名称
                .setDriverName(condition.getDriver())
                //数据库连接的URL
                .setUrl(condition.getUrl())
                //数据库连接用户名
                .setUsername(condition.getUsername())
                //数据库连接密码
                .setPassword(condition.getPassword());
        autoGenerator.setDataSource(dsc);

        //==============================包配置
        PackageConfig pc = new PackageConfig()
                //父包路径
                .setParent(condition.getParentPackage())
                //配置Mapper包名
                .setMapper("dao")
                //配置
                .setEntity("entity.table");
        autoGenerator.setPackageInfo(pc);

        //============================== 配置模板
        TemplateConfig tc = new TemplateConfig().setXml(null);
        if (condition.getIsController()) {
            tc.setController("/template/controller.java");
        } else {
            //null表示不会创建Controller层代码
            tc.setController(null);
        }
        if (condition.getIsEntity()) {
            tc.setEntity("/template/entity.java");
        } else {
            tc.setEntity(null);
        }
        if (condition.getIsMapper()) {
            tc.setMapper("/template/mapper.java");
        } else {
            tc.setMapper(null);
        }
        if (condition.getIsService()) {
            tc.setService("/template/service.java");
        } else {
            tc.setService(null);
        }
        if (condition.getIsServiceImpl()) {
            tc.setServiceImpl("/template/serviceImpl.java");
        } else {
            tc.setServiceImpl(null);
        }
        autoGenerator.setTemplate(tc);

        //============================== 生成策略配置
        StrategyConfig sc = new StrategyConfig()
                //设置命名规则underline_to_camel底线变驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                //设置设置列命名underline_to_camel底线变驼峰(未指定以naming策略为准)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                //设置表名
                .setInclude(condition.getTableName())
                //设置表前缀
                .setTablePrefix(condition.getTablePrefix())
                //是否加入lombok
                .setEntityLombokModel(true)
                //生成@RestController控制器
                .setRestControllerStyle(true);
        sc.setLogicDeleteFieldName("is_del");
        String templatePath;
        //基础类相关,暂未修改
        if (condition.getBaseEntity() != null && StringUtils.isNoneBlank(condition.getBaseEntity().getValue())) {
            String superEntityClass = null;
            String[] superEntityColumns = null;
            templatePath = condition.getBaseEntity().getValue();
            byte var10 = -1;
            switch (templatePath.hashCode()) {
                case 3076014:
                    if (templatePath.equals("date")) {
                        var10 = 0;
                    }
                    break;
                case 3526476:
                    if (templatePath.equals("self")) {
                        var10 = 3;
                    }
                    break;
                case 93508654:
                    if (templatePath.equals("basic")) {
                        var10 = 2;
                    }
                    break;
                case 2022406093:
                    if (templatePath.equals("logicDel")) {
                        var10 = 1;
                    }
            }

            switch (var10) {
                case 0:
                    superEntityClass = "com.lxy.mbp.example.bean.DateEntity";
                    superEntityColumns = new String[]{"create_time", "update_time"};
                    break;
                case 1:
                    superEntityClass = "com.lxy.mbp.example.bean.LogicDelEntity";
                    superEntityColumns = new String[]{"is_del"};
                    break;
                case 2:
                    superEntityClass = "com.lxy.mbp.example.bean.BasicEntity";
                    superEntityColumns = new String[]{"create_time", "update_time", "is_del"};
                    break;
                case 3:
                    if (StringUtils.isBlank(condition.getSelfBaseEntityPath()) || null == condition.getSelfBaseEntityCommon() || condition.getSelfBaseEntityCommon().length == 0) {
                        log.error("请填写自定义父类全路径和公共字段数组");
                        return;
                    }
                    superEntityClass = condition.getSelfBaseEntityPath();
                    superEntityColumns = condition.getSelfBaseEntityCommon();
            }

            if (null != superEntityClass && null != superEntityColumns) {
                //设置继承类
                sc.setSuperEntityClass(superEntityClass);
                //设置继承类字段
                sc.setSuperEntityColumns(superEntityColumns);
            }
        }
        if (condition.getIsSuperController()) {
            //设置Controller的继承类
            sc.setSuperControllerClass(BaseController.class);
        }
        autoGenerator.setStrategy(sc);

        //添加自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                //使用方法：${cfg.date}
                map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                this.setMap(map);
            }
        };

        //设置模板引擎
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        //自定义输出配置
        List<FileOutConfig> focList = new ArrayList();
        //是否需要生成Mapper.xml
        if (condition.getIsXml()) {
            focList.add(new FileOutConfig("/template/mapper.xml.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
        }
        cfg.setFileOutConfigList(focList);
        autoGenerator.setCfg(cfg);

        //执行开始生成
        autoGenerator.execute();
    }
}
