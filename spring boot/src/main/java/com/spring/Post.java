package com.spring;

import com.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by XULE on 2018/12/1.
 */
@RestController
@Api(value = "/",description ="这是我全部的post请求")
@RequestMapping("/v1")
public class Post {
    //装cookie信息
    private  static Cookie cookie;
    //用户登录成功获取cookie信息，在用获取到的cookie访问其他接口
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，获取cookie信息成功",httpMethod = "POST")
    public  String login(HttpServletResponse response,
                         @RequestParam(value = "name",required = true) String name,
                         @RequestParam(value = "password",required = true) String password){
        if (name.equals("zdb")&password.equals("123")){
            cookie=new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登录成功";
        }
            return "登录账号密码错误";
    }
    @RequestMapping(value = "/getlist",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户信息",httpMethod = "POST")
    public String getuserlist(HttpServletRequest request, @RequestBody User u){
        User user;
        //获取cookie
        Cookie [] cookies=request.getCookies();
        //验证cookie是否正确
        for (Cookie c:cookies){
            if(c.getName().equals("login")&& c.getValue().equals("true")
                    && u.getName()=="zdb"&& u.getPassword().equals("123")){
                user=new User();
                user.setNN("lisi");
                user.setYy("12333");
                return user.toString();
            }
        }
        return "参数不合法";

    }
}
