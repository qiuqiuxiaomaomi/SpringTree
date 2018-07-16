package com.bonaparte.util;

import java.util.*;

/**
 * Created by yangmingquan on 2018/7/16.
 */
public class MapUtil {
    public MapUtil() {
    }

    public static <K, V> HashMap<K, V> createHashMap(Object... objs) {
        HashMap map = new HashMap();
        if(objs == null) {
            return map;
        } else if(objs.length % 2 != 0) {
            throw new IllegalArgumentException();
        } else {
            for(int i = 0; i < objs.length; i += 2) {
                map.put(objs[i], objs[i + 1]);
            }

            return map;
        }
    }

    public static <K, V> Map<V, List<Map<K, V>>> mapGroupByKey(List<Map<K, V>> maps, String groupKey, boolean delGroupKey) {
        HashMap groupMap = new HashMap();
        Iterator var4 = maps.iterator();

        while(var4.hasNext()) {
            Map m = (Map)var4.next();
            addMapList(groupMap, m.get(groupKey), m);
            if(delGroupKey) {
                m.remove(groupKey);
            }
        }

        return groupMap;
    }

    public static <K, V> Map<K, List<V>> addMapList(Map<K, List<V>> map, K key, V value) {
        Object list = (List)map.get(key);
        if(list == null) {
            list = new LinkedList();
            map.put(key, (List<V>) list);
        }

        ((List)list).add(value);
        return map;
    }

    public static <K, V> Map<V, Map<K, V>> listMapToMap(List<Map<K, V>> list, String key) {
        HashMap map = new HashMap();
        Iterator var3 = list.iterator();

        while(var3.hasNext()) {
            Map m = (Map)var3.next();
            map.put(m.get(key), m);
        }

        return map;
    }

    public static <K, V> void replaceMapKey(Map<K, V> map, Map<K, K> keyMap) {
        Iterator var2 = keyMap.keySet().iterator();

        while(var2.hasNext()) {
            Object key = var2.next();
            map.put(keyMap.get(key), map.get(key));
            map.remove(key);
        }

    }

    public static <K, V> void replaceListMapKey(List<Map<K, V>> maps, Map<K, K> keyMap) {
        Iterator var2 = maps.iterator();

        while(var2.hasNext()) {
            Map map = (Map)var2.next();
            replaceMapKey(map, keyMap);
        }

    }

    public static String formatCode(Map<String, Boolean> map, List<String> fields, String trueCode, String falseCode, String nullCode) {
        StringBuffer buffer = new StringBuffer();
        Iterator var6 = fields.iterator();

        while(var6.hasNext()) {
            String f = (String)var6.next();
            Boolean v = (Boolean)map.get(f);
            if(v == null) {
                buffer.append(nullCode);
            } else if(v.booleanValue()) {
                buffer.append(trueCode);
            } else {
                buffer.append(falseCode);
            }
        }

        return buffer.toString();
    }
}
