package org.example.usersmanagementsoftware.Service;

import lombok.RequiredArgsConstructor;
import org.example.usersmanagementsoftware.ApiResponse.ApiException;
import org.example.usersmanagementsoftware.Model.User;
import org.example.usersmanagementsoftware.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user){
        User oldUser = userRepository.findUserById(id);

        if (oldUser==null)
            throw new ApiException("User not found");

        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());
        oldUser.setAge(user.getAge());

        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User user = userRepository.findUserById(id);

        if (user==null)
            throw new ApiException("User not found");

        userRepository.delete(user);
    }


    public void login(String username, String password){
        User user = userRepository.login(username,password);

        if (user==null)
            throw new ApiException("Wrong username or password");
    }

    public User getUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);

        if (user==null)
            throw new ApiException("User not found");

        return user;
    }

    public List<User> getAllUsersByRole(String role){
        List<User> users = userRepository.findUsersByRole(role);

        if (users.isEmpty())
            throw new ApiException("There are no users matching this role");

        return users;
    }

    public List<User> getAllUsersAtAndAboveAge(Integer age){
        List<User> users = userRepository.getUsersByAndAboveAge(age);

        if (users.isEmpty())
            throw new ApiException("There are no users at or above "+age);

        return users;
    }

}
