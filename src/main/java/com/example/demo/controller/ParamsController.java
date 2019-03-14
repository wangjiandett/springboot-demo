package com.example.demo.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

// @RestController相当于@Controller+@ResponseBody(每一个方法上默认返回的是json串)
@RestController
@RequestMapping("/params")
public class ParamsController {

    // @PathVariable:
    // 获取url中的数据，如：
    // http://localhost:8080/say/101
    // return id = 101
    @ApiOperation(value = "测试RequestMapping和PathVariable的使用", notes = "url后拼接id来请求，如：http://localhost:8080/say/101")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Integer")
    @RequestMapping(value = {"/say/{id}"}, method = RequestMethod.GET)
    public String say(@PathVariable("id") Integer id) {
        return "say id:" + id;
    }

    // @RequestParam
    // 这个针对的是？=这种url,支持默认值
    // http://localhost:8080/say?id=101
    // return id = 101
    @ApiOperation(value = "测试RequestMapping的使用", notes = "url后拼接id来请求，如：http://localhost:8080/say?id=101")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "query", dataType = "Integer")
    @RequestMapping(value = {"/say1"}, method = RequestMethod.GET)
    public String say1(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myId) {
        return "say1 id:" + myId;
    }

    // @GetMapping
    // 简化的RequestMapping
    // @RequestMapping(value={"/say"},method = RequestMethod.GET)
    // http://localhost:8080/say?id=101
    // return id = 101
    @ApiOperation(value = "测试GetMapping的使用", notes = "url后拼接id来请求，如：http://localhost:8080/say?id=101")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "query", dataType = "Integer")
    @GetMapping(value = "/say2")
    public String say2(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myId) {
        return "say2 id:" + myId;
    }

    @ApiIgnore
    @RequestMapping(value = "/redirect6Next", method = RequestMethod.GET)
    public String redirect6Next(HttpServletRequest request) {
        return request.getParameter("id");
    }
}
