package top.dongzeviva.util.db;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ze.dong on 2016/11/23.
 */
public class BaseSqlSession {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public SqlSession getDefaultSqlSession() {
        SqlSession sqlSession = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSession;

    }

}
