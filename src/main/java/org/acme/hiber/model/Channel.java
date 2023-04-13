package org.acme.hiber.model;

import java.util.*;

import javax.persistence.*;

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

    @JoinColumn(name = "channel_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Message> messages;

    public Channel() {
        users = new LinkedHashSet<>();
        messages = new LinkedHashSet<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Channel other = (Channel) obj;
        return Objects.equals(hash, other.hash);
    }

    
}
