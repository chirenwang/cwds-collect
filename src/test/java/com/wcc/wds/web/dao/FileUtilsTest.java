package com.wcc.wds.web.dao;

import com.wcc.wds.web.utils.FileUtils;
import org.junit.Test;

import java.io.File;

public class FileUtilsTest {

    @Test
    public void fileEnc() {
        File file1 = new File("F:\\test\\xxxxx.txt");
        File file2 = new File("F:\\test\\1.txt");
        FileUtils.encFile(file1, file2);
    }

    @Test
    public void fileDes() {
        File file1 = new File("F:\\test\\xxxx.txt");
        File file2 = new File("F:\\test\\1.txt");
        FileUtils.decFile(file2, file1);

    }

    @Test
    public void fileDelete() {
        File file = new File("F:\\test\\1.txt");
        FileUtils.delete(file);
    }

    @Test
    public void fileSetContent() {
        File file = new File("/1111/xxxxx.txt");
        FileUtils.setContent(file, "xxxxxxxxx");
    }
}
