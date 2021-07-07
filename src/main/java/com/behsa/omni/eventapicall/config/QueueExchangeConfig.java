package com.behsa.omni.eventapicall.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueExchangeConfig {

    @Value("${queue-name}")
    private String qName;
    @Value("${exchange-name}")
    private String xName;
    @Value("${queue-durable}")
    boolean qDurable;
    @Value("${exchange-durable}")
    boolean xDurable;
    @Value("${exchange-autoDelete}")
    boolean xAutoDel;
    @Value("${routingKey}")
    private String routingKey;

    @Bean
    Queue queue() {
        return new Queue(qName, qDurable);
    }

    @Bean
    TopicExchange getTopicExchange() {
        return new TopicExchange(xName, xDurable, xAutoDel);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    MessageListenerAdapter getMessageListenerAdapter() {
        return new MessageListenerAdapter();
    }

}
