package com.itis.respositories;

public interface BlackListRepository {
    void save(String token);

    boolean exists(String token);
}
