package de.kit.recruitergame.service;

import de.kit.recruitergame.Repository.QuestionRepository;
import de.kit.recruitergame.Repository.RecruiterRepository;
import de.kit.recruitergame.dto.AnswerDTO;
import de.kit.recruitergame.dto.QuestionDTO;
import de.kit.recruitergame.model.Question;
import de.kit.recruitergame.model.Recruiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Long addRecruiter(Recruiter rec) {
        Recruiter recruiter = recruiterRepository.save(rec);
        Set<Question> standardQuestions = questionRepository.findAll().stream().collect(Collectors.toSet());
        recruiter.setQuestons(standardQuestions);
        recruiterRepository.save(recruiter);
        return recruiter.getId();
    }

    public List<Recruiter> getAllRecruiter() {
        return recruiterRepository.findAll();
    }


    public LinkedHashSet<QuestionDTO> getQuestionsWithAnswers(int recID) {
        Recruiter rec = recruiterRepository.findById(recID).get();
        Set<Question> questions = rec.getQuestons();

        return questions.stream().map(this::mapToQDTO).sorted((q1,q2)->Long.compare(q1.getId(),q2.getId())).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private QuestionDTO mapToQDTO(Question q) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId( q.getId());
        dto.setText( q.getText());
        dto.setPoints(q.getPoint());

        dto.setAnswers( q.getAnswers().stream()
                .map(a -> {
                    AnswerDTO ad = new AnswerDTO();
                    ad.setId(a.getId());
                    ad.setText( a.getText());
                    ad.setCorrect(a.isCorrect());
                    return ad;
                })
                .toList());

        return dto;
    }
}
