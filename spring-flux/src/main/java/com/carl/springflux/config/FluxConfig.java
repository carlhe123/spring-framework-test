package com.carl.springflux.config;

import com.carl.springflux.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @Description Flux route Config
 * @Author carl.he
 * @Date 2019/5/27 9:51
 * @Version 1.0
 **/
@Configuration
public class FluxConfig {

    @Autowired
    private UserHandler handler;

    @Bean
    public RouterFunction<ServerResponse> userRoute(){
           return route().GET("/user/list", accept(APPLICATION_JSON), handler::findAll)
            .POST("/user/save", handler::saveUser)
            .build();
    }
}
