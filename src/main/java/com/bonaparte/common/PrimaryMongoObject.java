package com.bonaparte.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * Created by yangmingquan on 2018/7/26.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "first_mongo")
public class PrimaryMongoObject {
    @Id
    private String id;
    private String value;

    @Override
    public String toString(){
        return "PrimaryMongoObject{" + "id='" + id + '\'' + ", value='" +value +'\'' + '}';
    }
}
