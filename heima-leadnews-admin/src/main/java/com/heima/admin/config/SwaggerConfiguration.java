package com.heima.admin.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//这里需要访问localhost:9001/swagger-ui.xml
@EnableSwagger2
//github的集成包,集成了swagger,还可以在线联调,生成接口文档使用地址是:问localhost:9001/doc.html
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
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
