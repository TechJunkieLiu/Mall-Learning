# Mall-Learning
## 一、项目简介
`mall-learning`是一套电商系统（学习），包括前台商城系统及后台管理系统，基于SpringBoot+MyBatis实现。 
前台商城系统包含首页门户、商品推荐、商品搜索、商品展示、购物车、订单流程、会员中心、客户服务、帮助中心等模块。 
后台管理系统包含商品管理、订单管理、会员管理、促销管理、运营管理、内容管理、统计报表、财务管理、权限管理、设置等模块。
## 二、项目结构
  ```
  mall
  ├─ document -- 文档集合
  ├─ mall-common -- 封装工具类及通用代码
  ├─ mall-enter -- 商城系统业务代码
  ├─ mall-security -- 封装SpringSecurity公用模块
  ```
## 三、功能模块
- 商品模块
  - 商品管理
  - 商品分类管理
  - 商品类型管理
  - 品牌管理
- 订单模块
  - 订单管理
  - 订单设置
  - 退货申请处理
  - 退货原因设置
- 营销模块
  - 秒杀活动管理
  - 优惠价管理
  - 品牌推荐管理
  - 新品推荐管理
  - 人气推荐管理
  - 专题推荐管理
  - 首页广告管理
## 四、数据库表设计
- PMS - 商品模块
  - 商品分类
    - pms_product_category - 商品分类表
  - 品牌管理
    - pms_brand - 商品品牌表
  - 商品类型（即商品属性，主要是指商品的规格和参数，规格用于用户购买商品时选择，参数用于标示商品属性及搜索时筛选）
    - pms_product_attribute_category - 商品属性分类表
    - pms_product_attribute - 商品属性表（type字段用于控制其是规格还是参数）
    - pms_product_attribute_value - 商品属性值表（如果对应的参数是规格且规格支持手动添加，那么该表用于存储手动新增的值；如果对应的商品属性是参数，那么该表用于存储参数的值）
    - pms_product_category_attribute_relation - 商品分类和属性的关系表（用于选中分类后搜索时生成筛选属性）
  - 编辑商品
    - pms_product - 商品表（商品信息主要包括四部分：商品的基本信息、商品的促销信息、商品的属性信息、商品的关联，商品表是整个商品的基本信息部分）
    - pms_sku_stock - 商品SKU表
    - pms_product_ladder - 商品阶梯价格表（商品优惠相关表，购买同商品满足一定数量后，可以使用打折价格进行购买。如：买两件商品可以打八折）
    - pms_product_full_reduction - 商品满减表（商品优惠相关表，购买同商品满足一定金额后，可以减免一定金额。如：买满1000减100元）
    - pms_member_price - 商品会员价格表（根据不同会员等级，可以以不同的会员价格购买。此处设计有缺陷，可以做成不同会员等级可以减免多少元或者按多少折扣进行购买）
  - 商品评价及回复
    - pms_comment - 商品评价表
    - pms_comment_replay - 商品评价回复表
  - 商品审核及操作记录
    - pms_product_verify_record - 商品审核记录表（用于记录商品审核记录）
    - pms_product_operate_log - 商品操作记录表（用于记录商品操作记录）
- OMS - 订单模块  
  - 订单
    - oms_order - 订单表（订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单）
    - oms_order_item - 订单商品信息表（订单中包含的商品信息，一个订单中会有多个订单商品信息）
    - oms_order_operate_history - 订单操作记录表（当订单状态发生改变时，用于记录订单的操作信息）
  - 订单设置
    - oms_order_setting - 订单设置表（用于对订单的一些超时操作进行设置）
  - 购物下单
    - oms_cart_item - 购物车表（用于存储用户选择的商品信息及计算购物车中商品的优惠金额）
  - 订单退货
    - oms_order_return_apply - 订单退货申请表（主要用于存储会员退货申请信息，需要注意的是订单退货申请表的四种状态：0->待处理；1->退货中；2->已完成；3->已拒绝）
    - oms_company_address - 公司收货地址表（用于处理退货申请时选择收货地址）
  - 订单退货原因设置
    - oms_order_return_reason - 订单退货原因表（用于会员退货时选择退货原因）
