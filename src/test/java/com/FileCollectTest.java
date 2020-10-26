package com;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.yarn.webapp.hamlet2.Hamlet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FileCollectTest {


    public static void main(String[] args) throws IOException, URISyntaxException {
        File sourceFile = new File("C:\\Users\\60269\\Desktop\\content_1876543.html");
        System.out.println(sourceFile.exists());
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.getLocal(configuration);
        Path path = new Path("C:\\Users\\60269\\Desktop\\content_1876543.html");
        String contributionId = path.getName().replaceAll("content_", "").replaceAll(".html","");
        System.out.println(contributionId);

        System.out.println(path.toString());
        FSDataInputStream in = fileSystem.open(new Path("C:\\Users\\60269\\Desktop\\content_1876543.htm"));
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        String content = new String(bytes);
        Document document = Jsoup.parse(content);
        String body = document.getElementsByTag("body").text();
        System.out.println(body);
        String title = document.getElementsByTag("title").text();
        String articleMain  = document.getElementsByClass("article-main").text();
        String editor = document.getElementsByClass("editor").text();
        String publishTime = document.getElementsByClass("publish-time").text();
        String positionInner = document.getElementsByClass("position-inner").text();
        String a = "{\"query\":{\"match_all\":{}}}";

//        System.out.println(articleMain.getElementsByTag("p").get(0).getElementsByTag("img").toString());


    }
}
