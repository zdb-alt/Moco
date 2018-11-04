package com.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by XULE on 2018/11/4.
 */
public class PostCookies {
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
        String result1;
        //从配置文件拼接url
        String url=  bundle.getString("getcookie.url");
        String testurl=this.url+url;
        //测试逻辑代码书写
        HttpGet get =new HttpGet(testurl);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response =client.execute(get);
        result1= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result1);
        //获取cookies信息
        this.store=client.getCookieStore();
        List<Cookie> cookieList=store.getCookies();
        for(Cookie cookie:cookieList){
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("cookies的name  ;"+name+";"+"cookies的value  ;"+value);
        }

    }
    @Test(dependsOnMethods ={"cookies"})
    public  void postcookies() throws IOException {
        String url=bundle.getString("testpostcookies");
        //拼接post地址
        String testurl=this.url+url;
        //声明一个Client对象，来进行方法的执行
        DefaultHttpClient client=new DefaultHttpClient();
        //声明一个方法，post方法
        HttpPost post=new HttpPost(testurl);

        //添加参数
        JSONObject param=new JSONObject();
        param.put("name","zhangdaba");
        param.put("age","18");
        //设置请求头
        post.setHeader("Content-Type","application/json");
        //将参数信息添加到参数中
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //设置cookie信息
        client.setCookieStore(store);
        //执行post方法
        HttpResponse response=client.execute(post);
        //获取响应结果
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //将返回的结果转化为json对象
        JSONObject resultJson=new JSONObject(result);
        //具体的判断返回的值
        String success= (String) resultJson.get("zhangdaba");
        String status= (String) resultJson.get("status");
        //处理结果，判断结果是否符合预期
        Assert.assertEquals("success","success");
        Assert.assertEquals("1","status");

    }


}
