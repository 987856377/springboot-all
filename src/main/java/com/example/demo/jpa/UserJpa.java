package com.example.demo.jpa;

import com.example.demo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * Created by daier on 2018/2/14.
 */
public interface UserJpa extends JpaRepository<UserEntity,Long> , JpaSpecificationExecutor<UserEntity> , Serializable{
}
