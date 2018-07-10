package com.bonaparte.service;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by yangmingquan on 2018/7/1.
 */
@Service
public class LuceneService {
    private Directory directory;

    private IndexReader indexReader;

    private IndexSearcher indexSearcher;

    @PostConstruct
    public void setUp() throws IOException {

        //索引存放的位置，设置在当前目录中
        directory = FSDirectory.open(Paths.get("indexDir/"));
        //创建索引的读取器
        indexReader = DirectoryReader.open(directory);

        //创建一个索引的查找器，来检索索引库
        indexSearcher = new IndexSearcher(indexReader);
    }

    public void indexWriter() throws IOException {
        Directory directory = FSDirectory.open(Paths.get("indexDir/"));
        Version version = Version.LUCENE_7_1_0;
        IKAnalyzer analyzer = new IKAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        Document document = new Document();
        document.add(new StringField("address", "中国四川省成都市高新区", Field.Store.YES));
        document.add(new StringField("userName", "成都刘德华张学友张靓颖", Field.Store.YES));
        document.add(new StringField("nation", "成都", Field.Store.YES));
        indexWriter.addDocument(document);
        indexWriter.commit();
        indexWriter.close();
    }

    public void queryCheck(String text) throws IOException {
        String searchField = "address";
        text = "成都";
        TermQuery termQuery = new TermQuery(new Term(searchField, text));
        executeQuery(termQuery);
    }

    public void executeQuery(Query query) throws IOException {

        TopDocs topDocs = indexSearcher.search(query, 100);

        //打印查询到的记录数
        System.out.println("总共查询到" + topDocs.totalHits + "个文档");
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {

            //取得对应的文档对象
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println("address：" + document.get("address"));
            System.out.println("userName：" + document.get("userName"));
        }
    }

    public void multiQuery() throws IOException {
        String searchField1 = "address";
        String searchField2 = "userName";
        Query query1 = new TermQuery(new Term(searchField1, "成都"));
        Query query2 = new TermQuery(new Term(searchField2, "成都"));
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        builder.add(query1, BooleanClause.Occur.SHOULD);
        builder.add(query2, BooleanClause.Occur.SHOULD);
        BooleanQuery query = builder.build();
        executeQuery(query);
    }

    public void prefixQuery() throws IOException {
        String searchField = "userName";
        Term term = new Term(searchField, "成都");
        Query query = new PrefixQuery(term);
        executeQuery(query);
    }
}
