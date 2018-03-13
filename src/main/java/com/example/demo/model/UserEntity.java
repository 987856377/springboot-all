package com.example.demo.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by daier on 2018/2/14.
 */
@Entity
@Table(name = "person")
public class UserEntity implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @NotEmpty
    @Column(name = "name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 测试 SelfValidator 注解
     */

    /*
        @SelfValidator(values="root")   password 必须为 root
        private String password;
     */
}
