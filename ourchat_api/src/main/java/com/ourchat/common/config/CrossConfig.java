package com.ourchat.common.config;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import javax.annotation.Resource;
@Configuration
public class CrossConfig implements WebMvcConfigurer {
    @Resource
    private CorsInterceptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 跨域拦截器需放在最上面
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
        // 校验token的拦截器
    }

//    @Bean
//    public Docket swaggerSpringMvcPlugin() {
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                .enable(true)//enable表示是否开启Swagger
//                .select()
//                //RequestHandlerSelectors指定扫描的包
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.any())
//                .build();
//    }
//    //配置API的基本信息（会在http://项目实际地址/swagger-ui.html页面显示）
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("OurChat测试API")
//                .description("我们一起聊")
//                .termsOfServiceUrl("http://localhost:8087/swagger-ui/index.html")
//                .version("1.0")
//                .build();
//    }
}

