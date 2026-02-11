package com.example.demo.controller;

import com.example.demo.entity.UserBean;
import com.example.demo.entity.WeatherResponse;
import com.example.demo.services.UserService;
import com.example.demo.services.WeatherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @Autowired
    private WeatherServices weather;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserBean user){
        service.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created Successfully");
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<String> addAdmin(@RequestBody UserBean user){
        service.registerAdmin(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created Successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> DeleteUser(@PathVariable int id){
        boolean flag = service.deleteUser(id);
        if(!flag) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        return ResponseEntity.ok("User deleted successfully");
    }
    @GetMapping("/display")
    public List<UserBean> display(){
        return service.displayAll();
    }

    @PutMapping("/NameAndAge/{id}")
    public ResponseEntity<String> update(@PathVariable int id,@RequestParam String name,@RequestParam int age){
        try{
            service.updateNameAndAge(id,name,age);
            return ResponseEntity.ok("Updated succesfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Not updated -> rolled back");
        }
    }

    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse.MainData> getWeather(@RequestParam String city){
        WeatherResponse.MainData response = weather.getWeather(city);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
