package com.LocalisFood.LocalisFood.Repository;

import com.LocalisFood.LocalisFood.Model.Product;
import com.LocalisFood.LocalisFood.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUser(User user);
}
