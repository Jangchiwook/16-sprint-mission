package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class JCFUserService implements UserService {
    private final Map<UUID, User> data;
    public JCFUserService() {
        this.data = new LinkedHashMap<>();
    }
    @Override
    public User create(String username, String password, String email) {
        User user = new User(username, password, email);
        data.put(user.getId(), user);
        return user;
    }
    @Override
    public User findById(UUID id) {
        return data.get(id);
    }
    @Override
    public List<User> findAll() {
        return new ArrayList<>(data.values());
    }
    @Override
    public void update(UUID id, String username, String password, String email) {
        User user = findById(id);

        if (user == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다");
        }

        user.update(username, password, email);
    }
    @Override
    public void delete(UUID id) {
        data.remove(id);
    }

}
