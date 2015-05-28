package springfox.springconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2   
@EnableAutoConfiguration
@ComponentScan("stats")    
public class Swagger2SpringBoot {

  public static void main(String[] args) {
    @SuppressWarnings("unused")
	ApplicationContext ctx = SpringApplication.run(Swagger2SpringBoot.class, args);
  }

  @Bean
  public Docket webCallApi() {
    return new Docket(DocumentationType.SWAGGER_2)    
            .select()   
            .build();   
  }
}