package com.bonaparte.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

/**
 * Created by yangmingquan on 2018/7/1.
 * Socket 通信基础
 */
@Service
public class SocketService {
    //使用URL读取网页内容
    public void readFromUrl() throws IOException {
        try {
            URL url = new URL("https://zhidao.baidu.com/question/243626243216124124.html");
            InputStream inputStream = url.openStream();
            System.out.println(inputStream);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            System.out.println(inputStreamReader);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String data = br.readLine();
            while (data != null){
                System.out.println(data);
                data = br.readLine();
            }
            br.close();
            inputStream.close();
            inputStreamReader.close();
        }catch (Exception e){

        }finally {

        }
    }

    public void InetAddress() throws IOException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress.getHostAddress());
        System.out.println(inetAddress.getAddress());
        System.out.println(inetAddress.getHostName());
        System.out.println(inetAddress.getCanonicalHostName());
        System.out.println(inetAddress.isReachable(5));
    }
}
