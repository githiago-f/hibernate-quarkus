package org.acme.hiber.controller.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class ChannelDTO {
    private String hash;
    private Set<Long> users = new HashSet<>();
}
