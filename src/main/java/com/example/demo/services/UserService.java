package com.example.demo.services;

import com.example.demo.entity.UserBean;
import com.example.demo.repo.JPARepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private JPARepo re;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
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

            re.save(user);
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

        re.save(user);
    }

    public boolean deleteUser(int id){
        if(!re.existsById(id)) return false;
        re.deleteById(id);
        return true;
    }

    public List<UserBean> displayAll(){
        return re.findAll();
    }

    @Transactional
    public void updateNameAndAge(int id,String name,int age){
        UserBean u = re.findById(id).get();

        u.setName(name);
        u.setAge(age);

        re.save(u);

    }



}
