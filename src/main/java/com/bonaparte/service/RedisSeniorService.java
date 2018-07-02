package com.bonaparte.service;

import com.bonaparte.dao.mapper.ChargeMapper;
import com.bonaparte.entity.Charge;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCommands;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yangmingquan on 2018/6/29.
 * redis高级功能
 *   String,
 *   Hash,
 *   List,
 *   Set,
 *   ZSet
 *   队列，热门数据，微博粉丝，排行榜，粉丝数据订阅
 *   Redis 输入缓存，输出缓存
 *   Redis的RDB, AOP机制
 *   Redis的内存分配机制，数据过期策略，内存回收策略
 */
@Service
public class RedisSeniorService {
    @Resource(name = "jedisCluster")
    private JedisCommands jedis;
    @Autowired
    private ChargeMapper chargeMapper;

    public void basicOperate(){
        //String  基本操作
        jedis.set("20180630中奖号码", "01,02,03,04,05,06,07");
        jedis.get("20180630中奖号码");
        //String 30分钟之后数据失效  --注意并不会释放空间，删除数据，只是会让数据失效，客户端不可见
        jedis.expire("20180630中奖号码", 60*30);
        jedis.setex("20180630中奖号码", 2,"01,02,03,04,05,06,07");

        //Hash 基本操作
        jedis.hset("hash1", "name", "qiuqiu");
        jedis.hset("hash1", "name", "xiaomaomi");
        jedis.hset("hash2", "sex", "boycat");
        jedis.hgetAll("hash1");
        jedis.hget("hash1", "name");
        jedis.hkeys("hash1");
        jedis.hdel("hash1", "name");
        jedis.hlen("hash1");
        jedis.hscan("hash1", "name");
        List<Charge> charges = chargeMapper.selectAll();
        Map<String, String> map = new HashedMap();
        charges.stream().forEach(x ->{
            map.put("money", x.getMoney().toString());
            jedis.hmset("list1", map);
        });

        //List 基本操作
        jedis.lpush("list1", "qiuqiu");
        jedis.lpush("list1", "xiaomaomi");
        jedis.lpushx("list1", "watchdog");
        jedis.lindex("list1", 0);
        jedis.llen("list1");
        jedis.lpop("list1");
        jedis.lrange("list1", 0, 2);
        jedis.ltrim("list1", 2,2);

        //Set 基本操作
        jedis.sadd("set1", "qiuqiu", "xiaomaomi", "watchdog");

        //Zset 基本操作
        jedis.zadd("zset", 3.0, "zfeild1");
        jedis.zadd("zset", 2.0, "zfeild2");
        jedis.zadd("zset", 5.0, "zfeild3");
        jedis.zrange("zset", 0, -1);
        jedis.zcard("zset");
        jedis.zscore("zset", "zfeild1");
    }
}
