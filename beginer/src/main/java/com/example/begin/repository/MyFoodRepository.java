package com.example.begin.repository;

import com.example.begin.entity.MyFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyFoodRepository extends JpaRepository<MyFood, Long> {
}
