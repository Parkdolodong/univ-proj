package com.example.begin.repository;

import com.example.begin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNick(String nick);
    Optional<List<User>> findByCreatedAtAfter(LocalDateTime createdAt);
    Optional<List<User>> findByNickLike(String nickStr);
    Optional<User> findTopByNickOrderByIdxDesc(String nick);

}
