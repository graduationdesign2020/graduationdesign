# 毕业设计管理系统
 
 ## 小组成员
 - 518021910456 李萱
 - 518021910515 许嘉琦
 - 518021910375 廖苡辰
 - 510830910007 方拯
 ## 项目介绍
`gdms`基于微信的生态，利用小程序管理毕业设计信息处理全过程，包括选导师、开题、中期检查、答辩、论文定稿、归档等环节；期间还涉及各种通知的发送。院系教务管理人员则通过网页获取院系学生毕设流程信息。目的是让学生、导师及教学管理人员有通畅的消息传递、交流以及数据统计分析功能
## 系统架构图
![Image text](https://github.com/graduationdesign2020/graduationdesign/blob/master/picture/architecture.PNG)
## 组织结构 
``` lua
gdms
├── gdms-auth -- 基于Spring Security Oauth2的统一的认证中心
├── gdms-cloud --
├── gdms-common -- 工具类及通用代码模块
├── gdms-gateway -- 基于Spring Cloud Gateway的微服务API网关服务
├── gdms-monitor -- 基于Spring Boot Admin的微服务监控中心
├── gdms-register -- 
└── gdms-server -- 功能模块
         ├── gdms-core -- 消息模块，定时模块，流程模块
         ├── gdms-notice -- 通知模块
         └── gdms-search -- 基于Elasticsearch的通知搜索系统服务
```
## 技术选型

### 后端技术

| 技术                   | 说明                 | 官网                                                 |
| ---------------------- | -------------------- | ---------------------------------------------------- |
| Spring Cloud           | 微服务框架           | https://spring.io/projects/spring-cloud              |
| Spring Cloud Alibaba   | 微服务框架           | https://github.com/alibaba/spring-cloud-alibaba      |
| Spring Boot            | 容器+MVC框架         | https://spring.io/projects/spring-boot               |
| Spring Security Oauth2 | 认证和授权框架       | https://spring.io/projects/spring-security-oauth     |
| Swagger                | 文档生产工具         | https://swagger.io/    |
| Elasticsearch          | 搜索引擎             | https://github.com/elastic/elasticsearch             |
| MongoDb                | NoSql数据库          | https://www.mongodb.com/                             |
| MySQL                  | Sql数据库            | https://www.mysql.com/                               |
| Docker                 | 应用容器引擎         | https://www.docker.com/                              |
| JWT                    | JWT登录支持          | https://github.com/jwtk/jjwt                         |
| LogStash               | 日志收集             | https://github.com/logstash/logstash-logback-encoder |
| Lombok                 | 简化对象封装工具     | https://github.com/rzwitserloot/lombok               |
| Jenkins                | 自动化部署工具       | https://github.com/jenkinsci/jenkins                 |

### 前端技术

| 技术       | 说明                  | 官网                           |
| ---------- | --------------------- | ------------------------------ |
| 微信开发者工具        | 移动端框架              | https://developers.weixin.qq.com/miniprogram/dev/devtools/devtools.html             |
| React | 前端框架              | https://reactjs.org/      |
| React Router | 路由框架              | http://react-guide.github.io/react-router-cn/docs/Introduction.html     |
| weui-miniprogram    | 移动端UI框架            |https://github.com/wechat-miniprogram/weui-miniprogram      |
| material-ui    | 前端UI框架            |https://material-ui.com/      |

## 开发环境

| 工具          | 版本号 | 下载                                                         |
| ------------- | ------ | ------------------------------------------------------------ |
| JDK           | 1.8    | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Mysql         | 5.7    | https://www.mysql.com/                                       |
| MongoDb       | 3.4  | https://www.mongodb.com/download-center                      |   
| Elasticsearch | 7.9.0  | https://www.elastic.co/cn/downloads/elasticsearch            |
| Kibana        | 7.9.0  | https://www.elastic.co/cn/downloads/kibana                   |
| Logstash      | 7.9.0  | https://www.elastic.co/cn/downloads/logstash                 |
                      
## 功能概览
### 登陆模块
- 用学/工号和姓名注册用户
- 微信小程序自动登录
- 获取用户个人信息（姓名，学/工号，学院，项目名……）
- 获取用户权限
- 注销用户

### 通知模块
- 获取最近三条校内/院内通知
- 获取全部校内/院内通知
- 获取通知详情

### 消息模块
> 教师
- 获取全部负责学生
- 设置回复内容
- 批量发送单向/可回复消息
- 查看全部发出消息
- 查看消息详情
- 查看单向消息学生阅读情况（已读/未读人数，具体名单）
- 查看可回复消息学生阅读情况（已回复/未回复人数，具体名单）
- 查看所有学生回复内容
> 学生
- 查看全部收到消息
- 查看消息详情
- 获取自身是否阅读/回复情况
- 获取自身回复内容
- 发送回复

### 搜索模块
-查询含有关键字的全部校内通知/院内通知/教师消息

### 定时模块
> 教师
- 设置某阶段的截止日期
> 学生
- 在距离截止日期24h时，收到消息提醒

## 数据库概览
> 共16张表，其中NoSQL表4张，存储通知/消息内容等非格式化数据；SQL表12张，存储格式化信息
- MySQL
- MongoDB

## 测试结果
### Unit Test
![Image text](https://github.com/graduationdesign2020/graduationdesign/blob/master/picture/unittest.png)

