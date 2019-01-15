package com.lynn.blog.common.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    private final AmqpAdmin amqpAdmin;
    private final AmqpTemplate amqpTemplate;

    @Autowired
    public MyBean(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
        this.amqpAdmin = amqpAdmin;
        this.amqpTemplate = amqpTemplate;
    }

    @RabbitHandler
    @RabbitListener(queues = "someQueue")
    public void processMessage(String content) {
        // ...
        System.out.println(content);
    }

    public void send(String content){
        amqpAdmin.declareQueue(new Queue("someQueue"));
        amqpTemplate.convertSendAndReceive(content);
    }
}
