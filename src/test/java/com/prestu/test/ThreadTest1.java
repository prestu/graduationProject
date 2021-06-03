package com.prestu.test;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

public class ThreadTest1 extends Thread {
    private String url;
    private String name;
    public ThreadTest1(String url,String name) {
        this.url = url;
        this.name = name;
    }
    @Override
    public void run() {
//        super.run();
        DownLoader downLoader = new DownLoader();
        downLoader.down(url,name);
        System.out.println("下载成功"+name);
    }

    public static void main(String[] args) {
        ThreadTest1 test1 = new ThreadTest1("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/04/28/kuangstudy64a54e27-7e96-4b9f-9fdc-4398a7b39eef.png","1.jpg");
        ThreadTest1 test2 = new ThreadTest1("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/04/28/kuangstudy64a54e27-7e96-4b9f-9fdc-4398a7b39eef.png","2.jpg");
        ThreadTest1 test3 = new ThreadTest1("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/04/28/kuangstudy64a54e27-7e96-4b9f-9fdc-4398a7b39eef.png","3.jpg");
        test1.start();
        test2.start();
        test3.start();
    }
}
class DownLoader {
    @SneakyThrows
    public void down(String url, String name) {
        FileUtils.copyURLToFile(new URL(url), new File(name));
    }
}
