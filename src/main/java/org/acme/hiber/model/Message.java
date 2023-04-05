package org.acme.hiber.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Message extends PanacheEntity {
    private String text;
    
    @ManyToOne
    private User user;

    @ManyToOne()
    @JoinColumn()
    private Channel channel;
}
