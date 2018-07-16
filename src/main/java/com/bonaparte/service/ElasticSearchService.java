package com.bonaparte.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bonaparte.bean.UserInfo;
import com.bonaparte.util.HttpClientUtil;
import com.bonaparte.util.MapUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by yangmingquan on 2018/1/23.
 * es 的高级功能
 */
@Service
public class ElasticSearchService {
    private final static Log log = LogFactory.getLog(ElasticSearchService.class);

    @Value("${elasticsearch.maxQueryNum}")
    private Integer maxQueryNum;
    @Value("${elasticsearch.url}")
    private String url;
    @Value("${elasticsearch.index}")
    private String esIndex;
    @Autowired
    private TransportClient transportClient;

    //有中文的不走拼音分词
    private static String[] cfields = new String[]{
            "name^100",
            "name.only^1500",
            "ename^50",
            "ename.only^1300"};

    private static String[] efields = new String[]{
            "name^50",
            "name.only^1500",
            "ename^40",
            "ename.only^1300"};

    public void batchQuery(){
        String searchName = "球球";
        Map multiMatch = getMultiMatch(searchName, cfields, null);
        List<Map> list = new ArrayList<>();
        List<Map> filters = new ArrayList<>();
        list.add(multiMatch);
        List statusList = new ArrayList();
        statusList.add(0);
        statusList.add(1);
        Map statusDSL = getTerms(null, "status", statusList);
        filters.add(statusDSL);
        Map sort = new LinkedHashMap();
        sort.put("_score", "desc");
        Map query = getBoolQuery(null, list, filters, null,1, null);
        String[] sources = new String[]{"id", "name", "ename"};
        JSONObject hits = query(esIndex, "user", query,sort, sources,100, 1);
        JSONArray hitsJsonArray = hits.getJSONArray("hits");
        Long total = hits.getLong("total");
    }

    public Map getMultiMatch(String query,String[] fields,String type,String... analyzer){
        Map map = new HashMap();
        map.put("query",query);
        map.put("fields",fields);
        if(type != null){
            map.put("type",type);
        }
        if(analyzer.length>0){
            map.put("analyzer",analyzer[0]);
        }
        return MapUtil.createHashMap("multi_match",map);
    }

    public Map getBoolQuery(Object must,Object should,Object filter,Object must_not,Integer minimum_should_match,Float boost){
        Map bool = new HashMap();
        if(must != null){
            bool.put("must",must);
        }
        if(should != null){
            bool.put("should",should);
        }
        if(filter != null){
            bool.put("filter",filter);
        }
        if(must_not != null){
            bool.put("must_not",must_not);
        }
        if(minimum_should_match != null){
            bool.put("minimum_should_match",minimum_should_match);
        }
        if(boost != null){
            bool.put("boost",boost);
        }
        return MapUtil.createHashMap("bool",bool);
    }

    public Map getTerms(String path,String key,List values){
        if(path != null){
            key = path+"."+key;
        }
        Map term = MapUtil.createHashMap(key,values);
        return MapUtil.createHashMap("terms",term);
    }

    public JSONObject query(String index,String type,Map query,Map sort,String[] fields,Integer size,Integer from){
        JSONObject hits = null;
        String path = String.format(url, index, type);
        Map param = new HashMap();
        param.put("query",query);
        if(sort != null){
            param.put("sort",sort);
        }
        if(fields != null){
            param.put("_source",fields);
        }
        if(from != null){
            if(from>maxQueryNum){
                from = maxQueryNum;
            }
            param.put("from",from);
        }
        if(size != null){
            if(size>maxQueryNum){
                size = maxQueryNum;
            }
            if(from != null){
                size = from + size > 10000 ? 10000-from : size;
            }
            param.put("size",size);
        }

        String str = JSON.toJSONString(param);
        log.info("DSL语句为"+str);
        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost(path);
            post.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
            post.setHeader("Connection", "keep-alive");
            String sessionId = getSessionId();
            post.setHeader("SessionId", sessionId);
            post.setHeader("appid", "mzk");
            StringEntity entity = new StringEntity( str, Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/x-www-form-urlencoded");
            post.setEntity(entity);
            HttpClientUtil.Response res = (HttpClientUtil.Response)httpClient.execute(post, new ResponseHandler() {
                public HttpClientUtil.Response handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    StatusLine statusLine = response.getStatusLine();
                    HttpEntity entity = response.getEntity();
                    if(statusLine.getStatusCode() != 200) {
                        throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
                    } else {
                        HttpClientUtil.Response res = new HttpClientUtil.Response();
                        res.setHeaders(response.getAllHeaders());
                        if(entity == null) {
                            throw new ClientProtocolException("Response contains no content");
                        } else {
                            ContentType contentType = ContentType.get(entity);
                            res.setContentType(contentType);
                            InputStream is = entity.getContent();
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();

                            try {
                                byte[] buf = new byte[2048];
                                boolean len = false;

                                int len1;
                                while((len1 = is.read(buf)) != -1) {
                                    bos.write(buf, 0, len1);
                                }

                                res.setContent(bos.toByteArray());
                                HttpClientUtil.Response var10 = res;
                                return var10;
                            } finally {
                                IOUtils.closeQuietly(is);
                                IOUtils.closeQuietly(bos);
                            }
                        }
                    }
                }
            });
            JSONObject resultJson = JSON.parseObject(res.toString());
            //logger.info("es返回数据为"+resultJson);
            hits = resultJson.getJSONObject("hits");
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("请求es数据失败");
        }

        return hits;
    }

    public JSONObject query(String index,String type,Map query,Map sort,Integer size,Integer from){
        return query(index,type,query,sort,null,size,from);
    }

    // 构建唯一会话Id
    public static String getSessionId(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    }

    public void saveSome(){
        Map<String, Object> map = new HashedMap();
        UserInfo userInfo = new UserInfo();
        BulkRequestBuilder builder = transportClient.prepareBulk();
        builder.add(transportClient.prepareIndex(esIndex, "user", userInfo.getPhone().toString()).setSource(JSON.toJSONString(userInfo)));
        BulkResponse response = builder.execute().actionGet();
        String error = response.buildFailureMessage();
    }
}
