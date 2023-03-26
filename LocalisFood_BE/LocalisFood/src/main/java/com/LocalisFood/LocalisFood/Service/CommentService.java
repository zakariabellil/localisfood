package com.LocalisFood.LocalisFood.Service;

import com.LocalisFood.LocalisFood.Exceptions.ProductNotFoundException;
import com.LocalisFood.LocalisFood.Exceptions.SpringLocalisFoodException;
import com.LocalisFood.LocalisFood.Mapper.CommentMapper;
import com.LocalisFood.LocalisFood.Model.Comment;
import com.LocalisFood.LocalisFood.Model.Product;
import com.LocalisFood.LocalisFood.Model.User;
import com.LocalisFood.LocalisFood.Repository.CommentRepository;
import com.LocalisFood.LocalisFood.Repository.ProductRepository;
import com.LocalisFood.LocalisFood.Repository.UserRepository;
import com.LocalisFood.LocalisFood.dto.CommentsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String Product_URL = "";
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;


    public void save(CommentsDto commentsDto) {
        Product product = productRepository.findById(commentsDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(commentsDto.getProductId().toString()));
        Comment comment = commentMapper.map(commentsDto, product, authService.getCurrentUser());
        commentRepository.save(comment);

    }



    public List<CommentsDto> getAllCommentsForProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId.toString()));
        return commentRepository.findByProduct(product)
                .stream()
                .map(commentMapper::mapToDto).collect(toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName);

        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

    public boolean containsSwearWords(String comment) {
        if (comment.contains("shit")) {
            throw new SpringLocalisFoodException("Comments contains unacceptable language");
        }
        return false;
    }
}
