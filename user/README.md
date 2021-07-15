# SpringBoot+SpringSecurity+MySql+MyBatis-Plus+Maven

    1、权限代码主要在user模块-》security包下
    2、核心类 com.glass.user.security.config.WebSecurityConfig
    
## 测试
    url:
        http://localhost:8088/login
    请求参数
    {
    	"username":"admin",
    	"password":"123456"
    }
    登陆成功返回的Headers-》User-Token保存好，后续所有的接口调用都要在请求头中加入User-Token 
   