package com.LocalisFood.LocalisFood.Repository;

import com.LocalisFood.LocalisFood.Model.Comment;
import com.LocalisFood.LocalisFood.Model.Product;
import com.LocalisFood.LocalisFood.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProduct(Product product);

    List<Comment> findAllByUser(User user);
}
