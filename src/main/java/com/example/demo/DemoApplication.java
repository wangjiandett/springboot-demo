package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//https://github.com/ityouknow/spring-boot-examples

//扫描的mapper,或者直接在Mapper类上面添加注解@Mapper,建议使用这种，不然每个mapper加个注解也挺麻烦的
@MapperScan("com.example.demo.mapper")
//该@SpringBootApplication注解等价于以默认属性使用@Configuration，@EnableAutoConfiguration和@ComponentScan。
@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * 为了打包springboot项目发布
     * 运行在tomcat中需要继承SpringBootServletInitializer并重新如下方法
     *
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }
}
