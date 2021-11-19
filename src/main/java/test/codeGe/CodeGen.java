package test.codeGe;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;

public class CodeGen {

    // 生成代码
    public void getCode(){
        AutoGenerator autoGenerator = new AutoGenerator();


        // 全局配置
        GlobalConfig cfg = new GlobalConfig();
        cfg.setAuthor("Will.wei");
        String dir = System.getProperty("user.dir");
        cfg.setOutputDir(dir+"src/main/java");
        cfg.setOpen(false);
        cfg.setFileOverride(false);
        cfg.setServiceName("%Service");
        cfg.setServiceImplName("%ServiceImpl");
        autoGenerator.setGlobalConfig(cfg);

        // 数据库
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName("");
        dsc.setUrl("");
        dsc.setUsername("");
        dsc.setPassword("");
        dsc.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dsc);


        // 配置包
        PackageConfig pgc = new PackageConfig();
        pgc.setModuleName("user");
        pgc.setParent("com.lcfc.demo");
        pgc.setController("controller");
        pgc.setEntity("entity");
        pgc.setMapper("mapper");
        pgc.setService("service");
        pgc.setServiceImpl("Imp");
        autoGenerator.setPackageInfo(pgc);
        autoGenerator.execute();

    }

}
