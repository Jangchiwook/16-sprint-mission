package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Message;

import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.*;


public class JCFMessageService implements MessageService {

    private final Map<UUID, Message> data;

    private final UserService userService;
    private final ChannelService channelService;

    public JCFMessageService(UserService userService, ChannelService channelService) {
        this.data = new LinkedHashMap<>();
        this.userService = userService;
        this.channelService = channelService;
    }
    @Override
    public Message create(String content, UUID userId, UUID channelId) {
        if (userService.findById(userId) == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }
        if (channelService.findById(channelId) == null) {
            throw new IllegalArgumentException("존재하지 않는 채널입니다.");
        }

        Message message = new Message(content, userId, channelId);
        data.put(message.getId(), message);
        return message;
    }
    @Override
    public Message findById(UUID id) {
        return data.get(id);
    }
    @Override
    public List<Message> findAll() {
        return new ArrayList<>(data.values());
    }
    @Override
    public void update(UUID id, String content) {
        Message message = data.get(id);
        if (message == null) {
            throw new IllegalArgumentException("메세지를 찾을 수 없습니다.");
        }
        message.update(content);
    }
    @Override
    public void delete(UUID id) {
        data.remove(id);
    }
}
