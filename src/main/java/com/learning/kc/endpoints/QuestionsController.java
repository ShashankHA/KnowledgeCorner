package com.learning.kc.endpoints;


import com.learning.kc.model.Question;
import com.learning.kc.model.ResponseDTO;
import com.learning.kc.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/question")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addQuestion(@Valid @RequestBody Question question) {
        ResponseDTO responseDTO = new ResponseDTO();
        Question savedQuestion = questionsService.addQuestion(question);
        responseDTO.setResponse(savedQuestion);
        responseDTO.setCode("201");  // queston added
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllQuestions() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse(questionsService.getAllQuestions());
        responseDTO.setCode("202");  // queston retrieved
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
