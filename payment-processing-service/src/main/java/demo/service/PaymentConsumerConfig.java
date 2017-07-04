package demo.service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConsumerConfig {
    public static final String REQUEST_QUEUE_NAME = "foodDelivery.payment.request";

    @Bean
    Queue requestQueue() {
        return QueueBuilder.durable(REQUEST_QUEUE_NAME ).build();
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}