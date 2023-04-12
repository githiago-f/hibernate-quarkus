package org.acme.hiber.controller;

import java.util.List;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.acme.hiber.controller.dto.MessageDTO;
import org.acme.hiber.model.Channel;
import org.acme.hiber.model.Message;
import org.acme.hiber.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class MessageController {
    public List<Message> getChannelMessages(Long id) {
        Channel channel = Channel.findById(id);
        return channel.getMessages();
    }

    @Transactional
    public Message saveMessage(Long channelId, MessageDTO messageDTO) {
        log.info("User {} sent {} on channel {}", messageDTO.getSenderId(), messageDTO.getText());
        Channel channel = Channel.findById(channelId);
        User user = User.findById(messageDTO.getSenderId());
        Message message = new Message();
        message.setText(messageDTO.getText());
        channel.addMessage(message);
        user.addMessage(message);
        channel.persist();
        return message;
    }
}
