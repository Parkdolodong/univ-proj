package com.example.begin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FoodResDto {
    private String title;
    private String category;
    private String address;
    private String roadAddress;
    private String homepageLink;
    private String imageLink;
}
