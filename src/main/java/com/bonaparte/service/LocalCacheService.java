package com.bonaparte.service;

import com.bonaparte.entity.Charge;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangmingquan on 2018/6/29.
 */
@Service
public class   LocalCacheService {
     public static WeakReference<Integer> sessonIdWeakRef = new WeakReference<Integer>(1000);

    public static CacheLoader<Integer, Charge> cacheLoader = new CacheLoader<Integer, Charge>() {
        @Override
        public Charge load(Integer key) throws Exception {
            return new Charge(key, 12.0);
        }
    };

     public static LoadingCache<Integer, Charge> localCache = CacheBuilder.newBuilder()
             .maximumSize(1000)
             .refreshAfterWrite(60, TimeUnit.MINUTES)
             .expireAfterAccess(12, TimeUnit.HOURS)
             .build(cacheLoader);

     public Integer getSessionId(){
         if (sessonIdWeakRef.isEnqueued()){
             if (sessonIdWeakRef.get() == 10){
                 System.out.println("数据被缓存在弱引用中");
                 return sessonIdWeakRef.get();
             }
         }
         return null;
     }

     public Charge getChargeInfo(Integer key){
         try {
             if (localCache.get(key) != null){
                 return localCache.get(key);
             }
         }catch (Exception e){

         }
         return null;
     }

     public void putChargeInfo(Integer key, Charge charge){
         try{
             localCache.put(key, charge);
         }catch (Exception e){

         }
     }
}
