package com.learning.kc.endpoints;

import com.learning.kc.model.Question;
import com.learning.kc.model.ResponseDTO;
import com.learning.kc.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("/ping")
    public String ping(){
        return "pong from QuizController";
    }

    @GetMapping("/newSet")
    public List<Question> getQuiz(@RequestParam("limit") int limit){
        return quizService.getQuestions(limit);
    }

    @PostMapping("/results")
    public ResponseEntity<ResponseDTO> getResult(@RequestBody List<Question> request) {
        ResponseDTO res = new ResponseDTO();
        res.setResponse(quizService.getResults(request));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
