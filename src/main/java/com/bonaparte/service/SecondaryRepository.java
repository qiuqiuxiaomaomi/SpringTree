package com.bonaparte.service;

import com.bonaparte.common.PrimaryMongoObject;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by yangmingquan on 2018/7/26.
 */
public interface SecondaryRepository extends MongoRepository<PrimaryMongoObject, String>{
}
