package com.heima.common.knife4j;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
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
public class Swagger2Configuration {
    //创建docket
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("1.0")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.heima"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
    //创建接口文档的方法
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("乐天看点API文档")
                .description("乐天看点API文档")
                .version("1.0")
                .build();
    }
}