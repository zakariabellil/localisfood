package com.LocalisFood.LocalisFood.Mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.LocalisFood.LocalisFood.dto.ProductRequest;
import com.LocalisFood.LocalisFood.dto.ProductResponse;
import com.LocalisFood.LocalisFood.Model.*;
import com.LocalisFood.LocalisFood.Repository.CommentRepository;
import com.LocalisFood.LocalisFood.Repository.VoteRepository;
import com.LocalisFood.LocalisFood.Service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.LocalisFood.LocalisFood.Model.VoteType.DOWNVOTE;
import static com.LocalisFood.LocalisFood.Model.VoteType.UPVOTE;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;


    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "productRequest.description")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Product map(ProductRequest productRequest,  User user);

    @Mapping(target = "id", source = "productId")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(product))")
    @Mapping(target = "duration", expression = "java(getDuration(product))")
    @Mapping(target = "upVote", expression = "java(isProductUpVoted(product))")
    @Mapping(target = "downVote", expression = "java(isProductDownVoted(product))")
    public abstract ProductResponse mapToDto(Product product);

    Integer commentCount(Product product) {
        return commentRepository.findByProduct(product).size();
    }

    String getDuration(Product product) {
        return TimeAgo.using(product.getCreatedDate().toEpochMilli());
    }

    boolean isProductUpVoted(Product product) {
        return checkVoteType(product, UPVOTE);
    }

    boolean isProductDownVoted(Product product) {
        return checkVoteType(product, DOWNVOTE);
    }

    private boolean checkVoteType(Product product, VoteType voteType) {
        if (authService.isLoggedIn()) {
            Optional<Vote> voteForProductByUser =
                    voteRepository.findTopByProductOrderByVoteIdDesc(product);
            return voteForProductByUser.filter(vote -> vote.getVoteType().equals(voteType))
                    .isPresent();
        }
        return false;
    }

}
