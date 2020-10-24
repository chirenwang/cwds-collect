package com;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FileCollectTest {


    public static void main(String[] args) throws IOException, URISyntaxException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create("file:///"), configuration);
        FSDataInputStream in = fileSystem.open(new Path("D:\\BaiduNetdiskDownload\\ctnews\\ctnews\\news\\2019-03\\21\\content_949558.html"));
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        String content = new String(bytes);
        Document document = Jsoup.parse(content);
        String title = document.getElementsByTag("title").text();
        String articleMain  = document.getElementsByClass("article-main").text();
        String editor = document.getElementsByClass("editor").text();
        String publishTime = document.getElementsByClass("publish-time").text();
        String positionInner = document.getElementsByClass("position-inner").text();


//        System.out.println(articleMain.getElementsByTag("p").get(0).getElementsByTag("img").toString());


    }
}
