package com.example.micrigramm.dto;

import com.example.micrigramm.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    public static UserDTO from(User user) {
        return UserDTO.builder()
                .accountName(user.getAccountName())
                .profileLink("/users/" + user.getId())
                .build();
    }

    private String accountName;
    private String profileLink;
}
