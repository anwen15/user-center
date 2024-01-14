package com.example.usercenter.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


@Configuration
@EnableSwagger2WebMvc
@Profile("dev")
public class swaggerconfig {
    /**
     * 创建swagger接口bean实体
     * @return
     */
    @Bean(value="docket")
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.usercenter.controller"))
                .paths(PathSelectors.any())//限制接口路径
                .build();
    }

    /**
     * 定义api信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("用户中心")
                .description("测试")
                .termsOfServiceUrl("https://github.com/anwen15")
                .contact(new Contact("anwen","", ""))//信息
                .version("1.0")
                .build();
    }
    /**
     * 如果哦有拦截器需要更改代码不然冲突
     */
}
