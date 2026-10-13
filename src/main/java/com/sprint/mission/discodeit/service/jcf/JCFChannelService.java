package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class JCFChannelService implements ChannelService {

    private final Map<UUID, Channel> data;

    public JCFChannelService() {
        this.data = new LinkedHashMap<>();
    }

    @Override
    public Channel create(String name, String description) {
        Channel channel = new Channel(name, description);

        data.put(channel.getId(), channel);
        return channel;
    }
    @Override
    public Channel findById(UUID id) {
        return data.get(id);
    }
    @Override
    public List<Channel> findAll() {
        return new ArrayList<>(data.values());
    }
    @Override
    public void update(UUID id, String name, String description) {
        Channel channel = data.get(id);

        if (channel == null) {
            throw new IllegalArgumentException("채널을 찾을 수 없습니다.");
        }
        channel.update(name, description);
    }
    @Override
    public void delete(UUID id) {
        data.remove(id);
    }
}
