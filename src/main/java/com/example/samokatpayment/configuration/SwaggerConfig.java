package com.example.samokatpayment.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .servers(
                        List.of(
                                new Server().url("http://localhost:8083")
                        )
                )
                .info(
                        new Info()
                                .title("Сервис оплаты")
                                .description("Данный сервис предназначен для оплаты заказов. Он принимает все данные, " +
                                        "необходимые для оплаты заказа и URL запроса, по которому необходимо отправить " +
                                        "информацию об успешности оплаты")
                );
    }
}
