package org.acme;


// import io.smallrye.mutiny.Multi;
// import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
// import org.eclipse.microprofile.reactive.messaging.Emitter;
import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.inject.Inject;
// import jakarta.ws.rs.GET;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.Produces;
// import jakarta.ws.rs.core.MediaType;
import io.smallrye.reactive.messaging.annotations.Blocking;
// import org.eclipse.microprofile.reactive.messaging.Incoming;

// import jakarta.enterprise.context.ApplicationScoped;
import java.util.concurrent.ConcurrentLinkedQueue;


@ApplicationScoped
public class KafkaConsumer {

    private ConcurrentLinkedQueue<String> messages = new ConcurrentLinkedQueue<>();

    @Incoming("my-topic")
    @Blocking
    public void consume(String message) {
        messages.add(message);
    }

    public String getLastMessage() {
        return messages.poll();
    }
}