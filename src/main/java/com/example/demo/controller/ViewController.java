package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by daier on 2018/3/5.
 */
@Controller
@RequestMapping("/")
public class ViewController {

    @RequestMapping("index")
    public ModelAndView toIndex(HttpServletRequest request, Model model){
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        model.addAttribute("path",basePath);
        System.out.println(basePath);
        return new ModelAndView("index");
    }
}
