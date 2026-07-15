package de.kit.recruitergame.controller;

import de.kit.recruitergame.dto.*;
import de.kit.recruitergame.model.Recruiter;
import de.kit.recruitergame.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;

@RestController
@RequestMapping("/r")
@CrossOrigin(origins = "http://localhost:5173")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @GetMapping("/all")
    public List<Recruiter> getAllRecruiter(){
        return recruiterService.getAllRecruiter();
    }

    @PostMapping("/add")
    public Long addRecruiter(@RequestBody Recruiter rec){
        return recruiterService.addRecruiter(rec);
    }

    @GetMapping("/{recID}")
    public LinkedHashSet<QuestionDTO> getQuestionsWithAnswers(@PathVariable Long recID){
        return recruiterService.getQuestionsWithAnswers(recID);
    }

    @PostMapping("/answers")
    public void  saveRecruiterAnswers(@RequestBody List<RecruiterAnswerDTO> recruiterAnswersDTO){
        recruiterService.saveRecruiterAnswers(recruiterAnswersDTO);
    }

    @GetMapping("/points/{recID}")
    public int getPointsOfRec(@PathVariable Long recID){
        return recruiterService.getPointsOfRec(recID);
    }

    @GetMapping("/results/{recID}")
    public List<QuesResultDTO> getResultsOfRec(@PathVariable Long recID){
         return recruiterService.getResultsOfRec(recID);
    }

    @GetMapping("/recruiter_list")
    public List<RecruiterResultDTO> getRecruiterList(){
         return recruiterService.getRecruiterList();
    }

    @GetMapping("/recruiter_data/{recID}")
    public RecruiterDTO getRecruiterData(@PathVariable Long recID){
        return recruiterService.getRecData(recID);
    }

}
