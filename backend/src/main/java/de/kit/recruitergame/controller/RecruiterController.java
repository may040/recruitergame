package de.kit.recruitergame.controller;

import de.kit.recruitergame.dto.QuestionDTO;
import de.kit.recruitergame.model.Question;
import de.kit.recruitergame.model.Recruiter;
import de.kit.recruitergame.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
    public LinkedHashSet<QuestionDTO> getQuestionsWithAnswers(@PathVariable int recID){
        return recruiterService.getQuestionsWithAnswers(recID);
    }


}
