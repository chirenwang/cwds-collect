package com.wcc.wds.core.biz;

import lombok.SneakyThrows;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.wcc.wds.web.data.PublicData.FILE_SCHEMA;

public class ListRunnable implements Runnable{

    private final static Logger logger = LoggerFactory.getLogger(ListRunnable.class);
    private final ConcurrentLinkedQueue<String> fileQueue;
    private final String collectPath;
    private final String regex;
    private final AtomicInteger atomicInteger;


    public ListRunnable(ConcurrentLinkedQueue<String> fileQueue, String collectPath, String regex, AtomicInteger atomicInteger) {
        this.fileQueue = fileQueue;
        this.collectPath = collectPath;
        this.regex = regex;
        this.atomicInteger = atomicInteger;
    }



    @SneakyThrows
    @Override
    public void run() {
        try {
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
        //获取本地文件系统
        FileSystem fileSystem = FileSystem.get(URI.create(FILE_SCHEMA), new Configuration());
        //生成正则表达式对象
        Pattern pattern = Pattern.compile(regex);
        //获取当前文件夹下的对象
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path(collectPath));
        for (FileStatus fileStatus : fileStatuses){
            String path = fileStatus.getPath().toString();
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