package org.acme.hiber;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.acme.hiber.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/users")
public class ExemploResource {

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public User hello(@QueryParam("name") String name) {
        User user = new User();
        user.setName(name);
        User.persist(user);
        log.info("Salvando o usuario: {}", user.getName());
        return user;
    }
}