package com.LocalisFood.LocalisFood.Repository;

import com.LocalisFood.LocalisFood.Model.ImageModel;
import com.LocalisFood.LocalisFood.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);
    List<Product> findByProduct(Product product);
}
