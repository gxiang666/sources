# springcloud-atguigu
尚硅谷SC的第一部分视频教程代码

## 包括的内容
  1.  ==eureka的搭建和集群的搭建，如果要用域名的话需要在hosts中修改对应的127.0.0.1==
     
      
    ```
    eureka: 
      instance:
        hostname: eureka7001.com #eureka服务端的实例名称
      client: 
        register-with-eureka: false     #false表示不向注册中心注册自己。
        fetch-registry: false     #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
        service-url: 
          #单机 defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/       #设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址（单机）。
          defaultZone: http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
    ```

     
  2. ==provider的提供，注意进行注册到eureka的服务列表==
     
    ```
         @EnableEurekaClient //本服务启动后会自动注册进eureka服务中
         @EnableDiscoveryClient //服务发现
    ```

    
    ```
    eureka:
              client: #客户端注册进eureka服务列表内
                service-url: 
                  #defaultZone: http://localhost:7001/eureka
                   defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/      
              instance:
                instance-id: microservicecloud-dept8001
                prefer-ip-address: true     #访问路径可以显示IP地址
    ```
  3. ==客户端==
  
    ```
    eureka:
      client:
        register-with-eureka: false
        service-url: 
          defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
    ```
   4. ==Ribbon== 
       
```
      @EnableEurekaClient
        //在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效
        //@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
        @RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
```

   

---
     
       
```
        //RestTemplate 的调用
        //private static final String REST_URL_PREFIX = "http://localhost:8001";
    	private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";
    
    	/**
    	 * 使用 使用restTemplate访问restful接口非常的简单粗暴无脑。 (url, requestMap,
    	 * ResponseBean.class)这三个参数分别代表 REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。
    	 */
    	@Autowired
    	private RestTemplate restTemplate;
    
    	@RequestMapping(value = "/consumer/dept/add")
    	public boolean add(Dept dept)
    	{
    		return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    	}
```

---

```
        @Configuration
        public class ConfigBean //boot -->spring   applicationContext.xml --- @Configuration配置   ConfigBean = applicationContext.xml
        { 
        	@Bean
        	@LoadBalanced//Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端       负载均衡的工具。
        	public RestTemplate getRestTemplate()
        	{
        		return new RestTemplate();
        	}
        	
        	@Bean
        	public IRule myRule()
        	{
        		//return new RoundRobinRule();
        		//return new RandomRule();//达到的目的，用我们重新选择的随机算法替代默认的轮询。
        		return new RetryRule();
        	}
        }
```
    
   
5. ==Feign(定义一个接口，标注一个注解@FeignClient,直接可以进行@Autowired)==
    
    
```
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages= {"com.atguigu.springcloud"})
@ComponentScan("com.atguigu.springcloud")
public class DeptConsumer80_Feign_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(DeptConsumer80_Feign_App.class, args);
	}
}
```

---

```
/**
 * 
 * @Description: 修改microservicecloud-api工程，根据已经有的DeptClientService接口

新建

一个实现了FallbackFactory接口的类DeptClientServiceFallbackFactory
 * @author zzyy
 * @date 2018年4月21日
 */
 @FeignClient(value = "MICROSERVICECLOUD-DEPT")   
 
 @FeignClient(value = "MICROSERVICECLOUD-DEPT",fallbackFactory=DeptClientServiceFallbackFactory.class)
public interface DeptClientService
{
	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") long id);

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list();

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(Dept dept);
}
```

---

```

@RestController
public class DeptController_Consumer
{
	@Autowired
	private DeptClientService service;

	@RequestMapping(value = "/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id)
	{
		return this.service.get(id);
	}

	@RequestMapping(value = "/consumer/dept/list")
	public List<Dept> list()
	{
		return this.service.list();
	}

	@RequestMapping(value = "/consumer/dept/add")
	public Object add(Dept dept)
	{
		return this.service.add(dept);
	}
}
```

---

```
@Component // 不要忘记添加，不要忘记添加.这是带有fallbackFactory进行降级的类
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService>
{
	@Override
	public DeptClientService create(Throwable throwable)
	{
		return new DeptClientService() {
			@Override
			public Dept get(long id)
			{
				return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭")
						.setDb_source("no this database in MySQL");
			}

			@Override
			public List<Dept> list()
			{
				return null;
			}

			@Override
			public boolean add(Dept dept)
			{
				return false;
			}
		};
	}
}
```

     
