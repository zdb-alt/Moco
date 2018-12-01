package com.spring;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpResponse;
import org.apache.http.cookie.Cookie;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * Created by XULE on 2018/11/13.
 */
@RestController
@Api(value = "/",description = "这是我的所有get方法")
public class Gettcookies {
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取cookies信息 ",httpMethod = "GET")
    public  String getcookies(HttpServletResponse response){
        //HttpServletResponse装响应信息的类
        //HttpServletRequest装请求信息的类
      //  javax.servlet.http.Cookie cookie=new javax.servlet.http.Cookie("login","true");
        javax.servlet.http.Cookie cookie=new javax.servlet.http.Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获取cookies成功!!!!!";
    }
    /*
    要求客户端必须携带cookies访问
     */
    @RequestMapping(value = "/getcookiess",method = RequestMethod.GET)
    @ApiOperation(value = "你必须携带cookies信息！！",httpMethod = "GET")
    public  String getcookiess(HttpServletRequest request){
        javax.servlet.http.Cookie [] cookies=request.getCookies();
        if(Objects.isNull(cookies)){
            return  "你必须携带cookies信息！！";
        }
        for(javax.servlet.http.Cookie cookie:cookies){
            if(cookie.getName().equals("login")
                    &&cookie.getValue().equals("true")) {
                    return "这是必须携带cookies信息。。。。。";
            }

        }

             return  "你必须携带cookies信息！！";

    }

    /*
  开发一个需要携带参数才能访问的get请求
  第一种格式（key=value&key=value）
   */
    //路径：localhost://8800/getlist?start=10&end=20
    @RequestMapping(value = "/getlist",method = RequestMethod.GET)
    @ApiOperation(value ="第一种需要携带参数才能访问的get请求",httpMethod = "GET")
    public Map<String,Integer> getlist(@RequestParam  Integer start,
                                       @RequestParam Integer end){
            Map<String,Integer> myList=new HashMap<String, Integer>();
            myList.put("鞋子",1000);
            myList.put("衣服",800);
            myList.put("墨镜",1000);
            return myList;

    }
    @RequestMapping(value = "/getlistt/{start}/{end}")
    @ApiOperation(value = "第二种需要携带参数才能访问的get请求",httpMethod = "GET")
    public Map<String,Integer> getlistt(@PathVariable Integer start,
                                        @PathVariable Integer end){
        Map<String,Integer> myList=new HashMap<String, Integer>();
        myList.put("鞋子",1000);
        myList.put("衣服",800);
        myList.put("墨镜",1000);
        return myList;

    }
}
