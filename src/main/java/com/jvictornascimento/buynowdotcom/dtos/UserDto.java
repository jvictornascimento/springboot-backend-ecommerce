package com.jvictornascimento.buynowdotcom.dtos;

import com.jvictornascimento.buynowdotcom.model.Cart;
import com.jvictornascimento.buynowdotcom.model.Order;
import com.jvictornascimento.buynowdotcom.model.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Cart cart;
    private List<Order> orders;
    private Collection<Role> roles = new HashSet<>();
}
