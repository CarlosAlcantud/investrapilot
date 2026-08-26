package org.alcantud.springcloud.msvc.gateway;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.web.servlet.function.RequestPredicates.path;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> usuariosRoute() {
        return GatewayRouterFunctions.route("msvc-usuarios")
                .route(path("/api/users/**"), HandlerFunctions.http())
                .filter(FilterFunctions.stripPrefix(2))
                .filter(FilterFunctions.uri("http://localhost:8001"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> budgetRoute() {
        return GatewayRouterFunctions.route("msvc-budgetservice")
                .route(path("/api/budgets/**"), HandlerFunctions.http())
                .filter(FilterFunctions.stripPrefix(2))
                .filter(FilterFunctions.uri("http://localhost:8002"))
                .build();
    }
}
