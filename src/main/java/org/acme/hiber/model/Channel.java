package org.acme.hiber.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Channel extends PanacheEntity {
    private String hash;

    @JsonManagedReference
    @ManyToMany(mappedBy = "channels", fetch = FetchType.EAGER)
    private Set<User> users;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Message> messages;

    public Channel() {
        users = new HashSet<>();
        messages = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
