
package top.dongzeviva.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import top.dongzeviva.config.ResourcesConfig;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * MyBatis基础配置
 *
 * @author jung.zhang
 * @since 2016-07-07 10:11
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class MyBatisConfig implements TransactionManagementConfigurer {

    @Bean
    public DataSource dataSource() {
        System.out.println("注入druid！！！");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(ResourcesConfig.getInstance().getMessage("spring.datasource.url", ""));
        datasource.setDriverClassName("com.mysql.jdbc.Driver");//ResourcesConfig.getInstance().getMessage("spring.datasource.type",""));
        datasource.setUsername(ResourcesConfig.getInstance().getMessage("spring.datasource.username", ""));
        datasource.setPassword(ResourcesConfig.getInstance().getMessage("spring.datasource.password", ""));
        datasource.setValidationQuery(ResourcesConfig.getInstance().getMessage("spring.datasource.validationQuery", ""));
//		datasource.setInitialSize(Integer.valueOf(ResourcesConfig.getInstanspring.datasource.typece().getMessage("spring.datasource.initialSize","")));
//		datasource.setMinIdle(Integer.valueOf(ResourcesConfig.getInstance().getMessage("spring.datasource.minIdle","")));
//		datasource.setMaxWait(Long.valueOf(ResourcesConfig.getInstance().getMessage("spring.datasource.maxWait","")));
//		datasource.setMaxActive(Integer.valueOf(ResourcesConfig.getInstance().getMessage("spring.datasource.maxActive","")));
//		datasource.setMinEvictableIdleTimeMillis(Long.valueOf(ResourcesConfig.getInstance().getMessage("spring.datasource.minEvictableIdleTimeMillis","")));
        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws PropertyVetoException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());
        // 分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "false");
        properties.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(properties);
        bean.setPlugins(new Interceptor[]{pageHelper});
        try {
            // 指定mapper xml目录
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
