package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.example.demo.enums.UserSexEnum;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM users")
    // @Result 修饰返回的结果集，关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    List<User> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    User getOne(Long id);

    @Insert("INSERT INTO users(userName,passWord,user_sex,nick_name) VALUES(#{userName}, #{passWord}, #{userSex}, #{nickName})")
    int insert(User user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName},user_sex=#{userSex} WHERE id =#{id}")
    int update(User user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    int delete(Long id);

    // This example creates a prepared statement, something like select * from teacher where name = ?;
    //@Select("Select * from teacher where name = #{name}")
    //Teacher selectTeachForGivenName(@Param("name") String name);

    // This example creates n inlined statement, something like select * from teacher where name = 'someName';
    //@Select("Select * from teacher where name = '${name}'")
    //Teacher selectTeachForGivenName(@Param("name") String name);
}

