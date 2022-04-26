package com.example.micrigramm.dto;

import com.example.micrigramm.entity.Like;
import lombok.*;


@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class LikeDTO {

    public static LikeDTO from(Like like) {
        return builder()
                .id(like.getId())
                .likeOwner(like.getLikeOwner().getAccountName())
                .build();
    }

    private Long id;
    private String likeOwner;
}
