package com.heima.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
/**
 * swagger的配置类,可以拿出配置中的数据信息,省去了使用xml注解
 */
public class SwaggerConfiguration {
    //创建docket
    @Bean
    public Docket buildDocket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo()).
                select()
                // 要扫描的API(Controller)基础包
                .apis(RequestHandlerSelectors.basePackage("com.heima"))
                .paths(PathSelectors.any())
                .build();
    }

    //创建接口文档的方法
    private ApiInfo buildApiInfo() {
        Contact contact = new Contact("乐天看点", "", "");
        return new ApiInfoBuilder()
                .title("乐天看点-平台管理API文档")
                .description("平台管理服务api")
                .contact(contact)
                .version("1.0.0")
                .build();
    }
}
