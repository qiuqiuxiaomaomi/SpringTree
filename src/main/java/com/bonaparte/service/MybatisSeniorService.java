package com.bonaparte.service;

import com.bonaparte.bean.StatisticBean;
import com.bonaparte.dao.mapper.ChargeMapper;
import com.bonaparte.dao.mapper.KeyWordMapper;
import com.bonaparte.entity.Charge;
import com.bonaparte.entity.KeyWord;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by yangmingquan on 2018/6/29.
 * Mybatis 高级功能
 * 自定义TypeHandler
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
     * Mybatis 的默认TypeHandler
     *  public TypeHandlerRegistry() {
                 this.register((Class)Boolean.class, (TypeHandler)(new BooleanTypeHandler()));
                 this.register((Class)Boolean.TYPE, (TypeHandler)(new BooleanTypeHandler()));
                 this.register((JdbcType)JdbcType.BOOLEAN, (TypeHandler)(new BooleanTypeHandler()));
                 this.register((JdbcType)JdbcType.BIT, (TypeHandler)(new BooleanTypeHandler()));
                 this.register((Class)Byte.class, (TypeHandler)(new ByteTypeHandler()));
                 this.register((Class)Byte.TYPE, (TypeHandler)(new ByteTypeHandler()));
                 this.register((JdbcType)JdbcType.TINYINT, (TypeHandler)(new ByteTypeHandler()));
                 this.register((Class)Short.class, (TypeHandler)(new ShortTypeHandler()));
                 this.register((Class)Short.TYPE, (TypeHandler)(new ShortTypeHandler()));
                 this.register((JdbcType)JdbcType.SMALLINT, (TypeHandler)(new ShortTypeHandler()));
                 this.register((Class)Integer.class, (TypeHandler)(new IntegerTypeHandler()));
                 this.register((Class)Integer.TYPE, (TypeHandler)(new IntegerTypeHandler()));
                 this.register((JdbcType)JdbcType.INTEGER, (TypeHandler)(new IntegerTypeHandler()));
                 this.register((Class)Long.class, (TypeHandler)(new LongTypeHandler()));
                 this.register((Class)Long.TYPE, (TypeHandler)(new LongTypeHandler()));
                 this.register((Class)Float.class, (TypeHandler)(new FloatTypeHandler()));
                 this.register((Class)Float.TYPE, (TypeHandler)(new FloatTypeHandler()));
                 this.register((JdbcType)JdbcType.FLOAT, (TypeHandler)(new FloatTypeHandler()));
                 this.register((Class)Double.class, (TypeHandler)(new DoubleTypeHandler()));
                 this.register((Class)Double.TYPE, (TypeHandler)(new DoubleTypeHandler()));
                 this.register((JdbcType)JdbcType.DOUBLE, (TypeHandler)(new DoubleTypeHandler()));
                 this.register((Class)Reader.class, (TypeHandler)(new ClobReaderTypeHandler()));
                 this.register((Class)String.class, (TypeHandler)(new StringTypeHandler()));
                 this.register((Class)String.class, JdbcType.CHAR, (TypeHandler)(new StringTypeHandler()));
                 this.register((Class)String.class, JdbcType.CLOB, (TypeHandler)(new ClobTypeHandler()));
                 this.register((Class)String.class, JdbcType.VARCHAR, (TypeHandler)(new StringTypeHandler()));
                 this.register((Class)String.class, JdbcType.LONGVARCHAR, (TypeHandler)(new ClobTypeHandler()));
                 this.register((Class)String.class, JdbcType.NVARCHAR, (TypeHandler)(new NStringTypeHandler()));
                 this.register((Class)String.class, JdbcType.NCHAR, (TypeHandler)(new NStringTypeHandler()));
                 this.register((Class)String.class, JdbcType.NCLOB, (TypeHandler)(new NClobTypeHandler()));
                 this.register((JdbcType)JdbcType.CHAR, (TypeHandler)(new StringTypeHandler()));
                 this.register((JdbcType)JdbcType.VARCHAR, (TypeHandler)(new StringTypeHandler()));
                 this.register((JdbcType)JdbcType.CLOB, (TypeHandler)(new ClobTypeHandler()));
                 this.register((JdbcType)JdbcType.LONGVARCHAR, (TypeHandler)(new ClobTypeHandler()));
                 this.register((JdbcType)JdbcType.NVARCHAR, (TypeHandler)(new NStringTypeHandler()));
                 this.register((JdbcType)JdbcType.NCHAR, (TypeHandler)(new NStringTypeHandler()));
                 this.register((JdbcType)JdbcType.NCLOB, (TypeHandler)(new NClobTypeHandler()));
                 this.register((Class)Object.class, JdbcType.ARRAY, (TypeHandler)(new ArrayTypeHandler()));
                 this.register((JdbcType)JdbcType.ARRAY, (TypeHandler)(new ArrayTypeHandler()));
                 this.register((Class)BigInteger.class, (TypeHandler)(new BigIntegerTypeHandler()));
                 this.register((JdbcType)JdbcType.BIGINT, (TypeHandler)(new LongTypeHandler()));
                 this.register((Class)BigDecimal.class, (TypeHandler)(new BigDecimalTypeHandler()));
                 this.register((JdbcType)JdbcType.REAL, (TypeHandler)(new BigDecimalTypeHandler()));
                 this.register((JdbcType)JdbcType.DECIMAL, (TypeHandler)(new BigDecimalTypeHandler()));
                 this.register((JdbcType)JdbcType.NUMERIC, (TypeHandler)(new BigDecimalTypeHandler()));
                 this.register((Class)InputStream.class, (TypeHandler)(new BlobInputStreamTypeHandler()));
                 this.register((Class)Byte[].class, (TypeHandler)(new ByteObjectArrayTypeHandler()));
                 this.register((Class)Byte[].class, JdbcType.BLOB, (TypeHandler)(new BlobByteObjectArrayTypeHandler()));
                 this.register((Class)Byte[].class, JdbcType.LONGVARBINARY, (TypeHandler)(new BlobByteObjectArrayTypeHandler()));
                 this.register((Class)byte[].class, (TypeHandler)(new ByteArrayTypeHandler()));
                 this.register((Class)byte[].class, JdbcType.BLOB, (TypeHandler)(new BlobTypeHandler()));
                 this.register((Class)byte[].class, JdbcType.LONGVARBINARY, (TypeHandler)(new BlobTypeHandler()));
                 this.register((JdbcType)JdbcType.LONGVARBINARY, (TypeHandler)(new BlobTypeHandler()));
                 this.register((JdbcType)JdbcType.BLOB, (TypeHandler)(new BlobTypeHandler()));
                 this.register(Object.class, this.UNKNOWN_TYPE_HANDLER);
                 this.register(Object.class, JdbcType.OTHER, this.UNKNOWN_TYPE_HANDLER);
                 this.register(JdbcType.OTHER, this.UNKNOWN_TYPE_HANDLER);
                 this.register((Class)Date.class, (TypeHandler)(new DateTypeHandler()));
                 this.register((Class)Date.class, JdbcType.DATE, (TypeHandler)(new DateOnlyTypeHandler()));
                 this.register((Class)Date.class, JdbcType.TIME, (TypeHandler)(new TimeOnlyTypeHandler()));
                 this.register((JdbcType)JdbcType.TIMESTAMP, (TypeHandler)(new DateTypeHandler()));
                 this.register((JdbcType)JdbcType.DATE, (TypeHandler)(new DateOnlyTypeHandler()));
                 this.register((JdbcType)JdbcType.TIME, (TypeHandler)(new TimeOnlyTypeHandler()));
                 this.register((Class)java.sql.Date.class, (TypeHandler)(new SqlDateTypeHandler()));
                 this.register((Class)Time.class, (TypeHandler)(new SqlTimeTypeHandler()));
                 this.register((Class)Timestamp.class, (TypeHandler)(new SqlTimestampTypeHandler()));
     }
     */

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

    /**
     * 使用内存排序
     * */
    public Map<String, Object> statisticInfoDetail(StatisticBean bean){
        Map<String, Object> mapResult = new HashedMap();
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<Integer> orgIds = new ArrayList<>();

        /**
         * 具体业务省略
         * */

        if(!CollectionUtils.isEmpty(mapList)){
            //排序，分页
            Collections.sort(mapList, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    if (bean.getOrderPriority() == null) {
                        if (StringUtils.length(o1.get("code").toString()) < StringUtils.length(o2.get("code").toString())) {
                            return 1;
                        } else {
                            return -1;
                        }
                    } else {
                        if (bean.getOrderPriority() == 1){
                            if (bean.getOrderByType() == 0){
                                if (Long.valueOf(o1.get("count").toString()) >= Long.valueOf(o2.get("count").toString())){
                                    return 1;
                                } else {
                                    return -1;
                                }
                            } else {
                                if (Long.valueOf(o1.get("count").toString()) < Long.valueOf(o2.get("count").toString())){
                                    return 1;
                                } else {
                                    return -1;
                                }
                            }
                        } else if (bean.getOrderPriority() == 1){
                            if (bean.getOrderByType() == 0){
                                if (Long.valueOf(o1.get("forwardFriendCount").toString()) >= Long.valueOf(o2.get("forwardFriendCount").toString())){
                                    return 1;
                                } else {
                                    return -1;
                                }
                            } else {
                                if (Long.valueOf(o1.get("forwardFriendCount").toString()) < Long.valueOf(o2.get("forwardFriendCount").toString())){
                                    return 1;
                                } else {
                                    return -1;
                                }
                            }
                        } else {
                            if (bean.getOrderByType() == 0){
                                if (Long.valueOf(o1.get("forwardCircleCount").toString()) >= Long.valueOf(o2.get("forwardCircleCount").toString())){
                                    return 1;
                                } else {
                                    return -1;
                                }
                            } else {
                                if (Long.valueOf(o1.get("forwardCircleCount").toString()) < Long.valueOf(o2.get("forwardCircleCount").toString())){
                                    return 1;
                                } else {
                                    return -1;
                                }
                            }
                        }
                    }
                }
            });

            //分页
            if (bean.getStartRow() >= mapList.size()){
                mapList = null;
            } else {
                if ((bean.getStartRow() + bean.getPageSize()) > mapList.size()){
                    mapList = mapList.subList(bean.getStartRow(), mapList.size());
                } else {
                    mapList = mapList.subList(bean.getStartRow(), bean.getStartRow() + bean.getPageSize() - 1);
                }
            }
        }
        return mapResult;
    }
}
