package net.suyudi.pubsub.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.suyudi.pubsub.queue.RedisMessagePublisher;


/**
 * PublishController
 */
@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private RedisMessagePublisher redisMessagePublisher;

    @GetMapping("/message")
    public String getMethodName(@RequestBody String message) {

        String full_message = "Message " + UUID.randomUUID() + " " + message;
        redisMessagePublisher.publish(full_message);

        return message;
    }
    
}