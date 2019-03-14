package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

// @RestController相当于@Controller+@ResponseBody(每一个方法上默认返回的是json串)
@RestController
// 实例 http://localhost:8080/users/getUsers
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    // 实例 http://localhost:8080/getUsers
    @ApiOperation(value = "获取用户列表", notes = "")
    @GetMapping("/getUsers")
    public List<User> getUsers(HttpServletRequest request) {
        System.out.println("UserController--" + request.getRequestURI());
        System.out.println("UserController--" + request.getRequestURL());
        System.out.println("UserController--" + request.getParameter("id"));
        return userMapper.getAll();
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "query", dataType = "Long")
    @GetMapping("/getUser")
    public User getUser(@RequestParam(value = "id") Long id) {
        return userMapper.getOne(id);
    }

    @ApiOperation(value = "新增用户", notes = "根据用户对象新增用户json格式")
    @PostMapping("/insertUser")
    public int insertUser(@RequestBody @ApiParam(name = "用户对象", value = "传入json格式", required = true) User user) {
        return userMapper.insert(user);
    }

    //@ApiIgnore//忽略不生成文档
    @ApiOperation(value = "更新用户信息", notes = "根据传入的json格式数据更新用户")
    //@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "body", dataType = "Long")
    @PostMapping(value = "/updateUser")
    public int update(@RequestBody @ApiParam(name = "用户对象", value = "传入json格式", required = true)User user) {
        return userMapper.update(user);
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public int delete(@PathVariable("id") Long id) {
        return userMapper.delete(id);
    }

}
