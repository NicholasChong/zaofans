package top.dongzeviva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import top.dongzeviva.config.ResourcesConfig;
import top.dongzeviva.zaofans.model.UcUser;
import top.dongzeviva.zaofans.dao.UserCenterDao;

/**
 * Created by ze.dong on 2016/11/23.
 */
@ComponentScan("top.dongzeviva")
@Configuration
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ResourcesConfig.getInstance().init();
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class);
        UcUser user = new UcUser();
        user.setWechatOpenid("openid");
        UserCenterDao dao = applicationContext.getBean(UserCenterDao.class);
        user = dao.insertSelective(user);
        System.out.println(user.toString());
    }
}
