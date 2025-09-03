package com.jvictornascimento.buynowdotcom.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message="Invalide login credencial")
    private String email;
    @NotBlank(message="Invalide login credencial")
    private String password;
}
