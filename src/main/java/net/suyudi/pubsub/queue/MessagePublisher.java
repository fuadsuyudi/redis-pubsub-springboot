package net.suyudi.pubsub.queue;

/**
 * MessagePublisher
 */
public interface MessagePublisher {

    void publish(final String message);
}