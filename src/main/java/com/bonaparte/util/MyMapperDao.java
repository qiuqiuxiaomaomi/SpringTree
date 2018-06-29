package com.bonaparte.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by yangmingquan on 2018/6/29.
 */
public class MyMapperDao {
//    private ApplicationContext context;
//
//    public MyMapperDao() {
//    }
//
//    public MyMapperDao(ApplicationContext context) {
//        this.context = context;
//    }
//
//    private <T> Mapper<T> getMapper(Class<T> entityClass) {
//        String mapperName = StringUtils.uncapitalize(entityClass.getSimpleName()) + "Mapper";
//        Mapper<T> mapper = (Mapper)this.context.getBean(mapperName);
//        if(mapper == null) {
//            throw new NullPointerException(entityClass.getSimpleName() + "Mapper not found");
//        } else {
//            return mapper;
//        }
//    }
//
//    public <T> int insert(T record) {
//        return this.getMapper(record.getClass()).insert(record);
//    }
//
//    public <T> int insertSelective(T record) {
//        return this.getMapper(record.getClass()).insertSelective(record);
//    }
//
//    public <T> int delete(T record, Class<T> entityClass) {
//        return this.getMapper(entityClass).delete(record);
//    }
//
//    public <T> int deleteByPrimaryKey(Object key, Class<T> entityClass) {
//        return this.getMapper(entityClass).deleteByPrimaryKey(key);
//    }
//
//    public <T> int updateByPrimaryKey(T record) {
//        return this.getMapper(record.getClass()).updateByPrimaryKey(record);
//    }
//
//    public <T> int updateByPrimaryKeySelective(T record) {
//        return this.getMapper(record.getClass()).updateByPrimaryKeySelective(record);
//    }
//
//    public <T> List<T> select(T record, Class<T> entityClass) {
//        return this.getMapper(entityClass).select(record);
//    }
//
//    public <T> List<T> selectAll(Class<T> entityClass) {
//        return this.getMapper(entityClass).selectAll();
//    }
//
//    public <T> T selectByPrimaryKey(Object key, Class<T> entityClass) {
//        return this.getMapper(entityClass).selectByPrimaryKey(key);
//    }
//
//    public <T> int selectCount(T record, Class<T> entityClass) {
//        return this.getMapper(entityClass).selectCount(record);
//    }
}
