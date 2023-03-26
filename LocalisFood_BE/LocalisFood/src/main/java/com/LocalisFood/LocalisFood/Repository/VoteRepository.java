package com.LocalisFood.LocalisFood.Repository;

import com.LocalisFood.LocalisFood.Model.Product;
import com.LocalisFood.LocalisFood.Model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByProductOrderByVoteIdDesc(Product product);
}
