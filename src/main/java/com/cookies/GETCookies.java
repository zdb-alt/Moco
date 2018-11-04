package com.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import  org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by XULE on 2018/10/29.
 */
public class GETCookies {
    private  String url;
    private ResourceBundle bundle;
    //原来存储cookies信息的变量
    private CookieStore store;
    @BeforeTest
    public  void test(){
        bundle=ResourceBundle.getBundle("peizhi", Locale.CHINA);
        url=bundle.getString("test.url");
    }
    @Test
    public  void cookies() throws IOException {
        String result;
        //从配置文件拼接url
      String url=  bundle.getString("getcookie.url");
      String testurl=this.url+url;
        //测试逻辑代码书写
        HttpGet get =new HttpGet(testurl);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response =client.execute(get);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //获取cookies信息
         this.store=client.getCookieStore();
        List<Cookie> cookieList=store.getCookies();
        for(Cookie cookie:cookieList){
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("cookies的name  ;"+name+";"+"cookies的value  ;"+value);
        }

    }
    @Test(dependsOnMethods = {"cookies"})
    public void getcookies() throws IOException {
        String url=  bundle.getString("testgetcookies");
        String testurl=this.url+url;
        HttpGet get=new HttpGet(testurl);
        DefaultHttpClient client=new DefaultHttpClient();
        //设置cookies信息
        client.setCookieStore(this.store);
        HttpResponse response=client.execute(get);
        int statusCode= response.getStatusLine().getStatusCode();
        if(statusCode==200){
          String  result= EntityUtils.toString(response.getEntity(),"UTF-8");
            System.out.println(result);
        }


    }


}
