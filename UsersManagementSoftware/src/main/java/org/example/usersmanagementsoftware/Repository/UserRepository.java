package org.example.usersmanagementsoftware.Repository;

import org.example.usersmanagementsoftware.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.username=?1 and u.password=?2")
    User login(String username,String password);

    User findUserByEmail(String email);

    List<User> findUsersByRole(String role);

    @Query("select u from User u where u.age>=?1")
    List<User> getUsersByAndAboveAge(Integer age);

    User findUserById(Integer id);
}
