package com.example.micrigramm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
    private String name;
    private String accountName;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
