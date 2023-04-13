package org.acme.hiber.routes;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.acme.hiber.controller.UserController;
import org.acme.hiber.controller.dto.UserDTO;
import org.acme.hiber.model.User;

@Path("/users")
public class UserRouter {
    @Inject
    UserController userController;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() {
        return userController.findAllUsers();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") Long id) {
        return userController.findUser(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User registerUser(UserDTO userDTO) {
        return userController.save(userDTO);
    }
}
