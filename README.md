## 简介

Remote-Express-Server是一个电脑桌面远程解决方案后端项目，监控用户通过客户端通过外网登陆远程内网电脑桌面，仅允许用户通过配置的端口和电脑登陆远程电脑桌面，保证端与端之间的的隐私与安全。数据库sql文件在/src/main/resource下的[RemoteExpress.sql][sql]文件.

- 要用到的技术以及作用:
```
spring、springmvc、mybatis、mysql、nginx、maven-3.5.4、JDK1.8
spring做后端整合框架
springmvc前端控制和请求转发、文件上传处理、http消息转换
mysql数据库做数据存储
mybatis做数据访问框架
maven用作项目构建工具
mybatis-generator 是一个mybatis代码生成插件,该插件根据数据库的表生成1.与数据库表对应的对象实体 2. 访问数据库的dao层接口 3.dao层接口的实现的mapper.xml文件
```
- 用户：
  客户端用户， 管理员


## 前序准备

你需要在本地安装 [jdk](http://nodejs.org/) 1.7+和 [git](https://git-scm.com/)。

项目环境：
  - eclipse
  - maven
  - git

技术栈：
  - mysql 
  - druid 数据连接方式
  - spring				
  - mybatis 持久化
  - OkhttpClient4 网络请求
  - ehcache 进程内缓存
  - fastjson
  - shiro 权限验证框架
  - linux 脚本命令
  - netty
  - nginx


**如有问题请先看上述使用文档和文章，若不能满足，欢迎 issue 和 pr**

## 模块


- 员工管理
  - 添加，登陆，登出，查看个人信息，修改个人信息，删除，强制下线，当前在线用户数量
- 用户组管理
  - 员工组
    - 添加，删除，修改，查询
  - 远程组
    - 添加，删除，修改，查询
- 远程管理
  - 添加远程配置，映射内外与外网，开放内网端口给到当前电脑ip
  - 修改远程配置，并更新内网映射与防火墙
  - 删除远程配置
  - 查询远程配置
- 管理员
  - 添加，删除，修改，查询
- 远程连接记录
  - 查看远程配置连接记录列表
- 用户登陆记录
  - 查看用户登陆连接记录列表



## 更新日志

各版本更新详情文档在[更新备注][changelog].

## 捐赠

如果你觉得这个项目帮助到了你，你可以帮作者买一杯果汁表示鼓励 :tropical_drink:

![捐赠][donate-qrcode]

<br>
Copyright (c) 2020-present kimleysoft





[sql]:src/main/resources/RemoteExpress.sql
[donate-qrcode]:https://kimleysoft.github.io/donate/donation.png
[changelog]:https://github.com/kimleysoft/Remote-Express-Server/releases
