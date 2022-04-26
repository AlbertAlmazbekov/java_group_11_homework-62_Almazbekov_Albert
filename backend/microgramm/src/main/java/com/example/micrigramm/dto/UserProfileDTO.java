package com.example.micrigramm.dto;

import com.example.micrigramm.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {

    public static UserProfileDTO from(User user, List<PublicationDTO> publications){
        return UserProfileDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .accountName(user.getAccountName())
                .countPublications(user.getCountPublications())
                .countSubscribers(user.getCountSubscribers())
                .countSubscribes(user.getCountSubscribes())
                .publications(publications)
                .build();
    }

    private Long id;
    private String name;
    private String accountName;
    private Integer countPublications;
    private Integer countSubscribers;
    private Integer countSubscribes;
    private List<PublicationDTO> publications;
}
