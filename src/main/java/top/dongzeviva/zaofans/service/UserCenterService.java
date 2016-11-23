package top.dongzeviva.zaofans.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Component;
import top.dongzeviva.zaofans.dao.UserCenterDao;

/**
 * Created by ze.dong on 2016/11/23.
 */
@Component
public class UserCenterService {
    @Autowired
    UserCenterDao userCenterDao;

    public JSONObject register(){
        return  new JSONObject();
    }
}
