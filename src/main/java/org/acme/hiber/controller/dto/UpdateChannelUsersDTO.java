package org.acme.hiber.controller.dto;

import lombok.Data;

@Data
public class UpdateChannelUsersDTO {
    private Long userId;
    private Long channelId;
}
