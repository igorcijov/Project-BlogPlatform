package blog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Java Pro. BlogPlatform
 *
 * @author Igor Cijov
 * @version 11 Jan 2023
 * Data Validation Spring Boot + Rest Api + Thymeleaf
 *
 */

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Article API", version = "1.0", description = "Article Information"))
public class SimpleWebApp {

	public static void main(String[] args) {
		SpringApplication.run(SimpleWebApp.class, args);
	}
}
