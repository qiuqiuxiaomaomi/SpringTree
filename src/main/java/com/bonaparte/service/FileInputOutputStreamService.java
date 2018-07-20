package com.bonaparte.service;

import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by yangmingquan on 2018/7/19.
 * 本章要研究的是输入输出流
 * 要点：正确认识字符流，字节流的应用场景以及想换转换
 */
@Service
public class FileInputOutputStreamService {

    /**
     * 字符流
     * */
    public static void checkStream() throws IOException {
        FileWriter fileWriter = new FileWriter("E:\\xiaoming\\checkstream.txt");
        fileWriter.write("IO 流处理逻辑");
        fileWriter.flush();
        fileWriter.close();

        FileReader fileReader = new FileReader("E:\\xiaoming\\checkstream.txt");
        char [] buf = new char[100];
        while (fileReader.read(buf) != -1){

        }
        System.out.println(buf);
        fileReader.close();

        //缓冲区
        FileReader fileReader1 = new FileReader("E:\\xiaoming\\checkstream.txt");
        //装饰器模式
        BufferedReader bufferedReader = new BufferedReader(fileReader1);
        for(;;){
            String s = bufferedReader.readLine();
            if (s == null){
                break;
            }else{
                System.out.println(s);
            }
        }
        bufferedReader.close();
        fileReader1.close();
    }

    /**
     * 字节流
     * */
    public void checkByteStream() throws IOException {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try{
            fileInputStream = new FileInputStream("E:\\xiaoming\\checkstream.txt");
            fileOutputStream = new FileOutputStream("E:\\xiaoming\\checkstreamout.txt");
            byte[] copy = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(copy)) != -1){
                fileOutputStream.write(copy);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fileInputStream.close();
            fileOutputStream.close();
        }

        //缓冲区，装饰器模式
        fileInputStream = new FileInputStream("E:\\xiaoming\\checkstream.txt");
        fileOutputStream = new FileOutputStream("E:\\xiaoming\\checkstreamout.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        byte[] copy1 = new byte[1024];
        while(bufferedInputStream.read(copy1) != -1){
            System.out.println("go on read");
            bufferedOutputStream.write(copy1);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }

//    public static void main(String[] args){
//        try {
//            checkStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
