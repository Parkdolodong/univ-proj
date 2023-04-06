package com.example.begin.repository;

import com.example.begin.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNick(String nick);
    Optional<List<User>> findByCreatedAtAfter(LocalDateTime createdAt);
    Optional<List<User>> findByNickLike(String nickStr);
    Optional<Page<User>> findByNickLike(String nickStr, Pageable pageable);
    Optional<User> findTopByNickOrderByIdxDesc(String nick);

    Optional<User> findByUserId(String userId);
    Optional<User> findByUserIdAndUserPw(String userId, String userPw);

}
