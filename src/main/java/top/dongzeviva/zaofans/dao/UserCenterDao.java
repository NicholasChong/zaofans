package top.dongzeviva.zaofans.dao;

import org.springframework.stereotype.Component;
import top.dongzeviva.util.db.BaseSqlSession;
import top.dongzeviva.zaofans.model.UcUser;
import top.dongzeviva.zaofans.model.mapper.UcUserMapper;

/**
 * Created by ze.dong on 2016/11/23.
 */
@Component
public class UserCenterDao extends BaseSqlSession {

    public UcUser insertSelective(UcUser ucUser){
        UcUserMapper ucUserMapper =  getDefaultSqlSession().getMapper(UcUserMapper.class);
        int row = ucUserMapper.insertSelective(ucUser);
        return ucUser;
    }

}
