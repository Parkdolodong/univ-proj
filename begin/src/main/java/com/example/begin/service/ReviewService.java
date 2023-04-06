package com.example.begin.service;

import com.example.begin.dto.ReviewDto;
import com.example.begin.dto.UserDto;
import com.example.begin.entity.Review;
import com.example.begin.entity.User;
import com.example.begin.repository.BookRepository;
import com.example.begin.repository.ReviewRepository;
import com.example.begin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Transactional
    public List<ReviewDto> findReviews(String userId) {
        var reviews = reviewRepository.findReviewsByUserId(userId);

        List<ReviewDto> reviewsDto = new ArrayList<>();
        if(reviews.isPresent() && reviews.get().size() > 0){
            reviews.get().forEach(r->{
                var dto = entityToDto(r);
                reviewsDto.add(dto);
            });
        }
        return reviewsDto;
    }

    @Transactional
    public ReviewDto addReview(ReviewDto reviewDto) {
        return entityToDto(reviewRepository.save(dtoToEntity(reviewDto)));
    }



    private ReviewDto entityToDto(Review review) {
        var dto = ReviewDto.builder().idx(review.getIdx())
                .userIdx(review.getUser().getIdx())
                .userId(review.getUser().getUserId())
                .bookIdx(review.getBooks().getIdx())
                .title(review.getTitle())
                .contents(review.getContents())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
        return dto;
    }

    private Review dtoToEntity(ReviewDto reviewDto) {

        var user = userRepository.findByUserId(reviewDto.getUserId()).get();
        var book = bookRepository.findById(reviewDto.getBookIdx()).get();

        var entity = Review.builder().idx(reviewDto.getIdx())
                .title(reviewDto.getTitle())
                .books(book)
                .contents(reviewDto.getContents())
                .user(user)
                .build();

        return entity;
    }


}
