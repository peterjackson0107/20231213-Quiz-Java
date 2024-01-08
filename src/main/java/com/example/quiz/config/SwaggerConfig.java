//package com.example.quiz.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration // �� spring �f�� class �������
//@EnableOpenApi // ͨ�^���]ጁ톢�� swagger
//@EnableWebMvc // ���� DelegatingWebMvcConfiguration ������K���� spring MVC
//public class SwaggerConfig {
//
//	// �ӿ�ӍϢ������ ���}���f�����汾���j�˵�
//	private ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder() //
//			.title("Quiz Restful API") // ���}
//			.description("Quiz API") // �f��
////   .termsOfServiceUrl("urn:tos") // ���՗l��Wַ��Ĭ�J urn:tos
////   .contact(new Contact("DEFAULT", "", "")) // �j��
////   .license("Apache 2.0") //
////   .version("v3") // �汾
////   .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.txt") //
//			.build();
//
//	@Bean
//	public Docket api() {
//		// �����ęn��ҪӍϢ
//		return new Docket(DocumentationType.SWAGGER_2) //
//				.apiInfo(DEFAULT_API_INFO)//
//				.select()//
//				.apis(RequestHandlerSelectors.basePackage("com.example.quiz.controller"))//
//				.paths(PathSelectors.any())//
//				.build();
//	}
//}