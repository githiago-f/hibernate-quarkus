package org.acme.hiber.controller.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private Long senderId;
    private String text;
}
