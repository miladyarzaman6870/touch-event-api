package com.behsa.omni.eventapicall.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueExchangeConfig {

    @Value("${queueDurable}")
    boolean queueDurable;
    @Value("${exchangeDurable}")
    boolean exchangeDurable;
    @Value("${exchangeAutoDelete}")
    boolean exchangeAutoDelete;

    @Value("${exchangeName1}")
    private String exchangeName1;
    @Value("${routingKey1}")
    private String routingKey1;
    @Value("${queueName1}")
    private String queueName1;

    @Value("${exchangeName3}")
    private String exchangeName3;
    @Value("${routingKey3}")
    private String routingKey3;
    @Value("${queueName3}")
    private String queueName3;

    @Value("${exchangeName2}")
    private String exchangeName2;
    @Value("${routingKey2}")
    private String routingKey2;
    @Value("${queueName2}")
    private String queueName2;



    @Bean
    Queue queue1() {
        return new Queue(queueName1, queueDurable);
    }

    @Bean
    TopicExchange getTopicExchange1() {
        return new TopicExchange(exchangeName1, exchangeDurable, exchangeAutoDelete);
    }

    @Bean
    Binding binding1(Queue queue1, TopicExchange getTopicExchange1) {
        return BindingBuilder.bind(queue1).to(getTopicExchange1).with(routingKey1);
    }

    @Bean
    Queue queue2() {
        return new Queue(queueName2, queueDurable);
    }

    @Bean
    TopicExchange getTopicExchange2() {
        return new TopicExchange(exchangeName2, exchangeDurable, exchangeAutoDelete);
    }

    @Bean
    Binding binding2(Queue queue2, TopicExchange getTopicExchange2) {
        return BindingBuilder.bind(queue2).to(getTopicExchange2).with(routingKey2);
    }

    @Bean
    Queue queue3() {
        return new Queue(queueName3, queueDurable);
    }

    @Bean
    TopicExchange getTopicExchange3() {
        return new TopicExchange(exchangeName3, exchangeDurable, exchangeAutoDelete);
    }

    @Bean
    Binding binding3(Queue queue3, TopicExchange getTopicExchange3) {
        return BindingBuilder.bind(queue3).to(getTopicExchange3).with(routingKey3);
    }

    @Bean
    MessageListenerAdapter getMessageListenerAdapter() {
        return new MessageListenerAdapter();
    }

}
