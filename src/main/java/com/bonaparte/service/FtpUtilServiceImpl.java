package com.bonaparte.service;

import com.bonaparte.dao.mapper.FtpAddressMapper;
import com.bonaparte.entity.FtpAddress;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;

/**
 * Created by yangmingquan on 2018/8/29.
 * ftp连接  文件上传，下载
 */
@Service
public class FtpUtilServiceImpl {
    private final static Log logger = LogFactory.getLog(FtpUtilServiceImpl.class);
    @Autowired
    private FtpAddressMapper ftpAddressMapper;

    public boolean uploadFile(int addressId, String filename, InputStream input) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            FtpAddress ftpAddress = ftpAddressMapper.selectByPrimaryKey(addressId);
            if (ftpAddress == null) {
                return success;
            }
            int reply;
            logger.info("ftp开始获取连接时间"+new Date());
            ftp.connect(ftpAddress.getHost(), ftpAddress.getPort());// 连接FTP服务器
            logger.info("ftp获取连接时间"+new Date());
            ftp.setControlEncoding("UTF-8");
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            logger.info("开始用户"+ftpAddress.getUserName()+"登录");
            ftp.login(ftpAddress.getUserName(), ftpAddress.getPassword());// 登录
            logger.info("用户"+ftpAddress.getUserName()+"登录成功");
            ftp.enterLocalPassiveMode();
            ftp.setBufferSize(1024*1024);
            ftp.setDataTimeout(60000);
            ftp.setConnectTimeout(60000);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.makeDirectory(ftpAddress.getPath());
            ftp.changeWorkingDirectory(ftpAddress.getPath());
            //修改目录后，再进行一次，状态码判断
            reply = ftp.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply)){
                ftp.disconnect();
                return success;
            }
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            logger.info("ftp文件开始时间"+new Date());
            ftp.storeFile(new String(filename.getBytes("UTF-8"), "ISO-8859-1"), input);
            logger.info("ftp文件结束时间"+new Date());
            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                    logger.info("ftp关闭连接时间"+new Date());
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    /**
     * Description: 从FTP服务器下载文件
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName 要下载的文件名
     * @param localPath 下载后保存到本地的路径
     * @return
     */
    public boolean downFile(int addressId, String remotePath, String fileName, String localPath) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            FtpAddress ftpAddress = ftpAddressMapper.selectByPrimaryKey(addressId);
            if (ftpAddress == null) {
                return success;
            }
            int reply;
            OutputStream is = null;
            ftp.connect(ftpAddress.getHost(), ftpAddress.getPort());
            ftp.setControlEncoding("UTF-8");
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(ftpAddress.getUserName(), ftpAddress.getPassword());// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + fileName);
                    is = new FileOutputStream(localFile);
                    ftp.retrieveFile(new String(fileName.getBytes("UTF-8"), "ISO-8859-1"), is);
                    is.close();
                }
            }
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    /**
     * Description: 从FTP服务器下载文件
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName 要下载的文件名
     * @param localPath 下载后保存到本地的路径
     * @param charset 下载编码
     * @return
     */
    public boolean downFile(int addressId, String remotePath, String fileName, String localPath, String charset, String localfileName) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            OutputStream is = null;
            FtpAddress ftpAddress = ftpAddressMapper.selectByPrimaryKey(addressId);
            if (ftpAddress == null) {
                return success;
            }
            ftp.connect(ftpAddress.getHost(), ftpAddress.getPort());
            ftp.setControlEncoding(charset);
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(ftpAddress.getUserName(), ftpAddress.getPassword());// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + localfileName);
                    is = new FileOutputStream(localFile);
                    ftp.retrieveFile(new String(fileName.getBytes(charset), "ISO-8859-1"), is);
                    is.close();
                }
            }
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    public boolean deleteFile(int addressId, String path) {
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            FtpAddress ftpAddress = ftpAddressMapper.selectByPrimaryKey(addressId);
            if (ftpAddress == null) {
                return false;
            }
            String host = ftpAddress.getHost();
            Integer port = ftpAddress.getPort();
            String username = ftpAddress.getUserName();
            String password = ftpAddress.getPassword();
            String tmpath = ftpAddress.getPath();
            String delpath = path.replace("ftp://"+ host + ":" + port , "");
            logger.info("ftp开始获取连接时间" + new Date());
            ftp.connect(host, port);// 连接FTP服务器
            logger.info("ftp获取连接时间" + new Date());
            ftp.setControlEncoding("UTF-8");
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            ftp.enterLocalPassiveMode();
            ftp.setBufferSize(1024 * 1024);
            ftp.setDataTimeout(60000);
            ftp.setConnectTimeout(60000);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            return ftp.deleteFile(delpath);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除文件失败"+e.getMessage());
        }finally {
            if(ftp.isConnected()){
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
