package com.example.demo.controller;

import com.example.demo.jpa.UserJpa;
import com.example.demo.model.UserEntity;
import com.example.demo.service.UserService;
import org.apache.tomcat.jni.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by daier on 2018/2/14.
 */
@RestController
@RequestMapping("user")
public class UserController {

//    不开 Redis 缓存

    @Resource
    private UserJpa userJpa;

    @RequestMapping(value = "getAllUser")
    public List<UserEntity> getAllUser(){
        return userJpa.findAll();
    }

    @RequestMapping(value = "addUser")
    public UserEntity addUser(UserEntity userEntity){
        return userJpa.save(userEntity);
    }

    @RequestMapping(value = "updateUser")
    public UserEntity updateUser(UserEntity userEntity){
        return userJpa.save(userEntity);
    }

    @RequestMapping(value = "deleteUser")
    public List<UserEntity> userList(Long id) {
        userJpa.delete(id);
        return userJpa.findAll();
    }

//    开启 Redis 缓存

    @Resource
    private UserService userService;
    @Resource
    private MessageSource messageSource;

    @RequestMapping(value = "userList")
    public List<UserEntity> userList(){
        return userService.userList();
    }

    @RequestMapping(value = "getUser")
    public UserEntity getUserById(long id){
        return userService.getUserById(id);
    }

    @RequestMapping(value = "validator",produces = "text/plain;charset=UTF-8")
    public String validator(@Valid UserEntity userEntity , BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuffer stringBuffer = new StringBuffer();
//            获取错误字段集合
            List<FieldError> errorList = bindingResult.getFieldErrors();
//            获取本地locale
            Locale currentLocale = LocaleContextHolder.getLocale();
//            遍历错误字段获取错误消息
            for(FieldError fieldError:errorList){
                String errorMessage = messageSource.getMessage(fieldError,currentLocale);
                stringBuffer.append(fieldError.getField()+":"+errorMessage+",");
            }
            return stringBuffer.toString();
        }
        return "validator success";
    }
}
