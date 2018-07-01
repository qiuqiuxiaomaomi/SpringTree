package com.bonaparte.util;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Map;

/**
 * Created by yangmingquan on 2018/7/1.
 */
public class MongoDao {
    private DB db;
    private MongoTemplate mongoTemplate;

    public MongoDao() {
    }

    public final DB getDb() {
        return this.db;
    }

    public final void setDb(DB db) {
        this.db = db;
    }

    public final MongoTemplate getMongoTemplate() {
        return this.mongoTemplate;
    }

    public final void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.db = mongoTemplate.getDb();
    }

    public void save(String collectionName, Map<String, Object> map) {
        this.db.getCollection(collectionName).save(new BasicDBObject(map));
    }

    public void save(String collectionName, String jsonStr) {
        this.db.getCollection(collectionName).save(BasicDBObject.parse(jsonStr));
    }

    public DBObject findById(String collectionName, Object id) {
        return this.db.getCollection(collectionName).findOne(id);
    }
}
