package com.LocalisFood.LocalisFood.Mapper;

import com.LocalisFood.LocalisFood.dto.CommentsDto;
import com.LocalisFood.LocalisFood.Model.Comment;
import com.LocalisFood.LocalisFood.Model.Product;
import com.LocalisFood.LocalisFood.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "user", source = "user")
    Comment map(CommentsDto commentsDto, Product product, User user);

    @Mapping(target = "productId", expression = "java(comment.getProduct().getProductId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentsDto mapToDto(Comment comment);
}
