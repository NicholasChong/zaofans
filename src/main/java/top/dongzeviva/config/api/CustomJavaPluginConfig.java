package top.dongzeviva.config.api;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by ze.dong on 2016/11/21.
 */
@Configuration
@EnableWebMvc
@EnableSwagger
@ComponentScan("com")
public class CustomJavaPluginConfig {
    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

//    @Bean //Don't forget the @Bean annotation
//    public SwaggerSpringMvcPlugin customImplementation() {
//        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
//                .apiInfo(apiInfo())
//                .includePatterns("/*");
//    }
    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return  new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(new ApiInfo("Resource Schedule", "资源排班表", null, null, null, null))
                .useDefaultResponseMessages(false)
                .includePatterns(".*?");
    }

//    private ApiInfo
//apiInfo() {
//        ApiInfo apiInfo = new ApiInfo(
//                "My Apps API Title",
//                "My Apps API Description",
//                "My Apps API terms of service",
//                "My Apps API Contact Email",
//                "My Apps API Licence Type",
//                "My Apps API License URL"
//        );
//        return apiInfo;
//    }
}
