package com.learning.kc.service;

import com.learning.kc.model.Question;
import com.learning.kc.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsService {

    @Autowired
    private QuestionsRepository questionsRepository;

    public Question addQuestion(Question question) {
        return questionsRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionsRepository.findAll();
    }


}
