package com.spring;

import org.apache.http.HttpResponse;
import org.apache.http.cookie.Cookie;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;


/**
 * Created by XULE on 2018/11/13.
 */
@RestController
public class Gettcookies {
    @RequestMapping(value = "/get",method = RequestMethod.GET)
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

}
