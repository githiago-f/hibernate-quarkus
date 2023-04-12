package org.acme.hiber.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor 
public class User extends PanacheEntity {
    private String name;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Channel> channels = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Message> messages = new ArrayList<>();

    @Override
    public String toString() {
        return "(name=" + name + ", id=" + id + ")";
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
