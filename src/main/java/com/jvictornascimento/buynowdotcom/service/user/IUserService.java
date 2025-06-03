package com.jvictornascimento.buynowdotcom.service.user;

import com.jvictornascimento.buynowdotcom.dtos.UserDto;
import com.jvictornascimento.buynowdotcom.model.User;
import com.jvictornascimento.buynowdotcom.request.CreateUserRequest;
import com.jvictornascimento.buynowdotcom.request.UserUpdateRequest;

public interface IUserService {

    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long Id);
    User getUserById(Long userId);
    void deleteUser(Long userId);
    UserDto convertToDto(User user);


}
