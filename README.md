# SpringTree

<pre>
    springboot
    mybatis
    mysql
    zookeeper
    mongodb
    kafka
    rocketmq
    memcached
    es
    lamda
    fork/join
    ThreadPool
    微信支付
    Elasticsearch
</pre>

### 1:微信支付，暂时只支持公众号网页（调用微信客户端）支付，不支持二维码扫码支付 
### 2:Lamda 函数式编程基础，优化美丽的高性能代码
     函数式编程
     Consumer
### 3:Redis 让业务更快，让锁，mq有多重选择
### 4:Guava 
     Ratelimiter接口限速
     Preconditions参数检测
     本地缓存 CacheBuilder（相比于数组,List, 可固定大小，定期删除等优势）
     Optional 判断空值类
### 5:Concurrent 并发工具包 ThreadPoolExcutor线程池
### 6:Fork/Join JDK 1.7提供的多线程框架已经与JDK 1.8 lamda的关系
     采用工作窃取模式（当前线程任务执行完成，可窃取其他线程的执行任务），将大任务分解成多个小任务，最后将结果join
### 7:分布式锁 
     redis
     zookeeper
### 8:mongodb
     多数据源配置
     文档数据库
     基础查询
     保存数据到磁盘
     创建索引等
### 9:synchronized
     临界区间
### 10:Transactional本地事务
     事务的传播属性
     事务的隔离级别
     事务的回滚策略 
### 11:ThreadLocal提高程序的并发性能 
### 12:Volatile 
     内存可见性，禁止指令重排序
### 13:Map
     HashMap（非线程安全） 
     Concurrent有很多锁（使用锁分段技术，每一段一把锁，大大的提高程序的并发性能，安全性）
### 14:Socket通信  
### 15:Queue队列
    1:消费者/生产者模型
### 16:Mybatis
    1:一级缓存，二级缓存
    2:Mybatis的内置TypeHandler
    3:注解
### 17:Aop功能
    设置优先级的Aop拦截功能
### 18:Memcached Nosql数据库
    1:Memcached客户端的负载均衡
### 19:Quartz定时任务
    1:分布式环境下的定时任务status配置
### 20 Lucene 全文检索工具
### 21 Protocal Buffer 
    序列化工具
### 22 RxJava 线程间通信框架
### 23:Kafka/Rocketmq 消息
### 24 Kaptcha 验证码功能
### 25:Es搜索
### 27:泛型编程
### 28:编解码
### 29:Mycat
### 30:Groovy
    Java中如何执行Groovy的Shell脚本
### 31:Tencent云人脸检测，人脸识别功能
    基于对象存储桶imgUrl的检测
    基于图片对象image的检测
### 32:排序算法的研究
    1：冒泡排序
    2：快速排序
### 33:堆栈，堆
### 35:二叉树
    先序遍历（递归与非递归方式）
    中序遍历（递归与非递归方式）
    后续遍历（递归与非递归方式）
### 36:HashMap     
    模拟Hashmap的扩容过程
      扩容需要重新计算hash
      扩容与Treemap二叉树
      扩容与负载因子
### 37:Java原生持久化层JPA
### 38:java全局事务（分布式事务）
    Jta
    XA
    2PC
    Tcc
### 39:Mysql分库分表
### 40:Elevator电梯算法
