package ru.cinemaabyss.events.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic movieEventsTopic() {
        return new NewTopic("movie-events", 1, (short) 1);
    }

    @Bean
    public NewTopic userEventsTopic() {
        return new NewTopic("user-events", 1, (short) 1);
    }

    @Bean
    public NewTopic paymentEventsTopic() {
        return new NewTopic("payment-events", 1, (short) 1);
    }
}