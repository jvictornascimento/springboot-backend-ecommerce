package com.jvictornascimento.buynowdotcom.security.user;

import com.jvictornascimento.buynowdotcom.model.User;
import com.jvictornascimento.buynowdotcom.repository.UserRepositoy;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopUserDetailService implements UserDetailsService {
    private final UserRepositoy userRepositoy;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepositoy.findByEmail(email))
                .orElseThrow(()-> new EntityNotFoundException("User not found!"));
        return ShopUserDetail.buildUserDetail(user);
    }
}
