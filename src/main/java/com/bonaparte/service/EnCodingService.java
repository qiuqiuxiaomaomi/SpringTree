package com.bonaparte.service;

import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by yangmingquan on 2018/7/16.
 * 编解码
 * UTF-8等等
 */
@Service
public class EnCodingService {

    public void readWriteFile() throws UnsupportedEncodingException {
        File file = new File("resources/application.properties");
        byte[] content = new byte[1000];
        try {
            InputStream inputStream = new FileInputStream(file);
            inputStream.read(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(content);
        String s = new String(content, "UTF-8");
        System.out.println(s);
        s = new String(content, "GBK");
        System.out.println(s);
    }
}
