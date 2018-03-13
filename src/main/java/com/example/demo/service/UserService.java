package com.example.demo.service;

import com.example.demo.jpa.UserJpa;
import com.example.demo.model.UserEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by daier on 2018/2/24.
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserService {

    @Resource
    private UserJpa userJpa;

    @Cacheable
    public List<UserEntity> userList(){
        return userJpa.findAll();
    }

    @Cacheable
    public UserEntity getUserById(long id){
        return userJpa.findOne(id);
    }
}
