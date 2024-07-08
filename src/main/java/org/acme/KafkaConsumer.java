package org.acme;


import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.annotations.Channel;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// @ApplicationScoped
// public class KafkaConsumer {

//     private final BlockingQueue<String> messages = new LinkedBlockingQueue<>();

//     @Incoming("my-topic")
//     @Blocking
//     public void consume(String message) {
//         messages.add(message);
//     }

//     public String getLastMessage() {
//         return messages.poll();
//     }
// }

public class KafkaConsumer {

    @Inject
    @Channel("kafka-stream")
    @Broadcast
    Emitter<String> emitter;

    @Incoming("kafka-messages")
    public void consume(String message) {
        System.out.println("Received: " + message);
        emitter.send(message);
    }
}