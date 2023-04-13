package org.acme.hiber.controller;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.acme.hiber.controller.dto.ChannelDTO;
import org.acme.hiber.controller.dto.UpdateChannelUsersDTO;
import org.acme.hiber.model.Channel;
import org.acme.hiber.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class ChannelController {
    @Transactional
    public Channel createChannel(ChannelDTO channelDTO) {
        log.info("Creating channel {}", channelDTO.getHash());
        Channel channel = new Channel();
        channel.setHash(channelDTO.getHash());
        log.info("Channel have {} initial users", channelDTO.getUsers().size());
        channel.persist();
        channelDTO.getUsers().stream()
            .map(userId -> (User) User.findById(userId))
            .forEach(user -> {
                user.addChannel(channel);
                user.persist();
                // only to generate a good response with the 
                // set of users populated.
                channel.addUser(user);
            });
        log.info("Channel persisted and added to user's channels list");
        return channel;
    }

    public Channel getChannel(Long id) {
        return Channel.findById(id);
    }

    @Transactional
    public Channel addUser(UpdateChannelUsersDTO updateChannelUsersDTO) {
        Channel channel = getChannel(updateChannelUsersDTO.getChannelId());
        log.info("Updating channel {}", channel.getHash());
        User user = User.findById(updateChannelUsersDTO.getUserId());
        log.info("User added: {}", user.id);
        user.addChannel(channel);
        user.persist();
        log.info("Channel added to user's channel list");
        channel.addUser(user);
        return channel;
    }
}
