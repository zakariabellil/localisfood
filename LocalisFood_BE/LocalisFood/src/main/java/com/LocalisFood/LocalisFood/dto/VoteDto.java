package com.LocalisFood.LocalisFood.dto;

import com.LocalisFood.LocalisFood.Model.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
    private VoteType voteType;
    private Long productId;
}
