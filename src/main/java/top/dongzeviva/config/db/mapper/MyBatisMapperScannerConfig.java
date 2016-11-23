package top.dongzeviva.config.db.mapper;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;
import top.dongzeviva.config.db.MyBatisConfig;

import java.util.Properties;

/**
 * Created by ze.dong on 2016/11/23.
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("top.dongzeviva.zaofans.model.mapper");
//        mapperScannerConfigurer.setBasePackage("tk.mybatis.springboot.mapper");
        Properties properties = new Properties();
//        properties.setProperty("mappers", "top.config.db.mapper.MyMapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

}
