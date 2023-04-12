package org.acme.hiber.routes;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.acme.hiber.controller.ChannelController;
import org.acme.hiber.controller.dto.ChannelDTO;
import org.acme.hiber.controller.dto.UpdateChannelUsersDTO;
import org.acme.hiber.model.Channel;

@Path("/channels")
public class ChannelRouter {
    @Inject
    private ChannelController channelController;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Channel createChannel(ChannelDTO channelDTO) {
        return channelController.createChannel(channelDTO);
    }

    @PATCH
    @Path("/{id}/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Channel addUserToChannel(@PathParam("id") Long id, UpdateChannelUsersDTO updateChannelUsersDTO) {
        updateChannelUsersDTO.setChannelId(id);
        return channelController.addUser(updateChannelUsersDTO);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Channel getChannel(@PathParam("id") Long id) {
        return channelController.getChannel(id);
    }
}
