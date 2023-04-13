package org.acme.hiber.routes;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.acme.hiber.controller.MessageController;
import org.acme.hiber.controller.dto.MessageDTO;
import org.acme.hiber.model.Message;

@Path("channels/{id}/messages")
public class MessageRouter {
    @Inject
    MessageController messageController;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Message> getMessages(@PathParam("id") Long id) {
        return messageController.getChannelMessages(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Message sendMessage(@PathParam("id") Long id, MessageDTO messageDTO) {
        return messageController.saveMessage(id, messageDTO);
    }
}
