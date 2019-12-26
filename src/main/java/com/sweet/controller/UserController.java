package com.sweet.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 宋德能
 * @date 2019年12月26日---上午 9:34
 */
@Controller
public class UserController {

    @RequestMapping("/name")
    @ResponseBody
    public String name(){
        return "张三";
    }

    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        model.addAttribute("name","百战尚硅谷");
        return "test";
    }


    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }


    @RequestMapping("/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String name,String password,Model model){
        //使用shiro编写认证操作
        // 1. 获取subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        // 3. 执行登录方法
        try{
            //登陆成功,跳转到test
            subject.login(token);
            return "testThymeleaf";
        }catch (UnknownAccountException e){
            //登陆失败：用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //登陆失败：密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

}
