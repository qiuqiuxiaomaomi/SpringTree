package util;

import java.util.UUID;

/**
 * Created by yangmingquan on 2018/6/29.
 */
public class StringTools {
    public static String randonStr(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
