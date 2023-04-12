package org.acme.hiber.controller;

import java.util.Set;
import java.util.stream.Collectors;

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
        Set<User> users = channelDTO.getUsers().stream()
            .map(userId -> (User) User.findById(userId))
            .collect(Collectors.toSet());
        users.forEach(user -> log.info("User: {}", user.getName()));
        channel.setUsers(users);
        channel.persist();
        log.info("Channel persisted");
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
        channel.addUser(user);
        channel.persist();
        log.info("Channel persisted");
        return channel;
    }
}
