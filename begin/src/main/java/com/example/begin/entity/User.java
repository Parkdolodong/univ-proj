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
@EntityListeners(value = { LibraryEntityListener.class })
public class User implements DateListener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    //@Column(name = "user_id", nullable = false, length = 100, unique = true)
    private String userId;
    private String userPw;
    private String nick;
    private String addr;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();
    public void addReview(Review review){
        this.reviews.add(review);
    }

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
