package com.example.demo.repo;

import com.example.demo.entity.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPARepo extends JpaRepository<UserBean,Integer> {
    public UserBean findByName(String name);
}
