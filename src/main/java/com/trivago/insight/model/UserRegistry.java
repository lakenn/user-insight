package com.trivago.insight.model;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//get rid of this class... it serves  no puporse.. clients can use the underlying map directly

@Component
public class UserRegistry {

    private Map<String, UserStats> userTable;

    public UserRegistry() {
        this.userTable = new ConcurrentHashMap<>();
    }

    public UserStats getUserStats(String userId){
        return userTable.computeIfAbsent(userId, key -> new UserStats(key));
    }

}
