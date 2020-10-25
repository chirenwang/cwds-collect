package com.wcc.wds.core.biz;

import lombok.SneakyThrows;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 遍历文件线程
 */
public class ListRunnable implements Runnable{

    private final static Logger logger = LoggerFactory.getLogger(ListRunnable.class);
    private final ConcurrentLinkedQueue<String> fileQueue;
    private final String collectPath;
    private final String regex;
    private final AtomicInteger atomicInteger;
    private final List<String> withdrawFiles;
    private final Timestamp latestDate;


    public ListRunnable(ConcurrentLinkedQueue<String> fileQueue, String collectPath, String regex, AtomicInteger atomicInteger, List<String> withdrawFiles, Timestamp latestDate) {
        this.fileQueue = fileQueue;
        this.collectPath = collectPath;
        this.regex = regex;
        this.atomicInteger = atomicInteger;
        this.withdrawFiles = withdrawFiles;
        this.latestDate = latestDate;
    }



    @SneakyThrows
    @Override
    public void run() {
        try {
            logger.info("start to list file：" + collectPath);
            listFiles(collectPath, regex);
            atomicInteger.getAndAdd(1);
        }catch (Exception e){
            logger.error("list file failed：" + e.getMessage());
            throw e;
        }
    }


    /**
     * 递归遍历文件夹获取文件名
     * @param collectPath 采集路径
     * @param regex 文件名正则
     * @throws IOException
     */
    private void listFiles(String collectPath, String regex) throws IOException {
        //构建本地文件系统
        FileSystem fileSystem = FileSystem.getLocal(new Configuration());
        //生成正则表达式对象
        Pattern pattern = Pattern.compile(regex);
        //获取当前文件夹下的对象
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path(collectPath));
        for (FileStatus fileStatus : fileStatuses){
            //去掉路径头
            String path = fileStatus.getPath().toString().replaceAll("file:/", "");
            //如果文件修改时间比上次采集的时间大，则为更新文件，需要采集
            if (latestDate != null){ if (latestDate.getTime() > fileStatus.getModificationTime())continue;}
            //如果是已撤稿文件，则不采集
            if (withdrawFiles.contains(path)){continue;}
            //如果是文件则将文件路径加入文件队列，若是目录则递归下一级
            if (!fileStatus.isDirectory()){
                //获取文件名并匹配正则表达式
                String fileName = fileStatus.getPath().getName();
                Matcher matcher = pattern.matcher(fileName);
                if (matcher.matches()){
                    fileQueue.add(path);
                }
            }else {
                listFiles(path, regex);
            }
        }
    }
}
