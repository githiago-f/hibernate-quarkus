package org.acme.hiber.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Channel extends PanacheEntity {
    private String hash;

    @ManyToMany(mappedBy = "channels")
    private Set<User> users = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "channel")
    private List<Message> messages = new ArrayList<>();
}
