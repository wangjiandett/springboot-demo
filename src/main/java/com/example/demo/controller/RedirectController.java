package com.example.demo.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 访问html模板页面
// 注意：不要使用@RestController注解，
// @RestController注解是@ResponseBody和@Controller的集合体
// ，使用@RestController注解会默认返回数据，而不会请求到页面。
@Controller
public class RedirectController {

    @ApiOperation(value = "测试redirect重定向的使用", notes = "使用response重定向如：redirect:/users/getUsers")
    @RequestMapping(value = "/redirect", method = {RequestMethod.GET})
    public String redirect() {
        return "redirect:/users/getUsers";
    }

    @ApiOperation(value = "测试HttpServletResponse重定向的使用", notes = "使用response重定向如：response.sendRedirect(\"/users/getUsers\")")
    @RequestMapping(value = "/redirect2", method = {RequestMethod.GET})
    public ModelAndView redirect2(HttpServletResponse response) {
        try {
            response.sendRedirect("/users/getUsers");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "测试带参数的重定向", notes = "通过RedirectAttributes将参数拼接到地址后面")
    @RequestMapping(value = "/redirect3", method = RequestMethod.GET)
    public String redirect3(Model model, RedirectAttributes attr) {
        attr.addAttribute("id", 9988);//跳转地址带上test参数
        attr.addFlashAttribute("u2", "can not send data");//跳转地址不带上u2参数
        return "redirect:/params/say2";
    }

    @RequestMapping(value="/redirect4",method = { RequestMethod.GET })
    public  ModelAndView redirect4(){
        ModelAndView  model = new ModelAndView("redirect:/params/say2");
        return model;
    }

    @RequestMapping(value="/redirect5",method = { RequestMethod.GET })
    public  ModelAndView redirect5(){
        //ModelAndView默认使用forward重定向方式，只能跳转到template中的界面
        ModelAndView  model = new ModelAndView("/redirect_page");
        model.addObject("userName", "username-redirect5");  //把userName参数带入到controller的RedirectAttributes
        return model;
    }

    @RequestMapping(value = "/redirect6", method = RequestMethod.GET)
    public String redirect6(RedirectAttributes attr) {
        attr.addAttribute("id", 9988);//跳转地址带上test参数,可通过HttpServletRequest中获取
        return "redirect:/params/redirect6Next";
    }

    @RequestMapping(value = "/redirect7", method = RequestMethod.GET)
    public String redirect7(RedirectAttributes attr) {
        attr.addFlashAttribute("userName", "redirect7 name is xxx");//跳转地址带上test参数,可通过HttpServletRequest中获取
        return "redirect:/my_page";
    }
}
