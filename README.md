# Spring的学习笔记  
## 目录  
[toc] 后续生成目录  
### 1、基于XML的IOC控制反转    
+ **IOC控制反转**  
将类的对象的创建交给Spring容器管理创建，以此来降低耦合 
  - 入门使用  
    1、配置pom.xml  
    ```
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.0.2.RELEASE</version>
        </dependency>
    </dependencies>
    ```  
    2、配置bean.xml  
    ```
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">
      
        <bean id="accountService" class="com.spring.service.impl.AccountServiceImpl"></bean>
        <bean id="accountDao" class="com.spring.dao.impl.AccountDaoImpl"></bean>
    </beans>
    ```  
    3、创建对象并且使用  
    ```
    // 1、获取核心容器对象
    ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
    // 2、根据ID获取bean对象
    AccountService as = (AccountService)ac.getBean("accountService");
    // 3、使用对象  
    as.saveAccount();
    ```  
  - ApplicationContext的三个常用实现类  
    + ClassPathXmlApplicationContext: 它可以加载类路径下的配置文件，要求配置文件必须在类路径下,不在，无法使用（常用方式）  
    + FileSystemXmlApplicationContext: 它可以加载磁盘任意路径下的配置文件（必须有访问权限）  
    + AnnotationConfigApplicationContext: 它是用于读取注解创建容器的，是后续内容   
  - 核心容器两个接口ApplicationContext和BeanFactory的异同  
    + ApplicationContext: 单例对象适用 采用此接口 它在构建核心容器时，创建对象采取的策略是采用立即加载的方式。也就是说，只要一读取完配置文件马上就创建配置文件中配置的对象    
    + BeanFactory: 多例对象使用 它在构建核心容器时，创建对象采取的策略是采用延迟加载的方式。也就是说，什么时候根据id获取对象了，什么时候才真正的创建对象    
    + BeanFactory适用于多例，而ApplicationContext会自动判断单例还是多例，实际开发多采用ApplicationContext   
  - 三种创建Bean对象的方式  
    + 第一种: 使用默认构造函数  
    ``` 
        <!-- 创建bean的三种方式 -->
        <!-- 第一种方式: 使用默认构造函数创建。
            在spring的配置文件中使用bean标签，配以id和class属性之后，且没有其他属性和标签时。
            采用的就是默认构造函数创建bean对象，此时如果类中没有默认构造函数，则对象无法创建。
        -->
        <bean id="accountService" class="com.spring.service.impl.AccountServiceImpl"></bean>
    ```
    + 第二种: 使用普通工厂的方法  
    ```
        <!-- 第二种方式: 使用普通工厂中的方法创建对象(使用某个类中的方法)，并存入spring容器 -->
        <bean id="instanceFactory" class="com.spring.factory.InstanceFactory"></bean>
        <bean id="accountService" factory-bean="instanceFactory" factory-method="getAccountService"></bean>
    ```
    + 第三种: 使用工厂中的静态方法 
    ```
        <!-- 第三种方式，使用工厂中的静态方法创建对象（使用某个类中的静态方法来创建对象），并存入spring容器 -->
        <bean id="accountService" class="com.spring.factory.StaticFactory" factory-method="getAccountService"></bean>
    ```
  - bean的作用范围调整  
  ```
      <!-- bean的作用范围调整
           bean标签的scope属性用于指定bean的作用范围
              取值:
                  singleton: 单例的（默认值）
                  prototype: 多例的
                  request: 作用于web应用的请求范围
                  session: 作用于web应用的会话范围
                  gloab-session: 作用于集群环境的会话范围（全局会话范围），当不是集群环境时，相当于session
      -->
      <bean id="accountService" class="com.spring.service.impl.AccountServiceImpl" scope="prototype"></bean>
  ```
  - bean对象的生命周期  
  ```
      <!-- bean对象的生命周期
              单例对象:
                  出生: 当容器创建时对象出生
                  活着: 只要容器还在，对象就活着
                  死亡: 容器销毁，对象消亡
                  总结: 单例对象的生命周期和容器相同
              多例对象:
                  出生: 当使用对象时，Spring框架为我们创建
                  活着: 对象只要是在使用过程中就一直活着
                  死亡: 当对象长时间不用，且没有别的对象引用时，由Java的垃圾回收器回收
      -->
      <bean id="accountService" class="com.spring.service.impl.AccountServiceImpl"
            scope="prototype" init-method="init" destroy-method="destroy"></bean>
  ```
  
     
      
    
 