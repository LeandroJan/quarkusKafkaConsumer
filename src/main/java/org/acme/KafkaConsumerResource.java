package org.acme;
import io.smallrye.mutiny.operators.multi.processors.BroadcastProcessor;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/kafka")
public class KafkaConsumerResource {

    private final BroadcastProcessor<String> processor = BroadcastProcessor.create();

    @Incoming("kafka-messages")
    public void consume(String message) {
        System.out.println("Received: " + message);
        processor.onNext(message);
    }

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public BroadcastProcessor<String> stream() {
        return processor;
    }
}