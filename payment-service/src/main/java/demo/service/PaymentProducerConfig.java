
package demo.service;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentProducerConfig {

    public static final String PAYMENT_EXCHANGE_NAME = "foodDelivery.payments";
    public static final String REQUEST_QUEUE_NAME = "foodDelivery.payment.request";
    public static final String REPLY_QUEUE_NAME = "foodDelivery.payment.reply";
    public static final String ROUTING_KEY_NAME = "payment";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ConnectionFactory connectionFactory;


    @Bean
    DirectExchange exchange() {
        return new DirectExchange(PAYMENT_EXCHANGE_NAME);
    }

    @Bean
    Queue requestQueue() {
        return QueueBuilder.durable(REQUEST_QUEUE_NAME).build();
    }

    @Bean
    Queue replyQueue() {
        return QueueBuilder.durable(REPLY_QUEUE_NAME).build();
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(requestQueue()).to(exchange()).with(ROUTING_KEY_NAME);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    AsyncRabbitTemplate template() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(REPLY_QUEUE_NAME);
        container.setConcurrentConsumers(10);
        return new AsyncRabbitTemplate(rabbitTemplate, container);
    }
}
