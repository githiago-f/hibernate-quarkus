package org.acme.hiber.controller;

import java.util.Set;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.acme.hiber.controller.dto.MessageDTO;
import org.acme.hiber.model.Channel;
import org.acme.hiber.model.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class MessageController {
    public Set<Message> getChannelMessages(Long id) {
        Channel channel = Channel.findById(id);
        return channel.getMessages();
    }

    @Transactional
    public Message saveMessage(Long channelId, MessageDTO messageDTO) {
        log.info("User {} sent {} on channel {}", messageDTO.getSenderId(), messageDTO.getText());
        Message message = new Message();
        message.setText(messageDTO.getText());
        message.setUserId(messageDTO.getSenderId());
        message.setChannelId(channelId);
        message.persist();
        return message;
    }
}
