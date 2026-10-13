package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        super(); // 부모 클래스 생성자 호출 및 ID, 생성 시간, 수정 시간을 초기화함.

        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void update(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        updateTimestamp();
    }
}