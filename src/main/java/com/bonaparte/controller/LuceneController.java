package com.bonaparte.controller;

import com.bonaparte.service.LuceneService;
import com.bonaparte.util.ControllerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * Created by yangmingquan on 2018/7/10.
 */
@Api(value = "LuceneController", description = "Lucene分词器API")
@RestController
@RequestMapping("/lucene")
public class LuceneController {
//    @Resource
//    private LuceneService luceneService;

    @ApiOperation(value = "Lucene操作接口",notes = "Lucene操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/info")
    public Object luceneInfo() throws IOException, ParseException, InvalidTokenOffsetsException {
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
//        luceneService.indexWriter();
//        luceneService.queryCheck("成都");
//        luceneService.multiQuery();
//        luceneService.prefixQuery();
//        luceneService.fuzzyQuery();
//        luceneService.wildcardQuery();
//        luceneService.queryParser();
//        luceneService.multiFieldQueryParse();
//        luceneService.highlighter();
//        luceneService.analyzerCN();
        return map;
    }
}
