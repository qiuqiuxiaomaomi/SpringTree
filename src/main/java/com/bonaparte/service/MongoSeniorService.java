package com.bonaparte.service;

import com.bonaparte.entity.Charge;
import com.bonaparte.util.BeanUtil;
import com.bonaparte.util.MongoDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.connection.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by yangmingquan on 2018/6/29.
 * mongodb 高级功能
 */
@Service
public class MongoSeniorService {
    @Autowired
    private MongoDao mongoDao;

    public static final String CHARGEINFO = "chargeinfo";

    public void testMongo(){
        Charge charge = new Charge(1,100.0);
        try {
            Map<String, Object> map = BeanUtil.objectToMap(charge);
            mongoDao.save(CHARGEINFO, map);
            DBObject dbObject = mongoDao.findById(CHARGEINFO, "1");
            DB db = mongoDao.getMongoTemplate().getDb();
            DBObject query = new BasicDBObject();
            DBObject orderBy = new BasicDBObject();
            query.put("speclibId", 1);;
            orderBy.put("ctime", -1);
            DBCursor dbCursor = db.getCollection(CHARGEINFO).find(query);
            dbCursor.sort(orderBy);
            dbCursor.skip(10);
            dbCursor.limit(100);
            while (dbCursor.hasNext()) {
                DBObject row = dbCursor.next();
            }
        }catch (Exception e){
            System.out.println("插入mongodb数据");
        }
    }
}
