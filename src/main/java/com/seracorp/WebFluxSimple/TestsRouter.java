package com.seracorp.WebFluxSimple;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TestsRouter {
	
	@Autowired
	private TestsHandler testsHandler;
	
	@Bean
	public RouterFunction<ServerResponse> route(){
		 return RouterFunctions.route(GET("/people/{id}").and(accept(APPLICATION_JSON)), testsHandler::get)
				 .andRoute(POST("/people/{id}").and(accept(APPLICATION_JSON)), testsHandler::post)
				 ;
	}

}
