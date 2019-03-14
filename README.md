# springboot-demo实例demo
**springboot学习的小demo，希望对初学者有点帮助**
1. 项目使用了swagger2自动生成文档
2. 使用thymeleaf进行模板数据填充
3. 使用mybatis封装对数据库的操作（数据库文件在根目录的springboot.sql,需要创建数据库springboot进行导入，具体配置可参考application.properties）

具体实现可参看代码

**以下是使用过程中需要了解的东西**

Spring Boot的静态资源resources下面的加载顺序是：

`META-INF/resources > resources > static > public`

所以本项目中访问 http://localhost:8080/index.html，只会显示META-INF/resources文件夹中的界面

在resources文件夹或与其并列的文件夹下建立public文件夹,
在public文件夹下的html文件可以通过浏览器中输入文件+后缀名的方式直接访问的.

1. public文件夹，就相当于在eclipse的web项目中的web-inf文件夹外的文件，是不需要通过服务器内部进行访问的。
2. templates文件夹，是放置模板文件的，因此需要视图解析器来解析它。所以必须通过服务器内部进行访问，也就是要走控制器--服务--视图解析器这个流程才行。
3. static文件夹这个文件夹，可能是放一些css、图片的静态文件供服务器内部引用。

----------------------------------
springboot项目默认访问是/ 默认不配置下面属性的话（在application.properties） 

可以直接通过http://ip:port/可以访问index

如果在配置文件中增加如下配置 则访问时需要访问http://ip:port/demo

context-path使用与否 结合自己的需求

server.servlet.context-path=/demo

----------------------------------

**Swagger2(可配置第三方界面操作界面)**

1.访问地址：http://localhost:端口号/项目名称/swagger-ui.html

2.很多spring boot的项目是没有项目名称的http://localhost:端口号/swagger-ui.html

本项目的地址是 http://localhost:8080/swagger-ui.html

使用如下第三方库：
https://gitee.com/xiaoym/swagger-bootstrap-ui

ui地址：http://localhost:8080/doc.html


**使用Swagger2相关注解说明**

@Api：用在请求的类上，表示对类的说明

    tags="说明该类的作用，可以在UI界面上看到的注解"
    value="该参数没什么意义，在UI界面上也看到，所以不需要配置"

@ApiOperation：用在请求的方法上，说明方法的用途、作用

     value="说明方法的用途、作用"
     notes="方法的备注说明"

@ApiImplicitParams：用在请求的方法上，表示一组参数说明

@ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面

     name：参数名
     value：参数的汉字说明、解释
     required：参数是否必须传
     paramType：参数放在哪个地方
        · header --> 请求参数的获取：@RequestHeader
        · query --> 请求参数的获取：@RequestParam
        · path（用于restful接口）--> 请求参数的获取：@PathVariable
        · body（不常用）
        · form（不常用）
     dataType：参数类型，默认String，其它值dataType="Integer"
     defaultValue：参数的默认值

 @ApiResponses：用在请求的方法上，表示一组响应
 
 @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
 
    code：数字，例如400
    message：信息，例如"请求参数没填好"
    response：抛出异常的类

 @ApiModel：用于响应类上，表示一个返回响应数据的信息
 
 （这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
 
 @ApiModelProperty：用在属性上，描述响应类的属性
