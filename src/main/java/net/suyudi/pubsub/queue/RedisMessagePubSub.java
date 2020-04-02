package net.suyudi.pubsub.queue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;

/**
 * RedisMessagePubSub
 */
@Service
public class RedisMessagePubSub implements MessagePublisher, MessageListener {

    // Redis pubsub Publiser

    private String topicPublish = "uma";
    private String topicSubcribe = "uma";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void publish(String message) {
		redisTemplate.convertAndSend(topicPublish, message);
    }
    
    // Redis pubsub Subcriber

    public static List<String> messageList = new ArrayList<String>();

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    MessageListenerAdapter messageListener() { 
        return new MessageListenerAdapter(new RedisMessagePubSub());
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer(); 
        container.setConnectionFactory(jedisConnectionFactory); 
        container.addMessageListener(messageListener(), new ChannelTopic(topicSubcribe)); 
        return container; 
    }

	@Override
	public void onMessage(Message message, byte[] pattern) {
		messageList.add(message.toString());
        System.out.println("Message received: " + new String(message.getBody()));
	}
    
}