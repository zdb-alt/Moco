package com.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by XULE on 2018/10/29.
 */
public class GETCookies {
    private  String url;
    private ResourceBundle bundle;
    @BeforeTest
    public  void test(){
        bundle=ResourceBundle.getBundle("peizhi", Locale.CHINA);
        bundle.getString("test.url");
    }
    @Test
    public  void cookies() throws IOException {
        String result;
        //从配置文件拼接url
      String url=  bundle.getString("getcookie.url");
      String testurl=this.url+url;
        //测试逻辑代码书写
        HttpGet get =new HttpGet(testurl);
        HttpClient client=new DefaultHttpClient();
        HttpResponse response =client.execute(get);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.print(result);

    }

}
