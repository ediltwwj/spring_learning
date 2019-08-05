# Spring的学习笔记  
## 目录  
[toc] 后续生成目录  
### 1、基于XML的IOC控制反转和DI依赖注入        
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
+ **DI依赖注入**  
  **概念: 在当前类需要用到其他类的对象，由spring为我们提供，我们只需要在配置文件中说明依赖关系的维护，就称之为依赖注入**
  - 能注入的数据有三类  
    1、基本类型和String  
    2、其他bean类型(在配置文件中或者注解配置过的bean)  
    3、复杂类型/集合类型  
  - 注入的方式有三种  
    1、使用构造函数注入  
    ```
        <!-- 构造函数注入  AccountServiceImpl.java
             使用的标签: constructor-arg
             标签出现的位置: bean标签内部
             标签中的属性:
                type: 用于指定要注入的数据的数据类型，该数据类型也是构造函数中某个或某些参数的类型,无法单独使用
                index: 用于指定要注入的数据给构造函数中指定索引位置的参数赋值,索引位置从0开始,要记索引对应的类型，不然容易错
                name: 用于指定给构造函数中指定名称的参数赋值               常用的
                ===========================以上三个用于指定给构造函数中哪个参数赋值==============================
                value: 用于提供基本类型和String类型的数据
                ref: 用于指定其他的bean类型数据，它指的就是在spring的IOC核心容器中出现过的bean对象
    
             优势:
                在获取bean对象时，注入数据是必须的操作，否则对象无法构建成功
             弊端:
                改变了bean对象的实例化方式，使我们再创建对象时，如果用不到这些数据也必须提供
        -->
        <bean id="accountService" class="com.spring.service.impl.AccountServiceImpl">
            <constructor-arg name="name" value="test"></constructor-arg>
            <constructor-arg name="age" value="18"></constructor-arg>
            <constructor-arg name="birthday" ref="now"></constructor-arg>
        </bean>
    
        <!-- 配置一个日期对象 -->
        <bean id="now" class="java.util.Date"></bean>
    ```  
    2、使用set方法注入  
    ```
        <!-- set方法注入(更常用)   AccountServiceImpl2.java
             涉及的标签: property
             出现的位置: bean标签的内部
             标签中的属性:
                name: 用于指定注入时所调用的set方法名称,如setName则参数就是name
                value: 用于提供基本类型和String类型的数据
                ref: 用于指定其他的bean类型数据，它指的就是在spring的IOC核心容器中出现过的bean对象
                
             优势:
                创建对象时没有明确的限制，可以直接使用默认构造函数
             弊端:
                如果某个成员必须有值，则获取对象时有可能set方法没有执行
        -->
        <bean id="accountService2" class="com.spring.service.impl.AccountServiceImpl2">
            <property name="name" value="Test"></property>
            <property name="age" value="20"></property>
            <property name="birthday" ref="now"></property>
        </bean>
    ```
    3、使用注解注入（后序章节介绍） 
  - 注入集合数据  
  ```
     <!-- 复杂类型的注入/集合类型的注入 
          用于给List结构集合注入的标签: list array set
          用于个Map结构集合注入的标签:map  props
          结构相同，标签可以互换,所以记List和Map就够了!
      -->
      <bean id="accountService3" class="com.spring.service.impl.AccountServiceImpl3">
          <property name="myStrs">
              <array>
                  <value>Str1</value>
                  <value>Str2</value>
                  <value>Str3</value>
              </array>
          </property>
          <property name="myList">
              <list>
                  <value>List1</value>
                  <value>List2</value>
              </list>
          </property>
          <property name="mySet">
              <set>
                  <value>Set1</value>
                  <value>Set2</value>
              </set>
          </property>
          <property name="myMap">
              <map>
                  <entry key="map1" value="value1"></entry>
                  <entry key="map2">
                      <value>value2</value>
                  </entry>
              </map>
          </property>
          <property name="myPros">
              <props>
                  <prop key="prop1">value1</prop>
                  <prop key="prop2">value2</prop>
              </props>
          </property>
      </bean>
  ```
  
