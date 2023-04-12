package org.acme.hiber.routes;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.hiber.controller.MessageController;
import org.acme.hiber.controller.dto.MessageDTO;
import org.acme.hiber.model.Message;

@Path("channels/{id}/messages")
public class MessageRouter {
    @Inject
    private MessageController messageController;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getMessages(@PathParam("id") Long id) {
        return messageController.getChannelMessages(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Message sendMessage(@PathParam("id") Long id, MessageDTO messageDTO) {
        return messageController.saveMessage(id, messageDTO);
    }
}
