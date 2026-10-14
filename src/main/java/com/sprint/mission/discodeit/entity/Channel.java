package com.sprint.mission.discodeit.entity;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class Channel extends BaseEntity {

    private String name;
    private String description;

    private final Set<UUID> memberIds;
    private final Set<UUID> messageIds;

    public Channel(String name, String description) {
        super();
        this.name = name;
        this.description = description;
        this.memberIds = new LinkedHashSet<>();
        this.messageIds = new LinkedHashSet<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<UUID> getMemberIds() {
        return Collections.unmodifiableSet(memberIds);
    }

    public Set<UUID> getMessageIds() {
        return Collections.unmodifiableSet(messageIds);
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
        updateTimestamp();
    }

    public void addMember(UUID userId) {
        if (memberIds.add(userId)) {
            updateTimestamp();
        }
    }

    public void removeMember(UUID userId) {
        if (memberIds.remove(userId)) {
            updateTimestamp();
        }
    }

    public void addMessage(UUID messageId) {
        if (messageIds.add(messageId)) {
            updateTimestamp();
        }
    }

    public void removeMessage(UUID messageId) {
        if (messageIds.remove(messageId)) {
            updateTimestamp();
        }
    }
}
