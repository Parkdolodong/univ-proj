package com.example.begin.entity;

import com.example.begin.entity.listener.DateListener;
import com.example.begin.entity.listener.LibraryEntityListener;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(value = {LibraryEntityListener.class})
public class Books implements DateListener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ToString.Exclude
    @OneToMany(mappedBy = "books")
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();
    public void addReview(Review review){
        this.reviews.add(review);
    }

    private String title;
    private String publisher;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
