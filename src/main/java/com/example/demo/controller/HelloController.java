package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;

// 访问模板页面
// 注意：不要使用@RestController注解，
// @RestController注解是@ResponseBody和@Controller的集合体，使用@RestController注解会默认返回数据，而不会请求到页面。
@Controller
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 本地访问内容地址 ：http://localhost:8080/hello
     *
     * @param map
     * @return
     */
    @ApiOperation(value = "跳转到置顶界面", notes = "通过Model对象传值到对应的返回页面，返回页面通过thymeleaf模板引擎来接收传来的值")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloHtml(HashMap<String, Object> map, Model model) {
        User user = userMapper.getOne(1L);
        // 通过Model对象传值到对应的返回页面，返回页面通过thymeleaf模板引擎来接收传来的值
        model.addAttribute("say", "欢迎欢迎,热烈欢迎");
        map.put("hello", "欢迎进入HTML页面" + user.toString());
        return "temp";// temp.html页面如有路径可：xxx/temp.html
    }

    /**
     * 本地访问内容地址 ：http://localhost:8080/helloHtml2
     *
     * @return
     */
    @ApiOperation(value = "跳转到置顶界面", notes = "通过Model对象传值到对应的返回页面，返回页面通过thymeleaf模板引擎来接收传来的值")
    @RequestMapping(value = "/helloHtml2", method = RequestMethod.GET)
    public ModelAndView helloHtml2(ModelAndView model) {
        // templates文件夹下的文件名
        model.setViewName("temp");
        User user = userMapper.getOne(1L);
        // 通过Model对象传值到对应的返回页面，返回页面通过thymeleaf模板引擎来接收传来的值
        model.addObject("say", "helloHtml2欢迎欢迎,热烈欢迎");
        model.addObject("hello", "helloHtml2欢迎进入HTML页面" + user.toString());
        return model;// temp.html页面如有路径可：xxx/temp.html
    }

    @ApiIgnore
    @RequestMapping("/my_page")
    public String my_page(RedirectAttributes attr) {
        return "/redirect_page";
    }
}
