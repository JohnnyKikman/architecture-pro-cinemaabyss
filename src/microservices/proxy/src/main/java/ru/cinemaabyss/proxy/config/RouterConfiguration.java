package ru.cinemaabyss.proxy.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RouterConfiguration {

    @Bean
    public RouteLocator routeLocator(
            RouteLocatorBuilder builder,
            Environment environment
    ) {
        boolean enableGradualMigration = environment.getProperty("GRADUAL_MIGRATION", Boolean.class);
        int migrationPercent = environment.getProperty("MOVIES_MIGRATION_PERCENT", Integer.class);
        String monolithUrl = environment.getProperty("MONOLITH_URL");
        String moviesUrl = environment.getProperty("MOVIES_SERVICE_URL");
        log.info("GRADUAL_MIGRATION = {}", enableGradualMigration);
        log.info("MOVIES_MIGRATION_PERCENT = {}", migrationPercent);
        if (enableGradualMigration) {
            return builder.routes()
                    .route("moviesMonolith", p ->
                            p.path( "/api/movies/**")
                                    .and()
                                    .weight("moviesGroup", 100 - migrationPercent)
                                    .uri(monolithUrl))
                    .route("moviesMicroservice", p ->
                            p.path("/api/movies/**")
                                    .and()
                                    .weight("moviesGroup", migrationPercent)
                                    .uri(moviesUrl))
                    .route("users", p ->
                            p.path("/api/users/**")
                                    .uri(monolithUrl))
                    .route("health", p ->
                            p.path("/health")
                                    .uri(monolithUrl))
                    .build();
        } else {
            return builder.routes().build();
        }
    }

}
