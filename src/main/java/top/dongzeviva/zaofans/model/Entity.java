package top.dongzeviva.zaofans.model;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by ze.dong on 2016/11/23.
 */
public class Entity {

    @Override
    public String toString() {
        return JSONObject.toJSONStringWithDateFormat(this , "yyyy-MM-dd HH:mm:ss");
    }
}
