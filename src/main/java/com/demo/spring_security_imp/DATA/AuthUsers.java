package com.demo.spring_security_imp.DATA;

import java.util.ArrayList;
import java.util.HashMap;

public class AuthUsers {
    private HashMap<Long, User> users = new HashMap<>();

    public User addUser(User user) {
        user.setId((long) users.size()+1);
        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    public User getUser(Long id) {
        return users.get(id);
    }

    public ArrayList<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User findByEmail(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public User findByUsername(String userName) {
        for (User user : users.values()) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }
}
