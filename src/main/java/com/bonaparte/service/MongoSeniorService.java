package com.bonaparte.service;

import com.bonaparte.entity.Charge;
import com.bonaparte.util.BeanUtil;
import com.bonaparte.util.MongoDao;
import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by yangmingquan on 2018/6/29.
 * mongodb 高级功能
 */
@Service
public class MongoSeniorService {
    @Autowired
    private MongoDao mongoDao;

    public static GridFS gfs = null;

    String file = "src/main/resources/20180413153203.jpg";

    public static final String CHARGEINFO = "chargeinfo";

    public void testMongo() {
        Charge charge = new Charge(1, 100.0);
        try {
            Map<String, Object> map = BeanUtil.objectToMap(charge);
            mongoDao.save(CHARGEINFO, map);
            DBObject dbObject = mongoDao.findById(CHARGEINFO, "1");
            DB db = mongoDao.getMongoTemplate().getDb();
            DBObject query = new BasicDBObject();
            DBObject orderBy = new BasicDBObject();
            query.put("speclibId", 1);
            ;
            orderBy.put("ctime", -1);
            DBCursor dbCursor = db.getCollection(CHARGEINFO).find(query);
            dbCursor.sort(orderBy);
            dbCursor.skip(10);
            dbCursor.limit(100);
            while (dbCursor.hasNext()) {
                DBObject row = dbCursor.next();
            }

            //
            gfs = new GridFS(db);
            save(new FileInputStream(file), "20180413153203.jpg", "20180413153203.jpg");

            //
            DBCollection dbCollection = db.getCollection(CHARGEINFO);
            dbCollection.createIndex(new BasicDBObject("money", 1));
            dbCollection.getIndexInfo();
            //dbCollection.aggregate()
            DBObject dbObject1 = new BasicDBObject();
            dbObject1.put("status", 1);
            dbCollection.distinct("money", dbObject1);
            dbObject1.put("status", 2);
            dbCollection.findAndRemove(dbObject1);
            db.collectionExists(CHARGEINFO);
            //创建固定集合
            DBObject dbObject2 = new BasicDBObject();
            dbObject2.put("capped",true);
            dbObject2.put("size", 1024);
            dbObject2.put("max", 1024);
            db.createCollection(CHARGEINFO, dbObject2);
            //
            db.getReadPreference();
            db.getCollectionNames();
        } catch (Exception e) {
            System.out.println("插入mongodb数据");
        }
    }

    /**
     * 用给出的id，保存文件，透明处理已存在的情况
     * id 可以是string，long，int，org.bson.types.ObjectId 类型
     *
     * @param in
     * @param id
     */
    public static void save(InputStream in, Object id, String fileName) {
        DBObject query = new BasicDBObject("_id", id);
        GridFSDBFile gridFSDBFile = gfs.findOne(query);
        if (gridFSDBFile == null) {
            GridFSInputFile gridFSInputFile = gfs.createFile(in);
            gridFSInputFile.setId(id);
            gridFSInputFile.setFilename(fileName);
            gridFSInputFile.save();
        }
    }

    /**
     * 据id返回文件
     *
     * @param id
     * @return
     */
    public static GridFSDBFile getById(Object id) {
        DBObject query = new BasicDBObject("_id", id);
        GridFSDBFile gridFSDBFile = gfs.findOne(query);
        return gridFSDBFile;
    }

    /**
     * 据文件名返回文件，只返回第一个
     *
     * @param fileName
     * @return
     */
    public static GridFSDBFile getByFileName(String fileName) {
        DBObject query = new BasicDBObject("filename", fileName);
        GridFSDBFile gridFSDBFile = gfs.findOne(query);
        return gridFSDBFile;
    }


}
