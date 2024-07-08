package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/messages")
public class KafkaResource {

    @Inject
    KafkaConsumer kafkaConsumer;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getLastMessage() {
        String message = kafkaConsumer.getLastMessage();
        return message != null ? "<html><body><h1>Última Mensagem: " + message + "</h1></body></html>"
                : "<html><body><h1>Nenhuma mensagem recebida</h1></body></html>";
    }
}