- SMS - 营销模块（运营+促销）
  - 限时购（秒杀）
    - sms_flash_promotion - 限时购表（用于存储限时购活动的信息，包括开始时间、结束时间以及上下线状态）
    - sms_flash_promotion_session - 限时购场次表（用于存储限时购场次的信息，在一天中，一个限时购活动会有多个不同的活动时间段）
    - sms_flash_promotion_product_relation - 限时购与商品关系表（用于存储与限时购相关的商品信息，一个限时购中有多个场次，每个场次都可以设置不同活动商品）
    - sms_flash_promotion_log - 限时购通知记录表（用于存储会员的限时购预约记录，当有的限时购场次还未开始时，会员可以进行预约操作，当场次开始时，系统会进行提醒）
  - 优惠券
    - sms_coupon - 优惠券表（用于存储优惠券信息，需要注意的是优惠券的使用类型：0->全场通用；1->指定分类；2->指定商品，不同使用类型的优惠券使用范围不一样）
    - sms_coupon_history - 优惠券历史记录表（用于存储会员领取及使用优惠券的记录，当会员领取到优惠券时，会产生一条优惠券的记录，需要注意的是它的使用状态：0->未使用；1->已使用；2->已过期）
    - sms_coupon_product_relation - 优惠券和商品的关系表（用于存储优惠券与商品的关系，当优惠券的使用类型为指定商品时，优惠券与商品需要建立关系）
    - sms_coupon_product_category_relation - 优惠券和商品分类关系表（用于存储优惠券与商品分类的关系，当优惠券的使用类型为指定分类时，优惠券与商品分类需要建立关系）
  - 首页内容推荐
    - sms_home_brand - 首页品牌推荐表（用于管理首页显示的品牌制造商直供信息）
    - sms_home_new_product - 新品推荐商品表（用于管理首页显示的新鲜好物信息）
    - sms_home_recommend_product - 人气推荐商品表（用于管理首页显示的人气推荐信息）
    - sms_home_recommend_subject - 首页专题推荐表（用于管理首页显示的专题精选信息）
    - sms_home_advertise - 首页轮播广告表（用于管理首页显示的轮播广告信息）
- CMS - 内容模块
  - 专题管理
  - 优选主题
  - 话题管理
  - 帮助管理
  - 举报管理
- UMS - 用户模块
  - 用户管理：可以对后台用户进行管理并分配角色，支持分配多个角色
  - 菜单管理：可以实现对后台管理系统左侧菜单的管理，支持更换图标、更换名称、控制菜单显示和排序
  - 资源管理：实现了基于访问路径的后台动态权限控制，控制的权限可以精确到接口级别
  - 角色管理：可以自定义角色，并为角色分配菜单和资源
  - 动态菜单控制：系统管理员分配了所有菜单，商品管理员只分配了商品相关的菜单
  - 动态资源控制：商品管理员只分配了商品相关的资源，他无法访问其他资源（如订单模块）