### 2、基于注解的IOC控制反转和依赖注入(还有bean.xml存在)   
  **基于注解的XML配置**  
  ```
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/context
          http://www.springframework.org/schema/context/spring-context.xsd">
  
      <!-- 告知spring在创建容器时要扫描的包,配置所需要的标签不是在beans约束中，而是一个名称为
          context名称空间和约束中 -->
      <context:component-scan base-package="com.spring"></context:component-scan>
  
  </beans>
  ```
  **基于XML的XML配置**   
  ```
   <bean id="accountService" class="com.spring.service.impl.AccountServiceImpl"
        scope="" init-method="" destroy-method="">
        <property name="" value=""/ref=""></property>
   </bean>
  ```
  **用于创建对象的注解(他们的作用就和在XML配置文件中编写一个<bean>标签实现的功能是一样的)**  
  ```
      1、Component:  
           作用: 把当前类对象存入spring容器中  
           属性:  
               value: 用于指定bean的id，当不写时，它的默认值是当前类名且首字母改为小写  
      2、Controller: 一般使用在表现层  
      3、Service: 一般用在业务层  
      4、Repository: 一般用于持久层  
      以上三个注解他们的作用和属性和Component是一样的，他们三个是spring框架为我们提供明确的三层使用的注解，使我们三层对象更加清晰 
  ``` 
  **用于注入数据的注解(他们的作用就和在XML配置文件中的bean标签中写一个<property>标签的作用是一样的)**   
  ```
      1、Autowired:
           作用: 自动按照类型注入，只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功
                 如果IOC容器中没有任何bean类型和要注入的变量类型匹配，则报错
                 如果IOC容器中有多个类型匹配时: 先看变量类型，再看变量名称，如果有唯一一个变量名称一样则成功，否则报错
           出现位置: 可以是变量上，也可以是方法上
           细节: 在使用注解注入时，set方法就不是必须的
      2、Qualifier:
           作用: 在按照类注入的基础之上再按照名称注入，它在给类成员注入时，不能单独使用，但是给方法注入参数时可以（稍后讲）
           属性:
               value: 用于指定注入bean的id
      3、Resource:
           作用: 直接按照bean的id注入，它可以独立使用
           属性:
               name: 用于指定bean的id
      另外: 以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型都无法使用上诉注解实现
      4、Value:
           作用: 用于注入基本类型和String类型的数据
           属性:
               value: 用于指定数据的值，它可以使用spring中SpEl（也就是spring的el表达式）
                        SpEl的写法: ${表达式}
  ```
  **用于改变作用范围的注解(他们的作用就和在bean标签中使用scope属性实现的功能是一样的)**   
  ```
      1、Scope:
            作用: 用于指定bean的作用范围
            属性:
                value: 指定范围的取值，常用取值: singleton, prototype 默认单例
  ```  
  **和生命周期相关的注解（他们的作用就和在bean标签中使用Init-method和destroy-method的作用是一样的）**   
  ```
      1、PreDestroy:
            作用: 用于指定销毁方法
      2、PostConstruct:
            作用: 用于指定初始化方法
  ```  
  
### 3、Spring新注解(代替bean.xml) 
**chap_05**   
**主配置类SpringConfiguration.java**  
```
/**
 * @author 13967
 * @date 2019/8/5 13:04
 *
 * 该类是一个配置类，作用和bean.xml是一样的
 * spring中的新注解
 *      Configuration:
 *          作用: 指定当前类是一个配置类
 *          细节:
 *              当配置类作为AnnotationConfigApplicationContext对象创建的参数时，该注解可以不写
 *      ComponentScan:
 *          作用: 用于通过注解指定spring在创建容器时要扫描的包
 *          属性:
 *              value: 它和basePackages的作用是一样的，都是用于指定创建容器时要扫描的包
 *                     使用此注解相当于在xml中配置<context:component-scan base-package="com.spring"></context:component-scan>
 *      Bean:
 *          作用: 用于把当前方法的返回值作为bean对象存入spring的IOC容器
 *          属性:
 *              name: 用于指定bean的id，当不写时，默认值就是当前方法的名称
 *          细节:
 *              当我们使用注解配置方法时，如果方法有参数，spring框架会去容器中查找有没有可用的bean对象
 *              查找方式和Autowired注解的作用是一样的
 *      Import:
 *          作用: 用于导入其他的配置类
 *          属性:
 *              value: 用于指定其他配置类的字节码，使用Import注解之后，有Import注解的类就是主配置类，而导入的都是子配置类
 *      PropertySource:
 *          作用: 用于指定properties文件的位置
 *          属性:
 *              value: 指定文件的名称和路径， 关键字classpath表示类路径下
 *
 */
// @Configuration  // 可以省略
@ComponentScan("com.spring")  // 类路径，{} 可以用Import导入其他配置类代替@ComponentScan({"com.spring", "config"})
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties")  
public class SpringConfiguration {

}
```
**子配置类JdbcConfig.java**  
```
// 被主配置类使用ComponentScan，不能省略
// 省略的话当做AnnotationConfigApplicationContext对象创建的参数,或者被主配置类Import
// @Configuration
public class JdbcConfig {

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String pasword;

    /**
     * 用于创建一个QueryRunner对象
     * @param dataSource
     * @return
     */
    @Bean(name="runner")
    @Scope("prototype")  // 默认单例
    public QueryRunner createQueryRunner(DataSource dataSource){

        return new QueryRunner(dataSource);
    }

    /**
     * 创建数据源对象
     * @return
     */
    @Bean(name="dataSource")
    public DataSource createDataSource(){

        try{
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(pasword);

            return ds;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
```  

    

            
    
 