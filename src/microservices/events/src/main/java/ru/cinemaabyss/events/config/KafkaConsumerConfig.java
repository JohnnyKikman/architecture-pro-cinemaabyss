package ru.cinemaabyss.events.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.cinemaabyss.events.dto.MovieEvent;
import ru.cinemaabyss.events.dto.UserEvent;
import ru.cinemaabyss.events.dto.PaymentEvent;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableConfigurationProperties
public class KafkaConsumerConfig {

    @Bean("kafkaBaseProps")
    public Map<String, Object> baseProps(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${spring.kafka.consumer.group-id}") String consumerGroupId,
            @Value("${spring.kafka.consumer.auto-offset-reset}") String consumerAutoOffsetReset
    ) {
        Map<String, Object> props = new HashMap<>();
        log.debug("Config values: bootstrapServers={}, consumerGroupId={}, consumerAutoOffsetReset={}", bootstrapServers, consumerGroupId, consumerAutoOffsetReset);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerAutoOffsetReset);
        return props;
    }

    @Bean("movieConsumerFactory")
    public ConsumerFactory<String, MovieEvent> movieConsumerFactory(
            @Qualifier("kafkaBaseProps") Map<String, Object> baseProps
    ) {
        JsonDeserializer<MovieEvent> deserializer = new JsonDeserializer<>(MovieEvent.class);
        deserializer.addTrustedPackages("ru.cinemaabyss.events.dto");
        return new DefaultKafkaConsumerFactory<>(baseProps, new StringDeserializer(), deserializer);
    }

    @Bean("userConsumerFactory")
    public ConsumerFactory<String, UserEvent> userConsumerFactory(
            @Qualifier("kafkaBaseProps") Map<String, Object> baseProps
    ) {
        JsonDeserializer<UserEvent> deserializer = new JsonDeserializer<>(UserEvent.class);
        deserializer.addTrustedPackages("ru.cinemaabyss.events.dto");
        return new DefaultKafkaConsumerFactory<>(baseProps, new StringDeserializer(), deserializer);
    }

    @Bean("paymentConsumerFactory")
    public ConsumerFactory<String, PaymentEvent> paymentConsumerFactory(
            @Qualifier("kafkaBaseProps") Map<String, Object> baseProps
    ) {
        JsonDeserializer<PaymentEvent> deserializer = new JsonDeserializer<>(PaymentEvent.class);
        deserializer.addTrustedPackages("ru.cinemaabyss.events.dto");
        return new DefaultKafkaConsumerFactory<>(baseProps, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MovieEvent> movieKafkaListenerContainerFactory(
            @Qualifier("movieConsumerFactory") ConsumerFactory<String, MovieEvent> movieConsumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, MovieEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(movieConsumerFactory);
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserEvent> userKafkaListenerContainerFactory(
            @Qualifier("userConsumerFactory") ConsumerFactory<String, UserEvent> userConsumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, UserEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactory);
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentEvent> paymentKafkaListenerContainerFactory(
            @Qualifier("paymentConsumerFactory") ConsumerFactory<String, PaymentEvent> paymentConsumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, PaymentEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(paymentConsumerFactory);
        return factory;
    }
}
