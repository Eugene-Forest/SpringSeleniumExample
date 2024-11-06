#  日志

Spring Boot包含许多Logback扩展，可以帮助进行高级配置。可以在logback-spring.xml配置文件中使用这些扩展。如果需要比较复杂的配置，建议使用扩展配置的方式

如果需要比较复杂的配置，建议使用扩展配置的方式，SpringBoot推荐我们使用带-spring后缀的 logback-spring.xml 扩展配置，因为默认的的logback.xml标准配置，Spring无法完全控制日志初始化。（spring扩展对springProfile节点的支持）


以下是项目常见的完整logback-spring.xml，SpringBoot默认扫描classpath下面的logback.xml、logback-spring.xml，所以不需要再指定spring.logging.config，当然，你指定也没有问题

```yaml
logging:
  config: classpath:logback-spring.xml
```