package com.jvictornascimento.buynowdotcom.service.user;

import com.jvictornascimento.buynowdotcom.dtos.UserDto;
import com.jvictornascimento.buynowdotcom.model.User;
import com.jvictornascimento.buynowdotcom.repository.OrderRepository;
import com.jvictornascimento.buynowdotcom.repository.UserRepositoy;
import com.jvictornascimento.buynowdotcom.request.CreateUserRequest;
import com.jvictornascimento.buynowdotcom.request.UserUpdateRequest;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepositoy userRepositoy;
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

    @Override
    public User createUser(CreateUserRequest request) {
        return Optional.of(request)
                .filter(user -> !userRepositoy.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setFirstName(req.getFirstName());
                    user.setLastName(req.getLastName());
                    user.setEmail(req.getEmail());
                    user.setPassword(req.getPassword());
                    return userRepositoy.save(user);
                }).orElseThrow(() -> new EntityExistsException("Oops! " + request.getEmail() + " already exists"));
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId) {
        return userRepositoy.findById(userId)
                .map(existingUser -> {
                    existingUser.setFirstName(request.getFirstName());
                    existingUser.setLastName(request.getLastName());
                    return userRepositoy.save(existingUser);
                }).orElseThrow(() -> new EntityNotFoundException("User not found!"));
    }

    @Override
    public User getUserById(Long userId) {
        return userRepositoy.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepositoy.findById(userId).ifPresentOrElse(userRepositoy::delete, () -> {
            throw new EntityNotFoundException("User not found");
        });
    }

    @Override
    public UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}