# spring-boot-feature-demos
基于SpringBoot创建的一些Spring特性使用示例，所有项目均基于SpringBoot 2.2.6.RELEASE。


# 项目清单
* rabbitmq-demo
* redis-demo
* mysql-demo
* mybatis-demo
* mybatis-plus-demo
* security-basic-demo
* kafka-demo

# 项目介绍
### rabbitmq-demo
示例了在 SpringBoot 项目中使用 RabbitMQ，并实现了四种类型交换器的简单使用，分别为 direct (直连型)，topic (主题型)，fanout (广播型)，headers(头交换型)

### redis-demo
使用 Spring Cache 实现数据缓存，缓存服务器使用 redis。项目内实现了缓存 key 自定义，数据存储序列化机制修改（默认使用 jdk 的对象序列化，修改为使用 jackson 的序列化，便于在文本编辑器中查看存储内容）

### mysql-demo
利用了 SpringBoot 的自动化数据源配置，默认创建 Hikari 连接池。使用 Spring 对数据库的支持实现了三个从数据库读取数据的示例：
1. spring-jdbc：Spring 对 Jdbc API 的封装，使用 JdbcTemplate 实现访问数据库的操作
2. spring-data-jdbc：Spring 对 ORM 的一个轻量级实现，能快速实现对数据库单表的 CRUD 操作
3. spring-data-jpa：其实就是集成了 Hibernate ，基于 JPA 实现的 ORM 框架

### mybatis-demo
mybatis 简单使用示例，实际开发中对应表的 mapper 文件，Mapper 接口，持久化对象都可以使用 mybatis-generator 生成。

### mybatis-plus-demo
mybatis-plus 简单使用示例，包括简单查询，分页查询，mybatis-plus 提供了单表基础操作模板，可以为开发省去大量代码，如果后续有更深度的使用，会在此项目更新一些更高级的用法

### security-basic-demo
spring security 使用示例，默认使用 Basic 认证，此项目也是使用 Basic 认证的示例，由于 Basic 认证用户名跟密码都是明文传输，所以并不安全，因此不建议在实际 web 项目中使用。

### kafka-demo
使用 Spring 提供的 kafka API 实现的一个 kafka 消息生产与消费的简单 demo，后续若有深度使用，会更新此项目。