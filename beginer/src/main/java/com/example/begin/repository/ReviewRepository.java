package com.example.begin.repository;

import com.example.begin.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "select r.books.title from Review r where r.user.userId = :userId")
    Optional<List<String>> findBookTitieByEmail(@Param("userId") String userId);

    /*
    * 특정 사용자 리뷰 검색..
    * */
    // select r.* from sample1.review r
    // inner join sample1.user u on u.idx = r.user_idx
    // where u.user_id = 'kavin';
    @Query(value = "select r from Review r where r.user.userId = :userId")
    Optional<List<Review>> findReviewsByUserId(@Param("userId") String userId);

}
