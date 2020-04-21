package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String PACKAGE_SCAN = "com.example.demo";

    @Bean
    public Docket restfulApi() {
        ParameterBuilder token = new ParameterBuilder();
        token.name("ACCESS_TOKEN").description("请求凭证")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .required(false).build();
        List<Parameter> pars = new ArrayList<>();
        pars.add(token.build());
        // pars.add(openid.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(true)
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage(PACKAGE_SCAN))
                .build()
                .globalOperationParameters(pars)
                .apiInfo(intoApiInfo());
    }

    private ApiInfo intoApiInfo() {
        String versionStr = "unknown";
        try {
            URL resource = getClass().getClassLoader()
                    .getResource("META-INF/build-info.properties");
            Properties properties = new Properties();
            properties.load(resource.openStream());
            versionStr = properties.getProperty("build.version");
        } catch (Exception ex) {
            log.warn("无法读取jar包版本信息：{}", ex.getMessage());
        }

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("demo")
                .description("接口说明文档")
                .version(versionStr)
                .build();
        return apiInfo;
    }
}
