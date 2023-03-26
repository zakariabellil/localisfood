package com.LocalisFood.LocalisFood.Service;

import com.LocalisFood.LocalisFood.dto.VoteDto;
import com.LocalisFood.LocalisFood.Exceptions.ProductNotFoundException;
import com.LocalisFood.LocalisFood.Exceptions.SpringLocalisFoodException;
import com.LocalisFood.LocalisFood.Model.Product;
import com.LocalisFood.LocalisFood.Model.Vote;
import com.LocalisFood.LocalisFood.Repository.ProductRepository;
import com.LocalisFood.LocalisFood.Repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.LocalisFood.LocalisFood.Model.VoteType.UPVOTE;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final ProductRepository productRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Product product = productRepository.findById(voteDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found with ID - " + voteDto.getProductId()));
        Optional<Vote> voteByProductAndUser = voteRepository.findTopByProductOrderByVoteIdDesc(product);
        if (voteByProductAndUser.isPresent() &&
                voteByProductAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringLocalisFoodException("You have already "
                    + voteDto.getVoteType() + "'d for this Product");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            product.setVoteCount(product.getVoteCount() + 1);
        } else {
            product.setVoteCount(product.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, product));
        productRepository.save(product);
    }

    private Vote mapToVote(VoteDto voteDto, Product product) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .product(product)
                .build();
    }
}
