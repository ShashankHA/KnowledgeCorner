package com.learning.kc.service;

import com.learning.kc.model.Question;
import com.learning.kc.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    QuestionsRepository questionsRepository;

    public List<Question> getQuestions(int questionsCount){
        List<Question> quizQuestions = new ArrayList<>();
        List<Question> questions = getRandomQuestions(questionsCount);
        for(Question question : questions) {
            Question q = new Question();
            q.setQuestion(question.getQuestion());
            q.setOptions(question.getOptions());
            q.setId(question.getId());
            q.setOptions_count(question.getOptions_count());
            quizQuestions.add(q);
        }
        return quizQuestions;
    }

    private List<Question> getRandomQuestions(int questionsCount) {
        Random random = new Random();
        List<Integer> randomIds = new ArrayList<>(questionsCount);
        Long totalQuestions = getQuestionsCountinDB();
        List<Question> questions = new ArrayList<>();
        for(int i=1; i<=questionsCount;i++){
            int randomNumber = random.nextInt(totalQuestions.intValue());
            while( randomIds.contains(randomNumber)){
                randomNumber = random.nextInt(totalQuestions.intValue());
            }
            randomIds.add(randomNumber);
            questions.add(questionsRepository.getOne(randomNumber));
        }
        return questions;
    }

    private Long getQuestionsCountinDB(){
        return questionsRepository.count();
    }

    public Object getResults(List<Question> responses) {

        Map<String, Object> resultMap = new HashMap<>();
        int correctAnswers = 0;
        List<Map<String,Object>> results = new ArrayList<>();
        for( Question question : responses ){
            boolean isCorrectAnswer = false ;
            int answerRows = questionsRepository.getAnswer(question.getId(),question.getAnswer());
            if(answerRows == 1){
                isCorrectAnswer = true ;
                correctAnswers++;
            }
            Map<String,Object> response = new HashMap<>();
            response.put("id",question.getId());
            response.put("question",question.getQuestion());
            response.put("answer",questionsRepository.getAnswer(question.getId()));
            response.put("IsCorrectAnswer", isCorrectAnswer);
            results.add(response);
        }
        resultMap.put("CorrectAnswers",correctAnswers);
        resultMap.put("Result",results);
        return resultMap;
    }
}
