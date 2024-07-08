package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.smallrye.reactive.messaging.annotations.Channel;


// @Path("/messages")
// public class KafkaResource {

//     @Inject
//     KafkaConsumer kafkaConsumer;

//     @GET
//     @Produces(MediaType.TEXT_HTML)
//     public String getLastMessage() {
//         String message = kafkaConsumer.getLastMessage();
//         return message != null ? "<html><body><h1>Última Mensagem: " + message + "</h1></body></html>"
//                 : "<html><body><h1>Nenhuma mensagem recebida</h1></body></html>";
//     }
// }

@Path("/kafka")
public class KafkaResource {

    @Inject
    @Channel("kafka-stream")
    Multi<String> kafkaStream;

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<String> stream() {
        return kafkaStream.onItem().transform(item -> "data: " + item + "\n\n");
    }
}
