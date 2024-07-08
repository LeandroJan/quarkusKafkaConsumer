package org.acme;


import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@ApplicationScoped
public class KafkaConsumer {

    private final BlockingQueue<String> messages = new LinkedBlockingQueue<>();

    @Incoming("my-topic")
    @Blocking
    public void consume(String message) {
        messages.add(message);
    }

    public String getLastMessage() {
        return messages.poll();
    }
}
