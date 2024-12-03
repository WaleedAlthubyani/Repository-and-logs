package org.example.usersmanagementsoftware.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.usersmanagementsoftware.ApiResponse.ApiResponse;
import org.example.usersmanagementsoftware.Model.User;
import org.example.usersmanagementsoftware.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/users-management-software/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    Logger logger =LoggerFactory.getLogger(UserController.class);

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers(){
        logger.info("Inside get all users");
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.getAllUsers()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addUser(@RequestBody @Valid User user, Errors errors){
        logger.info("Inside add user");
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        userService.addUser(user);
        return ResponseEntity.status(201).body(new ApiResponse<>("User created successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateUser(@PathVariable Integer id,@RequestBody @Valid User user,Errors errors){
        logger.info("Inside update user");
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse<>("User updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Integer id){
        logger.info("Inside delete user");
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("User deleted successfully"));
    }

    @PostMapping("/login/username/{username}/password/{password}")
    public ResponseEntity<ApiResponse<String>> login(@PathVariable String username,@PathVariable String password){
        logger.info("Inside login");
        userService.login(username,password);
        return ResponseEntity.status(200).body(new ApiResponse<>("Login successful"));
    }

    @GetMapping("/get-user-by-email/{email}")
    public ResponseEntity<ApiResponse<User>> getUserByEmail(@PathVariable String email){
        logger.info("Inside get users by email");
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.getUserByEmail(email)));
    }

    @GetMapping("/get-users-by-role/{role}")
    public ResponseEntity<ApiResponse<List<User>>> getUsersByRole(@PathVariable String role){
        logger.info("Inside get users by role");
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.getAllUsersByRole(role)));
    }

    @GetMapping("/get-users-at-or-above-age/{age}")
    public ResponseEntity<ApiResponse<List<User>>> getUsersAtOrAboveAge(@PathVariable Integer age){
        logger.info("Inside get users by or above age");
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.getAllUsersAtAndAboveAge(age)));
    }
}
