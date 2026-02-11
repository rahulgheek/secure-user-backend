package com.example.demo.services;

import com.example.demo.entity.UserBean;
import com.example.demo.repo.UserRepositoryLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserService {
    private final UserRepositoryLayer repo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserService(UserRepositoryLayer repo,PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;
    }

    public void registerUser(UserBean user){
        try {
            if(user.getName() == null || user.getName().isEmpty()){
                throw new RuntimeException("Name is empty");
            }

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            if (user.getRole() == null || user.getRole().isEmpty()) {
                user.setRole("ROLE_USER");
            }

            repo.addUser(user);
        }catch (DuplicateKeyException e){
            throw new DuplicateKeyException("Email " + user.getEmail() + " already in use");
        }
    }

    public void registerAdmin(UserBean user){
        if(user.getName() == null || user.getName().isEmpty()){
            throw new RuntimeException("Name is empty");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_ADMIN");
        }

        repo.addUser(user);
    }

    public boolean deleteUser(int id){
        if(!repo.userExists(id)) return false;
        repo.deleteUser(id);
        return true;
    }

    public List<UserBean> displayAll(){
        return repo.displayAll();
    }

    @Transactional
    public void updateNameAndAge(int id,String name,int age){
        repo.updateName(id,name);

        if(age < 0){
            throw new RuntimeException("Invalid age -> Rollback");
        }
        repo.updateAge(id,age);
    }



}
