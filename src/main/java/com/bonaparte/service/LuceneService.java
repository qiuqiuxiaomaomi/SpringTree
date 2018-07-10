package com.bonaparte.service;

import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
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
        document.add(new StringField("userName", "成都刘德华张学友张靓颖car", Field.Store.YES));
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

    public void fuzzyQuery() throws IOException {
        String searchField = "userName";
        Term t = new Term(searchField, "cars");
        Query query = new FuzzyQuery(t);
        executeQuery(query);
    }

    public void wildcardQuery() throws IOException {
        String searchField ="address";
        Term term = new Term(searchField, "中国*成都");
        Query query = new WildcardQuery(term);
        executeQuery(query);
    }

    /**
     * 分词查询
    */
    public void queryParser() throws ParseException, IOException {
        Analyzer analyzer = new IKAnalyzer();
        String searchField = "userName";
        QueryParser parser = new QueryParser(searchField, analyzer);
        Query query = parser.parse("成都");
        executeQuery(query);
    }

    /**
     * 多个字段分词查询
     * */
    public void multiFieldQueryParse() throws IOException, ParseException {
        Analyzer analyzer = new IKAnalyzer();
        String[] filedStr = new String[]{"userName", "address", "nation"};
        QueryParser queryParser = new MultiFieldQueryParser(filedStr, analyzer);
        Query query = queryParser.parse("成都");
        executeQuery(query);
    }

    /**
     * 高亮处理
     * */
    public void highlighter() throws ParseException, IOException, InvalidTokenOffsetsException {
        Analyzer analyzer = new IKAnalyzer();
        String searchField = "address";
        String text = "成都";
        QueryParser parser = new QueryParser(searchField, analyzer);
        Query query = parser.parse(text);
        TopDocs topDocs = indexSearcher.search(query, 100);

        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<span style='color:red'>", "</span>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
        for(ScoreDoc scoreDoc: topDocs.scoreDocs){
            Document document = indexSearcher.doc(scoreDoc.doc);
            TokenStream tokenStream = analyzer.tokenStream("address", new StringReader(document.get("address")));
            String content = highlighter.getBestFragment(tokenStream, document.get("address"));
            System.out.println(content);
        }
    }

    /**
     * 打印分词
     * */
    public void printAnalyzerDoc(Analyzer analyzer, String text) throws IOException {
        TokenStream tokenStream = analyzer.tokenStream("address", new StringReader(text));
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        try{
            tokenStream.reset();
            while (tokenStream.incrementToken()){
                System.out.println(charTermAttribute.toString());
            }
            tokenStream.end();
        }catch (Exception e){

        }finally {
            tokenStream.close();
            analyzer.close();
        }
    }

    public void analyzerCN() throws IOException {
        Analyzer analyzer = null;
        String text = "Hadoop Spark 是出自美国洛杉矶的计算机大师的专为大规模数据处理而设计的快速通用的计算引擎";
        analyzer = new IKAnalyzer();
        printAnalyzerDoc(analyzer, text);

        analyzer = new ComplexAnalyzer();
        printAnalyzerDoc(analyzer, text);

        analyzer = new SmartChineseAnalyzer();
        printAnalyzerDoc(analyzer, text);
    }
}
