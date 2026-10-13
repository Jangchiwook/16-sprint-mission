package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Channel extends BaseEntity{

    private String name;
    private String description;

    public Channel(String description, String name) {
        super();

        this.name = name;
        this.description = description;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
        updateTimestamp();
    }
}

