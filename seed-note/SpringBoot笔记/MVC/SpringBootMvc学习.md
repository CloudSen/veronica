## Spring Boot的MVC自动配置
> 此部分内容参见：  
> MVC配置类: seed-config/src/main/java/com/umbrella/config/MvcConfig.java  
> SpringBoot配置文件: seed-config/src/main/resources/application.properties  
> 网站图标: seed-config/src/main/resources/favicon.ico

- 包含ContentNegotiatingViewResolver 和 BeanNameViewResolver Beans
- 支持静态资源，WebJars等
- 自动注册Converter, GenericConverter, 和 Formatter beans
- 支持HttpMessageConverters
- 自动注册MessageCodesResolver
- 支持静态主页 index.html
- 支持自定义的Favicon图标
- 自动使用ConfigurableWebBindingInitializer Bean

配置mvc有两种方法
1. 如果想保留MVC的一些默认设置的同时加入自己的配置，
那么就新建一个类并实现`WebMvcConfigurer`接口，
且只使用`@Configuration`注解,不使用`@EnableWebMvc`注解;
2. 如果想完全控制Spring MVC，则需要同时使用`@Configuration`和`@EnableWebMvc`注解。

> 如果想自定义RequestMappingHandlerMapping, 
> RequestMappingHandlerAdapter, 或 ExceptionHandlerExceptionResolver则需要新建一个
> 实现WebMvcRegistrationsAdapter接口的类来提供这些组件。

## HttpMessageConverter接口
> 用于转换Http Request和Responses，如，通过jackson自动将对象转换为Json或XML，字符串默认编码为UTF-8。

如果想自定义HttpMessage转换器，在配置类里写一个bean并返回`HttpMessageConverters`类:
```java
@Configuration
public class MyMvcConfiguration implements WebMvcConfigurer {
    //...
    @Bean
    public HttpserverMessages customHttpMessageConverters() {
        //HttpMessageConverter<?> additional = ...
        //HttpMessageConverter<?> another = ...
        return new HttpMessageConverters(additional, another);
    }
}
```

## 自定义JSON序列化/反序列化
> jackson使用`JsonSerializer`和`JsonDeserializer`抽象类来实现json数据的序列化/反序列化

需要新建一个继承`JsonSerializer`或`JsonDeserializer`抽象类的新类，并使用`@JsonComponent`注解
```java
@JsonComponent
public class CustomerJson {
    public static class Serializer extends JsonSerializer<SomeObject>{
        //...
    }
    public static class DeSerializer extends JsonDeserializer<SomeObject>{
        //...
    }
}
```
> @JsonComponent注解能被自动扫描，因为它是和@Component一样的元注解
SpringBoot还提供了`JsonObjectSerializer`和`JsonObjectDeserializer`基类来替代jackson标准。

## 默认识别的静态路径
> 通过ResourceHttpRequestHandler读取静态资源

Spring Boot默认从classpath里读取以下几个路径中的静态资源，这些路径都默认映射在`/**`:
- /static
- /resources
- /public
- /META-INF/resources

优先级：/META-INF/resources > /resources > /static > /public

若想自定义静态资源路径，则有以下几种方法：
1. 在自己的WebMvcConfigurer配置类中，重写`addResourceHandlers()`方法

    先添加的优先级高于后添加的，如果与默认的`/**`重名，则默认配置被覆盖。
    ```text
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 注册一个自定义的映射 /myPath/**  去加载/test 和 /test2路径, 
        registry.addResourceHandler("/myPath/**").addResourceLocations("classpath:/test", "classpath:/test2");
    }
    ```
2. 直接更改properties配置文件

    使用`spring.mvc.static-path-pattern`来设置默认Handler:
    ```text
    ## 将默认的/** 改为自定义的/myPath/**
    spring.mvc.static-path-pattern = /myPath/**
    ```
    使用`spring.resources.static-locations`来设置默认路径
    ```text
    spring.resources.static-locations = classpath:/test, classpath:/test2
    ```
## 模板引擎
> Spring MVC支持多种模板引擎用于生成动态html内容  

Spring Boot支持以下模板的自动化配置：
- FreeMarker
- Groovy
- Thymeleaf
- Mustache  

Spring Boot默认自动到以下路径寻找模板：`src/main/resources/templates`