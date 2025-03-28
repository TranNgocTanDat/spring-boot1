package com.example.springboot1.controller;

import com.example.springboot1.dto.request.APIResponse;
import com.example.springboot1.dto.request.UserCreationRequest;
import com.example.springboot1.dto.request.UserUpdateRequest;
import com.example.springboot1.dto.response.UserResponse;
import com.example.springboot1.entity.User;
import com.example.springboot1.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;

    @PostMapping
    APIResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        APIResponse<User> apiResponse = new APIResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    APIResponse<List<UserResponse>> getUsers(){
        var authentication =  SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));


        return APIResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();

    }

    @GetMapping("/{userId}")
    UserResponse getUserById(@PathVariable("userId") String id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}
