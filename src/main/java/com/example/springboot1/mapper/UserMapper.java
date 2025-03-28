package com.example.springboot1.mapper;

import com.example.springboot1.dto.request.UserCreationRequest;
import com.example.springboot1.dto.request.UserUpdateRequest;
import com.example.springboot1.dto.response.UserResponse;
import com.example.springboot1.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
