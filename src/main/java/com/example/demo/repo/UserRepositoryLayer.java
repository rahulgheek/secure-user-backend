package com.example.demo.repo;

import com.example.demo.entity.UserBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.List;

@Repository
public class UserRepositoryLayer {
    private final JdbcTemplate temp;

    public UserRepositoryLayer(JdbcTemplate temp){
        this.temp = temp;
    }

    public void addUser(UserBean user){
        String ADD_SQL_Query = "INSERT INTO userdata(name,age,email,password,role) VALUES(?,?,?,?,?)";
        temp.update(ADD_SQL_Query,user.getName(),user.getAge(),user.getEmail(),user.getPassword(),user.getRole());
    }

    public void deleteUser(int id){
        String DELETE_SQL_Query = "DELETE FROM userdata where id = ?";
        temp.update(DELETE_SQL_Query,id);
    }

    public boolean userExists(int id){
        String USER_EXIST = "SELECT COUNT(*) FROM userdata where id = ?";

        Integer count = temp.queryForObject(USER_EXIST,Integer.class,id);

        return count != null && count > 0;
    }

    public List<UserBean> displayAll(){
        String sql = "Select * from userdata";

        return temp.query(sql, (rs, rowNum) -> {
            UserBean user = new UserBean();
            user.setId(rs.getInt("id"));      // Make sure your Bean has setId()
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
            user.setEmail(rs.getString("email"));
            user.setRole(rs.getString("role"));
            return user;
        });
    }

    public void updateName(int id, String newName) {
        String sql = "UPDATE userdata SET name = ? WHERE id = ?";
        temp.update(sql, newName, id);
    }

    public void updateAge(int id, int newAge) {
        String sql = "UPDATE userdata SET age = ? WHERE id = ?";
        temp.update(sql, newAge, id);
    }

    public UserBean findByName(String name){
        String sql = "Select * from userdata where name = ?";

        try{
            return temp.queryForObject(sql,(rs, rowNum) -> {
                UserBean user = new UserBean();
                user.setName(rs.getString("name"));
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            },name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
}