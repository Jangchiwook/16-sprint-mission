package com.sprint.mission.discodeit.entity;

import java.util.UUID;

// 공통 필드, 기능 관리를 하는 추상 클래스
// User, Channel, Message 가 상속받는 부모 클래스

public abstract class BaseEntity {
    private final UUID id;            // 객제의 ID
    private final Long createdAt;     // 객체의 생성 시간
    private Long updatedAt;           // 객체의 마지막 수정 시간

    protected BaseEntity() {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = this.createdAt;
    }
// ID 조회
    public UUID getId() {
        return id;
    }
// 생성 시간 조회
    public Long getCreatedAt() {
        return createdAt;
    }
// 수정 시간 조회
    public Long getUpdatedAt() {
        return updatedAt;
    }
// 수정 시간 변경 및 자식 클래스의 update 메서드에서 사용함.
    protected void updateTimestamp() {
        this.updatedAt = System.currentTimeMillis();
    }
}