## 五、技术选型
### 1、后端技术
| 技术                 | 说明                | 官网                                           |
| -------------------- | ------------------- | ---------------------------------------------- |
| SpringBoot           | Web应用开发框架      | https://spring.io/projects/spring-boot         |
| SpringSecurity       | 认证和授权框架       | https://spring.io/projects/spring-security     |
| MyBatis              | ORM框架             | http://www.mybatis.org/mybatis-3/zh/index.html |
| MyBatisGenerator     | 数据层代码生成器     | http://www.mybatis.org/generator/index.html    |
| Elasticsearch        | 搜索引擎            | https://github.com/elastic/elasticsearch       |
| RabbitMQ             | 消息队列            | https://www.rabbitmq.com/                      |
| Redis                | 内存数据存储         | https://redis.io/                              |
| MongoDB              | NoSql数据库         | https://www.mongodb.com                        |
| LogStash             | 日志收集工具        | https://github.com/elastic/logstash            |
| Kibana               | 日志可视化查看工具  | https://github.com/elastic/kibana              |
| Nginx                | 静态资源服务器      | https://www.nginx.com/                         |
| Docker               | 应用容器引擎        | https://www.docker.com                         |
| Jenkins              | 自动化部署工具      | https://github.com/jenkinsci/jenkins           |
| Druid                | 数据库连接池        | https://github.com/alibaba/druid               |
| OSS                  | 对象存储            | https://github.com/aliyun/aliyun-oss-java-sdk  |
| MinIO                | 对象存储            | https://github.com/minio/minio                 |
| JWT                  | JWT登录支持         | https://github.com/jwtk/jjwt                   |
| Lombok               | Java语言增强库      | https://github.com/rzwitserloot/lombok         |
| Hutool               | Java工具类库        | https://github.com/looly/hutool                |
| PageHelper           | MyBatis物理分页插件 | http://git.oschina.net/free/Mybatis_PageHelper |
| Swagger-UI           | API文档生成工具      | https://github.com/swagger-api/swagger-ui      |
| Hibernator-Validator | 验证框架            | http://hibernate.org/validator                 |
### 2、前端技术
| 技术       | 说明                  | 官网                                   |
| ---------- | --------------------- | -------------------------------------- |
| Vue        | 前端框架              | https://vuejs.org/                     |
| Vue-router | 路由框架              | https://router.vuejs.org/              |
| Vuex       | 全局状态管理框架      | https://vuex.vuejs.org/                |
| Element    | 前端UI框架            | https://element.eleme.io               |
| Axios      | 前端HTTP框架          | https://github.com/axios/axios         |
| v-charts   | 基于Echarts的图表框架 | https://v-charts.js.org/               |
| Js-cookie  | cookie管理工具        | https://github.com/js-cookie/js-cookie |
| nprogress  | 进度条控件            | https://github.com/rstacruz/nprogress  |
### 3、开发工具
| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
| IDEA          | 开发IDE             | https://www.jetbrains.com/idea/download         |
| RedisDesktop  | redis客户端连接工具 | https://github.com/qishibo/AnotherRedisDesktopManager  |
| Robomongo     | mongo客户端连接工具 | https://robomongo.org/download                  |
| SwitchHosts   | 本地host管理        | https://oldj.github.io/SwitchHosts/             |
| X-shell       | Linux远程连接工具   | http://www.netsarang.com/download/software.html |
| Navicat       | 数据库连接工具      | http://www.formysql.com/xiazai.html             |
| PowerDesigner | 数据库设计工具      | http://powerdesigner.de/                        |
| Axure         | 原型设计工具        | https://www.axure.com/                          |
| MindMaster    | 思维导图设计工具    | http://www.edrawsoft.cn/mindmaster              |
| ScreenToGif   | gif录制工具         | https://www.screentogif.com/                    |
| ProcessOn     | 流程图绘制工具      | https://www.processon.com/                      |
| PicPick       | 图片处理工具        | https://picpick.app/zh/                         |
| Snipaste      | 屏幕截图工具        | https://www.snipaste.com/                       |
| Postman       | API接口调试工具      | https://www.postman.com/                        |
| Typora        | Markdown编辑器      | https://typora.io/                              |
### 4、开发环境
| 工具          | 版本号 | 下载                                                         |
| ------------- | ------ | ------------------------------------------------------------ |
| JDK           | 1.8    | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Mysql         | 5.7    | https://www.mysql.com/                                       |
| Redis         | 7.0    | https://redis.io/download                                    |
| MongoDB       | 5.0    | https://www.mongodb.com/download-center                      |
| RabbitMQ      | 3.10.5 | http://www.rabbitmq.com/download.html                        |
| Nginx         | 1.22   | http://nginx.org/en/download.html                            |
| Elasticsearch | 7.17.3 | https://www.elastic.co/downloads/elasticsearch               |
| Logstash      | 7.17.3 | https://www.elastic.co/cn/downloads/logstash                 |
| Kibana        | 7.17.3 | https://www.elastic.co/cn/downloads/kibana                   |
## 六、关键业务分析
### 1、SPU
Standard Product Unit，标准商品单位，商品信息聚合的最小单位，是一组可复用、易检索的标准化信息的集合，该集合描述了一个商品的特性。
### 2、SKU
Stock Keeping Unit，库存量单位，是物理上不可分割的最小存货单元。举例：iphone xs 是一个 SPU，而 iphone xs 公开版 64G 银色是一个 SKU。
### 3、购物下单流程
从商品加入购物车到下单的整个流程，涉及购物车优惠计算流程、确认单生成流程、下单流程及取消订单流程
- [购物下单整体流程](D:\workspace-learning1\mall-learning\picture\购物下单整体流程.png)
- 购物车优惠计算流程（待补充...）
- 确认单生成流程（待补充...）
- 订单生成流程（下单流程）（待补充...） 
- 订单取消流程（待补充...）
## 七、架构图
### 1、系统架构图
[系统架构图](D:\workspace-learning1\mall-learning\picture\系统架构图.jpg)
### 2、业务架构图
[业务架构图](D:\workspace-learning1\mall-learning\picture\业务架构图.png)
