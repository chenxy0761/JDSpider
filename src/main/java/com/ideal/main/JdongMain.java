package com.ideal.main;

import java.util.List;

import com.ideal.db.MYSQLControl;
import com.ideal.model.JdModel;
import com.ideal.util.URLFecter;
import org.apache.log4j.Logger;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class JdongMain {
    private static Logger logger = Logger.getLogger(JdongMain.class);
    public static void main(String[] args) throws Exception {
        //初始化一个httpclient
        HttpClient client = new DefaultHttpClient();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
        String url="http://search.jd.com/Search?keyword=Python&enc=utf-8&book=y&wq=Python&pvid=33xo9lni.p4a1qb";
        //抓取的数据
        List<JdModel> bookdatas= URLFecter.URLParser(client, url);
        //循环输出抓取的数据
        for (JdModel jd:bookdatas) {
            logger.info("bookID:"+jd.getBookID()+"\t"+"bookPrice:"+jd.getBookPrice()+"\t"+"bookName:"+jd.getBookName());
        }
        //将抓取的数据插入数据库
        MYSQLControl.executeInsert(bookdatas);
    }
}
