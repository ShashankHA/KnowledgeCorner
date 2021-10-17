package com.learning.kc.repository;

import com.learning.kc.model.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Question,Integer> {
    @Query("select count(*) from Question where id = ?1 and answer = ?2")
    public int getAnswer(int id, String answer);

    @Query("select answer from Question where id = ?1")
    public String getAnswer(int id);
}

