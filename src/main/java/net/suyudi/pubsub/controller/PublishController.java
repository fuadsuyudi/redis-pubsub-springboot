package net.suyudi.pubsub.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.suyudi.pubsub.queue.RedisMessagePubSub;


/**
 * PublishController
 */
@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private RedisMessagePubSub redisMessagePubSub;

    @GetMapping("/message")
    public String getMethodName(@RequestBody String message) {

        String full_message = "Message " + UUID.randomUUID() + " " + message;
        redisMessagePubSub.publish(full_message);

        return message;
    }
    
}