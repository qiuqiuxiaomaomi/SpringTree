package com.bonaparte.service;

import com.bonaparte.dao.mapper.ChargeMapper;
import com.bonaparte.dao.mapper.KeyWordMapper;
import com.bonaparte.entity.Charge;
import com.bonaparte.entity.KeyWord;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.List;

/**
 * Created by yangmingquan on 2018/6/29.
 * Mybatis 高级功能
 */
@Service
public class MybatisSeniorService {
    @Autowired
    private ChargeMapper chargeMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public void basicOp(){
        chargeMapper.getAllChargesByUid(1);
    }

    /**
     * 一级缓存，二级缓存
     * */
    public void oneLevelSession(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ChargeMapper chargeMapper = sqlSession.getMapper(ChargeMapper.class);
        //一级缓存
        Charge charge = chargeMapper.selectByPrimaryKey(1);
        System.out.println(charge.getMoney());
        charge.setMoney(3.99);
        chargeMapper.updateByPrimaryKey(charge);
        charge = chargeMapper.selectByPrimaryKey(1);
        System.out.println(charge.getMoney());

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        KeyWordMapper keyWordMapper1 = sqlSession1.getMapper(KeyWordMapper.class);
        List<KeyWord> keyWordList1 = keyWordMapper1.selectAll();

        KeyWord keyWord = new KeyWord();
        keyWord.setKeyword("卡哇伊");
        keyWordMapper1.insert(keyWord);

        KeyWordMapper keyWordMapper = sqlSession.getMapper(KeyWordMapper.class);
        List<KeyWord> keyWordList = keyWordMapper.selectAll();
        //keyWordList1 与 keyWordList 是一致的
    }
}
