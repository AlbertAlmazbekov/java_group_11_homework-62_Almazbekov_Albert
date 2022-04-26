package com.example.micrigramm.dto;

import com.example.micrigramm.entity.Comment;
import com.example.micrigramm.entity.Like;
import com.example.micrigramm.entity.Publication;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PublicationDTO {

    public static PublicationDTO from(Publication publication, List<Like> likesList, List<Comment> commentsList) {
        return builder()
                .id(publication.getId())
                .imageId(publication.getImage().getId())
                .description(publication.getDescription())
                .author(publication.getAuthor().getAccountName())
                .dateAdded(publication.getDateAdded())
                .likes(likesList.stream()
                        .map(LikeDTO::from)
                        .collect(Collectors.toList()))
                .comments(commentsList.stream()
                        .map(CommentDTO::from)
                        .collect(Collectors.toList()))
                .build();
    }

    private Long id;
    private Long imageId;
    private String description;
    private String author;
    private LocalDateTime dateAdded;
    private List<LikeDTO> likes;
    private List<CommentDTO> comments;
}