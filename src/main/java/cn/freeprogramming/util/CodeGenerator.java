package cn.freeprogramming.util;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import java.util.Set;

/**
 * 代码生成器
 *
 * @author humorchen
 * @date 2021/12/5 22:02
 */
public class CodeGenerator {
    public static final String DEFAULT_URL_FORMAT = "jdbc:mysql://localhost:3306/%s?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public static final String DEFAULT_USERNAME = "root";
    public static final String DEFAULT_PASSWORD = "root";
    public static final String DEFAULT_AUTHOR = "humorchen";

    /**
     * 生成代码函数
     *
     * @param url                 数据库地址
     * @param username            用户名
     * @param password            密码
     * @param author              代码作者
     * @param packageName         package包名 com.example.project
     * @param database            数据库名
     * @param tablePrefix         表前缀
     * @param needTableNames      需要生成的表（不写则生成所有的表）
     * @param exclusionTableNames 不需要生成的表（填了的不生成）
     * @param namingStrategy      命名策略（不填则按之前的来） 默认@see NamingStrategy.underline_to_camel
     * @param overwrite           存在的是否覆盖
     * @param dist                代码生成后保存的目录，不填则为当前项目java目录下
     */
    public static void generate(String url, String username, String password, String author, String packageName, String database, String tablePrefix, Set<String> needTableNames, Set<String> exclusionTableNames, NamingStrategy namingStrategy, Boolean overwrite, String dist) {
        if (url == null) {
            url = String.format(DEFAULT_URL_FORMAT, database);
        }
        if (username == null) {
            username = DEFAULT_USERNAME;
        }
        if (password == null) {
            password = DEFAULT_PASSWORD;
        }
        if (author == null) {
            author = DEFAULT_AUTHOR;
        }
        if (tablePrefix == null) {
            tablePrefix = "";
        }
        if (overwrite == null){
            overwrite = false;
        }
        if (dist == null) {
            String projectPath = System.getProperty("user.dir"); //当前项目
            dist = projectPath + "/src/main/java";
        }
        if (namingStrategy == null) {
            namingStrategy = NamingStrategy.underline_to_camel;
        }
        AutoGenerator generator = new AutoGenerator();
        // 全局变量配置
        GlobalConfig gc = new GlobalConfig();

        gc.setOutputDir(dist); // 输出路径
        gc.setFileOverride(overwrite); // 默认 false ,是否覆盖已生成文件
        gc.setOpen(true); //默认true ,是否打开输出目录
        gc.setEnableCache(false); // 默认false,是否开启二级缓存
        gc.setAuthor(author); // 作者
        gc.setBaseResultMap(true); // 默认false
//        gc.setDateType(DateType.TIME_PACK); // 时间策略 默认TIME_PACK
        gc.setBaseColumnList(true); //默认false  和basemodel相似
//        gc.setEntityName("%s");
//        gc.setControllerName("%sController");
//        gc.setServiceName("I%sService");
//        gc.setServiceImplName("%sServiceImpl");
//        gc.setMapperName("I%sMapper");
//        gc.setXmlName("%sMapper");
        gc.setIdType(IdType.AUTO); // 指定生成的主键类型
        generator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dc = new DataSourceConfig();
        dc.setDbQuery(new MySqlQuery()); // 数据库信息查询 //默认mysql
        dc.setDbType(DbType.MYSQL);// 数据库类型
        dc.setTypeConvert(new MySqlTypeConvert()); //类型转换 默认mysql

        dc.setUrl(url);
        dc.setDriverName("com.mysql.cj.jdbc.Driver");
        dc.setUsername(username);
        dc.setPassword(password);
        generator.setDataSource(dc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packageName);//代码生成到哪个包下面
//        pc.setModuleName(""); //此处是所属模块名称
        pc.setEntity("bean"); //默认entity,controller,service,service.impl,mapper,mapper.xml
        generator.setPackageInfo(pc);
//         自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        /**
         * 将xml生成到resource下面
         */
//        String templatePath = "/templates/mapper.xml.vm"; // Velocity模板
        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/"
//        + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);

        // 配置模板
//        TemplateConfig tc = new TemplateConfig();
        // templates/entity.java 模板路径配置，默认在templates目录下，.vm 后缀不用加
//        tc.setEntity("templates/entity.java");//使用自定义模板生成实体类
//        tc.setXml("");
//        generator.setTemplate(tc);

        // 数据库表配置
        StrategyConfig sc = new StrategyConfig();
        sc.setCapitalMode(false); //是否大写命名 默认false
        sc.setSkipView(true); //是否跳过试图 默认false
        sc.setNaming(namingStrategy);// 表映射 驼峰命名
        sc.setColumnNaming(namingStrategy); // 字段映射 驼峰
        sc.setEntityLombokModel(true); //是否使用lombak 默认为false
        sc.setRestControllerStyle(true); // 默认false
        sc.setEntitySerialVersionUID(true); //默认true
        sc.setEntityColumnConstant(true); //默认false 将mysql字段名生成静态变量
        if (needTableNames != null) {
            sc.setInclude((String[]) needTableNames.toArray());
        } else if (exclusionTableNames != null) {
            sc.setExclude((String[]) exclusionTableNames.toArray());
        }
//        sc.setInclude(scanner("表名，多个英文逗号分割").split(",")); //表名，用，隔开  需要生产
        //     sc.setExclude(""); //                 不需要生成  二选一
        sc.setEntityTableFieldAnnotationEnable(true); // 默认false 注释
        sc.setControllerMappingHyphenStyle(false); //默认false
        sc.setTablePrefix(new String[]{tablePrefix});//  此处可以修改为您的表前缀
//        sc.setLogicDeleteFieldName("status"); // 逻辑删除字段名称
        generator.setStrategy(sc);

        // 模板引擎
        generator.setTemplateEngine(new VelocityTemplateEngine());
        generator.execute();
    }


    public static void main(String[] args) {
        generate("jdbc:mysql://49.235.226.68:3305/user?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false"
                ,"root","freeprogrammingtest","humorchen","cn.freeprogramming.user",null,"t_",null,null,null,null,null);
    }
}
