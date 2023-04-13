package org.acme.hiber.model;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter 
public class User extends PanacheEntity {
    private String name;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL)
    private final Set<Channel> channels;

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Message> messages;

    public User() {
        channels = new LinkedHashSet<>();
        messages = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "(name=" + name + ", id=" + id + ")";
    }

    public void addMessage(final Message message) {
        messages.add(message);
    }
    
    public void addChannel(final Channel channel) {
        channels.add(channel);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, channels, messages);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final User other = (User) obj;
        return Objects.equals(name, other.name) && Objects.equals(channels, other.channels)
                && Objects.equals(messages, other.messages);
    }
}
