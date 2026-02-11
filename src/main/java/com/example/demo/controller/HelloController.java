package com.example.demo.controller;

import com.example.demo.entity.UserBean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // 1. Tells Spring: "This class handles web requests"
@RequestMapping("/student")
public class HelloController {

    List<UserBean> lst = new ArrayList<>();

    @GetMapping// 2. Tells Spring: "If someone visits /hello, run this method"
    public List<String> sayHi() {
        return List.of("Hello","1","Bob");
    }

    @GetMapping("/{id}")
    public String getStud(@PathVariable long id){
        return "Student with id: " + id;
    }

    @PostMapping
    public String addStud(@RequestBody String studentname){
        System.out.println(studentname);
        return "Student name: " + studentname;
    }

    @PostMapping("/create")
    public void obj(@RequestBody UserBean user){
        lst.add(user);
    }

    @GetMapping("/display")
    public List<UserBean> displayList(){
        return lst;
    }
}